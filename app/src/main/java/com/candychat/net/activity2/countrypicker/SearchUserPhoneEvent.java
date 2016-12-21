package com.candychat.net.activity2.countrypicker;

/**
 * Created by Mac on 3/2/16.
 */
public class SearchUserPhoneEvent {
    public String phoneCode;
    public String phone;

    public SearchUserPhoneEvent(String phoneCode, String phone) {
        this.phoneCode = phoneCode;
        this.phone = phone;
    }
}
