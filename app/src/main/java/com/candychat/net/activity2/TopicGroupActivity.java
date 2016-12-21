package com.candychat.net.activity2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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
import com.candychat.net.adapter.InviteTopicUserAdapter;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.ConversationEventSuccess;
import com.candychat.net.event.GetChatInfoSuccessTopic;
import com.candychat.net.event.GetChatInfoTopicEvent;
import com.candychat.net.event.InviteFriendToGroupChatEvent;
import com.candychat.net.event.InviteFriendToTopicGroupChatEvent;
import com.candychat.net.eventfeed.RefreshEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.model.ChatInfoTipic;
import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.RoundedTransformation;
import com.dpizarro.autolabel.library.AutoLabelUI;
import com.dpizarro.autolabel.library.Label;
import com.module.candychat.net.ChatActivity;
import com.module.candychat.net.adapter.GroupMemberAdapter;
import com.module.candychat.net.adapter.InviteAdapter;
import com.module.candychat.net.adapter.MyRecyclerListTopicAdapter;
import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.event.FriendsDataResponse;
import com.module.candychat.net.event.GetChatInfoSuccess;
import com.module.candychat.net.model.UserModel;
import com.module.candychat.net.service.EndpointManager;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TopicGroupActivity extends BaseActivity {
    private AutoLabelUI mAutoLabel;
    private ArrayList<ChatInfo.ConversationMembersEntity> mPersonList = new ArrayList<>();
    private ArrayList<ChatInfo.ConversationMembersEntity> selectedList = new ArrayList<>();
    private InviteTopicUserAdapter adapter;
    private RecyclerView recyclerView;
    ImageView groupAvatarIv;
    public int userId;
    Toolbar toolbar;
    private File photoPath;
    Button btn_Create;
    private static final int PHOTO_SIZE_WIDTH = 100;
    private static final int PHOTO_SIZE_HEIGHT = 100;
    private static final int REQUEST_CHOOSE_PHOTO = 2;
    Menu menu;
    int mCid;
    int mUserId;
    int mChatType;
    //AsyncHttpClient asyncHttpClient;
    EditText input_create;
    boolean checkData = false;
    String id;
    public PrefManager mPref;
    Context mContext;
    TextView memberCountTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_a_new_group;
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
        input_create = (EditText) findViewById(R.id.input_create);
        mAutoLabel = (AutoLabelUI) findViewById(R.id.label_view);
        btn_Create = (Button) findViewById(R.id.btn_Create);
        //asyncHttpClient = new AsyncHttpClient();
        mAutoLabel.setBackgroundResource(R.color.primary);

        memberCountTv = (TextView) findViewById(R.id.member_count);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        //adapter = new InviteRecyclerAdapter(getApplicationContext(), mPersonList);
        //recyclerView.setAdapter(adapter);

        adapter = new InviteTopicUserAdapter(this, mPersonList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new InviteTopicUserAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {


            }
        });

    }

    int mDefaultCount = 515;

    private void updateDoneText() {
        memberCountTv.setText(String.format("%s(%d/%d)",
                "", selectedList.size(), mDefaultCount));
    }

    public void showDialogPhoto() {
        final CharSequence[] items = {"Take Camera", "Choose from Gallery",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Camera")) {
                    takePhoto();

                } else if (items[item].equals("Choose from Gallery")) {
                    pickPhoto();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("Create Topic", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
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

        groupAvatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPhoto();
            }
        });


        adapter.setOnItemClickListener(new InviteTopicUserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemListClicked(view, position);

            }
        });

        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();

                ArrayList<Integer> userIds = new ArrayList<Integer>();

                for (int i = 0; i < mPersonList.size(); i++) {
                    ChatInfo.ConversationMembersEntity friend = mPersonList.get(i);
                    if (friend.isSelected()) {
                        responseText.append("\n" + friend.getUserId());
                        userIds.add(friend.getUserId());
                    }
                }

                String groupName = input_create.getText().toString().trim();

                if (groupName.equals(""))
                    Toast.makeText(mContext, "Please input group name", Toast.LENGTH_SHORT).show();
                else
                    ApiBus.getInstance().postQueue(new InviteFriendToTopicGroupChatEvent(mPref.userId().getOr(0), userIds, groupName, 0));


            }
        });
    }

    @Override
    protected void initData() {
        mPref = WOUApp.get(getApplicationContext()).getPrefManager();
        userId = mPref.userId().getOr(0);
        id = Integer.toString(userId);
        mCid = getIntent().getIntExtra("cid", 0);
        mUserId = getIntent().getIntExtra("id", 0);

        if (!checkData) {
            //prepareListData();
            fetchFriendList();
            GetTopicUserChat();
            checkData = true;
        }

        //mChatType = getIntent().getIntExtra("mChatType",0);


        //Log.e("Get_Extra", mCid + ":" + mUserId + ":");
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
        Log.e("groupCID", event.mCid + "");
        ApiBus.getInstance().post(new RefreshEvent());
        ChatActivity.startChat(getApplicationContext(), event.mCid, userId, 0, 2);
        finish();
    }


    private void itemListClicked(View view, int position) {
        ChatInfo.ConversationMembersEntity person = mPersonList.get(position);

        if (!person.isSelected()) {
            selectedList.add(mPersonList.get(position));
            updateDoneText();
        } else {
            selectedList.remove(mPersonList.get(position));
            updateDoneText();
        }


        boolean isSelected = !person.isSelected();

        //if (success) {
        adapter.setItemSelected(view, position, isSelected);
        //}
    }

    private void fetchFriendList() {
        ChatActivity.buildMainApi().getFriends(userId, new Callback<FriendsDataResponse>() {
            @Override
            public void success(FriendsDataResponse friendsDataResponse, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    private void GetTopicUserChat(){
        ChatActivity.buildChatApi().getChatById(mCid, new Callback<ChatInfo>() {
            @Override
            public void success(ChatInfo chatInfo, Response response) {
                List<ChatInfo.ConversationMembersEntity> conversationMembers = chatInfo.getConversationMembers();
                ArrayList<ChatInfo.ConversationMembersEntity> conversationMembersArrayList = new ArrayList<>(conversationMembers);

                adapter.setPeople(conversationMembersArrayList);
                mPersonList = conversationMembersArrayList;
                Log.d("mPersonList", adapter.getItemCount() + "");
                adapter.notifyDataSetChanged();
               // initGroupMembersListView(conversationMembersArrayList);

                if (conversationMembers != null)
                    for (int i = 0; i < conversationMembers.size(); i++) {
                        Log.e("pppppp",conversationMembers.get(i).getName());

                    }

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