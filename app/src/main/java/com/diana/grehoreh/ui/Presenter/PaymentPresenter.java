package com.diana.grehoreh.ui.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.diana.grehoreh.ui.Model.Basket;
import com.diana.grehoreh.ui.Model.BonusCardPayment;
import com.diana.grehoreh.ui.Model.BonusCashPayment;
import com.diana.grehoreh.ui.Model.CardPayment;
import com.diana.grehoreh.ui.Model.CashPayment;
import com.diana.grehoreh.ui.Model.UserAccount;

public class PaymentPresenter implements PaymentContract.Presenter {

    private final PaymentContract.View view;
    Context context;
    private UserAccount ua;
    private MyDataBaseHelper myDB;
    private Basket basket;

    public PaymentPresenter(PaymentContract.View view, Context context) {
        this.view = view;
        this.context=context;
        this.myDB = new MyDataBaseHelper(context);
        this.ua=myDB.getUserAccount();
        this.basket=new Basket(myDB.getAllPurchases());
    }

    @Override
    public boolean onPayButtonClick() {
        // Example logic for handling payment button click
        double price = basket.getTotalCost();
        boolean isBonusChecked = view.isBonusChecked();
        String paymentMethod = view.getPaymentMethod();
        if (isBonusChecked){
            if (paymentMethod=="Card"){
                basket.setPaymentMethod(new BonusCardPayment());
            }
            else basket.setPaymentMethod(new BonusCashPayment());
        }
        else {
            if (paymentMethod=="Card"){
                basket.setPaymentMethod(new CardPayment());
            }
            else basket.setPaymentMethod(new CashPayment());
        }
        if (basket.pay(ua)){
            ua.addBonus((int)(basket.getTotalCost()*0.02)); // начисляем бонусы
            myDB.updateUserAccount(ua);
            myDB.savePurchasesToMyPurchases(basket);
            myDB.clearBasket();
            return true;
        }
        else {
            Toast.makeText(context, "Недостаточно средств", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public int Bonus(){ return ua.getBonusCard();}
    public int Card(){ return ua.getDebetCard();}
    public int Cash(){ return ua.getCash();}
    public String TotalPriceView(){
        return String.format("%.2f руб", basket.getTotalCost());
    }
}

