package com.candychat.net;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.androidquery.auth.FacebookHandle;
import com.candychat.net.event.CheckUsernameEvent;
import com.candychat.net.gcm.MyPreferenceManager;
import com.candychat.net.gcm.event.RegisterGCM;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.handler.ApiHandler;
import com.candychat.net.handler.ApiHandlerWOU;
import com.candychat.net.handler.ApiService;
import com.candychat.net.handler.ApiServiceWOU;
import com.candychat.net.handler.ChatApiHandler;
import com.candychat.net.handler.ChatApiService;
import com.candychat.net.handler.NotiService;
import com.candychat.net.handler.TopicApiHandler;
import com.candychat.net.handler.TopicApiService;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.utils.StorageUtils;
import com.candychat.net.viewholder.PrefManagerRegister;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.leolin.shortcutbadger.ShortcutBadger;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class WOUApp extends Application implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = WOUApp.class
            .getSimpleName();
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "MwnR2AAqrkmX1YkEIQE40fvFL";
    private static final String TWITTER_SECRET = "PbWgBifCFvchZ4AnZG3yBVsjRHnZGEBKcfXq6wNjXU7YUqpzrE";

    private static FacebookHandle handle;
    public static final String FB_APP_ID = "1524384201124196";

    public static final String NOTI_ENDPOINT = "http://candychat.net/gcm/v1";
    public static final String SOCIAL_ENDPOINT = "http://candychat.net";
    public static final String CHAT_ENDPOINT = "http://chat.candychat.net";
    public static final String API_ENDPOINT = "http://api.candychat.net";
    public static final String API_ENDPOINT_TOPIC = "http://candychat.net:1313";

    public static Activity currentActivity;

    private static WOUApp Instance;
    public static volatile Handler applicationHandler = null;
    private ApiHandler someApiHandler;
    private ApiHandlerWOU loginApiHandler;


    public static PrefManager mPref;
    PrefManagerRegister mPrefManagerRegister;

    public Bitmap cropped = null;

    public static String USER_TOKEN;
    public static final String APP_PERMISSIONS = "email,public_profile,user_friends";
    private static OkHttpClient sHttpClient;
    private static Activity mFbHandleActivity;
    private static Context sContext = null;

    private MyPreferenceManager pref;
    public MyPreferenceManager getPrefNotiManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }

        return pref;
    }

    public static FacebookHandle getFacebookHandle(Activity activity) {
        mFbHandleActivity = activity;
        handle = new FacebookHandle(activity, FB_APP_ID, APP_PERMISSIONS);
        return handle;
    }

    public static Typeface CustomFontTypeFace() {
        return Typeface.createFromAsset(getAppContext().getAssets(), "fonts/SWZ721BR.ttf");
    }

    private ChatApiHandler chatApiHandlerMain;
    private TopicApiHandler topicApiHandler;

    String applicationID = "5UDvYSr2ngfrUVKo5G3cQUaaiTGakrIngAlXNhqC";
    String clientKey = "f0RqCB5EYYuTVoGghacM2ITIxWHST5iUipg5y6vs";

    public void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    public File tempFile;

    @Override
    public void onCreate() {
        super.onCreate();



        //if (BuildConfig.DEBUG) {
         //   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().build());
           // StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().build());
        //}
//        Stetho.initialize(Stetho.newInitializerBuilder(this)
//                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                .build());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Kitkat and lower has a bug that can cause in correct strict mode
            // warnings about expected activity counts
            enableStrictMode();
        }

        AccountKit.initialize(getApplicationContext());

//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        final Fabric fabric = new Fabric.Builder(this)
//                .kits(new Digits(), new TwitterCore(authConfig))
//                .logger(new DefaultLogger(Log.DEBUG))
//                .debuggable(true)
//                .build();
//        Fabric.with(fabric);

        sContext = this;
        Instance = this;

        applicationHandler = new Handler(getInstance().getMainLooper());
        mPref = new PrefManager(getSharedPreferences("App", MODE_PRIVATE));
        mPrefManagerRegister = new PrefManagerRegister(getSharedPreferences("App", MODE_PRIVATE));

        //CustomActivityOnCrash.install(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //FacebookSdk.setIsDebugEnabled(false);

        AppEventsLogger.activateApp(this);
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);


