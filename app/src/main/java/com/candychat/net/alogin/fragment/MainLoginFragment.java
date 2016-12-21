package com.candychat.net.alogin.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.auth.FacebookHandle;
import com.binaryfork.spanny.Spanny;
import com.candychat.net.ActivityResultBus;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.MainActivity;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.event.ActivityResultEvent;
import com.candychat.net.event.FailedNetworkEvent;
import com.candychat.net.event.FbAuthEvent;
import com.candychat.net.event.LoadFbProfileEvent;
import com.candychat.net.event.LoginEvent;
import com.candychat.net.event.LoginFailedAuthEvent;
import com.candychat.net.event.LoginSuccessEvent;
import com.candychat.net.event.UpdateProfileEvent;
import com.candychat.net.event.UsernameNotOkEvent;
import com.candychat.net.event.UsernameOkEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.model.FbProfile;
import com.candychat.net.model.UserProfile;
import com.candychat.net.view.CustomTypefaceSpan;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.module.candychat.net.util.ToastUtils;
import com.squareup.otto.Subscribe;
import com.wouchat.messenger.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainLoginFragment extends BaseFragment {
    private CallbackManager callbackManager;

    @Bind(R.id.fb_login_button)
    LoginButton fbLoginButton;
    @Bind(R.id.txt_sign_up_now)
    TextView txtSignNow;
    @Bind(R.id.forget_password_tv)
    TextView forgetPassTv;
    @Bind(R.id.mobile_login_button)
    Button mobileLoginBtn;


    @OnClick(R.id.txt_sign_up_now)
    public void signupNow() {

        //startActivity(new Intent(getActivity(),SignupFragment2.class));
        SignupFragment2 fragment = new SignupFragment2();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Bind(R.id.input_email)
    EditText input_email;
    @Bind(R.id.input_password)
    EditText input_password;
    @Bind(R.id.txt_login)
    Button txtLogin;

    @OnClick(R.id.txt_login)
    public void login() {
        dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
        dialog.setContentView(R.layout.dialog_login);
        dialog.show();
        if (!input_email.getText().toString().trim().equals("") && !input_password.getText().toString().trim().equals("")) {
            ApiBus.getInstance().post(new LoginEvent(input_email.getText().toString().trim(),
                    input_password.getText().toString().trim()));

        } else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, getResources().getString(R.string.empty_input), Snackbar.LENGTH_LONG);
            snackbar.show();
            dialog.dismiss();
        }

    }

    @OnClick(R.id.forget_password_tv)
    public void forgetPass() {
        onLoginEmail();
    }

    @OnClick(R.id.mobile_login_button)
    public void onMobileLoginBtnClick() {
        onLoginPhone();
    }

    private static final int FRAMEWORK_REQUEST_CODE = 1;

    public void onLoginEmail() {
        onLogin(LoginType.EMAIL);
    }

    public void onLoginPhone() {
        onLogin(LoginType.PHONE);
    }

    private void onLogin(final LoginType loginType) {
        final Intent intent = new Intent(getActivity(), AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = createAccountKitConfiguration(loginType);
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, FRAMEWORK_REQUEST_CODE);
    }

    private AccountKitConfiguration.AccountKitConfigurationBuilder createAccountKitConfiguration(
            final LoginType loginType) {
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                loginType,
                AccountKitActivity.ResponseType.TOKEN);

        configurationBuilder.setTitleType(AccountKitActivity.TitleType.APP_NAME);
        configurationBuilder.setTheme(R.style.AppLoginTheme_Wouchat);
        configurationBuilder.setFacebookNotificationsEnabled(true);
        configurationBuilder.setReadPhoneStateEnabled(true);
        configurationBuilder.setReceiveSMS(true);
        //configurationBuilder.setDefaultCountryCode("66");

        return configurationBuilder;
    }

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    public PrefManager prefManager;
    private FbProfile profile;
    private AQuery aq;
    private FacebookHandle handle;
    private String facebookToken;
    Typeface type;

    public MainLoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aq = new AQuery(getActivity());
        prefManager = WOUApp.get(getActivity()).getPrefManager();
        getActivity().setTitle(Spanny.spanText(getResources().getString(R.string.text_login), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
    }

    Dialog dialog;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);
        type = WOUApp.CustomFontTypeFace();
        //fbLoginButton = (LoginButton) rootView.findViewById(R.id.fb_login_button);
        fbLoginButton.setFragment(this);
        input_email.setTypeface(type);
        input_password.setTypeface(type);
        txtLogin.setTypeface(type);

        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                String userId = loginResult.getAccessToken().getUserId();
                String accessToken = loginResult.getAccessToken().getToken();
                String username = getName(profile);
                String lastName = getLastName(profile);
                String middleName = getMiddleName(profile);
                String firstName = getFirstName(profile);

                Log.d("fbToken",accessToken);
                Log.e("getName", getName(profile));
                Log.e("getLastName", lastName);
                Log.e("userId", userId);
                // save accessToken to SharedPreference
                prefManager
                        .fbToken().put(accessToken)
                        .fbId().put(userId).commit();
                FbProfile fbProfile = new FbProfile(userId, username, firstName, middleName, lastName, "", username, 0, "");

                ApiBus.getInstance().post(new LoadFbProfileEvent(fbProfile, facebookToken));
                ApiBus.getInstance().post(new FbAuthEvent(accessToken));
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        });


        return rootView;
    }

