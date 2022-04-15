package com.truongthanhtrung.tuan05;

public class Product {
    private String name;
    private String price;
    private String desc;
    private String color;

    public Product(String name, String price, String desc, String color) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
