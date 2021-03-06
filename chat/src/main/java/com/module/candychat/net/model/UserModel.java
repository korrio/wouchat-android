package com.module.candychat.net.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.module.candychat.net.base.BaseModel;
import com.module.candychat.net.service.EndpointManager;

public class UserModel extends BaseModel {

    public UserModel(int id, String username, String about, String active, String email, String language, String lastLogged, String name, String time, String timestamp, String timezone, String type, String verified, String avatar, String cover, String gender, String birthday, boolean online, boolean isLive, boolean isFollowing) {
        this.id = id;
        this.username = username;
        this.about = about;
        this.active = active;
        this.email = email;
        this.language = language;
        this.lastLogged = lastLogged;
        this.name = name;
        this.time = time;
        this.timestamp = timestamp;
        this.timezone = timezone;
        this.type = type;
        this.verified = verified;
        this.avatar = avatar;
        this.cover = cover;
        this.gender = gender;
        this.birthday = birthday;
        this.online = online;
        this.isLive = isLive;
        this.isFollowing = isFollowing;
    }

    public boolean selected = false;

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Expose
    public int id;
    @Expose
    public String username;
    @Expose
    public String about;
    @Expose
    public String active;
    @Expose
    public String email;
    @Expose
    public String language;
    @SerializedName("last_logged")
    @Expose
    public String lastLogged;
    @Expose
    public String name;
    @Expose
    public String time;
    @Expose
    public String timestamp;
    @Expose
    public String timezone;
    @Expose
    public String type;
    @Expose
    public String verified;
    @Expose
    public String avatar;
    @Expose
    public String cover;
    @Expose
    public String gender;
    @Expose
    public String birthday;
    @Expose
    public boolean online;
    @Expose
    @SerializedName("is_live")
    public boolean isLive;
    @Expose
    @SerializedName("is_following")
    public boolean isFollowing;

    public String getAvatarUrl() {
        return EndpointManager.getAvatarPath(avatar);
    }

    public String getCoverUrl() {
        return EndpointManager.getAvatarPath(cover);
    }
}