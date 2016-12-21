package com.candychat.net.event;


import com.candychat.net.model.BadgeCountResponse;
import com.candychat.net.model.UserModel;

/**
 * Created by Mac on 3/3/15.
 */
public class GetUserProfileSuccessEvent {
    private UserModel user;
    private BadgeCountResponse count;

    public GetUserProfileSuccessEvent(UserModel user, BadgeCountResponse count) {
        this.user = user;
        this.count = count;
    }

    public UserModel getUser() {
        return user;
    }

    public BadgeCountResponse getCount() {
        return count;
    }
}
