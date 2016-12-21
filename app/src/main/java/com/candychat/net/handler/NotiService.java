package com.candychat.net.handler;


import com.candychat.net.gcm.event.GCMLogin;
import com.candychat.net.gcm.event.GCMTokenUpdate;
import com.candychat.net.model.feed.UploadPostCallback;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import retrofit.http.Field;

public interface NotiService {

//    public static final String BASE_URL = "http://candychat.net/gcm/v1";
//    public static final String LOGIN = BASE_URL + "/user/login";
//    public static final String USER = BASE_URL + "/user/_ID_";
//    public static final String CHAT_ROOMS = BASE_URL + "/chat_rooms";
//    public static final String CHAT_THREAD = BASE_URL + "/chat_rooms/_ID_";
//    public static final String CHAT_ROOM_MESSAGE = BASE_URL + "/chat_rooms/_ID_/message";

    @POST("/user/login")
    void loginGCM(
            @QueryMap Map<String, String> options,
            Callback<GCMLogin> cb);

    @FormUrlEncoded
    @PUT("/user/create")
    void createGCMUser(@Field​("id") int id,
                       @Field​("name") String name,
                       @Field​("email") String email,
                       @Field​("gcm_registration_id") String gcmToken,
            Callback<GCMLogin> cb);

    @FormUrlEncoded
    @PUT("/user/{id}")
    void updateGCMToken(
            @Path("id") int userId,
            @Field​("gcm_registration_id") String gcmToken,
            Callback<GCMTokenUpdate> cb);
    // param : gcm_registration_id


    @GET("/chat_rooms")
    void getChatroom(
            Callback<UploadPostCallback> cb);

    @GET("/chat_rooms/{id}")
    void getChatroomHistory(
            @Path("id") int userId,
            Callback<UploadPostCallback> cb);

    @GET("/chat_rooms/{id}/message")
    void uploadPostClip(
            @Path("id") int userId,
            Callback<UploadPostCallback> cb);



}
