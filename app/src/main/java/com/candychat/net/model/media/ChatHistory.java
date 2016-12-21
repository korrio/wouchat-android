package com.candychat.net.model.media;

import java.util.List;

/**
 * Created by root1 on 2/1/16.
 */
public class ChatHistory {


    /**
     * page : 1
     * size : 1
     * totalElements : 205
     * numberOfElements : 1
     * totalPages : 205
     * finalPage : false
     */

    private PageEntity page;
    /**
     * id : 2935
     * conversationId : 154
     * senderId : 247
     * message :
     * messageType : 2
     * data : [{"id":"236","fileName":"temp.jpg","active":"1","fileType":"image/jpeg","full_path":"http://api.candychat.net/photos/2016/02/FdHlK_236_01161aaa0b6d1345dd8fe4e481144d84.jpg","thumb":"http://api.candychat.net/photos/2016/02/FdHlK_236_01161aaa0b6d1345dd8fe4e481144d84_thumb.jpg","url":"http://api.candychat.net/photos/2016/02/FdHlK_236_01161aaa0b6d1345dd8fe4e481144d84.jpg"}]
     * messageColorful : []
     * readCount : 0
     * ipAddress : null
     * createdAt : 2016-02-01T10:53:45.000Z
     * sender : {"senderId":247,"id":247,"username":"bluewolf224","name":"talisha bons","avatarIv":"photos/2016/01/1212312121_56_9f61408e3afb633e50cdf1b20de6f466_thumb","extension":"jpg"}
     * dataString :
     */

    private List<ContentEntity> content;

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public void setContent(List<ContentEntity> content) {
        this.content = content;
    }

    public PageEntity getPage() {
        return page;
    }

    public List<ContentEntity> getContent() {
        return content;
    }

    public static class PageEntity {
        private int page;
        private int size;
        private int totalElements;
        private int numberOfElements;
        private int totalPages;
        private boolean finalPage;

        public void setPage(int page) {
            this.page = page;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setFinalPage(boolean finalPage) {
            this.finalPage = finalPage;
        }

        public int getPage() {
            return page;
        }

        public int getSize() {
            return size;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public boolean isFinalPage() {
            return finalPage;
        }
    }

    public static class ContentEntity {
        private int id;
        private int conversationId;
        private int senderId;
        private String message;
        private int messageType;
        private int readCount;
        private Object ipAddress;
        private String createdAt;
        /**
         * senderId : 247
         * id : 247
         * username : bluewolf224
         * name : talisha bons
         * avatarIv : photos/2016/01/1212312121_56_9f61408e3afb633e50cdf1b20de6f466_thumb
         * extension : jpg
         */

        private SenderEntity sender;
        private String dataString;
        /**
         * id : 236
         * fileName : temp.jpg
         * active : 1
         * fileType : image/jpeg
         * full_path : http://api.candychat.net/photos/2016/02/FdHlK_236_01161aaa0b6d1345dd8fe4e481144d84.jpg
         * thumb : http://api.candychat.net/photos/2016/02/FdHlK_236_01161aaa0b6d1345dd8fe4e481144d84_thumb.jpg
         * url : http://api.candychat.net/photos/2016/02/FdHlK_236_01161aaa0b6d1345dd8fe4e481144d84.jpg
         */

        private List<DataEntity> data;
        private List<?> messageColorful;

        public void setId(int id) {
            this.id = id;
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

        public void setDataString(String dataString) {
            this.dataString = dataString;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public void setMessageColorful(List<?> messageColorful) {
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

        public String getDataString() {
            return dataString;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public List<?> getMessageColorful() {
            return messageColorful;
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

        public static class DataEntity {
            private String id;
            private String fileName;
            private String active;
            private String fileType;
            private String full_path;
            private String thumb;
            private String url;

            public void setId(String id) {
                this.id = id;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public void setActive(String active) {
                this.active = active;
            }

            public void setFileType(String fileType) {
                this.fileType = fileType;
            }

            public void setFull_path(String full_path) {
                this.full_path = full_path;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getId() {
                return id;
            }

            public String getFileName() {
                return fileName;
            }

            public String getActive() {
                return active;
            }

            public String getFileType() {
                return fileType;
            }

            public String getFull_path() {
                return full_path;
            }

            public String getThumb() {
                return thumb;
            }

            public String getUrl() {
                return url;
            }
        }
    }
}
