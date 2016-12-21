package com.candychat.net.model;

import com.candychat.net.WOUApp;

import java.util.List;

/**
 * Created by root1 on 11/30/15.
 */
public class ListRecentChat {


    /**
     * page : 1
     * size : 20
     * totalElements : 1
     * numberOfElements : 1
     * totalPages : 1
     * finalPage : true
     */

    private PageEntity page;
    /**
     * content : [{"conversationRecentId":2,"conversationId":2,"name":null,"createdBy":null,"memberType":"INDIVIDUAL","conversationType":"PRIVATE","avatarIv":"/config_uploads/default/no_group_avatar.jpg","type":"","messageType":0,"lastMessage":"Cghhn","lastHistoryDatetime":"2015-11-30T05:18:39.000Z","data":{},"badge":39,"readTime":null,"readed":0,"conversationMembers":[{"userId":1,"username":"koredevman","name":"Worachet Saengprab","avatarIv":"themes/vdomax1.1/images/default-male-avatarIv","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":2},{"userId":6,"username":"korrio","name":"korr korr","avatarIv":"themes/vdomax1.1/images/default-male-avatarIv","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":2}]}]
     * page : {"page":1,"size":20,"totalElements":1,"numberOfElements":1,"totalPages":1,"finalPage":true}
     * totalBadges : 39
     */

    private int totalBadges;
    /**
     * conversationRecentId : 2
     * conversationId : 2
     * name : null
     * createdBy : null
     * memberType : INDIVIDUAL
     * conversationType : PRIVATE
     * avatarIv : /config_uploads/default/no_group_avatar.jpg
     * type :
     * messageType : 0
     * lastMessage : Cghhn
     * lastHistoryDatetime : 2015-11-30T05:18:39.000Z
     * data : {}
     * badge : 39
     * readTime : null
     * readed : 0
     * conversationMembers : [{"userId":1,"username":"koredevman","name":"Worachet Saengprab","avatarIv":"themes/vdomax1.1/images/default-male-avatarIv","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":2},{"userId":6,"username":"korrio","name":"korr korr","avatarIv":"themes/vdomax1.1/images/default-male-avatarIv","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":2}]
     */

    private List<ContentEntity> content;

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public void setTotalBadges(int totalBadges) {
        this.totalBadges = totalBadges;
    }

    public void setContent(List<ContentEntity> content) {
        this.content = content;
    }

    public PageEntity getPage() {
        return page;
    }

    public int getTotalBadges() {
        return totalBadges;
    }

    public List<ContentEntity> getContent() {
        return content;
    }



    public static class DataBean {
        private String message;
        /**
         * color : b741a7
         * locale :
         * size : 24
         * style : Sniglet
         */

        private StyleBean style;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public StyleBean getStyle() {
            return style;
        }

        public void setStyle(StyleBean style) {
            this.style = style;
        }

        public static class StyleBean {
            private String color;
            private String locale;
            private String size;
            private String style;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getLocale() {
                return locale;
            }

            public void setLocale(String locale) {
                this.locale = locale;
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
        }
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

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        private int conversationRecentId;
        private int conversationId;
        private String name;
        private String createdBy;
        private String memberType;
        private String conversationType;
        private String avatar;
        private String type;
        private int messageType;
        private String lastMessage;
        private String lastHistoryDatetime;
        private int badge;
        private Object readTime;
        private int readed;
        /**
         * userId : 1
         * username : koredevman
         * name : Worachet Saengprab
         * avatarIv : themes/vdomax1.1/images/default-male-avatarIv
         * extension : png
         * activeFlag : 0
         * adminFlag : 0
         * inviteFlag : 1
         * canInviteFlag : 1
         * inviteAcceptFlag : 1
         * conversationId : 2
         */

        private List<ConversationMembersEntity> conversationMembers;

        public void setConversationRecentId(int conversationRecentId) {
            this.conversationRecentId = conversationRecentId;
        }

        public void setConversationId(int conversationId) {
            this.conversationId = conversationId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public void setMemberType(String memberType) {
            this.memberType = memberType;
        }

        public void setConversationType(String conversationType) {
            this.conversationType = conversationType;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public void setLastMessage(String lastMessage) {
            this.lastMessage = lastMessage;
        }

        public void setLastHistoryDatetime(String lastHistoryDatetime) {
            this.lastHistoryDatetime = lastHistoryDatetime;
        }

        public void setBadge(int badge) {
            this.badge = badge;
        }

        public void setReadTime(Object readTime) {
            this.readTime = readTime;
        }

        public void setReaded(int readed) {
            this.readed = readed;
        }

        public void setConversationMembers(List<ConversationMembersEntity> conversationMembers) {
            this.conversationMembers = conversationMembers;
        }

        public int getConversationRecentId() {
            return conversationRecentId;
        }

        public int getConversationId() {
            return conversationId;
        }

        public String getName() {
            return name;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public String getMemberType() {
            return memberType;
        }

        public String getConversationType() {
            return conversationType;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getAvatarUrl() {
            return WOUApp.CHAT_ENDPOINT + avatar;
        }

        public String getType() {
            return type;
        }

        public int getMessageType() {
            return messageType;
        }

        public String getLastMessage() {
            return lastMessage;
        }

        public String getLastHistoryDatetime() {
            return lastHistoryDatetime;
        }

        public int getBadge() {
            return badge;
        }

        public Object getReadTime() {
            return readTime;
        }

        public int getReaded() {
            return readed;
        }

        public List<ConversationMembersEntity> getConversationMembers() {
            return conversationMembers;
        }

        public static class ConversationMembersEntity {
            private int userId;
            private String username;
            private String name;
            private String avatar;
            private String extension;
            private int activeFlag;
            private int adminFlag;
            private int inviteFlag;
            private int canInviteFlag;
            private int inviteAcceptFlag;
            private int conversationId;

            public void setUserId(int userId) {
                this.userId = userId;
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

            public void setActiveFlag(int activeFlag) {
                this.activeFlag = activeFlag;
            }

            public void setAdminFlag(int adminFlag) {
                this.adminFlag = adminFlag;
            }

            public void setInviteFlag(int inviteFlag) {
                this.inviteFlag = inviteFlag;
            }

            public void setCanInviteFlag(int canInviteFlag) {
                this.canInviteFlag = canInviteFlag;
            }

            public void setInviteAcceptFlag(int inviteAcceptFlag) {
                this.inviteAcceptFlag = inviteAcceptFlag;
            }

            public void setConversationId(int conversationId) {
                this.conversationId = conversationId;
            }

            public int getUserId() {
                return userId;
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

            public int getActiveFlag() {
                return activeFlag;
            }

            public int getAdminFlag() {
                return adminFlag;
            }

            public int getInviteFlag() {
                return inviteFlag;
            }

            public int getCanInviteFlag() {
                return canInviteFlag;
            }

            public int getInviteAcceptFlag() {
                return inviteAcceptFlag;
            }

            public int getConversationId() {
                return conversationId;
            }
        }
    }
}
