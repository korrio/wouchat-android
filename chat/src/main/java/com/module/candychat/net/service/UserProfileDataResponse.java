package com.module.candychat.net.service;

import com.google.gson.annotations.Expose;

/**
 * Created by korrio on 12/17/15.
 */
public class UserProfileDataResponse {

    @Expose
    public String status;
    @Expose
    public UserLogin user;

}
