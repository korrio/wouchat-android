package com.candychat.net.event;

/**
 * Created by Mac on 3/3/15.
 */
public class RegisterEvent {
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String phone;
    private String phoneCode;

    public RegisterEvent(String name, String username, String password, String email, String gender, String phone, String phoneCode) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.phoneCode = phoneCode;
    }

    public RegisterEvent(String name, String username, String password, String email, String gender) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;

    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public String toString() {
        return "";
    }
}
