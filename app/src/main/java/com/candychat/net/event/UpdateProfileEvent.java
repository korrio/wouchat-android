package com.candychat.net.event;


import com.candychat.net.model.UserProfile;

/**
 * Created by Mac on 3/3/15.
 */
public class UpdateProfileEvent {

    private UserProfile user;

    public UpdateProfileEvent(UserProfile user) {
        this.user = user;
    }

    public UserProfile getUserProfile() {
        return user;
    }

}
