package com.candychat.net.event;

public class GetUserProfileEvent {
    public int userId;
    public String username;

    public GetUserProfileEvent(int userId) {
        this.userId = userId;
        this.username = "";
    }

    public GetUserProfileEvent(String username,boolean isFromUsername) {
        this.userId = 0;
        this.username = username;
    }
}
