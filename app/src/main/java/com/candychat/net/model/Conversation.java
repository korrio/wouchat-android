package com.candychat.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Conversation {

    public int type;
    @SerializedName("conversationId")
    public int cid;
    public int partnerId;
    public String msg;
    public String username;
    public String name;
    public String avatarUrl;
    public String time;

    public int recentId;
    public int badge;

    public List<ListRecentChat.DataBean> data;

    public Conversation(int badge, int type, int recentId, int cid, int partnerId, String msg, String username, String name, String avatarUrl, String time, List<ListRecentChat.DataBean> data) {
        this.badge = badge;
        this.type = type;
        this.recentId = recentId;
        this.cid = cid;
        this.partnerId = partnerId;
        this.msg = msg;
        this.username = username;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.time = time;
        this.data = data;
    }
}
