package com.candychat.net.event;

/**
 * Created by Mac on 7/12/15.
 */
public class NotiEvent {
    public String action;
    public int Id;

    public NotiEvent(int Id, String action) {
        this.Id = Id;
        this.action = action;
    }
}
