package com.candychat.net.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mac on 2/14/15.
 */
public class FbProfile extends BaseModel {
    @Expose public String id;
    @Expose public String bio;

    @SerializedName("first_name")
    @Expose public String firstName;
    @SerializedName("middle_name")
    @Expose public String middleName;
    @SerializedName("last_name")
    @Expose public String lastName;

    @Expose public String link;
    @Expose public String locale;

    @Expose public int timezone;
    @SerializedName("updated_time")
    @Expose public String updated;

    public FbProfile(String id, String bio, String firstName, String middleName, String lastName, String link, String locale, int timezone, String updated) {
        this.id = id;
        this.bio = bio;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.link = link;
        this.locale = locale;
        this.timezone = timezone;
        this.updated = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
