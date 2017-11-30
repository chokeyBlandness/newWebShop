package com.cn.model;

public class Merchandise {
    private String id;
    private String name;
    private double price;
    private String pictureSource;

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setPictureSource(String pictureSource) {
        this.pictureSource = pictureSource;
    }
    public boolean isEqualById(String merchandise){
        return this.getId().equals(merchandise);
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getPictureSource() {
        return pictureSource;
    }
}
