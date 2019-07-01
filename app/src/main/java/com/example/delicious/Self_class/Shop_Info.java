package com.example.delicious.Self_class;

import java.io.Serializable;

public class Shop_Info implements Serializable {
    private String id;
    private String shop_name;
    private String genre;
    private double rate;
    private String open_time;
    private String logo_link;
    private String bg_link;
    private String genre_link;

    public Shop_Info() {
    }

    public Shop_Info(String id, String shop_name, String genre, float rate, String open_time, String logo_link,String bg_link, String genre_link) {
        this.id = id;
        this.shop_name = shop_name;
        this.genre = genre;
        this.rate = rate;
        this.open_time = open_time;
        this.logo_link = logo_link;
        this.bg_link = bg_link;
        this.genre_link = genre_link;
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

    public String getLogo_link() {
        return logo_link;
    }

    public void setLogo_link(String logo_link) {
        this.logo_link = logo_link;
    }

    public String getBg_link() {
        return bg_link;
    }

    public void setBg_link(String bg_link) {
        this.bg_link = bg_link;
    }

    public String getGenre_link() {
        return genre_link;
    }

    public void setGenre_link(String genre_link) {
        this.genre_link = genre_link;
    }
}
