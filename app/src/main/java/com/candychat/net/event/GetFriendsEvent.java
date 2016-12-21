package com.candychat.net.event;

/**
 * Created by Mac on 7/28/15.
 */
public class GetFriendsEvent {

    public int userId;

    public GetFriendsEvent(int userId) {
        this.userId = userId;
    }
}
