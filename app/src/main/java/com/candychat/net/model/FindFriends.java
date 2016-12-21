package com.candychat.net.model;

import com.candychat.net.manager.EndpointManager;
import com.google.gson.annotations.Expose;


/**
 * Created by root1 on 6/6/15.
 */
public class FindFriends {
    @Expose
    public String name;
    @Expose
    public String username;
    @Expose
    public int userId;
    @Expose
    public String avatar;
    @Expose
    public String extension;
    @Expose
    public int isFollow;

    public boolean isFollowing = false;

    public FindFriends(String name, String username, int userId, String avatar, String extension, int isFollow, boolean isFollowing) {
        this.name = name;
        this.username = username;
        this.userId = userId;
        this.avatar = avatar;
        this.extension = extension;
        this.isFollow = isFollow;
        this.isFollowing = isFollowing;

        if(isFollow != 0) {
            isFollowing = true;
        }
    }

    public boolean getIsFollow() {
        if(isFollow != 0) {
            isFollowing = true;
        } else {
            isFollowing = false;
        }
        return isFollowing;
    }

    public String getAvatarUrl() {
        return EndpointManager.getAvatarPath(avatar + "." + extension);
    }
}
