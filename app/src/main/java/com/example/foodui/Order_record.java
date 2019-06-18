package com.example.foodui;

import java.util.Date;

public class Order_record {
    private String shop_name;
    private String iteam_name;
    private double price;
    private int number;
    private Date receive_time;

    public Order_record() {
    }

    public Order_record(String shop_name, String iteam_name, double price, int number, Date receive_time) {
        this.shop_name = shop_name;
        this.iteam_name = iteam_name;
        this.price = price;
        this.number = number;
        this.receive_time = receive_time;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(Date receive_time) {
        this.receive_time = receive_time;
    }
}
