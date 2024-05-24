package com.diana.grehoreh.ui.Model;

public class CashPayment implements IPayment {
    @Override
    public boolean Payment(UserAccount ua, double totalPrice) {
        if (ua.getCash() >= totalPrice) {
            ua.setCash(ua.getCash() - (int) totalPrice);
            return true;
        } else {
            return false;
        }
    }
}

