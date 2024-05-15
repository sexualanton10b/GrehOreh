package com.diana.grehoreh.ui.Model;

public class Product implements IProduct{
    private String name;
    private String category;
    private String country;
    private int price;
    public Product(String name, String category, String country, int price){
        this.name=name;
        this.category=category;
        this.country=country;
        this.price=price;
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
}
