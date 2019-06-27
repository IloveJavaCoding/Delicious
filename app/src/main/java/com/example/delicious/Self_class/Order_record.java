package com.example.delicious.Self_class;

import java.io.Serializable;
import java.util.Date;

public class Order_record implements Serializable {
    private String order_number;
    private String username;
    private String shop_name;
    private String iteam_name;
    private double price;
    private int number;
    private String receive_time;
    private int tag;
    private String item_tag;

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public Order_record() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Order_record(String order_number, String username, String shop_name, String iteam_name, double price, int number, String receive_time, int tag, String item_tag) {
        this.order_number = order_number;
        this.username = username;
        this.shop_name = shop_name;
        this.iteam_name = iteam_name;
        this.price = price;
        this.number = number;
        this.receive_time = receive_time;
        this.tag = tag;
        this.item_tag = item_tag;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getItem_tag() {
        return item_tag;
    }

    public void setItem_tag(String item_tag) {
        this.item_tag = item_tag;
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

    public String getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(String receive_time) {
        this.receive_time = receive_time;
    }
}