//        Uri targetUrl =
//                AppLinks.getTargetUrlFromInboundIntent(this, getIntent());
//        if (targetUrl != null) {
//            Log.i("Activity", "App Link Target URL: " + targetUrl.toString());
//        } else {
//            AppLinkData.fetchDeferredAppLinkData(
//                    activity,
//                    new AppLinkData.CompletionHandler() {
//                        @Override
//                        public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
//                            //process applink data
//                        }
//                    });
//        }
        //FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        //FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        //FacebookSdk.addLoggingBehavior(LoggingBehavior.DEVELOPER_ERRORS);

        //Parse.initialize(this, applicationID, clientKey);

//        PushService.setDefaultPushCallback(this,
//                PushManage.class);
//        ParsePush
//                .subscribeInBackground("EN");

        //saveInstallation(0);

        loginApiHandler = new ApiHandlerWOU(this, buildMainApi(), ApiBus.getInstance());
        loginApiHandler.registerForEvents();

        chatApiHandlerMain = new ChatApiHandler(this, buildApiChatApiService(), ApiBus.getInstance());
        chatApiHandlerMain.registerForEvents();

        someApiHandler = new ApiHandler(this, buildApi(), ApiBus.getInstance());
        someApiHandler.registerForEvents();


        topicApiHandler = new TopicApiHandler(this, buildApiTopicApiService(), ApiBus.getInstance());
        topicApiHandler.registerForEvents();

    }

    public static ApiBus theBus = ApiBus.getInstance();;


    public static Context getAppContext() {
        return sContext;
    }


    public static OkHttpClient getHttpCacheClient() {
        if (sHttpClient == null) {
            sHttpClient = new OkHttpClient();
            int cacheSize = 10 * 1024 * 1024;
            File cacheLocation = new File(StorageUtils.getIdealCacheDirectory(WOUApp.getAppContext()).toString());
            cacheLocation.mkdirs();
            com.squareup.okhttp.Cache cache = new com.squareup.okhttp.Cache(cacheLocation, cacheSize);
            sHttpClient.setCache(cache);
            //sHttpClient.networkInterceptors().add(new StethoInterceptor());
        }
        return sHttpClient;
    }



    ChatApiService buildApiChatApiService() {

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(CHAT_ENDPOINT)
                //.addNetworkInterceptor(new StethoInterceptor())
//                .setRequestInterceptor(new StethoInterceptor() {
//                    @Override
//                    public void intercept(RequestInterceptor.RequestFacade request) {
//                    }
//                })
//                .setRequestInterceptor(new RequestInterceptor() {
//                    @Override
//                    public void intercept(RequestFacade request) {
//                    }
//                })
                .build()
                .create(ChatApiService.class);
    }

    TopicApiService buildApiTopicApiService() {

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_ENDPOINT_TOPIC)
                //.addNetworkInterceptor(new StethoInterceptor())
