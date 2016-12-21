package com.candychat.net.gcm.event;

/**
 * Created by korrio on 2/15/16.
 */
public class GCMLogin {

    /**
     * error : false
     * user : {"user_id":34,"name":"0917366196","email":"0917366196@gmail.com","created_at":"2016-02-15 20:24:31"}
     */

    private boolean error;
    /**
     * user_id : 34
     * name : 0917366196
     * email : 0917366196@gmail.com
     * created_at : 2016-02-15 20:24:31
     */

    private UserEntity user;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public UserEntity getUser() {
        return user;
    }

    public static class UserEntity {
        private int user_id;
        private String name;
        private String email;
        private String created_at;

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getCreated_at() {
            return created_at;
        }
    }
}
