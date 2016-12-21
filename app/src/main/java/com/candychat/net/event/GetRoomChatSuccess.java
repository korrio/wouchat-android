package com.candychat.net.event;


import com.candychat.net.model.response.RoomChat;

/**
 * Created by Mac on 8/6/15.
 */
public class GetRoomChatSuccess {
    public RoomChat response;

    public GetRoomChatSuccess(RoomChat response) {
        this.response = response;
    }
}
