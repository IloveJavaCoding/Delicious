package com.example.delicious.Self_class;

import java.io.Serializable;

public class Item_info implements Serializable {
    private String shop_name;
    private String iteam_name;
    private double price;
    private String tag;

    public Item_info() {
    }

    public Item_info(String shop_name, String iteam_name, double price, String tag) {
        this.shop_name = shop_name;
        this.iteam_name = iteam_name;
        this.price = price;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getIteam_name() {
        return iteam_name;
    }

    public void setIteam_name(String iteam_name) {
        this.iteam_name = iteam_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
