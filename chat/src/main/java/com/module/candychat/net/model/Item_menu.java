package com.module.candychat.net.model;

/**
 * Created by root1 on 8/27/16.
 */
public class Item_menu {
    String title;
    String cal;
    int image;
    private boolean isSelected;

    public Item_menu(){

    }

    public Item_menu(String title, String cal, int image) {
        this.title = title;
        this.cal = cal;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
