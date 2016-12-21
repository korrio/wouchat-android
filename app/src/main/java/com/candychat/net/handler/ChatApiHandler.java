package com.candychat.net.handler;

import android.content.Context;
import android.util.Log;

import com.candychat.net.event.ContentInfoEvent;
import com.candychat.net.event.ContentInfoSuccess;
import com.candychat.net.event.ConversationEventSuccess;
import com.candychat.net.event.ConversationGroupEvent;
import com.candychat.net.event.InviteFriendToBroadcastEvent;
import com.candychat.net.event.InviteFriendToGroupChatEvent;
import com.candychat.net.event.InviteFriendToTopicGroupChatEvent;
import com.candychat.net.model.media.ChatHistory;
import com.candychat.net.model.response.ConversationIdResponse;
import com.squareup.otto.Subscribe;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ChatApiHandler {

    public Context context;
    private ChatApiService api;
    private ApiBus apiBus;

    public ChatApiHandler(Context context, ChatApiService api,
                          ApiBus apiBus) {

        this.context = context;
        this.api = api;
        this.apiBus = apiBus;
    }

    public void registerForEvents() {
        apiBus.register(this);
    }


    @Subscribe
    public void onGetConversationGroup(ConversationGroupEvent event) {

        Log.e("event.liveUserId", event.liveUserId + "");

        api.getConversationGroupPublic(event.liveUserId, new Callback<ConversationIdResponse>() {
            @Override
            public void success(ConversationIdResponse conversationIdResponse, Response response) {
                Log.e("conversationGroupPublic", conversationIdResponse.id + "");
                ApiBus.getInstance().post(new ConversationEventSuccess(conversationIdResponse.id));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @Subscribe
    public void createGroupChat(InviteFriendToGroupChatEvent event) {

        String inviteUserIdStr = "[";

        for (int i = 0; i < event.userIdsInt.size(); i++) {
            inviteUserIdStr = inviteUserIdStr.concat(event.userIdsInt.get(i) + ",");
        }

        if (inviteUserIdStr.endsWith(",")) {
            inviteUserIdStr = inviteUserIdStr.substring(0, inviteUserIdStr.length() - 1);
        }

        inviteUserIdStr = inviteUserIdStr.concat("]");
        if (inviteUserIdStr.startsWith("[") && inviteUserIdStr.endsWith("]")) {
            Log.e("mystring", inviteUserIdStr);
            api.createGroup(event.groupName, inviteUserIdStr, event.userId, new Callback<ConversationIdResponse>() {
                @Override
                public void success(ConversationIdResponse conversationIdResponse, Response response) {
                    Log.e("CreateGroup", response.getUrl() + "");
                    Log.e("CreateGroup", response.getBody() + "");
                    ApiBus.getInstance().postQueue(new ConversationEventSuccess(conversationIdResponse.id));

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error", error.getLocalizedMessage());
                }
            });
        } else {
            Log.e("mystring", inviteUserIdStr);
        }
    }



    @Subscribe
    public void createBroadcast(InviteFriendToBroadcastEvent event) {

        String inviteUserIdStr = "[";

        for (int i = 0; i < event.userIdsInt.size(); i++) {
            inviteUserIdStr = inviteUserIdStr.concat(event.userIdsInt.get(i) + ",");
        }

        if (inviteUserIdStr.endsWith(",")) {
            inviteUserIdStr = inviteUserIdStr.substring(0, inviteUserIdStr.length() - 1);
        }

        inviteUserIdStr = inviteUserIdStr.concat("]");
        if (inviteUserIdStr.startsWith("[") && inviteUserIdStr.endsWith("]")) {
            api.createBroadcast(event.groupName, inviteUserIdStr, event.userId, new Callback<ConversationIdResponse>() {
                @Override
                public void success(ConversationIdResponse conversationIdResponse, Response response) {
                    Log.e("CreateBroadcastss", response.getUrl() + "");
                    ApiBus.getInstance().postQueue(new ConversationEventSuccess(conversationIdResponse.id));

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("error", error.getLocalizedMessage());
                }
            });
        } else {
            Log.e("haha", inviteUserIdStr);
        }
    }

    @Subscribe
    public void onGetContentInfo(ContentInfoEvent event) {
        Log.e("event.liveUserId", event.id + "");

        HashMap<String, Integer> option = new HashMap<String, Integer>();
        option.put("page", event.page);
        option.put("size", event.size);

        api.getChatHistory(event.id, option, new Callback<ChatHistory>() {
            @Override
            public void success(ChatHistory content, Response response) {
                if (content != null) {
                    Log.e("bbbbb", content.getContent().size() + "");
                    ApiBus.getInstance().postQueue(new ContentInfoSuccess(content));

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("COnten", error.getUrl());
                Log.e("COnten", error.getLocalizedMessage());
            }
        });
    }


}
