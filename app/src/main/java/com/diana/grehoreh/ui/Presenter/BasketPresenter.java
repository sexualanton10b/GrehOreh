package com.diana.grehoreh.ui.Presenter;

import android.content.Context;

import com.diana.grehoreh.ui.Model.Purchase;

import java.util.List;

public class BasketPresenter {
    private Context context;
    private BasketView view;
    private MyDataBaseHelper myDB;

    public BasketPresenter(BasketView view, Context context) {
        this.view = view;
        this.context = context;
        this.myDB = new MyDataBaseHelper(context);
    }

    public void loadBasket() {
        List<Purchase> purchases = myDB.getAllPurchases();
        view.displayBasket(purchases);
    }

    public void updateData() {
        List<Purchase> purchases = myDB.getAllPurchases();
        view.displayBasket(purchases);
    }

    public interface BasketView {
        void displayBasket(List<Purchase> purchases);
    }
}
