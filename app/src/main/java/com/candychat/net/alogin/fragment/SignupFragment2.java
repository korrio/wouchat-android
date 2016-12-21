package com.candychat.net.alogin.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.auth.FacebookHandle;
import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.MainActivity;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.event.ActivityResultEvent;
import com.candychat.net.event.FbAuthEvent;
import com.candychat.net.event.LoadFbProfileEvent;
import com.candychat.net.event.LoginEvent;
import com.candychat.net.event.LoginSuccessEvent;
import com.candychat.net.event.UpdateProfileEvent;
import com.candychat.net.event.UsernameNotOkEvent;
import com.candychat.net.event.UsernameOkEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.manager.PrefManager;
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
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.module.candychat.net.util.ToastUtils;
import com.squareup.otto.Subscribe;
import com.wouchat.messenger.R;

public class SignupFragment2 extends BaseFragment {

    TextView txtSignPhone;
    TextView txtSignUpEmail;
    TextView txtSignUpFacebook;
    TextView txtSignIn;

    String thePhoneNumber;

    String code;
    String phone;

    TextView txtLoginFb;
    private CallbackManager callbackManager;

    private static final int FRAMEWORK_REQUEST_CODE = 1;

    public void onLoginEmail(final View view) {
        onLogin(LoginType.EMAIL);
    }

    public void onLoginPhone(final View view) {
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

    LoginButton fbLoginButton;

    public PrefManager prefManager;
    private FbProfile profile;
    private AQuery aq;
    private FacebookHandle handle;
    private String facebookToken;
    Typeface type;

    @Subscribe
    public void onCheckUsernameOk(UsernameOkEvent event) {
        Bundle i = new Bundle();
        SignByPhoneStep2Fragment oneFragment = new SignByPhoneStep2Fragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, oneFragment);
        i.putString("numberPhone", thePhoneNumber);
        i.putString("code", code);
        i.putString("phone", phone);
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

                .setTitle(thePhoneNumber)
                .setMessage("Already registered, \n do you want to sign in now ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        ApiBus.getInstance().post(new LoginEvent(WOUApp.mPref.phone().getOr(""), WOUApp.mPref.password().getOr("")));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


        }
        return super.onOptionsItemSelected(item);
    }

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
            Log.e("ARAIWA", event.getLoginData().toString());
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
                    .isLogin().put(true)
                    .commit();

            // WOUApp.fetchBadge(this);

            //WOUApp.saveInstallation(event.getLoginData().user.id);

            if (event.getLoginData().state != null) {
                if (event.getLoginData().state.equals("login")) {
                    Intent main = new Intent(getActivity(), MainActivity.class);
                    startActivity(main);
                } else if (event.getLoginData().state.equals("register")) {
//                Intent main = new Intent(this,MyIntro.class);
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_selection, container, false);

        aq = new AQuery(getActivity());
        prefManager = WOUApp.get(getActivity()).getPrefManager();
        getActivity().setTitle(Spanny.spanText(getResources().getString(R.string.text_login), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));

        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();

        //getToolbar().setTitle(Spanny.spanText("Sign up", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));

        Link terms = new Link("Terms")
                .setTextColor(Color.parseColor("#5db2d6"))    // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                       // optional, defaults to .15f
                .setUnderlined(true)
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                        // single clicked
                        String url = "https://www.wouchat.com/terms";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

        Link policy = new Link("Policy")
                .setTextColor(Color.parseColor("#5db2d6"))    // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                       // optional, defaults to .15f
                .setUnderlined(true)
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                        // single clicked
                        String url = "https://www.wouchat.com/privacy";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

        TextView termsTv = (TextView) rootView.findViewById(R.id.terms_tv);

// create the link builder object add the link rule
        LinkBuilder.on(termsTv)
                .addLink(terms)
                .addLink(policy)
                .build(); // create the clickable links



        txtSignPhone = (TextView) rootView.findViewById(R.id.txt_sign_phone);
        txtSignUpEmail = (TextView) rootView.findViewById(R.id.txt_sign_email);
        txtSignIn = (TextView) rootView.findViewById(R.id.txt_login_here);
        txtLoginFb = (TextView) rootView.findViewById(R.id.txt_login_facebook);

        txtSignUpEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //onLoginEmail(v);

                WOUApp.mPref.signupByPhone().put(false).commit();

                SignByEmailStep1Fragment oneFragment = new SignByEmailStep1Fragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, oneFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        txtSignPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onLoginPhone(v);
            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainLoginFragment fragment = new MainLoginFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        txtLoginFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fbLoginButton = (LoginButton) rootView.findViewById(R.id.login_button);

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.bus.ActivityResultBus.getInstance().postQueue(
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
                   // ToastUtils.showCenter(getActivity(),toastMessage);
                    //showHelloActivity(loginResult.getFinalAuthorizationState());
                } else if (authorizationCode != null) {
                    toastMessage = String.format(
                            "Success:%s...",
                            authorizationCode.substring(0, 10));
                    //ToastUtils.showCenter(this,toastMessage);
                    //showHelloActivity(authorizationCode, loginResult.getFinalAuthorizationState());
                } else {
                    toastMessage = "Unknown response type";
                }

                WOUApp.AccountKitGet(false);

                // thePhoneNumber = "+66910833820";


            }

        }
    }


}