//                .setRequestInterceptor(new StethoInterceptor() {
//                    @Override
//                    public void intercept(RequestInterceptor.RequestFacade request) {
//                    }
//                })
//                .setRequestInterceptor(new RequestInterceptor() {
//                    @Override
//                    public void intercept(RequestFacade request) {
//                    }
//                })
                .build()
                .create(TopicApiService.class);

    }


    public static ApiServiceWOU buildMainApi() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    private DateFormat format = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss Z");

                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        String s = json.getAsJsonPrimitive().getAsString();
                        try {
                            return format.parse(s);
                        } catch (ParseException ignore) {
                        }

                        return null;
                    }
                })
                .create();


        return new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //request.addHeader("Accept", "application/json;versions=1");
                        if (!mPref.token().getOr("").equals("")) {
                            request.addHeader("X-Auth-Token",mPref.token().getOr(""));
                        }
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .build()
                .create(ApiServiceWOU.class);

    }


    public static ApiService buildApi() {

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(CHAT_ENDPOINT)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //request.addQueryParam("p1", "var1");
                        //request.addQueryParam("p2", "");
                    }
                })

                .build()
                .create(ApiService.class);
    }

    public static NotiService buildNotiApi() {

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(WOUApp.NOTI_ENDPOINT)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //request.addQueryParam("p1", "var1");
                        //request.addQueryParam("p2", "");
                    }
                })
                .build()
                .create(NotiService.class);
    }

    public static void saveInstallation(int userId) {
        ApiBus.getInstance().post(new RegisterGCM(userId));

//        final ParseInstallation installation = ParseInstallation
//                .getCurrentInstallation();
//
//        installation.put("user_id", userId);
//        installation.saveInBackground(new SaveCallback() {
//            public void done(com.parse.ParseException e) {
//                if (e == null) {
//                    System.out.println("ok");
//                    //deviceToken = installation.get("deviceToken").toString();
//                    //System.out.println(deviceToken);
//                } else {
//                    System.out.println("not ok" + e.getLocalizedMessage());
//                }
//            }
//        });
    }
    public static void removeInstallation(int userId) {
//        final ParseInstallation installation = ParseInstallation
//                .getCurrentInstallation();
//
//        installation.deleteInBackground();
    }

    public static void logout(Activity context) {
        mPref.isLogin().put(false).commit();
        mPref.clear().commit();
        boolean isLogin = mPref.isLogin().getOr(false);
        context.finish();
//        PushService
//                .unsubscribe(getAppContext(), "EN");
        WOUApp.removeInstallation(mPref.userId().getOr(0));
        if (mFbHandleActivity != null)
            getFacebookHandle(mFbHandleActivity).unauth();
        //ParsePush.unsubscribeInBackground("EN");
        Log.e("isLogin", ":::" + isLogin);
        disconnectFromFacebook();
        AccountKit.logOut();
        ShortcutBadger.applyCount(getAppContext(), 0);
    }

    public static void logout() {
        mPref.isLogin().put(false).commit();
        mPref.clear().commit();
        boolean isLogin = mPref.isLogin().getOr(false);
        //context.finish();
//        PushService
//                .unsubscribe(getAppContext(), "EN");
        WOUApp.removeInstallation(mPref.userId().getOr(0));
        if (mFbHandleActivity != null)
            getFacebookHandle(mFbHandleActivity).unauth();
        //ParsePush.unsubscribeInBackground("EN");
        Log.e("isLogin", ":" + isLogin);
        disconnectFromFacebook();
        AccountKit.logOut();
        ShortcutBadger.applyCount(getAppContext(), 0);
    }

    public static WOUApp get(Context context) {
        return (WOUApp) context.getApplicationContext();
    }

    public static WOUApp getInstance() {
        return Instance;
    }

    public PrefManager getPrefManager() {
        return mPref;
    }

    public static boolean applicationOnPause = false;

    @Override
    public void onActivityCreated(Activity arg0, Bundle arg1) {
        currentActivity = arg0;
        Log.e("VMVMVM", "onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.e("VMVMVM", "onActivityDestroyed ");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
        applicationOnPause = false;
        Log.e("VMVMVM", "onActivityResumed " + activity.getClass());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        applicationOnPause = true;
        Log.e("VMVMVM", "onActivityPaused " + activity.getClass());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.e("VMVMVM", "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.e("VMVMVM", "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.e("VMVMVM", "onActivityDestroyed ");
    }

    public static void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    public static String AccountKitGet(final boolean logout) {
        final String[] returnValue = new String[1];

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                //final TextView userId = (TextView) findViewById(R.id.user_id);
                String accountId = account.getId();

                PhoneNumber accountNumber = account.getPhoneNumber();
                //String number == null ? null : number.toString();

                String accountEmail = account.getEmail();
                String toastMessage = accountId + " " + accountNumber + " " + accountEmail;

                Log.e("WOUAPPPPPP", accountNumber + " " + accountNumber.getCountryCode() + " " + accountNumber.getRawPhoneNumber() + " " + accountNumber.getPhoneNumber());

                if(accountEmail == null) {
                    mPref.phone().put(accountNumber.getRawPhoneNumber()).phoneCode().put(accountNumber.getCountryCode()).commit();
                    //ToastUtils.showCenter(getApplicationContext(),"Application:" + toastMessage);
                    returnValue[0] = accountNumber.toString();
                    String thePhoneNumber = returnValue[0];

                    String code = thePhoneNumber.substring(1,3);
                    String phone = thePhoneNumber.substring(3,thePhoneNumber.length());

                    Log.d("code",code);
                    Log.d("phone",phone);
                    Log.d("thePhoneNo",thePhoneNumber);

                    WOUApp.mPref.phoneCode().put(code).phone().put(phone).phoneNumber().put(thePhoneNumber).signupByPhone().put(true).commit();
                    ApiBus.getInstance().post(new CheckUsernameEvent("0" + phone));

                } else {
                    mPref.email().put(accountEmail).commit();
                    returnValue[0] = accountEmail;
                    ApiBus.getInstance().post(new CheckUsernameEvent(returnValue[0]));
                }

                if(logout) {
                    logout();
                }



            }

            @Override
            public void onError(final AccountKitError error) {
            }
        });

        return returnValue[0];
    }
}
