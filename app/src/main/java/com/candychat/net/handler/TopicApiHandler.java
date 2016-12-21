package com.candychat.net.handler;

import android.content.Context;
import android.util.Log;

import com.candychat.net.event.ContentInfoEvent;
import com.candychat.net.event.ContentInfoSuccess;
import com.candychat.net.event.ConversationEventSuccess;
import com.candychat.net.event.ConversationGroupEvent;
import com.candychat.net.event.GetChatInfoSuccessTopic;
import com.candychat.net.event.GetChatInfoTopicEvent;
import com.candychat.net.event.InviteFriendToBroadcastEvent;
import com.candychat.net.event.InviteFriendToGroupChatEvent;
import com.candychat.net.event.InviteFriendToTopicGroupChatEvent;
import com.candychat.net.model.ChatInfoTipic;
import com.candychat.net.model.media.ChatHistory;
import com.candychat.net.model.response.ConversationIdResponse;
import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.event.GetChatInfoSuccess;
import com.module.candychat.net.service.TheChatApiService;
import com.squareup.otto.Subscribe;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TopicApiHandler {

    public Context context;
    private TopicApiService api;
    private ApiBus apiBus;

    public TopicApiHandler(Context context, TopicApiService api,
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


    public void getChatInfoSelf(GetChatInfoTopicEvent event) {
        Log.e("event",event.cid+"");
        api.getSelfConversationTopic(event.cid, new Callback<ChatInfoTipic>() {
            @Override
            public void success(ChatInfoTipic chatInfo, Response response) {
                if (chatInfo != null) {
                    Log.e("xxxxx",chatInfo.getConversationMembers().size()+"");
                    ApiBus.getInstance().postQueue(new GetChatInfoSuccessTopic(chatInfo));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", error.getLocalizedMessage());
                Log.e("error", error.getUrl());
            }
        });
    }

    @Subscribe
    public void createTopicGroupChat(InviteFriendToTopicGroupChatEvent event) {

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
            api.createTopicGroup(event.groupName, inviteUserIdStr, event.userId, event.cid, new Callback<ConversationIdResponse>() {
                @Override
                public void success(ConversationIdResponse conversationIdResponse, Response response) {
//                    Log.e("CreateGroupTopic", response.getUrl() + "");
//                    Log.e("body_topic",response.getBody()+"");
//                    Log.e("body_url",response.getUrl()+"");
//                    Log.e("body_Reason",response.getReason()+"");
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




}
