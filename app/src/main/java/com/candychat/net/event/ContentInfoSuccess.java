package com.candychat.net.event;


import com.candychat.net.model.media.ChatHistory;

/**
 * Created by Mac on 8/6/15.
 */
public class ContentInfoSuccess {
    public ChatHistory response;

    public ContentInfoSuccess(ChatHistory response) {
        this.response = response;
    }
}
