package com.candychat.net.event;

import com.candychat.net.event.response.RoomSelfChat;

/**
 * Created by korrio on 2/16/16.
 */
public class GetRoomSelfChatSuccess {
    public RoomSelfChat roomSelfChat;
    public GetRoomSelfChatSuccess(RoomSelfChat roomSelfChat) {
        this.roomSelfChat = roomSelfChat;
    }
}
