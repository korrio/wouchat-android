package com.module.candychat.net.service;

import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.event.HistoryDataResponse;
import com.module.candychat.net.model.RelationsGroup;
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

public interface TheTopicApiService {


    @GET("/user/{id}/relations")
    public void getTopic(@Path("id") int id, Callback<RelationsGroup> responseJson);

}
