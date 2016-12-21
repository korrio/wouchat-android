package com.candychat.net.event;

/**
 * Created by Mac on 8/6/15.
 */
public class GetRecentChatEvent {
    public int userId;

    public GetRecentChatEvent(int userId) {
        this.userId = userId;
    }
}
