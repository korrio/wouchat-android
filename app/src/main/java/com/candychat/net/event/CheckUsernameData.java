package com.candychat.net.event;

import com.candychat.net.model.UserProfile;
import com.google.gson.annotations.Expose;

/**
 * Created by korrio on 12/17/15.
 */
public class CheckUsernameData {
    @Expose
    public String status;

    @Expose
    public UserProfile user;
}
