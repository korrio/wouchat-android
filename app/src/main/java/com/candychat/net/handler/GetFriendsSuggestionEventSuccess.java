package com.candychat.net.handler;

import com.candychat.net.model.response.FriendsDataResponse;

/**
 * Created by Mac on 12/31/15.
 */
public class GetFriendsSuggestionEventSuccess {

    public FriendsDataResponse friendsDataResponse;

    public GetFriendsSuggestionEventSuccess(FriendsDataResponse friendsDataResponse) {
        this.friendsDataResponse = friendsDataResponse;
    }
}
