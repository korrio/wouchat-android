package com.candychat.net.woumodel;

import java.util.List;

/**
 * Created by korrio on 1/18/16.
 */

public class Relations {

    /**
     * status : 1
     * count : {"me":1,"favorite":3,"group":3,"friends":5}
     * me : [{"id":3,"about":"","active":1,"avatar_id":29,"cover_id":0,"cover_position":0,"email":"anyarimpanus@gmail.com","email_verification_key":"4698f890b3f02cb08369b17f5adbabd7","email_verified":0,"language":"","last_logged":1452183369,"name":"Anya Rimpanus","password":"039a726ac0aeec3dde33e45387a7d4ac","time":1448944359,"timestamp":"2016-01-07 11:16:09","timezone":"","type":"user","username":"anya","phone_code":"","phone":"","verified":0,"birthday":"01/01/1990","gender":"male","avatarIv":"photos/2015/12/e3FbH_29_6ea9ab1baa0efb9e19094440c317e21b_100x100.jpg","cover":"themes/grape/images/default-cover.png"}]
     * favorite : []
     * group : [{"conversationId":145,"id":145,"conversationType":"PRIVATE","name":"ใบหญ้า","createdBy":"3","avatarIv":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-01-01T12:58:21.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"Thananon Ngoenthaworn","avatarIv":"photos/2015/12/ow8uf_30_34173cb38f07f89ddbebc2ac9128303f_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":145},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatarIv":"photos/2015/12/e3FbH_29_6ea9ab1baa0efb9e19094440c317e21b_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":145}]},{"conversationId":146,"id":146,"conversationType":"PRIVATE","name":"กลุ่มขอบฟ้า","createdBy":"3","avatarIv":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-01-01T13:40:42.000Z","conversationMembers":[{"userId":3,"username":"anya","name":"Anya Rimpanus","avatarIv":"photos/2015/12/e3FbH_29_6ea9ab1baa0efb9e19094440c317e21b_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":146}]},{"conversationId":161,"id":161,"conversationType":"PRIVATE","name":"Gr001","createdBy":"3","avatarIv":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-01-16T15:11:23.000Z","conversationMembers":[{"userId":3,"username":"anya","name":"Anya Rimpanus","avatarIv":"photos/2015/12/e3FbH_29_6ea9ab1baa0efb9e19094440c317e21b_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":161},{"userId":2,"username":"korrio","name":"Thananon Ngoenthaworn","avatarIv":"photos/2015/12/ow8uf_30_34173cb38f07f89ddbebc2ac9128303f_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":161}]}]
     * friends : [{"id":273,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"samuel.nikula@gmail.com","email_verification_key":"a16775b349a38b242c2137d1491a1f12","email_verified":0,"language":"","last_logged":0,"name":"+66917366196","password":"039a726ac0aeec3dde33e45387a7d4ac","time":1451818010,"timestamp":"2016-01-03 06:37:35","timezone":"","type":"user","username":"917366196","phone_code":"66","phone":"917366196","verified":2,"birthday":"01/01/1990","gender":"male","avatarIv":"themes/grape/images/default-male-avatarIv.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":true},{"id":2,"about":"","active":1,"avatar_id":30,"cover_id":0,"cover_position":0,"email":"iamnotkorr@gmail.com","email_verification_key":"3cea59cdd74660ff55be53543cb1c1e8","email_verified":0,"language":"","last_logged":1452834787,"name":"Thananon Ngoenthaworn","password":"039a726ac0aeec3dde33e45387a7d4ac","time":1448944317,"timestamp":"2016-01-15 00:13:07","timezone":"","type":"user","username":"korrio","phone_code":"","phone":"","verified":0,"birthday":"01/01/1990","gender":"male","avatarIv":"photos/2015/12/ow8uf_30_34173cb38f07f89ddbebc2ac9128303f_100x100.jpg","cover":"themes/grape/images/default-cover.png","is_following":true,"online":true},{"id":225,"about":"","active":1,"avatar_id":41,"cover_id":40,"cover_position":0,"email":"i.am.not.korr@gmail.com","email_verification_key":"d45715ec171c8f72ec1f2cf97ba1d8bf","email_verified":0,"language":"","last_logged":1451455282,"name":"Korr Best Before","password":"039a726ac0aeec3dde33e45387a7d4ac","time":1451451379,"timestamp":"2015-12-31 05:39:55","timezone":"","type":"user","username":"korrbestbefore","phone_code":"","phone":"","verified":3,"birthday":"01/01/1990","gender":"male","avatarIv":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_100x100.jpg","cover":"photos/2015/12/kGGKZ_40_d645920e395fedad7bbbed0eca3fe2e0_cover.jpg","is_following":true,"online":true}]
     */

