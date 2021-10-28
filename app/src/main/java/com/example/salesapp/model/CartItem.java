package com.example.salesapp.model;

public class CartItem {

    int id;
    String title, new_price;

    public CartItem(int id, String title, String new_price) {
        this.id = id;
        this.title = title;
        this.new_price = new_price;
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

    public String getNew_price() {
        return new_price;
    }

    public void setNew_price(String new_price) {
        this.new_price = new_price;
    }
}
