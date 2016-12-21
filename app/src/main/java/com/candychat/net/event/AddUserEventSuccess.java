package com.candychat.net.event;


import com.candychat.net.model.response.AddUserResponse;

/**
 * Created by Mac on 7/28/15.
 */
public class AddUserEventSuccess {
    public AddUserResponse user;

    public AddUserEventSuccess(AddUserResponse user) {
        this.user = user;
    }
}
