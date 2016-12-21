package com.candychat.net.event;


import com.candychat.net.model.response.FriendsDataResponse;

/**
 * Created by Mac on 7/28/15.
 */
public class GetFriendsEventSuccess {
    public FriendsDataResponse user;

    public GetFriendsEventSuccess(FriendsDataResponse user) {
        this.user = user;
    }
}