//
//    public void authFacebook() {
//
//        handle = new FacebookHandle(getActivity(), WOUApp.FB_APP_ID, WOUApp.APP_PERMISSIONS);
//        String url = "https://graph.facebook.com/me";
//        ProgressDialog dialog = new ProgressDialog(getActivity());
//        dialog.setIndeterminate(true);
//        dialog.setCancelable(true);
//        dialog.setInverseBackgroundForced(false);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setTitle("Authenicating...");
//
//        aq.auth(handle).progress(dialog)
//                .ajax(url, JSONObject.class, this, "facebookCb");
//    }
//
//    public void facebookCb(String url, JSONObject jo, AjaxStatus status)
//            throws JSONException {
//        if (jo != null) {
//
//            Log.e("FB_JSON", jo.toString());
//            Gson gson = new Gson();
//            profile = gson.fromJson(jo.toString(), FbProfile.class);
//            facebookToken = handle.getToken();
//            Log.e("FB_AUTHED_TOKEN", facebookToken + "");
//
//            mPref
//                    .fbToken().put(facebookToken)
//                    .fbId().put(profile.id).commit();
//            //getFragmentManager().beginTransaction().add(R.id.login_container, new FbAuthFragment()).commit();
//            //ApiBus.getInstance().post(new LoadFbProfileEvent(profile,facebookToken));
//
//
//            Log.e("POSTED", "SENT POST");
//        }
//    }

    private String getName(Profile profile) {
        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("").append(profile.getName());
        }
        return stringBuffer.toString();
    }

    private String getLastName(Profile profile) {
        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("").append(profile.getLastName());
        }
        return stringBuffer.toString();
    }

    private String getMiddleName(Profile profile) {
        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("").append(profile.getMiddleName());
        }
        return stringBuffer.toString();
    }


    private String getFirstName(Profile profile) {
        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("").append(profile.getFirstName());
        }
        return stringBuffer.toString();
    }



    @Subscribe
    public void onLoginSuccess(LoginSuccessEvent event) {
        if (event.getLoginData() != null) {
            WOUApp.USER_TOKEN = event.getLoginData().token;
            Log.e("ARAIWA", WOUApp.USER_TOKEN);
            Log.e("ARAIWA", event.getLoginData().user.toString());
            prefManager
                    .name().put(event.getLoginData().user.name)
                    .username().put(event.getLoginData().user.username)
                    .password().put(event.getLoginData().user.password)
                    .email().put(event.getLoginData().user.email)
                    .userId().put(event.getLoginData().user.id)
                    .token().put(event.getLoginData().token)
                    .cover().put(event.getLoginData().user.cover)
                    .avatar().put(event.getLoginData().user.avatar)
                    .about().put(event.getLoginData().user.about)
                    .phone().put(event.getLoginData().user.phone)
                    .phoneCode().put(event.getLoginData().user.phoneCode)
                    .phoneNumber().put(event.getLoginData().user.phone)
                    .isLogin().put(true)
                    .commit();

            // WOUApp.fetchBadge(getActivity());

            //WOUApp.saveInstallation(event.getLoginData().user.id);

            if (event.getLoginData().state != null) {
                if (event.getLoginData().state.equals("login")) {
                    Intent main = new Intent(getActivity(), MainActivity.class);
                    startActivity(main);
                } else if (event.getLoginData().state.equals("register")) {
//                Intent main = new Intent(getActivity(),MyIntro.class);
//                startActivity(main);
                }
            } else {
                Intent main = new Intent(getActivity(), MainActivity.class);
                startActivity(main);
            }

            Intent main = new Intent(getActivity(), MainActivity.class);
            startActivity(main);

            UserProfile user = event.getLoginData().user;
            ApiBus.getInstance().post(new UpdateProfileEvent(user));

            getActivity().finish();
        }

    }


    @Subscribe
    public void onLoginFailedNetwork(FailedNetworkEvent event) {
        Log.e("ARAIWA", "onLoginFailedNetwork");
        prefManager.clear();
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Cannot connect to server", Snackbar.LENGTH_LONG);
        snackbar.show();

        if(dialog != null){
            dialog.dismiss();
        }
        //
    }

    @Subscribe
    public void onLoginFailedAuth(LoginFailedAuthEvent event) {
        Log.e("ARAIWA", "onLoginFailedAuth");
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Wrong username or password", Snackbar.LENGTH_LONG);
        snackbar.show();
        if(dialog != null){
            dialog.dismiss();
        }
        //mPref.clear();
    }

