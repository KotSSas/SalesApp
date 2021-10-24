package com.example.salesapp.model;

public class Category {

    int id;
    String title, img;

    public Category(int id, String title, String img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
