package com.candychat.net.handler;

import com.candychat.net.event.response.RoomSelfChat;
import com.candychat.net.model.FindFriends;
import com.candychat.net.model.ListRecentChat;
import com.candychat.net.model.response.RoomChat;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by matthewlogan on 9/3/14.
 */
public interface ApiService {

    @GET("/api/chat/recent/user/{id}")
    public void getRecentChat(@Path("id") int id, Callback<ListRecentChat> callback);

    @GET("/api/chat/{userId}/to/{partnerId}")
    public void getConversation
            (@Path("userId") int userId,
             @Path("partnerId") int partnerId
             //,@Header("Content-Type") String contentType
                    , Callback<RoomChat> responseJson);

    @GET("/api/chat/public/channel/{userId}")
    public void getSelfConversation
            (@Path("userId") int userId
             //,@Header("Content-Type") String contentType
                    , Callback<RoomSelfChat> responseJson);

    @GET("/api/chat/find_friends")
    public void getFindFriends(@QueryMap Map<String, String> options, Callback<FindFriends> responseJson);

}
