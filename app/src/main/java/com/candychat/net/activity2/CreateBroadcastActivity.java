package com.candychat.net.activity2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.Constant;
import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.ConversationEventSuccess;
import com.candychat.net.event.InviteFriendToBroadcastEvent;
import com.candychat.net.eventfeed.RefreshEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.RoundedTransformation;
import com.dpizarro.autolabel.library.AutoLabelUI;
import com.dpizarro.autolabel.library.Label;
import com.module.candychat.net.ChatActivity;
import com.module.candychat.net.adapter.InviteAdapter;
import com.module.candychat.net.event.FriendsDataResponse;
import com.module.candychat.net.model.UserModel;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CreateBroadcastActivity extends BaseActivity {
    private AutoLabelUI mAutoLabel;
    private List<UserModel> mPersonList = new ArrayList<>();
    private List<UserModel> selectedList = new ArrayList<>();
    private InviteAdapter adapter;
    private RecyclerView recyclerView;
    ImageView groupAvatarIv;
    public PrefManager mPref;
    public int userId;
    Toolbar toolbar;

    Button btnCreate;
    //AsyncHttpClient asyncHttpClient;
    EditText etCreate;
    boolean checkData = false;
    String id;

    Context mContext;
    TextView memberCountTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_a_new_broadcast;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mContext = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        groupAvatarIv = (ImageView) findViewById(R.id.group_avatar);
        Picasso.with(getApplicationContext())
                .load(R.drawable.placeholder)
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(groupAvatarIv);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        etCreate = (EditText) findViewById(R.id.input_create);
        mAutoLabel = (AutoLabelUI) findViewById(R.id.label_view);
        btnCreate = (Button) findViewById(R.id.btn_Create);
        btnCreate.setTypeface(WOUApp.CustomFontTypeFace());
        //asyncHttpClient = new AsyncHttpClient();
        mAutoLabel.setBackgroundResource(R.color.primary);

        memberCountTv = (TextView) findViewById(R.id.member_count);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        //adapter = new InviteRecyclerAdapter(getApplicationContext(), mPersonList);
        //recyclerView.setAdapter(adapter);

        adapter = new InviteAdapter(this,mPersonList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new InviteAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {


            }
        });



    }

    int mDefaultCount = 515;

    private void updateDoneText(){
        memberCountTv.setText(String.format("%s(%d/%d)",
                "", selectedList.size(), mDefaultCount));
    }


    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("Multi Friend Chat", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected void initListeners() {
        mAutoLabel.setOnLabelsCompletedListener(new AutoLabelUI.OnLabelsCompletedListener() {
            @Override
            public void onLabelsCompleted() {
                Toast.makeText(getApplicationContext(), "Completed!", Toast.LENGTH_SHORT).show();
            }
        });

        mAutoLabel.setOnRemoveLabelListener(new AutoLabelUI.OnRemoveLabelListener() {
            @Override
            public void onRemoveLabel(View view, int position) {
                adapter.setItemSelected(view, position, false);
            }
        });

        mAutoLabel.setOnLabelsEmptyListener(new AutoLabelUI.OnLabelsEmptyListener() {
            @Override
            public void onLabelsEmpty() {
                Toast.makeText(getApplicationContext(), "EMPTY!", Toast.LENGTH_SHORT).show();
            }
        });

        mAutoLabel.setOnLabelClickListener(new AutoLabelUI.OnLabelClickListener() {
            @Override
            public void onClickLabel(View v) {
                Toast.makeText(getApplicationContext(), ((Label) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });


        adapter.setOnItemClickListener(new InviteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemListClicked(view,position);

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();

                ArrayList<Integer> userIds = new ArrayList<Integer>();

                for (int i = 0; i < mPersonList.size(); i++) {
                    UserModel friend = mPersonList.get(i);
                    if (friend.getSelected()) {
                        responseText.append("\n" + friend.id);
                        userIds.add(friend.id);
                    }
                }

                //String groupName = etCreate.getText().toString().trim();

                String groupName = "BROADCAST FROM " + mPref.username().getOr("");

                ApiBus.getInstance().postQueue(new InviteFriendToBroadcastEvent(mPref.userId().getOr(0), userIds, groupName));



            }
        });
    }

    @Override
    protected void initData() {
        mPref = WOUApp.get(getApplicationContext()).getPrefManager();
        userId = mPref.userId().getOr(0);
        id = Integer.toString(userId);
        if (!checkData) {
            //prepareListData();
            fetchFriendList();
            checkData = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Subscribe
    public void onCreateGroupChatSuccess(ConversationEventSuccess event) {
        Log.e("groupCID",event.mCid+"");
        ApiBus.getInstance().post(new RefreshEvent());
        ChatActivity.startChat(getApplicationContext(),event.mCid,userId,0,3);
        finish();
    }


    private void itemListClicked(View view,int position) {
        UserModel person = mPersonList.get(position);

        if(!person.getSelected()) {
            selectedList.add(mPersonList.get(position));
            updateDoneText();
        } else {
            selectedList.remove(mPersonList.get(position));
            updateDoneText();
        }


        boolean isSelected = !person.getSelected();

        //if (success) {
            adapter.setItemSelected(view, position, isSelected);
        //}
    }

    private void fetchFriendList() {
        ChatActivity.buildMainApi().getFriends(userId, new Callback<FriendsDataResponse>() {
            @Override
            public void success(FriendsDataResponse friendsDataResponse, Response response) {
                adapter.setPeople(friendsDataResponse.getUsers());
                // mPersonList = friendsDataResponse.getUsers();
                mPersonList = friendsDataResponse.getUsers();
                Log.d("mPersonList",adapter.getItemCount() + "");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", requestCode + " + " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Uri selectedImageUri = data.getData();

                Log.e("selectedImageUri", selectedImageUri + "");

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Uri i = getImageUri(getApplicationContext(), photo);

                // groupAvatarIv.setImageBitmap(photo);

                Picasso.with(getApplicationContext())
                        .load(i)
                        .centerCrop()
                        .resize(200, 200)
                        .transform(new RoundedTransformation(100, 4))
                        .into(groupAvatarIv);


            } else if (requestCode == Constant.SELECT_PHOTO && resultCode == Activity.RESULT_OK && null != data) {
                Uri selectedImageUri = data.getData();
                //Log.e("selectedImageUri", selectedImageUri + "");


                Picasso.with(getApplicationContext())
                        .load(selectedImageUri)
                        .centerCrop()
                        .resize(200, 200)
                        .transform(new RoundedTransformation(100, 4))
                        .into(groupAvatarIv);


            }
        }

    }

    public void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        // intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", Constant.PHOTO_SIZE_WIDTH);
        intent.putExtra("outputY", Constant.PHOTO_SIZE_HEIGHT);
        startActivityForResult(intent, Constant.SELECT_PHOTO);
    }

    public void takePhoto() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, Constant.CAMERA_REQUEST);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}