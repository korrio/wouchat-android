package com.candychat.net.model;

import com.candychat.net.manager.EndpointManager;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class UserModel extends BaseModel {

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
    public String phone;
    @SerializedName("phone_code")
    @Expose
    public String phoneCode;
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
