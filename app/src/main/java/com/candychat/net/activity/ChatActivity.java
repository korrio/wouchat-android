package com.candychat.net.activity;

import android.os.Bundle;
import android.util.Log;

import com.candychat.net.base.BaseActivity;
import com.module.candychat.net.model.RelationsGroup;
import com.module.candychat.net.service.TheChatApiService;
import com.wouchat.messenger.R;

import org.parceler.Parcels;

/**
 * Created by korrio on 1/28/16.
 */
public class ChatActivity extends BaseActivity {

    public static TheChatApiService chatApiService = com.module.candychat.net.ChatActivity.buildChatApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if(getIntent().getExtras()!=null) {
            int cid = getIntent().getExtras().getInt("CONVERSATION_ID");
            int userId1 = getIntent().getExtras().getInt("USER_ID_1");
            int userId2 = getIntent().getExtras().getInt("USER_ID_2");
            int chatType = getIntent().getExtras().getInt("CHAT_TYPE");


            com.module.candychat.net.ChatActivity.startChat(this,cid,userId1,userId2,chatType);
            this.finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }
}
