package com.candychat.net.event;

/**
 * Created by Mac on 6/27/15.
 */
public class FbAuthEvent {
    private String fbToken;

    public FbAuthEvent(String fbToken) {
        this.fbToken = fbToken;
    }

    public String getFbToken() {
        return fbToken;
    }
}
