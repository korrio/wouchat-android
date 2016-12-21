package com.module.candychat.net.model;

/**
 * Created by root1 on 12/1/15.
 */
public class HistoryFont {

    int color;
    String namFont;

    public HistoryFont(){

    }

    public HistoryFont(int color, String namFont) {
        this.color = color;
        this.namFont = namFont;
    }

    public String getNamFont() {
        return namFont;
    }

    public void setNamFont(String namFont) {
        this.namFont = namFont;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
