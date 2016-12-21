package com.candychat.net.gcm.event;

/**
 * Created by korrio on 2/15/16.
 */
public class GCMTokenUpdate {

    /**
     * error : false
     * message : GCM registration ID updated successfully
     */

    private boolean error;
    private String message;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
