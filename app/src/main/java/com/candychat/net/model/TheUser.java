package com.candychat.net.model;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by root1 on 10/20/15.
 */

@Parcel
public class TheUser {

    public int id;

    public String name;

    public String image;

    public String url;

    public boolean selected;

    public String description;
    ArrayList<String> title_candy;
    public TheUser(){

    }

    public TheUser(int id, String name, String image, String url, String description, ArrayList<String> title_candy, boolean selected) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.url = url;
        this.description = description;
        this.title_candy = title_candy;
        this.selected = selected;
    }

    public ArrayList<String> getTitle_candy() {
        return title_candy;
    }

    public void setTitle_candy(ArrayList<String> title_candy) {
        this.title_candy = title_candy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