    private String status;
    /**
     * me : 1
     * favorite : 3
     * group : 3
     * friends : 5
     */

    private CountEntity count;
    /**
     * id : 3
     * about :
     * active : 1
     * avatar_id : 29
     * cover_id : 0
     * cover_position : 0
     * email : anyarimpanus@gmail.com
     * email_verification_key : 4698f890b3f02cb08369b17f5adbabd7
     * email_verified : 0
     * language :
     * last_logged : 1452183369
     * name : Anya Rimpanus
     * password : 039a726ac0aeec3dde33e45387a7d4ac
     * time : 1448944359
     * timestamp : 2016-01-07 11:16:09
     * timezone :
     * type : user
     * username : anya
     * phone_code :
     * phone :
     * verified : 0
     * birthday : 01/01/1990
     * gender : male
     * avatarIv : photos/2015/12/e3FbH_29_6ea9ab1baa0efb9e19094440c317e21b_100x100.jpg
     * cover : themes/grape/images/default-cover.png
     */

    private List<MeEntity> me;
    private List<FavoriteEntity> favorite;
    /**
     * conversationId : 145
     * id : 145
     * conversationType : PRIVATE
     * name : ใบหญ้า
     * createdBy : 3
     * avatarIv : /config_uploads/default/no_group_avatar.jpg
     * createdAt : 2016-01-01T12:58:21.000Z
     * conversationMembers : [{"userId":2,"username":"korrio","name":"Thananon Ngoenthaworn","avatarIv":"photos/2015/12/ow8uf_30_34173cb38f07f89ddbebc2ac9128303f_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":145},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatarIv":"photos/2015/12/e3FbH_29_6ea9ab1baa0efb9e19094440c317e21b_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":145}]
     */

    private List<GroupEntity> group;

    /**
     * id : 273
     * about :
     * active : 1
     * avatar_id : 0
     * cover_id : 0
     * cover_position : 0
     * email : samuel.nikula@gmail.com
     * email_verification_key : a16775b349a38b242c2137d1491a1f12
     * email_verified : 0
     * language :
     * last_logged : 0
     * name : +66917366196
     * password : 039a726ac0aeec3dde33e45387a7d4ac
     * time : 1451818010
     * timestamp : 2016-01-03 06:37:35
     * timezone :
     * type : user
     * username : 917366196
     * phone_code : 66
     * phone : 917366196
     * verified : 2
     * birthday : 01/01/1990
     * gender : male
     * avatarIv : themes/grape/images/default-male-avatarIv.png
     * cover : themes/grape/images/default-cover.png
     * is_following : true
     * online : true
     */

    private List<FriendsEntity> friends;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCount(CountEntity count) {
        this.count = count;
    }

    public void setMe(List<MeEntity> me) {
        this.me = me;
    }

    public void setFavorite(List<FavoriteEntity> favorite) {
        this.favorite = favorite;
    }

    public void setGroup(List<GroupEntity> group) {
        this.group = group;
    }

    public void setFriends(List<FriendsEntity> friends) {
        this.friends = friends;
    }

    public String getStatus() {
        return status;
    }

    public CountEntity getCount() {
        return count;
    }

    public List<MeEntity> getMe() {
        return me;
    }

    public List<FavoriteEntity> getFavorite() {
        return favorite;
    }

