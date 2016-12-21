package com.candychat.net.event;


import com.candychat.net.model.FbProfile;

public class LoadFbProfileEvent {

    public FbProfile profile;

    public String facebookToken;

    public LoadFbProfileEvent(FbProfile profile, String facebookToken) {
        this.profile = profile;
        this.facebookToken = facebookToken;
    }
}
