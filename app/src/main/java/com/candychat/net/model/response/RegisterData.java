package com.candychat.net.model.response;

import com.candychat.net.model.UserProfile;
import com.google.gson.annotations.Expose;

import org.parceler.Parcel;

/**
 * Created by Mac on 3/3/15.
 */
@Parcel
public class RegisterData {
    @Expose public String status;
    @Expose public String message;
    @Expose public UserProfile user;
}
