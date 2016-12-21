package com.candychat.net.event;

/**
 * Created by korrio on 12/17/15.
 */
public class UsernameOkEvent {
    public CheckUsernameData checkUsernameData;

    public UsernameOkEvent(CheckUsernameData checkUsernameData) {
        this.checkUsernameData = checkUsernameData;
    }
}
