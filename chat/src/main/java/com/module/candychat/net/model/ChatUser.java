package com.module.candychat.net.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Mac on 6/25/15.
 */
public class ChatUser {
    @Expose
    public int senderId;
    @Expose
    public String username;
    @Expose
    public String avatar;
    @Expose
    public String extension;

    public ChatUser(){

    }

    public ChatUser(int senderId, String username, String avatar, String extension) {
        this.senderId = senderId;
        this.username = username;
        this.avatar = avatar;
        this.extension = extension;
    }

    public String getAvatarPath() {
        return "https://www.vdomax.com/" + avatar + "." + extension;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
