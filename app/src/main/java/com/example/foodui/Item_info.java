package com.example.foodui;

public class Item_info {
    private String shop_name;
    private String iteam_name;
    private double price;

    public Item_info() {
    }

    public Item_info(String shop_name, String iteam_name, double price) {
        this.shop_name = shop_name;
        this.iteam_name = iteam_name;
        this.price = price;
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
