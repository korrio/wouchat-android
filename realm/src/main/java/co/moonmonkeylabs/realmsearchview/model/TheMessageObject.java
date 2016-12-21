package co.moonmonkeylabs.realmsearchview.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.Spanned;

import java.util.List;

import io.realm.annotations.Ignore;

//import org.parceler.Parcel;
//import org.parceler.ParcelConverter;
//import org.parceler.Parcels;

//@RealmClass
//@Parcel(implementations = { TheMessageObject.TheMessageObjectConverter.class },
//        value = Parcel.Serialization.BEAN,
//        analyze = { TheMessageObject.class })
public class TheMessageObject
      //  extends RealmObject
{

//    public static class TheMessageObjectConverter implements ParcelConverter<TheMessageObject> {
//
//        @Override
//        public void toParcel(TheMessageObject input, android.os.Parcel parcel) {
//            parcel.writeParcelable(Parcels.wrap(input), 0);
//        }
//
//        @Override
//        public TheMessageObject fromParcel(android.os.Parcel parcel) {
//            return Parcels.unwrap(parcel.readParcelable(TheMessageObject.class.getClassLoader()));
//        }
//    }

    public boolean isAcked;

    public boolean isDelivered;

    public boolean unread = false;

    public boolean see = false;

    public boolean isListened;

    public boolean isAcked() {
        return this.isAcked;
    }

    public void setAcked(boolean var1) {
        this.isAcked = var1;
    }

    public boolean isDelivered() {
        return this.isDelivered;
    }

    public void setDelivered(boolean var1) {
        this.isDelivered = var1;
    }

    public boolean isUnread() {
        return this.unread;
    }

    public void setUnread(boolean var1) {
        this.unread = var1;
    }

    public boolean isListened() {
        return this.isListened;
    }

    public void setListened(boolean var1) {
        this.isListened = var1;
    }

    @Ignore
    public TheMessageObject.Direct direct;
    @Ignore
    public TheMessageObject.Status status;
    @Ignore
    public TheMessageObject.ChatType chatType;
    @Ignore
    public TheMessageObject.Type type;
    @Ignore
    public Bitmap localPhotoBitmap;
    @Ignore
    public Recorder recorder;

    public static class Recorder extends BaseModel {
        float time;
        String filePath;

        public Recorder(float time, String filePath) {
            this.time = time;
            this.filePath = filePath;
        }

        public float getTime() {
            return time;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }


    public static enum ChatType {
        Chat,
        GroupChat;

        private ChatType() {
        }
    }

    public static enum Direct {
        SEND,
        RECEIVE;

        private Direct() {
        }
    }

    public static enum Status {
        SUCCESS,
        FAIL,
        INPROGRESS,
        CREATE;

        private Status() {
        }
    }

    public static enum Type {
        TXT,
        STICKER,
        IMAGE,
        VIDEO,
        LOCATION,
        CONTACT,
        VOICE,
        CMD;

        private Type() {
        }
    }

    @Ignore
    public Uri clipUri;

    @Ignore
    public transient Spanned spanned = null;
    public String htmlStringColor = "";
    public String htmlStringFont = "";

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String dataString;
    public boolean onRight = false;
    public int state;

    public int id = 0;
    public int conversationId;
    public int senderId;
    public String message;
    public int messageType;

    @Ignore
    public List<DataEntity> data;

    public int readCount;
    public String ipAddress;
    public String createdAt;
    /**
     * senderId : 250
     * id : 250
     * username : lazyelephant809
     * name : bahri slangen
     * avatar : photos/2016/01/1212312121_59_093f65e080a295f8076b1c5722a46aa2_thumb
     * extension : jpg
     */
    @Ignore
    public SenderEntity sender;
    /**
     * style : {"style":"OpenSans-Regular.ttf","color":"000000","locale":"fontsEn","size":"16"}
     * message : sdasdsadsa
     */

    @Ignore
    public List<MessageColorfulEntity> messageColorful;
   // public List<DataEntity> data;

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        if(getSender() != null)
            return "http://www.candychat.net/" + getSender().getAvatar() + "." + getSender().getExtension();
        else
            return "http://candychat.net/themes/images/default-cover.png";
    }

    public TheMessageObject.ChatType getChatType() {
        return this.chatType;
    }

    public void setChatType(TheMessageObject.ChatType var1) {
        this.chatType = var1;
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

    public void setType(TheMessageObject.Type var1) {
        this.type = var1;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;

        if(this.getMessageType() == 0)
            this.setType(TheMessageObject.Type.TXT);
        else if(this.getMessageType() == 1)
            this.setType(TheMessageObject.Type.STICKER);
        else if(this.getMessageType() == 2)
            this.setType(TheMessageObject.Type.IMAGE);
        else if(this.getMessageType() == 3)
            this.setType(TheMessageObject.Type.VIDEO);
        else if(this.getMessageType() == 4)
            this.setType(TheMessageObject.Type.LOCATION);
        else if(this.getMessageType() == 5)
            this.setType(TheMessageObject.Type.CONTACT);
        else if(this.getMessageType() == 6)
            this.setType(TheMessageObject.Type.VOICE);
        else if(this.getMessageType() == 9)
            this.setType(TheMessageObject.Type.CMD);

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

    public void setIpAddress(String ipAddress) {
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

    public TheMessageObject.Type getType() {
        return this.type;
    }

    public int getReadCount() {
        return readCount;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long timestamp = 0;
    //public long messageTimestamp = 0;

//    public void setMessageTimestamp() {
//        if(getTimestamp() != 0)
//            messageTimestamp = getTimestamp() * 1000;
//        else {
//            messageTimestamp = System.currentTimeMillis();
//        }
//        setTimestamp(System.currentTimeMillis() / 1000L);
//    }

    //public long getMessageTimestamp() {
        //return messageTimestamp;
    //}

    public String getCreatedAt() {
        return createdAt;
    }

    public SenderEntity getSender() {
        return sender;
    }

    public List<MessageColorfulEntity> getMessageColorful() {
        return messageColorful;
    }

    //@Parcel(parcelsIndex = false)
    public static class DataEntity extends BaseModel {

    }

    //@Parcel(parcelsIndex = false)
    public static class SenderEntity {
        public int senderId;
        public int id;
        public String username;
        public String name;
        public String avatar;
        public String extension;

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

    //@Parcel(parcelsIndex = false)
    public static class MessageColorfulEntity {
        /**
         * style : OpenSans-Regular.ttf
         * color : 000000
         * locale : fontsEn
         * size : 16
         */

        public StyleEntity style;
        public String message;

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

        //@Parcel(parcelsIndex = false)
        public static class StyleEntity {
            public String style;
            public String color;
            public String locale;
            public String size;

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

    @Override
    public String toString() {
        return "TheMessageObject{" +
                "isAcked=" + isAcked +
                ", isDelivered=" + isDelivered +
                ", unread=" + unread +
                ", isListened=" + isListened +
                ", spanned=" + spanned +
                ", htmlStringColor='" + htmlStringColor + '\'' +
                ", htmlStringFont='" + htmlStringFont + '\'' +
                ", dataString='" + dataString + '\'' +
                ", onRight=" + onRight +
                ", state=" + state +
                ", id=" + id +
                ", conversationId=" + conversationId +
                ", senderId=" + senderId +
                ", message='" + message + '\'' +
                ", messageType=" + messageType +
                ", data=" + data +
                ", readCount=" + readCount +
                ", ipAddress=" + ipAddress +
                ", createdAt='" + createdAt + '\'' +
                ", sender=" + sender +
                ", messageColorful=" + messageColorful +
                '}';
    }
}
