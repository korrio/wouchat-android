package com.candychat.net.model.media;

/**
 * Created by Mac on 7/24/15.
 */
public class UploadAvatarCallback {

    /**
     * status : 1
     * user : {"id":2,"about":"บอกเลย","active":1,"avatar_id":419,"cover_id":0,"cover_position":0,"email":"manual@gmail.com","email_verification_key":"3cea59cdd74660ff55be53543cb1c1e8","email_verified":0,"language":"","last_logged":1455183103,"name":"AAAA","password":"039a726ac0aeec3dde33e45387a7d4ac","time":1448944317,"timestamp":"2016-02-11 16:31:46","timezone":"Pacific/Midway","type":"user","username":"korrio","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","avatarIv":"photos/2016/02/DQgCS_419_7eacb532570ff6858afd2723755ff790_100x100.jpg","cover":"themes/grape/images/default-cover.png","gender":"male","birthday":"01/01/1990"}
     * avatarIv : {"id":419,"active":1,"extension":"jpg","full_path":"http://api.candychat.net/photos/2016/02/DQgCS_419_7eacb532570ff6858afd2723755ff790.jpg","url":"photos/2016/02/DQgCS_419_7eacb532570ff6858afd2723755ff790.jpg","thumb":"http://api.candychat.net/photos/2016/02/DQgCS_419_7eacb532570ff6858afd2723755ff790_thumb.jpg","fileName":"11188151_10152847538956372_4232371261198879686_n.jpg"}
     */

    private String status;
    /**
     * id : 2
     * about : บอกเลย
     * active : 1
     * avatar_id : 419
     * cover_id : 0
     * cover_position : 0
     * email : manual@gmail.com
     * email_verification_key : 3cea59cdd74660ff55be53543cb1c1e8
     * email_verified : 0
     * language :
     * last_logged : 1455183103
     * name : AAAA
     * password : 039a726ac0aeec3dde33e45387a7d4ac
     * time : 1448944317
     * timestamp : 2016-02-11 16:31:46
     * timezone : Pacific/Midway
     * type : user
     * username : korrio
     * phone_code :
     * phone :
     * verified : 0
     * updated_at : -0001-11-30 00:00:00
     * created_at : 0000-00-00 00:00:00
     * avatarIv : photos/2016/02/DQgCS_419_7eacb532570ff6858afd2723755ff790_100x100.jpg
     * cover : themes/grape/images/default-cover.png
     * gender : male
     * birthday : 01/01/1990
     */

    private UserEntity user;
    /**
     * id : 419
     * active : 1
     * extension : jpg
     * full_path : http://api.candychat.net/photos/2016/02/DQgCS_419_7eacb532570ff6858afd2723755ff790.jpg
     * url : photos/2016/02/DQgCS_419_7eacb532570ff6858afd2723755ff790.jpg
     * thumb : http://api.candychat.net/photos/2016/02/DQgCS_419_7eacb532570ff6858afd2723755ff790_thumb.jpg
     * fileName : 11188151_10152847538956372_4232371261198879686_n.jpg
     */

    private AvatarEntity avatar;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setAvatar(AvatarEntity avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public UserEntity getUser() {
        return user;
    }

    public AvatarEntity getAvatar() {
        return avatar;
    }

    public static class UserEntity {
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
        private String updated_at;
        private String created_at;
        private String avatar;
        private String cover;
        private String gender;
        private String birthday;

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

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
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

        public String getUpdated_at() {
            return updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getCover() {
            return cover;
        }

        public String getGender() {
            return gender;
        }

        public String getBirthday() {
            return birthday;
        }
    }

    public static class AvatarEntity {
        private int id;
        private int active;
        private String extension;
        private String full_path;
        private String url;
        private String thumb;
        private String fileName;

        public void setId(int id) {
            this.id = id;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public void setFull_path(String full_path) {
            this.full_path = full_path;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public int getId() {
            return id;
        }

        public int getActive() {
            return active;
        }

        public String getExtension() {
            return extension;
        }

        public String getFull_path() {
            return full_path;
        }

        public String getUrl() {
            return url;
        }

        public String getThumb() {
            return thumb;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
