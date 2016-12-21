package com.candychat.net.event;


import com.candychat.net.model.FindFriends;

/**
 * Created by Mac on 7/28/15.
 */
public class SearchUserEventSuccess {
    public FindFriends user;

    public SearchUserEventSuccess(FindFriends user) {
        this.user = user;
    }
}
