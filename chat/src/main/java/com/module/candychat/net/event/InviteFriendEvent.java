package com.module.candychat.net.event;

import java.util.ArrayList;

/**
 * Created by korrio on 2/16/16.
 */
public class InviteFriendEvent {

    public int cid;
    public ArrayList<Integer> friendIds;

    public InviteFriendEvent(int cid,ArrayList<Integer> friendIds) {
        this.cid = cid;
        this.friendIds = friendIds;
    }
}
