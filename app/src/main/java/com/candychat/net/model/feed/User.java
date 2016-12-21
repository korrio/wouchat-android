package com.candychat.net.model.feed;

/**
 * Created by root1 on 10/24/15.
 */
public class User {

    String name1;
    String name2;

    public User(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}
