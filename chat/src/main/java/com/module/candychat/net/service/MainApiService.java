package com.module.candychat.net.service;


import com.module.candychat.net.event.FriendsDataResponse;
import com.module.candychat.net.model.Relations;

import java.util.HashMap;

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

public interface MainApiService {


    @Multipart
    @POST("/upload")
    void upload(@Part("file[]") TypedFile file,
                @Part("description") String description,
                Callback<UploadCallback> cb);

    @GET("/user/{id}")
    public void getProfile(@Path("id") int id,
                           Callback<UserProfileDataResponse> responseJson);

    @GET("/user/{id}/followers")
    public void getFriends(@Path("id") int id,
                           Callback<FriendsDataResponse> responseJson);

    @GET("/user/{id}/relations")
    public void getRelations(@Path("id") int id,
                           Callback<Relations> responseJson);

    //api.candychat.net/noti/history.php?id=4
    @GET("/noti/history.php")
    @Streaming
    public void downloadChatHistory(@QueryMap HashMap<String, String> options, Callback<Response> callback);


}
