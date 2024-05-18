package com.diana.grehoreh.ui.Model;

import java.io.Serializable;

public class Product implements IProduct, Serializable {
    private String name;
    private String category;
    private String country;
    private int price;
    private int pictureResource;
    public Product(String name, String category, String country, int price, int pictureResource){
        this.name=name;
        this.category=category;
        this.country=country;
        this.price=price;
        this.pictureResource=pictureResource;
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public int getPictureResource(){
        return pictureResource;
    }
}
