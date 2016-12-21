package com.module.candychat.net.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.module.candychat.net.ChatActivity;
import com.module.candychat.net.Constantss;
import com.module.candychat.net.R;
import com.module.candychat.net.adapter.InviteAdapter;
import com.module.candychat.net.adapter.InviteTopicAdapter;
import com.module.candychat.net.event.ConversationEventTopicSuccess;
import com.module.candychat.net.event.ConversationTopicEventSuccess;
import com.module.candychat.net.event.FriendsDataResponse;
import com.module.candychat.net.handler.ApiBus;
import com.module.candychat.net.model.ConversationIdResponse;
import com.module.candychat.net.model.UserModel;
import com.module.candychat.net.service.TheChatApiService;
import com.module.candychat.net.widgets.RoundedTransformation;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CreateTopicActivity extends AppCompatActivity {
    //private AutoLabelUI mAutoLabel;
    private List<UserModel> mPersonList = new ArrayList<>();
    private List<UserModel> selectedList = new ArrayList<>();
    private InviteTopicAdapter adapter;
    private RecyclerView recyclerView;
    ImageView groupAvatarIv;
    public int userId;
    Toolbar toolbar;
    private File photoPath;
    Button btn_Create, btn_cancel;
    private static final int PHOTO_SIZE_WIDTH = 100;
    private static final int PHOTO_SIZE_HEIGHT = 100;
    private static final int REQUEST_CHOOSE_PHOTO = 2;
    Menu menu;
    //AsyncHttpClient asyncHttpClient;
    EditText input_create;
    boolean checkData = false;
    String id;

    Context mContext;
    TextView memberCountTv;
    int mCid;
    int mUserId;
    int mChatType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_topic_chats);

        mCid = getIntent().getIntExtra("mCid",0);
        mUserId = getIntent().getIntExtra("mUserId",0);
        mChatType = getIntent().getIntExtra("mChatType",0);


        Log.e("Get_Extra",mCid+":"+mUserId+": "+mChatType+"");

        groupAvatarIv = (ImageView) findViewById(R.id.group_avatar5);
        Picasso.with(getApplicationContext())
                .load(R.drawable.placeholder)
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(groupAvatarIv);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView5);
        input_create = (EditText) findViewById(R.id.input_create5);
        btn_Create = (Button) findViewById(R.id.btn_Create);
       /// btn_cancel = (Button) findViewById(R.id.btn_cancel);

        memberCountTv = (TextView) findViewById(R.id.member_count5);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        //adapter = new InviteRecyclerAdapter(getApplicationContext(), mPersonList);
        //recyclerView.setAdapter(adapter);

        fetchFriendList();

        adapter = new InviteTopicAdapter(this, mPersonList);
        recyclerView.setAdapter(adapter);

        btn_Create.setOnClickListener(new View.OnClickListener() {
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

                String groupName = input_create.getText().toString().trim();

                if (groupName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input group name", Toast.LENGTH_SHORT).show();
                } else {

                    createTopics(userId,groupName,mCid,userIds);

                    //ApiBus.getInstance().postQueue(new InviteFriendToTopicGroupChatEvent(mPref.userId().getOr(0), userIds, groupName, 0));
                }
            }
        });


        groupAvatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPhoto();
            }
        });


        adapter.setOnItemClickListener(new InviteTopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemListClicked(view,position);

            }
        });

    }


    @Subscribe
    public void onCreateGroupTopicChatSuccess(ConversationEventTopicSuccess event) {
        Log.e("groupCID", event.mCid + "");
        Toast.makeText(getApplicationContext(), "" + event.mCid, Toast.LENGTH_SHORT).show();
//        ApiBus.getInstance().post(new RefreshEvent());
//        TopicContactActivity.startChat(getApplicationContext(),event.mCid,userId,0,2);
//        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiBus.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ApiBus.getInstance().unregister(this);
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


    private void itemListClicked(View view, int position) {
        UserModel person = mPersonList.get(position);

        if (!person.getSelected()) {
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




    public void createTopics(int userId, String groupName, int cid,ArrayList<Integer> userIdsInt) {

        TheChatApiService service = buildChatApi();
        String inviteUserIdStr = "[";

        for (int i = 0; i < userIdsInt.size(); i++) {
            inviteUserIdStr = inviteUserIdStr.concat(userIdsInt.get(i) + ",");
        }

        if (inviteUserIdStr.endsWith(",")) {
            inviteUserIdStr = inviteUserIdStr.substring(0, inviteUserIdStr.length() - 1);
        }

        inviteUserIdStr = inviteUserIdStr.concat("]");
        if (inviteUserIdStr.startsWith("[") && inviteUserIdStr.endsWith("]")) {
            service.createGroupTopicss(groupName, inviteUserIdStr, userId, cid ,new Callback<ConversationIdResponse>() {
                @Override
                public void success(ConversationIdResponse conversationIdResponse, Response response) {
                    Log.e("CreateGroups", response.getUrl() + "");
                    Log.e("CreateGroups", conversationIdResponse.id + "");
                    ApiBus.getInstance().postQueue(new ConversationTopicEventSuccess(conversationIdResponse.id));

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error_Topic", error.getLocalizedMessage());
                    Log.e("error_Topic", error.getUrl());
                }
            });
        } else {
            Log.e("haha", inviteUserIdStr);
        }
    }


    public static TheChatApiService buildChatApi() {
        String BASE_URL = "http://candychat.net:1313";

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                // .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                    }
                })
                .build()
                .create(TheChatApiService.class);
    }



    String inviteUserIdStr;

    private void fetchFriendList() {
        ChatActivity.buildMainApi().getFriends(userId, new Callback<FriendsDataResponse>() {
            @Override
            public void success(FriendsDataResponse friendsDataResponse, Response response) {
                adapter.setPeople(friendsDataResponse.getUsers());
                // mPersonList = friendsDataResponse.getUsers();
                mPersonList = friendsDataResponse.getUsers();
                Log.d("mPersonList", adapter.getItemCount() + "");
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
            if (requestCode == Constantss.CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
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


            } else if (requestCode == Constantss.SELECT_PHOTO && resultCode == Activity.RESULT_OK && null != data) {
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
        intent.putExtra("outputX", Constantss.PHOTO_SIZE_WIDTH);
        intent.putExtra("outputY", Constantss.PHOTO_SIZE_HEIGHT);
        startActivityForResult(intent, Constantss.SELECT_PHOTO);
    }

    public void takePhoto() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, Constantss.CAMERA_REQUEST);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


}
