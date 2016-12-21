package com.candychat.net.model;

/**
 * Created by root1 on 2/6/16.
 */
public class Noti {


    /**
     * user_id : 2
     * count : {"noti_unread":3,"chat_unread":3,"follow_unread":0}
     */

    private String user_id;
    /**
     * noti_unread : 3
     * chat_unread : 3
     * follow_unread : 0
     */

    private CountEntity count;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setCount(CountEntity count) {
        this.count = count;
    }

    public String getUser_id() {
        return user_id;
    }

    public CountEntity getCount() {
        return count;
    }

    public static class CountEntity {
        private int noti_unread;
        private int chat_unread;
        private int follow_unread;

        public void setNoti_unread(int noti_unread) {
            this.noti_unread = noti_unread;
        }

        public void setChat_unread(int chat_unread) {
            this.chat_unread = chat_unread;
        }

        public void setFollow_unread(int follow_unread) {
            this.follow_unread = follow_unread;
        }

        public int getNoti_unread() {
            return noti_unread;
        }

        public int getChat_unread() {
            return chat_unread;
        }

        public int getFollow_unread() {
            return follow_unread;
        }
    }
}