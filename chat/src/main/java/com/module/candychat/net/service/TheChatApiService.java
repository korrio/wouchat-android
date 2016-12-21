package com.module.candychat.net.service;

import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.event.HistoryDataResponse;
import com.module.candychat.net.model.ConversationIdResponse;
import com.module.candychat.net.model.RelationsTopic;
import com.module.candychat.net.wouclass.ChatInfoTo;
import com.module.candychat.net.wouclass.TheHistoryDataResponse;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

public interface TheChatApiService {

    @GET("/api/chat/{id}/history/android")
    public void getHistoryAndroid(@Path("id") int id, @QueryMap HashMap<String, Integer> options,
                           Callback<HistoryDataResponse> responseJson);

    @GET("/api/chat/{id}/history")
    public void getTheHistory(@Path("id") int id, @QueryMap HashMap<String, Integer> options,
                                  Callback<TheHistoryDataResponse> responseJson);


    @GET("/api/chat/public/channel/{id}")
    public void getConversationTopicGroupPublic
            (@Path("id") int id
                    , Callback<ConversationIdResponse> responseJson);




    @GET("/api/chat/topic/{userId}/{cid}")
    public void getTopicGriupChat(@Path("userId") int userId,@Path("cid") int cid,Callback<RelationsTopic> responseJson);




    @FormUrlEncoded
    @POST("/api/chat/broadcast/create")
    public void createBroadcastTopic
            (@Field("name") String name,
             @Field("inviteUserIds") String inviteUserIds,
             @Field("createdBy") int createdBy
             //@Header("Content-Type") String contentType
                    , Callback<ConversationIdResponse> responseJson);

    @FormUrlEncoded
    @POST("/api/chat/topic/create")
    public void createGroupTopicss
            (@Field("name") String name,
             @Field("inviteUserIds") String inviteUserIds,
             @Field("cid") int cid,
             @Field("createdBy") int createdBy
             //@Header("Content-Type") String contentType
                    , Callback<ConversationIdResponse> responseJson);

    @GET("/api/chat/{id}/android")
    public void getChatById(@Path("id") int id,Callback<ChatInfo> responseJson);

    @GET("/api/chat/{id}/to/{partnerId}")
    public void getChatByTo(@Path("id") int id,@Path("partnerId") int partnerId,Callback<ChatInfoTo> responseJson);

    @GET("/api/chat/public/channel/{userId}")
    public void getSelfConversation
            (@Path("userId") int userId
             //,@Header("Content-Type") String contentType
                    , Callback<ChatInfo> responseJson);

    @FormUrlEncoded
    @POST("/api/chat/group/{id}/invite")
    public void inviteFriendsToGroupChat
    (@Path("id") int cId,
     @Field("inviteUserIds") String inviteUserIds, Callback<Response> responseJson);

    @POST("/api/chat/recent/{id}/read")
    public void readRecent(@Path("id") int recentId, Callback<Response> responseJson);

    @POST("/api/chat/recent/{id}/archive")
    public void deleteRecent(@Path("id") int recentId, Callback<Response> responseJson);

    @POST("/api/chat/{id}/see")
    public void seeAll(@Path("id") int id, @QueryMap HashMap<String, Integer> options,
                       Callback<Response> responseJson);






}
