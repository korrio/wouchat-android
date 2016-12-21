package com.candychat.net.event;


import com.candychat.net.model.LoginData;

/**
 * Created by Mac on 3/2/15.
 */
public class LoginSuccessEvent {
    private LoginData loginData;

    public LoginSuccessEvent(LoginData loginData) {
        this.loginData = loginData;
    }

    public LoginData getLoginData() {
        return loginData;
    }
}
