package com.candychat.net.event;


/**
 * Created by Mac on 8/6/15.
 */
public class GetRoomChatEvent {
    public int userId;
    public int partnerId;

    public GetRoomChatEvent(int userId, int partnerId) {
        this.userId = userId;
        this.partnerId = partnerId;
    }
}
