package com.candychat.net.handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.candychat.net.PrettyPrintingMap;
import com.candychat.net.WOUApp;
import com.candychat.net.activity2.countrypicker.SearchPhoneEventSuccess;
import com.candychat.net.activity2.countrypicker.SearchUserPhoneEvent;
import com.candychat.net.event.AddUserEvent;
import com.candychat.net.event.AddUserEventSuccess;
import com.candychat.net.event.CheckUsernameData;
import com.candychat.net.event.CheckUsernameEvent;
import com.candychat.net.event.FailedNetworkEvent;
import com.candychat.net.event.FbAuthEvent;
import com.candychat.net.event.GetFriendSuggestionEvent;
import com.candychat.net.event.GetFriendsEvent;
import com.candychat.net.event.GetFriendsEventSuccess;
import com.candychat.net.event.GetUserProfileEvent;
import com.candychat.net.event.GetUserProfileSuccessEvent;
import com.candychat.net.event.LoginEvent;
import com.candychat.net.event.LoginFailedAuthEvent;
import com.candychat.net.event.LoginSuccessEvent;
import com.candychat.net.event.NotiEvent;
import com.candychat.net.event.NotiListSuccess;
import com.candychat.net.event.RegisterEvent;
import com.candychat.net.event.RegisterFailedEvent;
import com.candychat.net.event.RegisterSuccessEvent;
import com.candychat.net.event.RequestOtpEvent;
import com.candychat.net.event.UserProfileDataResponse;
import com.candychat.net.event.UsernameNotOkEvent;
import com.candychat.net.event.UsernameOkEvent;
import com.candychat.net.model.response.AddUserResponse;
import com.candychat.net.model.response.FriendsDataResponse;
import com.candychat.net.model.LoginData;
import com.candychat.net.model.Noti;
import com.candychat.net.model.response.RegisterData;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiHandlerWOU {

    private Context context;
    private ApiServiceWOU api;
    private ApiBus apiBus;

    public ApiHandlerWOU(Context context, ApiServiceWOU api,
                         ApiBus apiBus) {
        this.context = context;
        this.api = api;
        this.apiBus = apiBus;
    }

    public void registerForEvents() {
        apiBus.register(this);
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event) {
        Map<String, String> options = new HashMap<String, String>();
        options.put("username", event.getUsername());
        options.put("password", event.getPassword());

        api.login(options, new Callback<LoginData>() {
            @Override
            public void success(LoginData loginData, Response response) {
                if(loginData != null){
                    Log.e("loginData",loginData.message);
                    Toast.makeText(context,loginData.message,Toast.LENGTH_SHORT).show();



                    if (loginData.status.equals("1"))
                        ApiBus.getInstance().post(new LoginSuccessEvent(loginData));
                    else
                        ApiBus.getInstance().post(new LoginFailedAuthEvent());
                }


            }

            @Override
            public void failure(RetrofitError error) {
                //Log.e("response",error.getBody().toString());
                Log.e("failedNetwork", "failedNetworkEvent");
                apiBus.post(new FailedNetworkEvent());
            }
        });


    }


    @Subscribe
    public void onFbLoginEvent(final FbAuthEvent event) {

        Map<String, String> options = new HashMap<String, String>();
        options.put("access_token", event.getFbToken());
        Log.e("FbAuthEvent",event.getFbToken());
        api.fbLogin(options, new Callback<LoginData>() {
            @Override
            public void success(LoginData loginData, Response response) {

                Log.e("loginData", loginData.state);

                if(loginData.state.equals("wrong_access_token"))
                    onFbLoginEvent(event);


                if (loginData.status.equals("1"))
                    apiBus.post(new LoginSuccessEvent(loginData));
                else
                    apiBus.post(new LoginFailedAuthEvent());

            }

            @Override
            public void failure(RetrofitError error) {
                //Log.e("response",error.getBody().toString());
                apiBus.post(new FailedNetworkEvent());
            }
        });
    }

    @Subscribe
    public void onCheckUsernameEvent(CheckUsernameEvent event) {
        api.checkUsername(event.username, new Callback<CheckUsernameData>() {
            @Override
            public void success(CheckUsernameData checkUsernameData, Response response) {
                if (checkUsernameData.status.equals("1"))
                    apiBus.post(new UsernameNotOkEvent(checkUsernameData));
                else
                    apiBus.post(new UsernameOkEvent(checkUsernameData));

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Subscribe
    public void onRegisterEvent(RegisterEvent event) {
        Map<String, String> options = new HashMap<String, String>();

        options.put("name", event.getName());
        options.put("username", event.getUsername());
        options.put("password", event.getPassword());
        options.put("password_confirmation", event.getPassword());
        options.put("email", event.getEmail());
        options.put("gender", event.getGender());
        if(WOUApp.mPref.signupByPhone().getOr(false)) {
            options.put("phone", event.getPhone());
            options.put("phone_code", event.getPhoneCode());
        }

        System.out.println("=== REGISTER PARAMS ===");
        System.out.println(new PrettyPrintingMap<String, String>(options));

        api.register(options, new Callback<RegisterData>() {
            @Override
            public void success(RegisterData registerData, Response response) {

                if (registerData.status.equals("1")) {
                    apiBus.post(new RegisterSuccessEvent(registerData));
                } else {
                    apiBus.post(new RegisterFailedEvent(registerData.message));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //Log.e("response", error.getLocalizedMessage());
                apiBus.post(new FailedNetworkEvent());
            }
        });
    }

    @Subscribe
    public void onRequestOtp(RequestOtpEvent event) {
        Map<String, String> options = new HashMap<String, String>();

        options.put("mobile", event.getMobile());
        options.put("message", event.getMessage());

        api.otp(options);
    }

    @Subscribe
    public void onGetUserProfile(GetUserProfileEvent event) {

        Map<String, String> options = new HashMap<String, String>();

        if (event.userId != 0) {

            api.getProfile(event.userId, new Callback<UserProfileDataResponse>() {
                @Override
                public void success(UserProfileDataResponse userProfileDataResponse, Response response) {
                    if(userProfileDataResponse != null){
                        GetUserProfileSuccessEvent event = new GetUserProfileSuccessEvent(userProfileDataResponse.user, userProfileDataResponse.count);
                        ApiBus.getInstance().post(event);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error", error.toString());
                }

            });
        } else {
            api.getProfileUsername(event.username, new Callback<UserProfileDataResponse>() {
                @Override
                public void success(UserProfileDataResponse userProfileDataResponse, Response response) {

                    if (userProfileDataResponse != null) {
                        GetUserProfileSuccessEvent event = new GetUserProfileSuccessEvent(userProfileDataResponse.user, userProfileDataResponse.count);
                        ApiBus.getInstance().post(event);
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error", error.toString());
                }

            });
        }

    }

    @Subscribe public void findPhone(SearchUserPhoneEvent event) {

        Map<String, String> options = new HashMap<String, String>();

        options.put("phone_code",event.phoneCode);
        options.put("phone",event.phone);
        options.put("user_id",WOUApp.mPref.userId().getOr(0)+ "");

        api.getProfilePhone(options, new Callback<UserProfileDataResponse>() {
            @Override
            public void success(UserProfileDataResponse userProfileDataResponse, Response response) {
                apiBus.postQueue(new SearchPhoneEventSuccess(userProfileDataResponse));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Subscribe
    public void addFriends(AddUserEvent event) {
        Log.e("id", event.userId + "");
        api.getAdd(event.userId, event.meUserId, new Callback<AddUserResponse>() {
            @Override
            public void success(AddUserResponse addUserResponse, Response response) {
                Log.e("addFriends", addUserResponse.getUser_id() + "");
                ApiBus.getInstance().post(new AddUserEventSuccess(addUserResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                //Log.e("addFriendsError", error.getLocalizedMessage());
                //Log.e("addFriendsError", error.getUrl());
            }
        });
    }

    @Subscribe
    public void getFriends(GetFriendsEvent event) {
        api.getFriends(event.userId, new Callback<FriendsDataResponse>() {
            @Override
            public void success(FriendsDataResponse friendsDataResponse, Response response) {
                if(friendsDataResponse != null){
                    ApiBus.getInstance().post(new GetFriendsEventSuccess(friendsDataResponse));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //Log.e("error", error.getLocalizedMessage());
                //
                // Log.e("error", error.getUrl());
            }
        });

    }

    @Subscribe
    public void getFollowSuggesstionUser(GetFriendSuggestionEvent event) {
        api.getFollowSuggestion(event.userId, new Callback<FriendsDataResponse>() {
            @Override
            public void success(FriendsDataResponse friendsDataResponse, Response response) {
                if (friendsDataResponse != null) {
                    ApiBus.getInstance().post(new GetFriendsSuggestionEventSuccess(friendsDataResponse));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //Log.e("error", error.getLocalizedMessage());
                //Log.e("error", error.getUrl());
            }
        });

    }

    @Subscribe
    public void getNotiList(NotiEvent event) {

        Map<String, String> options = new HashMap<String, String>();

        options.put("a", event.action);
        options.put("user_id", event.Id + "");

        api.getNoti(options,new Callback<Noti>() {
            @Override
            public void success(Noti noti, Response response) {

                if(noti != null){
                    Log.e("sss", noti.getCount() + "");
                    ApiBus.getInstance().post(new NotiListSuccess(noti));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "getNotiList" + error.getLocalizedMessage());
                Log.e("error", "getNotiList" + error.getUrl());
            }
        });

    }






}
