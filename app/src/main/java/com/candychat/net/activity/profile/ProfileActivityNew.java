package com.candychat.net.activity.profile;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.candychat.net.WOUApp;
import com.candychat.net.activity.timeline.MediaFragment;
import com.candychat.net.event.UserProfileDataResponse;
import com.module.candychat.net.ChatActivity;
import com.module.candychat.net.wouclass.ChatInfoTo;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileActivityNew extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    @Bind(R.id.avatar)
    protected ImageView avatarIv;

    @Bind(R.id.toolbar_header_view)
    protected HeaderView toolbarHeaderView;

    @Bind(R.id.float_header_view)
    protected HeaderView floatHeaderView;

    @Bind(R.id.app_bar_layout)
    protected AppBarLayout appBarLayout;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    private boolean isHideToolbarView = false;

    int idMe;
    int idFriend;
    int cid;
    String nameMe,usernameMe,imageMe;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_new);
        ButterKnife.bind(this);
        if(getIntent() != null) {
            idMe = getIntent().getIntExtra("id", 0);
            idFriend = getIntent().getIntExtra("fid", 0);
            cid = getIntent().getIntExtra("cid", 0);
        }

        if(cid != 0) {
            MediaFragment threeFragment = MediaFragment.newInstance(cid);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, threeFragment);
            transaction.commit();
        } else {

            if(idMe == idFriend) {
                idFriend = idMe;
            }

            WOUApp.buildMainApi().getProfile(idFriend, new Callback<UserProfileDataResponse>() {
                @Override
                public void success(UserProfileDataResponse userProfileDataResponse, Response response) {
                    nameMe = userProfileDataResponse.user.name;
                    usernameMe = userProfileDataResponse.user.username;
                    imageMe = userProfileDataResponse.user.getAvatarUrl();

                    if(!nameMe.equals(""))
                        initUi(userProfileDataResponse.user.name,userProfileDataResponse.user.about);
                        //txt_name_user.setText(nameMe,);
                    else
                        initUi(userProfileDataResponse.user.username,userProfileDataResponse.user.about);

                    if(!imageMe.equals(""))
                        Picasso.with(ProfileActivityNew.this)
                                .load(imageMe.replace("_100x100",""))
                                //.centerCrop()
                                //.resize(200, 200)
                                //.transform(new RoundedTransformation(100, 4))
                                .into(avatarIv);
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



    private void initUi(String title, String subtitle) {
        appBarLayout.addOnOffsetChangedListener(this);

        toolbarHeaderView.bindTo(title, subtitle);
        floatHeaderView.bindTo("", "");


        if(toolbar != null) {
            toolbar.setTitle(title);
            toolbar.setSubtitle(subtitle);

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        //setTitle("");

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        Log.e("percentage",percentage + "%");

        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }
}
