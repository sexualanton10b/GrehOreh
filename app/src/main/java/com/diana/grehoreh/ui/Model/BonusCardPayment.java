package com.diana.grehoreh.ui.Model;

public class BonusCardPayment implements IPayment {
    @Override
    public boolean Payment(UserAccount ua, double totalPrice) {
        int totalBonus = ua.getBonusCard() + ua.getDebetCard();
        if (totalBonus >= totalPrice) {
            int bonusToUse = Math.min(ua.getBonusCard(), (int) totalPrice);
            int cardToUse = (int) totalPrice - bonusToUse;
            ua.setBonusCard(ua.getBonusCard() - bonusToUse);
            ua.setDebetCard(ua.getDebetCard() - cardToUse);
            return true;
        } else {
            return false;
        }
    }
}
