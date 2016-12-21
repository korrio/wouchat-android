package com.module.candychat.net.event;

import com.module.candychat.net.upload.UserLogin;

/**
 * Created by korrio on 12/17/15.
 */
public class GetUserInfoSuccessEvent {
    public UserLogin user;
    public GetUserInfoSuccessEvent(UserLogin user) {
        this.user = user;
    }
}
