package com.candychat.net.event;

/**
 * Created by korrio on 12/17/15.
 */
public class UsernameNotOkEvent {
    public CheckUsernameData checkUsernameData;

    public UsernameNotOkEvent(CheckUsernameData checkUsernameData) {
        this.checkUsernameData = checkUsernameData;
    }
}
