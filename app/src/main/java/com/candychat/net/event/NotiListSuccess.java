package com.candychat.net.event;


import com.candychat.net.model.Noti;

/**
 * Created by Mac on 8/6/15.
 */
public class NotiListSuccess {
    public Noti response;

    public NotiListSuccess(Noti response) {
        this.response = response;
    }
}
