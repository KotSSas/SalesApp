package com.example.salesapp.model;

public class Category {

    int id, color_ind;

    public Category(int id, int color_ind) {
        this.id = id;
        this.color_ind = color_ind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColor_ind() {
        return color_ind;
    }

    public void setColor_ind(int color_ind) {
        this.color_ind = color_ind;
    }
}
