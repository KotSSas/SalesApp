package com.example.salesapp.model;

import android.media.Image;

public class Shop {

    int id, category_s;
    String img, title;

    public Shop(int id, int category_s, String img, String title) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.category_s = category_s;
    }

    public int getCategory_s() {
        return category_s;
    }

    public void setCategory_s(int category_s) {
        this.category_s = category_s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
