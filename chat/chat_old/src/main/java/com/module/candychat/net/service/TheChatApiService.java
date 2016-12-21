package com.module.candychat.net.service;

import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.event.HistoryDataResponse;
import com.module.candychat.net.wouclass.ChatInfoTo;
import com.module.candychat.net.wouclass.TheHistoryDataResponse;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

public interface TheChatApiService {

    @GET("/api/chat/{id}/history/android")
    public void getHistoryAndroid(@Path("id") int id, @QueryMap HashMap<String, Integer> options,
                           Callback<HistoryDataResponse> responseJson);

    @GET("/api/chat/{id}/history")
    public void getTheHistory(@Path("id") int id, @QueryMap HashMap<String, Integer> options,
                                  Callback<TheHistoryDataResponse> responseJson);
    @GET("/api/chat/{id}/android")
    public void getChatById(@Path("id") int id,Callback<ChatInfo> responseJson);

    @GET("/api/chat/{id}/to/{partnerId}")
    public void getChatByTo(@Path("id") int id,@Path("partnerId") int partnerId,Callback<ChatInfoTo> responseJson);
}
