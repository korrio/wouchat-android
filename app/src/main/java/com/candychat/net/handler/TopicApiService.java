package com.candychat.net.handler;

import com.candychat.net.model.ChatInfoTipic;
import com.candychat.net.model.media.ChatHistory;
import com.candychat.net.model.response.ConversationIdResponse;
import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.wouclass.ChatInfoTo;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;


/**
 * Created by matthewlogan on 9/3/14.
 */
public interface TopicApiService {



    @FormUrlEncoded
    @POST("/api/chat/group/create")
    public void createGroup
            (@Field("name") String name,
             @Field("inviteUserIds") String inviteUserIds,
             @Field("createdBy") int createdBy
             //@Header("Content-Type") String contentType
                    , Callback<ConversationIdResponse> responseJson);


    @GET("/api/chat/public/channel/{userId}")
    public void getSelfConversationTopic
            (@Path("userId") int userId
             //,@Header("Content-Type") String contentType
                    , Callback<ChatInfoTipic> responseJson);


    @FormUrlEncoded
    @POST("/api/chat/topic/create")
    public void createTopicGroup
            (@Field("name") String name,
             @Field("inviteUserIds") String inviteUserIds,
             @Field("cid") int cid,
             @Field("createdBy") int createdBy
             //@Header("Content-Type") String contentType
                    , Callback<ConversationIdResponse> responseJson);

    @FormUrlEncoded
    @POST("/api/chat/broadcast/create")
    public void createBroadcast
            (@Field("name") String name,
             @Field("inviteUserIds") String inviteUserIds,
             @Field("createdBy") int createdBy
             //@Header("Content-Type") String contentType
                    , Callback<ConversationIdResponse> responseJson);

    @GET("/api/chat/public/channel/{id}")
    public void getConversationGroupPublic
            (@Path("id") int id
                    , Callback<ConversationIdResponse> responseJson);



    @GET("/api/chat/public/channel/{id}")
    public void getConversationTopicGroupPublic
            (@Path("id") int id
                    , Callback<ConversationIdResponse> responseJson);


    @GET("/api/chat/{id}/history")
    public  void getChatHistory(@Path("id") int id, @QueryMap HashMap<String, Integer> options
            , Callback<ChatHistory> responseJson);


    @GET("/api/chat/{id}/to/{partnerId}")
    public void getChatByTo(@Path("id") int id,@Path("partnerId") int partnerId,Callback<ChatInfoTo> responseJson);


}
