package com.candychat.net.handler;

import com.candychat.net.event.CheckUsernameData;
import com.candychat.net.event.UserProfileDataResponse;
import com.candychat.net.model.response.AddUserResponse;
import com.candychat.net.model.response.FriendsDataResponse;
import com.candychat.net.model.LoginData;
import com.candychat.net.model.Noti;
import com.candychat.net.model.response.RegisterData;
import com.candychat.net.model.media.UploadAvatarCallback;
import com.candychat.net.model.media.UploadCoverCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import retrofit.http.Streaming;
import retrofit.mime.TypedFile;

public interface ApiServiceWOU {
    @POST("/1.0/auth")
    public void login(@QueryMap Map<String, String> options,
                      Callback<LoginData> responseJson);

    @POST("/1.0/fbAuth")
    public void fbLogin(@QueryMap Map<String, String> options,
                        Callback<LoginData> responseJson);

    @POST("/user/signup")
    public void register(@QueryMap Map<String, String> options,
                         Callback<RegisterData> responseJson);

    @POST("/user/{username}/check")
    public void checkUsername(@Path("username") String username,
                         Callback<CheckUsernameData> responseJson);

    @GET("/user/otp")
    public void otp(@QueryMap Map<String, String> options);

    @GET("/user/{id}")
    public void getProfile(@Path("id") int id,
                           Callback<UserProfileDataResponse> responseJson);

    @GET("/user/{id}/follow_suggestion")
    public void getFollowSuggestion(@Path("id") int id,
                           Callback<FriendsDataResponse> responseJson);

    @GET("/username/{username}")
    public void getProfileUsername(@Path("username") String username,
                                   Callback<UserProfileDataResponse> responseJson);

    @GET("/phone")
    public void getProfilePhone(@QueryMap Map<String, String> options,
                                   Callback<UserProfileDataResponse> responseJson);

    @GET("/add/{userId}/{partnerId}")
    public void getAdd
            (@Path("userId") Integer userId,
             @Path("partnerId") Integer partnerId
             //,@Header("Content-Type") String contentType
                    , Callback<AddUserResponse> responseJson);

    @GET("/user/{id}/followers")
    public void getFriends(@Path("id") int id, Callback<FriendsDataResponse> responseJson);



    @GET("/noti/")
    public void getNoti(@QueryMap Map<String, String> options,Callback<Noti> responseJson);

    @Multipart
    //ajax.php?mobile=1&t=avatarIv&a=new&user_id="+ +"&token=123456&user_pass="+VMApp.mPref.password().getOr("");
    @POST("/user/update/{id}/avatar")
    void uploadAvatar(
            @Path("id") int userId,
            @Part("image") TypedFile file,
            Callback<UploadAvatarCallback> cb);

    @Multipart
    //ajax.php?mobile=1&t=avatarIv&a=new&user_id="+ +"&token=123456&user_pass="+VMApp.mPref.password().getOr("");
    @POST("/user/update/{id}/cover")
    void uploadCover(
            @Path("id") int userId,
            @Part("image") TypedFile file,
            Callback<UploadCoverCallback> cb);

    @GET("/noti/history.php")
    @Streaming
    public void downloadChatHistory(@QueryMap HashMap<String, String> options, Callback<Response> callback);


}

