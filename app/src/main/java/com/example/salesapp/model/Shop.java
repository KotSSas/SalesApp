package com.example.salesapp.model;

import android.media.Image;

public class Shop {

    int id, category_s;
    String img, title, category, work_time, site ;

    public Shop(int id, int category_s, String img, String title, String category, String work_time, String site) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.category = category;
        this.work_time = work_time;
        this.site = site;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
