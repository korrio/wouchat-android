package com.candychat.net.model.response;

/**
 * Created by root1 on 12/9/15.
 */
public class AddUserResponse {


    /**
     * status : 1
     * message : Add friend success
     * user_id : 7
     * me_user_id : 1
     * is_following : true
     */

    private String status;
    private String message;
    private String user_id;
    private int me_user_id;
    private boolean is_following;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setMe_user_id(int me_user_id) {
        this.me_user_id = me_user_id;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getMe_user_id() {
        return me_user_id;
    }

    public boolean isIs_following() {
        return is_following;
    }
}
