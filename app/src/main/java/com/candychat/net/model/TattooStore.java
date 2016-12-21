package com.candychat.net.model;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by root1 on 4/21/15.
 */

@Parcel
public class TattooStore {

    String imageLogo;
    ArrayList<String> title_vdomax;
    String name_sticker;
    String date;
    String price;
    String item_set_name;
    String create_by_name;

    public TattooStore(){

    }

    public TattooStore(String imageLogo, ArrayList<String> title_vdomax, String name_sticker, String date, String price, String item_set_name, String create_by_name) {
        this.imageLogo = imageLogo;
        this.title_vdomax = title_vdomax;
        this.name_sticker = name_sticker;
        this.date = date;
        this.price = price;
        this.item_set_name = item_set_name;
        this.create_by_name = create_by_name;
    }

    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }

    public ArrayList<String> getTitle_vdomax() {
        return title_vdomax;
    }

    public void setTitle_vdomax(ArrayList<String> title_vdomax) {
        this.title_vdomax = title_vdomax;
    }

    public String getName_sticker() {
        return name_sticker;
    }

    public void setName_sticker(String name_sticker) {
        this.name_sticker = name_sticker;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItem_set_name() {
        return item_set_name;
    }

    public void setItem_set_name(String item_set_name) {
        this.item_set_name = item_set_name;
    }

    public String getCreate_by_name() {
        return create_by_name;
    }

    public void setCreate_by_name(String create_by_name) {
        this.create_by_name = create_by_name;
    }
}
