package com.module.candychat.net.event;

import java.util.ArrayList;

public class InviteFriendToTopicChatEvent {
    public ArrayList<Integer> userIds;
    public int userId;
    public int cid;
    public String groupName;
    public ArrayList<Integer> userIdsInt = new ArrayList<>();

    public InviteFriendToTopicChatEvent(int userId, ArrayList<Integer> userIds, String name, int cid) {
        this.userIds = userIds;
        this.userId = userId;
        this.groupName = name;
        this.cid = cid;

        userIdsInt = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            userIdsInt.add(userIds.get(i));
        }
    }
}
