package com.module.candychat.net.wouclass;

import java.util.List;

/**
 * Created by korrio on 1/6/16.
 */
public class ChatInfoTo {

    public ChatInfoTo(int id, Object name, String conversationType, String memberType, Object createdBy, Object liveUserId, boolean active, boolean status, boolean deleteFlag, String timestamp, Object avatar, String created_at, String updated_at, List<Member1Entity> member1, List<Member2Entity> member2) {
        this.id = id;
        this.name = name;
        this.conversationType = conversationType;
        this.memberType = memberType;
        this.createdBy = createdBy;
        this.liveUserId = liveUserId;
        this.active = active;
        this.status = status;
        this.deleteFlag = deleteFlag;
        this.timestamp = timestamp;
        this.avatar = avatar;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.member1 = member1;
        this.member2 = member2;
    }

    /**
     * id : 149
     * name : null
     * conversationType : PRIVATE
     * memberType : INDIVIDUAL
     * createdBy : null
     * liveUserId : null
     * active : true
     * status : true
     * deleteFlag : false
     * timestamp : 2015-12-30T10:32:56.000Z
     * avatar : null
     * created_at : 2016-01-06T03:13:38.000Z
     * updated_at : 2016-01-06T03:13:38.000Z
     * member1 : [{"id":347,"conversationId":149,"userId":250,"activeFlag":false,"inviteFlag":true,"adminFlag":false,"canInviteFlag":true,"inviteAcceptFlag":true,"timestamp":"2015-12-30T10:32:56.000Z","created_at":"2016-01-06T03:13:38.000Z","updated_at":"2016-01-06T03:13:38.000Z","conversation_id":149}]
     * member2 : [{"id":348,"conversationId":149,"userId":249,"activeFlag":false,"inviteFlag":true,"adminFlag":false,"canInviteFlag":true,"inviteAcceptFlag":true,"timestamp":"2015-12-30T10:32:56.000Z","created_at":"2016-01-06T03:13:38.000Z","updated_at":"2016-01-06T03:13:38.000Z","conversation_id":149}]
     */

    private int id;
    private Object name;
    private String conversationType;
    private String memberType;
    private Object createdBy;
    private Object liveUserId;
    private boolean active;
    private boolean status;
    private boolean deleteFlag;
    private String timestamp;
    private Object avatar;
    private String created_at;
    private String updated_at;
    /**
     * id : 347
     * conversationId : 149
     * userId : 250
     * activeFlag : false
     * inviteFlag : true
     * adminFlag : false
     * canInviteFlag : true
     * inviteAcceptFlag : true
     * timestamp : 2015-12-30T10:32:56.000Z
     * created_at : 2016-01-06T03:13:38.000Z
     * updated_at : 2016-01-06T03:13:38.000Z
     * conversation_id : 149
     */

    private List<Member1Entity> member1;
    /**
     * id : 348
     * conversationId : 149
     * userId : 249
     * activeFlag : false
     * inviteFlag : true
     * adminFlag : false
     * canInviteFlag : true
     * inviteAcceptFlag : true
     * timestamp : 2015-12-30T10:32:56.000Z
     * created_at : 2016-01-06T03:13:38.000Z
     * updated_at : 2016-01-06T03:13:38.000Z
     * conversation_id : 149
     */

    private List<Member2Entity> member2;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public void setLiveUserId(Object liveUserId) {
        this.liveUserId = liveUserId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setMember1(List<Member1Entity> member1) {
        this.member1 = member1;
    }

    public void setMember2(List<Member2Entity> member2) {
        this.member2 = member2;
    }

    public int getId() {
        return id;
    }

    public Object getName() {
        return name;
    }

    public String getConversationType() {
        return conversationType;
    }

    public String getMemberType() {
        return memberType;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public Object getLiveUserId() {
        return liveUserId;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Object getAvatar() {
        return avatar;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public List<Member1Entity> getMember1() {
        return member1;
    }

    public List<Member2Entity> getMember2() {
        return member2;
    }

    public static class Member1Entity {
        private int id;
        private int conversationId;
        private int userId;
        private boolean activeFlag;
        private boolean inviteFlag;
        private boolean adminFlag;
        private boolean canInviteFlag;
        private boolean inviteAcceptFlag;
        private String timestamp;
        private String created_at;
        private String updated_at;
        private int conversation_id;

        public void setId(int id) {
            this.id = id;
        }

        public void setConversationId(int conversationId) {
            this.conversationId = conversationId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setActiveFlag(boolean activeFlag) {
            this.activeFlag = activeFlag;
        }

        public void setInviteFlag(boolean inviteFlag) {
            this.inviteFlag = inviteFlag;
        }

        public void setAdminFlag(boolean adminFlag) {
            this.adminFlag = adminFlag;
        }

        public void setCanInviteFlag(boolean canInviteFlag) {
            this.canInviteFlag = canInviteFlag;
        }

        public void setInviteAcceptFlag(boolean inviteAcceptFlag) {
            this.inviteAcceptFlag = inviteAcceptFlag;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setConversation_id(int conversation_id) {
            this.conversation_id = conversation_id;
        }

        public int getId() {
            return id;
        }

        public int getConversationId() {
            return conversationId;
        }

        public int getUserId() {
            return userId;
        }

        public boolean isActiveFlag() {
            return activeFlag;
        }

        public boolean isInviteFlag() {
            return inviteFlag;
        }

        public boolean isAdminFlag() {
            return adminFlag;
        }

        public boolean isCanInviteFlag() {
            return canInviteFlag;
        }

        public boolean isInviteAcceptFlag() {
            return inviteAcceptFlag;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getConversation_id() {
            return conversation_id;
        }
    }

    public static class Member2Entity {
        private int id;
        private int conversationId;
        private int userId;
        private boolean activeFlag;
        private boolean inviteFlag;
        private boolean adminFlag;
        private boolean canInviteFlag;
        private boolean inviteAcceptFlag;
        private String timestamp;
        private String created_at;
        private String updated_at;
        private int conversation_id;

        public void setId(int id) {
            this.id = id;
        }

        public void setConversationId(int conversationId) {
            this.conversationId = conversationId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setActiveFlag(boolean activeFlag) {
            this.activeFlag = activeFlag;
        }

        public void setInviteFlag(boolean inviteFlag) {
            this.inviteFlag = inviteFlag;
        }

        public void setAdminFlag(boolean adminFlag) {
            this.adminFlag = adminFlag;
        }

        public void setCanInviteFlag(boolean canInviteFlag) {
            this.canInviteFlag = canInviteFlag;
        }

        public void setInviteAcceptFlag(boolean inviteAcceptFlag) {
            this.inviteAcceptFlag = inviteAcceptFlag;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setConversation_id(int conversation_id) {
            this.conversation_id = conversation_id;
        }

        public int getId() {
            return id;
        }

        public int getConversationId() {
            return conversationId;
        }

        public int getUserId() {
            return userId;
        }

        public boolean isActiveFlag() {
            return activeFlag;
        }

        public boolean isInviteFlag() {
            return inviteFlag;
        }

        public boolean isAdminFlag() {
            return adminFlag;
        }

        public boolean isCanInviteFlag() {
            return canInviteFlag;
        }

        public boolean isInviteAcceptFlag() {
            return inviteAcceptFlag;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getConversation_id() {
            return conversation_id;
        }
    }
}
