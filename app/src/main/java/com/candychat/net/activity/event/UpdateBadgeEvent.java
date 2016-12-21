package com.candychat.net.activity.event;

/**
 * Created by Mac on 2/24/16.
 */
public class UpdateBadgeEvent {
    public int notiChatCount;
    public int notiFollowCount;

    public UpdateBadgeEvent(int notiChatCount, int notiFollowCount) {
        this.notiChatCount = notiChatCount;
        this.notiFollowCount = notiFollowCount;
    }
}
