package com.candychat.net.event;


import com.candychat.net.model.ListRecentChat;

/**
 * Created by Mac on 8/6/15.
 */
public class GetRecentChatSuccess {
    public ListRecentChat response;

    public GetRecentChatSuccess(ListRecentChat response) {
        this.response = response;
    }
}