    public List<GroupEntity> getGroup() {
        return group;
    }

    public List<FriendsEntity> getFriends() {
        return friends;
    }

    public static class CountEntity {
        private int me;
        private int favorite;
        private int group;
        private int friends;

        public void setMe(int me) {
            this.me = me;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public void setGroup(int group) {
            this.group = group;
        }

        public void setFriends(int friends) {
            this.friends = friends;
        }

        public int getMe() {
            return me;
        }

        public int getFavorite() {
            return favorite;
        }

        public int getGroup() {
            return group;
        }

        public int getFriends() {
            return friends;
        }
    }

    public static class MeEntity extends BaseModel {
        private int id;
        private String about;
        private int active;
        private int avatar_id;
        private int cover_id;
        private int cover_position;
        private String email;
        private String email_verification_key;
        private int email_verified;
        private String language;
        private int last_logged;
        private String name;
        private String password;
        private int time;
        private String timestamp;
        private String timezone;
        private String type;
        private String username;
        private String phone_code;
        private String phone;
        private int verified;
        private String birthday;
        private String gender;
        private String avatar;
        private String cover;
        private int cid;

        public void setId(int id) {
            this.id = id;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public void setAvatar_id(int avatar_id) {
            this.avatar_id = avatar_id;
        }

        public void setCover_id(int cover_id) {
            this.cover_id = cover_id;
        }

        public void setCover_position(int cover_position) {
            this.cover_position = cover_position;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setEmail_verification_key(String email_verification_key) {
            this.email_verification_key = email_verification_key;
        }

        public void setEmail_verified(int email_verified) {
            this.email_verified = email_verified;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public void setLast_logged(int last_logged) {
            this.last_logged = last_logged;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPhone_code(String phone_code) {
            this.phone_code = phone_code;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setVerified(int verified) {
            this.verified = verified;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getId() {
            return id;
        }

        public int getCid() {
            return cid;
        }

        public String getAbout() {
            return about;
        }

        public int getActive() {
            return active;
        }

        public int getAvatar_id() {
            return avatar_id;
        }

        public int getCover_id() {
            return cover_id;
        }

        public int getCover_position() {
            return cover_position;
        }

        public String getEmail() {
            return email;
        }

        public String getEmail_verification_key() {
            return email_verification_key;
        }

        public int getEmail_verified() {
            return email_verified;
        }

        public String getLanguage() {
            return language;
        }

        public int getLast_logged() {
            return last_logged;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        public int getTime() {
            return time;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getTimezone() {
            return timezone;
        }

        public String getType() {
            return type;
        }

        public String getUsername() {
            return username;
        }

        public String getPhone_code() {
            return phone_code;
        }

        public String getPhone() {
            return phone;
        }

        public int getVerified() {
            return verified;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getGender() {
            return gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getCover() {
            return cover;
        }


    }


    public static class GroupEntity {
        private int conversationId;
        private int id;
        private String conversationType;
        private String name;
        private String createdBy;
        private String avatar;
        private String createdAt;
        /**
         * userId : 2
         * username : korrio
         * name : Thananon Ngoenthaworn
         * avatarIv : photos/2015/12/ow8uf_30_34173cb38f07f89ddbebc2ac9128303f_thumb
         * extension : jpg
         * activeFlag : 0
         * adminFlag : 0
         * inviteFlag : 1
         * canInviteFlag : 1
         * inviteAcceptFlag : 0
         * conversationId : 145
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

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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

        public String getCreatedBy() {
            return createdBy;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getCreatedAt() {
            return createdAt;
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

    public static class FriendsEntity {
        private int id;
        private String about;
        private int active;
        private int avatar_id;
        private int cover_id;
        private int cover_position;
        private String email;
        private String email_verification_key;
        private int email_verified;
        private String language;
        private int last_logged;
        private String name;
        private String password;
        private int time;
        private String timestamp;
        private String timezone;
        private String type;
        private String username;
        private String phone_code;
        private String phone;
        private int verified;
        private String birthday;
        private String gender;
        private String avatar;
        private String cover;
        private boolean is_following;
        private boolean online;

        public void setId(int id) {
            this.id = id;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public void setAvatar_id(int avatar_id) {
            this.avatar_id = avatar_id;
        }

        public void setCover_id(int cover_id) {
            this.cover_id = cover_id;
        }

        public void setCover_position(int cover_position) {
            this.cover_position = cover_position;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setEmail_verification_key(String email_verification_key) {
            this.email_verification_key = email_verification_key;
        }

        public void setEmail_verified(int email_verified) {
            this.email_verified = email_verified;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public void setLast_logged(int last_logged) {
            this.last_logged = last_logged;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPhone_code(String phone_code) {
            this.phone_code = phone_code;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setVerified(int verified) {
            this.verified = verified;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }

        public int getId() {
            return id;
        }

        public String getAbout() {
            return about;
        }

        public int getActive() {
            return active;
        }

        public int getAvatar_id() {
            return avatar_id;
        }

        public int getCover_id() {
            return cover_id;
        }

        public int getCover_position() {
            return cover_position;
        }

        public String getEmail() {
            return email;
        }

        public String getEmail_verification_key() {
            return email_verification_key;
        }

        public int getEmail_verified() {
            return email_verified;
        }

        public String getLanguage() {
            return language;
        }

        public int getLast_logged() {
            return last_logged;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        public int getTime() {
            return time;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getTimezone() {
            return timezone;
        }

        public String getType() {
            return type;
        }

        public String getUsername() {
            return username;
        }

        public String getPhone_code() {
            return phone_code;
        }

        public String getPhone() {
            return phone;
        }

        public int getVerified() {
            return verified;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getGender() {
            return gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getCover() {
            return cover;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public boolean isOnline() {
            return online;
        }
    }

    public static class FavoriteEntity {
        private int id;
        private String about;
        private int active;
        private int avatar_id;
        private int cover_id;
        private int cover_position;
        private String email;
        private String email_verification_key;
        private int email_verified;
        private String language;
        private int last_logged;
        private String name;
        private String password;
        private int time;
        private String timestamp;
        private String timezone;
        private String type;
        private String username;
        private String phone_code;
        private String phone;
        private int verified;
        private String birthday;
        private String gender;
        private String avatar;
        private String cover;
        private boolean is_following;
        private boolean online;

        public void setId(int id) {
            this.id = id;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public void setAvatar_id(int avatar_id) {
            this.avatar_id = avatar_id;
        }

        public void setCover_id(int cover_id) {
            this.cover_id = cover_id;
        }

        public void setCover_position(int cover_position) {
            this.cover_position = cover_position;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setEmail_verification_key(String email_verification_key) {
            this.email_verification_key = email_verification_key;
        }

        public void setEmail_verified(int email_verified) {
            this.email_verified = email_verified;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public void setLast_logged(int last_logged) {
            this.last_logged = last_logged;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPhone_code(String phone_code) {
            this.phone_code = phone_code;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setVerified(int verified) {
            this.verified = verified;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }

        public int getId() {
            return id;
        }

        public String getAbout() {
            return about;
        }

        public int getActive() {
            return active;
        }

        public int getAvatar_id() {
            return avatar_id;
        }

        public int getCover_id() {
            return cover_id;
        }

        public int getCover_position() {
            return cover_position;
        }

        public String getEmail() {
            return email;
        }

        public String getEmail_verification_key() {
            return email_verification_key;
        }

        public int getEmail_verified() {
            return email_verified;
        }

        public String getLanguage() {
            return language;
        }

        public int getLast_logged() {
            return last_logged;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        public int getTime() {
            return time;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getTimezone() {
            return timezone;
        }

        public String getType() {
            return type;
        }

        public String getUsername() {
            return username;
        }

        public String getPhone_code() {
            return phone_code;
        }

        public String getPhone() {
            return phone;
        }

        public int getVerified() {
            return verified;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getGender() {
            return gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getCover() {
            return cover;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public boolean isOnline() {
            return online;
        }
    }
}
