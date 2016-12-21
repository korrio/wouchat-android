package com.candychat.net.event;

/**
 * Created by Mac on 12/31/15.
 */
public class GetFriendSuggestionEvent {
    public int userId;

    public GetFriendSuggestionEvent(int userId) {
        this.userId = userId;
    }
}
