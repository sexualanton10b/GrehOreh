package com.diana.grehoreh.ui.Model;

public class CardPayment implements IPayment{
    public boolean Payment(UserAccount ua, double totalPrice){
        if (ua.getDebetCard() >= totalPrice) {
            ua.setDebetCard(ua.getDebetCard() - (int) totalPrice);
            return true;
        } else {
            return false;
        }
    }
}
