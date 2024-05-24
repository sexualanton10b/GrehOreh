package com.diana.grehoreh.ui.Model;

import java.io.Serializable;
import java.util.List;

public class Basket implements Serializable { // Класс корзины
    public List<Purchase> purchases;
    public double TotalCost;
    public IPayment Payment;

    public Basket(List<Purchase> purchases) {
        this.purchases = purchases;
        TotalCost=calculateTotalPrice();
    }
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
        this.TotalCost = calculateTotalPrice();
    }

    // Метод для оплаты покупки с указанным методом оплаты
    public boolean pay(UserAccount ua) {
        return Payment.Payment(ua, TotalCost);
    }

    // Метод для вычисления общей стоимости покупок в корзине
    private double calculateTotalPrice() {
        double totalPrice = 0;
        for (Purchase purchase : purchases) {
            totalPrice += purchase.getSell();
        }
        return totalPrice;
    }
    // Геттеры и сеттеры
    public double getTotalCost() {
        return TotalCost;
    }

    public void setPaymentMethod(IPayment paymentMethod) {
        this.Payment = paymentMethod;
    }
}
