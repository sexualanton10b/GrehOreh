package com.diana.grehoreh.ui.Model;

public class Purchase extends Product {
    private double weight; // вес
    private double sell;   // стоимость
    private String date;

    // Конструктор для вызова конструктора родительского класса
    public Purchase(String name, String category, String country, int price, int pictureResource) {
        super(name, category, country, price, pictureResource);
    }

    // Конструктор для вызова конструктора родительского класса и установки дополнительных полей
    public Purchase(String name, String category, String country, int price, int pictureResource, double weight, double sell) {
        super(name, category, country, price, pictureResource);
        this.weight = weight;
        this.sell = sell;
    }
    public Purchase(String name, String category, String country, int price, int pictureResource, double weight, double sell, String date){
        super(name, category, country, price, pictureResource);
        this.weight = weight;
        this.sell = sell;
        this.date=date;
    }

    // Геттеры и сеттеры для новых полей
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }
    public String getDate(){return date;}
}
