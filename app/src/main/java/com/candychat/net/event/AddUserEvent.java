package com.candychat.net.event;

/**
 * Created by Mac on 7/28/15.
 */
public class AddUserEvent {
    public int meUserId;
    public int userId;

    public AddUserEvent(int meUserId, int userId) {
        this.meUserId = meUserId;
        this.userId = userId;
    }
}
