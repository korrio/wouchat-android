package com.candychat.net.event;

import com.candychat.net.model.BadgeCountResponse;
import com.google.gson.annotations.Expose;

import com.candychat.net.model.UserModel;


public class UserProfileDataResponse {

    @Expose
    public String status;
    @Expose
    public UserModel user;
    @Expose
    public BadgeCountResponse count;

}
