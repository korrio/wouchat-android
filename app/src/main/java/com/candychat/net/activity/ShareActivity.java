package com.candychat.net.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.candychat.net.ActivityResultEvent;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.event.UpdateBadgeEvent;
import com.candychat.net.activity.main.FriendsFragment;
import com.candychat.net.activity.main.RecentChatsFragment;
import com.candychat.net.activity.main.event.GetRelationDataEvent;
import com.candychat.net.adapter.FragmentPageAdapter;
import com.candychat.net.alogin.activity.LoginOrSignupActivity;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.event.GetRecentChatEvent;
import com.candychat.net.event.GetRecentChatSuccess;
import com.candychat.net.event.GetRoomChatSuccess;
import com.candychat.net.event.GetRoomSelfChatSuccess;
import com.candychat.net.event.NotiEvent;
import com.candychat.net.event.NotiListSuccess;
import com.candychat.net.gcm.Config;
import com.candychat.net.gcm.GcmIntentService;
import com.candychat.net.gcm.event.GCMLogin;
import com.candychat.net.gcm.event.GCMTokenUpdate;
import com.candychat.net.handler.ActivityResultBus;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.impls.OnFragmentInteractionListener;
import com.candychat.net.model.ListRecentChat;
import com.candychat.net.view.CustomViewPager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.squareup.otto.Subscribe;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShareActivity extends BaseToolbarActivity implements OnFragmentInteractionListener,UpdateableActivity
{

    @Bind(R.id.viewpager)
    CustomViewPager mViewpager;
    @Bind(R.id.tabs)
    TabLayout mTabs;

    private FragmentPageAdapter pageAdapter;
    String event, friend, camera, chat, more = "";

    public int userId;
    PrefManager mPref;

    public Context mContext;

    @Subscribe
    public void getRoomChat(GetRoomChatSuccess event) {

        //TODO:
        // loading dialog dismiss

        System.out.println(event.response.getMember1().get(0).getId() + " " + event.response.getMember2().get(0).getId());

        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra("USER_ID_1", event.response.getMember1().get(0).getUserId());
        i.putExtra("USER_ID_2", event.response.getMember2().get(0).getUserId());
        if(event.response.getMemberType().equals("INDIVIDUAL")
            // && event.response.getConversationType().equals("PRIVATE")
                ) {
            i.putExtra("CHAT_TYPE", 0);
        } else {
            i.putExtra("CHAT_TYPE", 2);
        }
        i.putExtra("CONVERSATION_ID", event.response.getId());
        startActivity(i);

    }

    @Subscribe
    public void getRoomSelfChat(GetRoomSelfChatSuccess event) {

        System.out.println("Self-chat:cid : " + event.roomSelfChat.getConversationId());

        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra("USER_ID_1", event.roomSelfChat.getCreatedBy());
        i.putExtra("USER_ID_2", event.roomSelfChat.getCreatedBy());
        i.putExtra("CHAT_TYPE", 1);
        i.putExtra("CONVERSATION_ID", event.roomSelfChat.getConversationId());
        startActivity(i);

    }

    public int notiChatCount = 0;
    public int notiFollowCount = 0;

    @Subscribe
    public void onGetNotiListSuccess(NotiListSuccess event) {
//        notiChatCount = event.response.getCount().getChat_unread();
//        notiFollowCount = event.response.getCount().getFollow_unread();
        //Log.e("notiChatCount", notiChatCount + "");

    }

    private String TAG = ShareActivity.class.getSimpleName();
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    private void registerGCM() {
        Intent intent = new Intent(this, GcmIntentService.class);
        intent.putExtra("key", "register");
        startService(intent);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported. Google Play Services not installed!");
                Toast.makeText(getApplicationContext(), "This device is not supported. Google Play Services not installed!", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    String theMessage;

    @Override
    protected void onResume() {
        super.onResume();
        if(mPref != null)
            if(!mPref.isLogin().getOr(false) || mPref.userId().getOr(0) == 0) {
                WOUApp.logout(this);
                Intent logout = new Intent(this, LoginOrSignupActivity.class);
                startActivity(logout);
            }

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            com.module.candychat.net.ChatActivity.startChatShareIntent(this,0,userId,userId,0,intent);
            finish();
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // Update UI to reflect image being shared
            com.module.candychat.net.ChatActivity.startChatShareIntent(this,0,userId,userId,0,intent);
            finish();
        }
    }

    void handleSendAudio(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // Update UI to reflect image being shared
            com.module.candychat.net.ChatActivity.startChatShareIntent(this,0,userId,userId,0,intent);
            finish();
        }
    }

    void handleSendVideo(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // Update UI to reflect image being shared
            com.module.candychat.net.ChatActivity.startChatShareIntent(this,0,userId,userId,0,intent);
            finish();
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // Update UI to reflect multiple images being shared
            Toast.makeText(mContext,"Can't handle multiple images",Toast.LENGTH_LONG).show();
        }
    }

    @Override
        protected void initViews(Bundle savedInstanceState) {

        mContext = this;
        mPref = WOUApp.get(getApplication()).getPrefManager();

        userId = mPref.userId().getOr(0);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
            Log.e("imageUri", String.valueOf(imageUri));
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            } else if (type.startsWith("audio/")) {
                handleSendAudio(intent); // Handle single image being sent
            } else if (type.startsWith("video/")) {
                handleSendVideo(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // Handle multiple images being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }



        mViewpager.setPagingEnabled(false);
        mViewpager.setOffscreenPageLimit(2);
        setupViewPager(mViewpager);
        setupTabLayout(mTabs);

        if(mToolbar != null)
            mToolbar.setTitle("Share to room:");



    }

    @Override
    protected void initListeners() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    String token = intent.getStringExtra("token");

                    mPref.gcmToken().put(token).commit();

                    Map<String, String> options2 = new HashMap<String, String>();
                    options2.put("id", mPref.userId().getOr(0) + "");
                    options2.put("name", mPref.name().getOr(""));
                    options2.put("email", mPref.email().getOr(""));
                    options2.put("gcm_registration_id", token);

                    WOUApp.buildNotiApi().createGCMUser(
                            mPref.userId().getOr(0),
                            mPref.name().getOr(""),
                            mPref.email().getOr(""),
                            token,
                            new Callback<GCMLogin>() {

                        @Override
                        public void success(GCMLogin gcmLogin, Response response) {
                            if(!gcmLogin.isError())
                                Log.e("RegisterGCM","Success!");
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            //Log.e("response",error.getBody().toString());
                            Log.e("failedNetwork", "failedNetworkEvent");
                        }
                    });

                    //Toast.makeText(getApplicationContext(), "GCM registration token: " + token, Toast.LENGTH_LONG).show();

                } else if (intent.getAction().equals(Config.SENT_TOKEN_TO_SERVER)) {
                    // gcm registration id is stored in our server's MySQL

                    //Toast.makeText(getApplicationContext(), "GCM registration token is stored in server!", Toast.LENGTH_LONG).show();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String toPrint = "";

                    for (String key : intent.getExtras().keySet()) {
                        Object value = intent.getExtras().get(key);
                        Log.d(TAG, String.format("%s %s", key,
                                value.toString()));

                        toPrint += String.format("%s %s  \n", key,
                                value.toString());
                    }

                    Toast.makeText(getApplicationContext(),intent.getExtras().getString("message"), Toast.LENGTH_LONG).show();

                    String notiType = intent.getExtras().getString("type");
                    if(notiType.equals("500")) {
                        isUpdateBadge = false;
                        ApiBus.getInstance().post(new GetRecentChatEvent(WOUApp.mPref.userId().getOr(0)));
                    } else if(notiType.equals("300")) {
                        isUpdateBadge = false;
                        notiFollowCount++;
                        ApiBus.getInstance().post(new GetRelationDataEvent());
                        ApiBus.getInstance().post(new UpdateBadgeEvent(notiChatCount,notiFollowCount));
                    }






                }
            }
        };

        if (checkPlayServices()) {
            registerGCM();
        }
    }

    int unreadMessageBadgeCount = 0;
    public static final String ARG_TAKEN_PHOTO_URI = "arg_taken_photo_uri";

    @Subscribe
    public void onGetRecentChatSuccess(GetRecentChatSuccess event) {
        List<ListRecentChat.ContentEntity> recentChatList = event.response.getContent();
        for (int i = 0; i < recentChatList.size(); i++) {
            ListRecentChat.ContentEntity recentChat = recentChatList.get(i);
            unreadMessageBadgeCount += recentChat.getBadge();
        }

        notiChatCount = unreadMessageBadgeCount;
    }

    @Override
    protected void initData() {



        userId = mPref.userId().getOr(0);
        //WOUApp.saveInstallation(userId);
        ApiBus.getInstance().post(new GetRecentChatEvent(WOUApp.mPref.userId().getOr(0)));

        WOUApp.buildNotiApi().updateGCMToken(userId, mPref.gcmToken().getOr(""), new Callback<GCMTokenUpdate>() {
            @Override
            public void success(GCMTokenUpdate gcmTokenUpdate, Response response) {
                //Utils.showToast(gcmTokenUpdate.getMessage());
                Log.v("GCMTokenUpdate",gcmTokenUpdate.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


        ApiBus.getInstance().post(new NotiEvent(userId,"badge"));

    }

    ArrayList<Fragment> mFragments = new ArrayList<>();

    public void setupViewPager(ViewPager viewPager) {
        pageAdapter = new FragmentPageAdapter(getApplicationContext(),getSupportFragmentManager());

        mFragments.add(FriendsFragment.getInstance(friend));
        mFragments.add(RecentChatsFragment.getInstance(chat));
       // mFragments.add(MoreFragment.getInstance(more));
        //mFragments.add(FeedListFragment.getInstance(event));
        //mFragments.add(CameraFragment.getInstance(camera));

        // pageAdapter.addFragment(mFragments.get(3), "event", R.drawable.ic_tabbar_time_line,"");
        pageAdapter.addFragment(mFragments.get(0), "friend", R.drawable.ic_tabbar_friends, notiFollowCount);
        // pageAdapter.addFragment(mFragments.get(4), "camera", R.drawable.ic_tabbar_photo,"");
        pageAdapter.addFragment(mFragments.get(1), "chat", R.drawable.ic_tabbar_chat,notiChatCount);
       // pageAdapter.addFragment(mFragments.get(2), "more", R.drawable.ic_tabbar_more, 0);
        viewPager.setAdapter(pageAdapter);

        pageAdapter.notifyDataSetChanged();


    }

    public static boolean isUpdateBadge = false;

    @Subscribe
    public void updateBadge(UpdateBadgeEvent event) {
        //TODO: still not work, don't know why
        notiChatCount = event.notiChatCount;
        //notiFollowCount = event.notiFollowCount;

        if(!isUpdateBadge) {
            pageAdapter.mFragmentCount.add(notiChatCount);
            pageAdapter.mFragmentCount.add(notiFollowCount);
            pageAdapter.mFragmentCount.add(0);

            mViewpager.destroyDrawingCache();
            pageAdapter.notifyDataSetChanged();
            mTabs.invalidate();

            setupViewPager(mViewpager);

            mTabs.removeAllTabs();
            mTabs.setTabMode(TabLayout.MODE_FIXED);
            mTabs.setTabGravity(TabLayout.GRAVITY_FILL);
            mTabs.setupWithViewPager(mViewpager);
            for (int i = 0; i < mTabs.getTabCount(); i++) {
                TabLayout.Tab tab = mTabs.getTabAt(i);
                if(pageAdapter != null) {
                    assert tab != null;
                    tab.setCustomView(pageAdapter.getTabView(i));
                }
            }
            mViewpager.setCurrentItem(1);
            isUpdateBadge = true;

        }


//
//        pageAdapter.getTabView(0);
//
//        pageAdapter.notifyDataSetChanged();
//        mViewpager.destroyDrawingCache();

        Log.e("fire","notiChatCount" + notiChatCount);
        Log.e("fire","notiFollowCount" + notiFollowCount);
    }

    @Override
    public void update(UpdateBadgeEvent event) {

    }

    public void setupTabLayout(TabLayout tabLayout) {

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewpager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if(pageAdapter != null) {
                assert tab != null;
                tab.setCustomView(pageAdapter.getTabView(i));
            }
        }
        mViewpager.setCurrentItem(1);
        tabLayout.requestFocus();
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired timeTv passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        mBackPressed = System.currentTimeMillis();
    }

    private final static int CAMERA_RQ = 6969;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultBus.getInstance().postQueue(new ActivityResultEvent(requestCode, resultCode, data));




    }


}