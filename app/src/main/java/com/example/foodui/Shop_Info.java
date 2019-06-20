package com.example.foodui;

import java.io.Serializable;

public class Shop_Info implements Serializable {
    private String id;
    private String shop_name;
    private String genre;
    private double rate;
    private String open_time;

    public Shop_Info() {
    }

    public Shop_Info(String id, String shop_name, String genre, double rate, String open_time) {
        this.id = id;
        this.shop_name = shop_name;
        this.genre = genre;
        this.rate = rate;
        this.open_time = open_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }
}
