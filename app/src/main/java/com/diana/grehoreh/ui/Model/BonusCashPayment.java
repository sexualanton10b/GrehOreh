package com.diana.grehoreh.ui.Model;

public class BonusCashPayment implements IPayment {
    @Override
    public boolean Payment(UserAccount ua, double totalPrice) {
        int totalBonus = ua.getBonusCard() + ua.getCash();
        if (totalBonus >= totalPrice) {
            int bonusToUse = Math.min(ua.getBonusCard(), (int) totalPrice);
            int cashToUse = (int) totalPrice - bonusToUse;

            ua.setBonusCard(ua.getBonusCard() - bonusToUse);
            ua.setCash(ua.getCash() - cashToUse);
            return true;
        } else {
            return false;
        }
    }
}
