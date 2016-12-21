package com.module.candychat.net.wouclass;

import android.text.Spanned;

import java.util.List;

public class TheMessageObject {

    public final static int MSG_TYPE_TEXT 	= 0;
    public final static int MSG_TYPE_STICKER = 1;
    public final static int MSG_TYPE_PHOTO 	= 2;
    public final static int MSG_TYPE_CLIP = 3;

    //public final static int MSG_TYPE_YOUTUBE_LINK = 3;
    public final static int MSG_TYPE_YOUTUBE_OBJ = 31;
    public final static int MSG_TYPE_SOUNDCLOUD_OBJ = 32;
    public final static int MSG_TYPE_AUDIO_CALL = 4;
    public final static int MSG_TYPE_VIDEO_CALL = 5;
    public final static int MSG_TYPE_LOCATION = 6;
    public final static int MSG_TYPE_CONTACT = 7;
    public final static int MSG_TYPE_VOICE = 8;

    public final static int MSG_STATE_SENDING 	= 0;
    public final static int MSG_STATE_SUCCESS 	= 1;
    public final static int MSG_STATE_FAIL 		= 2;

    /**
     * id : 1392
     * conversationId : 149
     * senderId : 250
     * message : sdasdsadsa
     * messageType : 0
     * data : {}
     * messageColorful : [{"style":{"style":"OpenSans-Regular.ttf","color":"000000","locale":"fontsEn","size":"16"},"message":"sdasdsadsa"}]
     * readCount : 0
     * ipAddress : null
     * createdAt : 2016-01-06T11:08:43.000Z
     * sender : {"senderId":250,"id":250,"username":"lazyelephant809","name":"bahri slangen","avatar":"photos/2016/01/1212312121_59_093f65e080a295f8076b1c5722a46aa2_thumb","extension":"jpg"}
     */


    public transient Spanned spanned = null;
    public String htmlStringColor = "";
    public String htmlStringFont = "";

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String dataString = "";
    public boolean onRight = false;
    public int state;

    private int id;
    private int conversationId;
    private int senderId;
    private String message;
    private int messageType;
    private List<DataEntity> data;

    private int readCount;
    private Object ipAddress;
    private String createdAt;
    /**
     * senderId : 250
     * id : 250
     * username : lazyelephant809
     * name : bahri slangen
     * avatar : photos/2016/01/1212312121_59_093f65e080a295f8076b1c5722a46aa2_thumb
     * extension : jpg
     */

    private SenderEntity sender;
    /**
     * style : {"style":"OpenSans-Regular.ttf","color":"000000","locale":"fontsEn","size":"16"}
     * message : sdasdsadsa
     */

    private List<MessageColorfulEntity> messageColorful;
   // private List<DataEntity> data;

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        if(getSender() != null)
            return "http://www.candychat.net/" + getSender().getAvatar() + "." + getSender().getExtension();
        else
            return "http://candychat.net/themes/images/default-cover.png";
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public void setIpAddress(Object ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setSender(SenderEntity sender) {
        this.sender = sender;
    }

    public void setMessageColorful(List<MessageColorfulEntity> messageColorful) {
        this.messageColorful = messageColorful;
    }

    public int getId() {
        return id;
    }

    public int getConversationId() {
        return conversationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageType() {
        return messageType;
    }

    public int getReadCount() {
        return readCount;
    }

    public Object getIpAddress() {
        return ipAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public SenderEntity getSender() {
        return sender;
    }

    public List<MessageColorfulEntity> getMessageColorful() {
        return messageColorful;
    }

    public static class DataEntity {

    }

    public static class SenderEntity {
        private int senderId;
        private int id;
        private String username;
        private String name;
        private String avatar;
        private String extension;

        public void setSenderId(int senderId) {
            this.senderId = senderId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public int getSenderId() {
            return senderId;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getName() {
            return name;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getExtension() {
            return extension;
        }
    }

    public static class MessageColorfulEntity {
        /**
         * style : OpenSans-Regular.ttf
         * color : 000000
         * locale : fontsEn
         * size : 16
         */

        private StyleEntity style;
        private String message;

        public void setStyle(StyleEntity style) {
            this.style = style;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public StyleEntity getStyle() {
            return style;
        }

        public String getMessage() {
            return message;
        }

        public static class StyleEntity {
            private String style;
            private String color;
            private String locale;
            private String size;

            public void setStyle(String style) {
                this.style = style;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public void setLocale(String locale) {
                this.locale = locale;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getStyle() {
                return style;
            }

            public String getColor() {
                return color;
            }

            public String getLocale() {
                return locale;
            }

            public String getSize() {
                return size;
            }
        }
    }
}
