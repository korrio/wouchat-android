package com.candychat.net.activity.timeline;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.UserProfileDataResponse;

import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.RoundedTransformation;
import com.module.candychat.net.ChatActivity;
import com.module.candychat.net.wouclass.ChatInfoTo;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChatInfoActivity extends BaseActivity {
    ImageView btn_wall, media, image_profile;
    String[] arr = {"Only me", "Public"};
    int idMe;
    int idFriend;
    int cid;
    String type;
    String nameMe;
    String imageMe;
    TextView txt_name_user;
    int  position;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        btn_wall = (ImageView) findViewById(R.id.btn_wall);
        media = (ImageView) findViewById(R.id.media);
        image_profile = (ImageView) findViewById(R.id.image_profile);
        txt_name_user = (TextView) findViewById(R.id.txt_name_user);

        Spinner mySpinner = (Spinner) findViewById(R.id.button3);
        mySpinner.setAdapter(new MyCustomAdapter(getApplicationContext(), R.layout.row, arr));

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("Chat Information", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected void initListeners() {
        btn_wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GroupMemberFragment threeFragment = new GroupMemberFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("id", idMe);
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container, threeFragment);
//                threeFragment.setArguments(bundle);
//                transaction.commit();


                GroupMemberFragment  myFragment = GroupMemberFragment.newInstance(cid,idMe,type,idFriend);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, myFragment);
                transaction.commit();


            }
        });

        media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaFragment threeFragment = MediaFragment.newInstance(cid);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, threeFragment);
                threeFragment.setArguments(bundle);
                transaction.commit();
            }
        });
    }

    @Override
    protected void initData() {

        idMe = getIntent().getIntExtra("id", 0);
        idFriend = getIntent().getIntExtra("fid", 0);
        cid = getIntent().getIntExtra("cid", 0);
        nameMe = getIntent().getStringExtra("nameMe");
        imageMe = getIntent().getStringExtra("imageMe");
        position = getIntent().getIntExtra("position", position);
        type = getIntent().getStringExtra("type");

        if(type.equals("1")){
            btn_wall.setVisibility(View.GONE);
        }else{
            btn_wall.setVisibility(View.VISIBLE);
        }

        Log.e("idMe", idMe + "");
        Log.e("PartnerId", idFriend + "");
        Log.e("nameMe", nameMe + "");
        Log.e("imageMe", imageMe + "");
        Log.e("position", position + "");
        Log.e("type",type);

        txt_name_user.setText(nameMe);
        if(imageMe != null)
            if(!imageMe.equals(""))
                Picasso.with(ChatInfoActivity.this)
                        .load(imageMe)
                        .centerCrop()
                        .resize(200, 200)
                        .transform(new RoundedTransformation(100, 4))
                        .into(image_profile);

        if(cid != 0) {
            MediaFragment threeFragment = MediaFragment.newInstance(cid);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, threeFragment);
            transaction.commit();
        } else {
            WOUApp.buildMainApi().getProfile(idFriend, new Callback<UserProfileDataResponse>() {
                @Override
                public void success(UserProfileDataResponse userProfileDataResponse, Response response) {
                    nameMe = userProfileDataResponse.user.name;
                    imageMe = userProfileDataResponse.user.getAvatarUrl();

                    Log.e("idMe", idMe + "");
                    Log.e("idMe", idFriend + "");
                    Log.e("nameMe", nameMe + "");
                    Log.e("imageMe", imageMe + "");

                    if (!nameMe.equals(""))
                        txt_name_user.setText(nameMe);
                    else
                        txt_name_user.setText(userProfileDataResponse.user.username);

                    if (!imageMe.equals(""))
                        Picasso.with(ChatInfoActivity.this)
                                .load(imageMe)
                                .centerCrop()
                                .resize(200, 200)
                                .transform(new RoundedTransformation(100, 4))
                                .into(image_profile);
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

            ChatActivity.buildChatApi().getChatByTo(idMe, idFriend, new Callback<ChatInfoTo>() {
                @Override
                public void success(ChatInfoTo chatInfoTo, Response response) {
                    if (chatInfoTo != null) {

                        cid = chatInfoTo.getId();

                        MediaFragment threeFragment = MediaFragment.newInstance(cid);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, threeFragment);
                        transaction.commit();

                    }

                }


                @Override
                public void failure(RetrofitError error) {
//            Log.e("error",error.getLocalizedMessage());
                }
            });
        }



    }



    public class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            TextView label = (TextView) row.findViewById(R.id.weekofday);
            label.setText(arr[position]);


            return row;
        }
    }

}