//    @Produce
//    public LoadFbProfileEvent produceProfileEvent() {
//        return new LoadFbProfileEvent(profile, facebookToken);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        ActivityResultBus.getInstance().postQueue(
                new ActivityResultEvent(requestCode, resultCode, data));

        if (requestCode == FRAMEWORK_REQUEST_CODE) {
            final AccountKitLoginResult loginResult =
                    data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            final String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                ToastUtils.showCenter(getActivity(),toastMessage);
                //showErrorActivity(loginResult.getError());
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                final AccessToken accessToken = loginResult.getAccessToken();
                final String authorizationCode = loginResult.getAuthorizationCode();
                final long tokenRefreshIntervalInSeconds =
                        loginResult.getTokenRefreshIntervalInSeconds();

                Log.e("accessToken",accessToken.getToken() +"");
                Log.e("authorizationCode",authorizationCode+"");
                Log.e("tokenRefresh",tokenRefreshIntervalInSeconds+"");
                Log.e("state",loginResult.getFinalAuthorizationState()+"");

                if (accessToken != null) {
                    toastMessage = "Success:" + accessToken.getAccountId()
                            + tokenRefreshIntervalInSeconds;
                    ToastUtils.showCenter(getActivity(),toastMessage);
                    //showHelloActivity(loginResult.getFinalAuthorizationState());
                } else if (authorizationCode != null) {
                    toastMessage = String.format(
                            "Success:%s...",
                            authorizationCode.substring(0, 10));
                    ToastUtils.showCenter(getActivity(),toastMessage);
                    //showHelloActivity(authorizationCode, loginResult.getFinalAuthorizationState());
                } else {
                    toastMessage = "Unknown response type";
                }

                WOUApp.AccountKitGet(false);


            }

        }

    }

    @Subscribe
    public void onCheckUsernameOk(UsernameOkEvent event) {
        Bundle i = new Bundle();
        SignByEmailStep1Fragment oneFragment = new SignByEmailStep1Fragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, oneFragment);

        oneFragment.setArguments(i);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    @Subscribe
    public void onCheckUsernameNotOk(UsernameNotOkEvent event) {

        WOUApp.mPref
                .name().put(event.checkUsernameData.user.name)
                .username().put(event.checkUsernameData.user.username)
                .password().put(event.checkUsernameData.user.password)
                .email().put(event.checkUsernameData.user.email)
                .userId().put(event.checkUsernameData.user.id)
                //.token().put(event.checkUsernameData.token)
                .cover().put(event.checkUsernameData.user.cover)
                .avatar().put(event.checkUsernameData.user.avatar)
                .isLogin().put(true)
                .commit();

        // create alert dialog to login with phone no.
        new AlertDialog.Builder(getActivity())

                .setTitle("Sign in by Phone Number")
                .setMessage("Do you want to sign in now ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        ApiBus.getInstance().post(new LoginEvent(WOUApp.mPref.email().getOr(""), WOUApp.mPref.password().getOr("")));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.phone)
                .show();
    }


}