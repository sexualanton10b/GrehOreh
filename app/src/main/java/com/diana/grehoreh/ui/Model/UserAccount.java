package com.diana.grehoreh.ui.Model;

public class UserAccount {
    private int debet_card;
    private int cash;
    private int bonus_card;

    public UserAccount(int debet_card, int cash, int bonus_card) {
        this.debet_card = debet_card;
        this.cash = cash;
        this.bonus_card = bonus_card;
    }

    public int getDebetCard() {
        return debet_card;
    }

    public void setDebetCard(int debet_card) {
        this.debet_card = debet_card;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getBonusCard() {
        return bonus_card;
    }

    public void setBonusCard(int bonus_card) {
        this.bonus_card = bonus_card;
    }
}
