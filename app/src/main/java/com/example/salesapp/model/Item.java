package com.example.salesapp.model;

import android.widget.Button;

public class Item {

    int id;
    String title;
    String price1, price2, img,link,description;

    public Item(int id, String title, String price1, String price2, String img,String link,String description) {
        this.id = id;
        this.title = title;
        this.price1 = price1;
        this.price2 = price2;
        this.img = img;
        this.link = link;
        this.description = description;

    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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


    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }
}
