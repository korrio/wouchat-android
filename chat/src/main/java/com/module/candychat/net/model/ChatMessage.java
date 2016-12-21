package com.module.candychat.net.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Mac on 6/25/15.
 */
public class ChatMessage {
    //    "content": [
//    {
//        "id": 2609,
//            "conversationId": 345,
//            "senderId": 6,
//            "message": "fasdfasd",
//            "messageType": 0,
//            "data": {
//        "message": "fasdfasd"
//    },
//        "readCount": 0,
//            "ipAddress": null,
//            "createdAt": "2015-06-25T03:41:38.000Z",
//            "sender": {
//        "senderId": 6,
//                "id": 6,
//                "username": "korrio",
//                "avatar": "photos/2015/04/pr8af_108899_c04356ab5e9726bb6e650e5b9cc17cbc",
//                "extension": "jpg"
//    }
//    },

    @Expose
    public int id;

    public int conversationId;

    public int senderId;

    public String message;

    public int messageType;

    public String data;
    //public MessageData data;

    public String createdAt;

    public ChatUser sender;

    public String username;

    public String avatar;

    public String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ChatMessage(){

    }

    public ChatMessage(int id, int conversationId, int senderId, String message, int messageType, String data, String createdAt, ChatUser sender, String username, String avatar) {
        this.id = id;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.message = message;
        this.messageType = messageType;
        this.data = data;
        this.createdAt = createdAt;
        this.sender = sender;
        this.username = username;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ChatUser getSender() {
        return sender;
    }

    public void setSender(ChatUser sender) {
        this.sender = sender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
