package com.candychat.net.event;

import com.candychat.net.model.ChatInfoTipic;
import com.module.candychat.net.event.ChatInfo;

/**
 * Created by Mac on 8/3/15.
 */
public class GetChatInfoSuccessTopic {
    public ChatInfoTipic info;

    public GetChatInfoSuccessTopic(ChatInfoTipic info) {
        this.info = info;
    }
}
