package com.module.candychat.net.model;

/**
 * Created by root1 on 11/10/15.
 */
public class Font {
    int id;
    int conversationId;
    int senderId;
    String message;
    int messageType;
    String data;
    String size;
    String style;
    String color;
    public  Font(){

    }

    public Font(String color, String style, String size, String data, int messageType, String message, int senderId, int conversationId, int id) {
        this.color = color;
        this.style = style;
        this.size = size;
        this.data = data;
        this.messageType = messageType;
        this.message = message;
        this.senderId = senderId;
        this.conversationId = conversationId;
        this.id = id;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
