package com.candychat.net.event;

import java.util.ArrayList;

public class InviteFriendToGroupChatEvent {
    public ArrayList<Integer> userIds;
    public int userId;
    public String groupName;
    public ArrayList<Integer> userIdsInt = new ArrayList<>();

    public InviteFriendToGroupChatEvent(int userId, ArrayList<Integer> userIds, String name) {
        this.userIds = userIds;
        this.userId = userId;
        this.groupName = name;

        userIdsInt = new ArrayList<>();
        for(int i = 0 ; i < userIds.size() ; i ++) {
            userIdsInt.add(userIds.get(i));
        }
    }
}
