package com.candychat.net.handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.candychat.net.WOUApp;
import com.candychat.net.event.GetRecentChatEvent;
import com.candychat.net.event.GetRecentChatSuccess;
import com.candychat.net.event.GetRoomChatEvent;
import com.candychat.net.event.GetRoomChatSuccess;
import com.candychat.net.event.GetRoomSelfChatSuccess;
import com.candychat.net.event.SearchUserEvent;
import com.candychat.net.event.SearchUserEventSuccess;
import com.candychat.net.event.SearchUserNotFoundEvent;
import com.candychat.net.event.response.RoomSelfChat;
import com.candychat.net.model.FindFriends;
import com.candychat.net.model.ListRecentChat;
import com.candychat.net.model.response.RoomChat;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ApiHandler {

    private Context context;
    private ApiService api;
    private ApiBus apiBus;

    public ApiHandler(Context context, ApiService api,
                      ApiBus apiBus) {

        this.context = context;
        this.api = api;
        this.apiBus = apiBus;
    }

    public void registerForEvents() {
        apiBus.register(this);
    }


    @Subscribe
    public void onGetRecentChat(final GetRecentChatEvent event) {
        api.getRecentChat(event.userId, new Callback<ListRecentChat>() {
            @Override
            public void success(ListRecentChat listRecentChat, Response response) {
                if (listRecentChat.getContent().size() != 0)
                    ApiBus.getInstance().postQueue(new GetRecentChatSuccess(listRecentChat));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    @Subscribe
    public void onGetConversation(GetRoomChatEvent event) {

        if(event.userId == event.partnerId) {
            api.getSelfConversation(event.userId, new Callback<RoomSelfChat>() {
                @Override
                public void success(RoomSelfChat roomChat, Response response) {
                    Log.e("conversationOneToOne", roomChat.getId() + "");
                    //ApiBus.getInstance().post(new GetChatInfoEvent(conversationId.id));
                    ApiBus.getInstance().post(new GetRoomSelfChatSuccess(roomChat));
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error", error.getLocalizedMessage() + " ooo");
                }
            });
        } else {
            api.getConversation(event.userId, event.partnerId, new Callback<RoomChat>() {
                @Override
                public void success(RoomChat roomChat, Response response) {
                    Log.e("conversationOneToOne", roomChat.getId() + "");
                    //ApiBus.getInstance().post(new GetChatInfoEvent(conversationId.id));
                    ApiBus.getInstance().post(new GetRoomChatSuccess(roomChat));
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error", error.getLocalizedMessage() + " ooo");
                }
            });
        }




    }

    @Subscribe
    public void findFriend(SearchUserEvent event) {
        Map<String, String> options = new HashMap<String, String>();
        options.put("searchTerm", event.username);
        options.put("userId", WOUApp.mPref.userId().getOr(0) + "");
        api.getFindFriends(options, new Callback<FindFriends>() {
            @Override
            public void success(FindFriends user, Response response) {
                if (user != null)
                    ApiBus.getInstance().postQueue(new SearchUserEventSuccess(user));
                else
                    ApiBus.getInstance().postQueue(new SearchUserNotFoundEvent());

                Log.e("user", user.username);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", error.getLocalizedMessage());
                Toast.makeText(context,"Not Found",Toast.LENGTH_SHORT).show();
            }
        });
    }



}
