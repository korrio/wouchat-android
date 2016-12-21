package com.module.candychat.net.model;

import java.util.List;

/**
 * Created by root1 on 9/2/16.
 */
public class RelationsTopic {


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
     * conversationId : 302
     * id : 302
     * conversationType : PRIVATE
     * name : topic_new
     * createdBy : 0
     * avatar : /config_uploads/default/no_group_avatar.jpg
     * createdAt : 2016-09-02T04:30:13.000Z
     * conversationMembers : [{"userId":0,"username":"hello","name":"hello","avatar":"photos/2016/06/5ZolZ_1026_24146db4eb48c718b84cae0a0799dcfc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":302},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":302},{"userId":251,"username":"edgurfgjkk","name":"Ethiopia ccvnnn","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":302},{"userId":304,"username":"test01","name":"test test","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":302}]
     */

    private List<ContentEntity> content;

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public List<ContentEntity> getContent() {
        return content;
    }

    public void setContent(List<ContentEntity> content) {
        this.content = content;
    }

    public static class PageEntity {
        private int page;
        private int size;
        private int totalElements;
        private int numberOfElements;
        private int totalPages;
        private boolean finalPage;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public boolean isFinalPage() {
            return finalPage;
        }

        public void setFinalPage(boolean finalPage) {
            this.finalPage = finalPage;
        }
    }

    public static class ContentEntity {
        private int conversationId;
        private int id;
        private String conversationType;
        private String name;
        private String createdBy;
        private String avatar;
        private String createdAt;
        /**
         * userId : 0
         * username : hello
         * name : hello
         * avatar : photos/2016/06/5ZolZ_1026_24146db4eb48c718b84cae0a0799dcfc_thumb
         * extension : jpg
         * activeFlag : 0
         * adminFlag : 0
         * inviteFlag : 1
         * canInviteFlag : 1
         * inviteAcceptFlag : 1
         * conversationId : 302
         */

        private List<ConversationMembersEntity> conversationMembers;

        public int getConversationId() {
            return conversationId;
        }

        public void setConversationId(int conversationId) {
            this.conversationId = conversationId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getConversationType() {
            return conversationType;
        }

        public void setConversationType(String conversationType) {
            this.conversationType = conversationType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public List<ConversationMembersEntity> getConversationMembers() {
            return conversationMembers;
        }

        public void setConversationMembers(List<ConversationMembersEntity> conversationMembers) {
            this.conversationMembers = conversationMembers;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getExtension() {
                return extension;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public int getActiveFlag() {
                return activeFlag;
            }

            public void setActiveFlag(int activeFlag) {
                this.activeFlag = activeFlag;
            }

            public int getAdminFlag() {
                return adminFlag;
            }

            public void setAdminFlag(int adminFlag) {
                this.adminFlag = adminFlag;
            }

            public int getInviteFlag() {
                return inviteFlag;
            }

            public void setInviteFlag(int inviteFlag) {
                this.inviteFlag = inviteFlag;
            }

            public int getCanInviteFlag() {
                return canInviteFlag;
            }

            public void setCanInviteFlag(int canInviteFlag) {
                this.canInviteFlag = canInviteFlag;
            }

            public int getInviteAcceptFlag() {
                return inviteAcceptFlag;
            }

            public void setInviteAcceptFlag(int inviteAcceptFlag) {
                this.inviteAcceptFlag = inviteAcceptFlag;
            }

            public int getConversationId() {
                return conversationId;
            }

            public void setConversationId(int conversationId) {
                this.conversationId = conversationId;
            }
        }
    }
}
