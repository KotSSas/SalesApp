package com.example.salesapp.model;

import android.media.Image;

public class Shop {

    int id, category_s;
    String img, img_l, title;

    public Shop(int id, int category_s, String img, String img_l, String title) {
        this.id = id;
        this.category_s = category_s;
        this.img = img;
        this.img_l = img_l;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_s() {
        return category_s;
    }

    public void setCategory_s(int category_s) {
        this.category_s = category_s;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg_l() {
        return img_l;
    }

    public void setImg_l(String img_l) {
        this.img_l = img_l;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
