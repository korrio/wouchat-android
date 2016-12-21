package com.candychat.net.event.response;

import java.util.List;

/**
 * Created by korrio on 2/16/16.
 */
public class RoomSelfChat {


    /**
     * conversationId : 66
     * id : 66
     * conversationType : PUBLIC
     * name : AAAA
     * createdBy : 2
     * avatarIv : photos/2016/02/dpG9b_439_eed5af6add95a9a6f1252739b1ad8c24_thumb.jpg
     * createdAt : 2016-02-15T23:00:20.000Z
     * conversationMembers : [{"userId":2,"username":"korrio","name":"AAAA","avatarIv":"photos/2016/02/dpG9b_439_eed5af6add95a9a6f1252739b1ad8c24_thumb","extension":"jpg","activeFlag":null,"adminFlag":null,"inviteFlag":null,"canInviteFlag":null,"inviteAcceptFlag":null,"conversationId":66}]
     * active : true
     */

    private int conversationId;
    private int id;
    private String conversationType;
    private String name;
    private int createdBy;
    private String avatar;
    private String createdAt;
    private boolean active;
    /**
     * userId : 2
     * username : korrio
     * name : AAAA
     * avatarIv : photos/2016/02/dpG9b_439_eed5af6add95a9a6f1252739b1ad8c24_thumb
     * extension : jpg
     * activeFlag : null
     * adminFlag : null
     * inviteFlag : null
     * canInviteFlag : null
     * inviteAcceptFlag : null
     * conversationId : 66
     */

    private List<ConversationMembersEntity> conversationMembers;

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setConversationMembers(List<ConversationMembersEntity> conversationMembers) {
        this.conversationMembers = conversationMembers;
    }

    public int getConversationId() {
        return conversationId;
    }

    public int getId() {
        return id;
    }

    public String getConversationType() {
        return conversationType;
    }

    public String getName() {
        return name;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isActive() {
        return active;
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
    }
}
