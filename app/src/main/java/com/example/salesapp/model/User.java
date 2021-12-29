package com.example.salesapp.model;

public class User {
    String name = "Default";
    String message = "Default";
    int rating = 1;


    public User(String name, String message, int rating) {
        this.name = name;
        this.message = message;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
