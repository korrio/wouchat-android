package com.candychat.net.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@Parcel
public class UserProfile extends BaseModel {
    @Expose
    public int id;
    @Expose
    public String name;
    //@SerializedName("avatar_url")
    @Expose
    public String avatar;
    //@SerializedName("cover_url")
    @Expose
    public String cover;
    @Expose
    public String username;
    @Expose
    public String password;
    @Expose
    public String email;
    @Expose
    public String about;
    @Expose
    public String phone;
    @SerializedName("phone_code")
    @Expose
    public String phoneCode;

}
