package com.candychat.net.activity2.countrypicker;

import com.candychat.net.event.UserProfileDataResponse;

/**
 * Created by Mac on 3/2/16.
 */
public class SearchPhoneEventSuccess {
    public UserProfileDataResponse userResponse;
    public SearchPhoneEventSuccess(UserProfileDataResponse userResponse) {
        this.userResponse = userResponse;
    }
}
