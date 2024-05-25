package com.diana.grehoreh.ui.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diana.grehoreh.AddActivity;
import com.diana.grehoreh.ui.Model.Product;

public class AddPresenter {
    private Product product;


    public AddPresenter(Product product) {
        this.product = product;
    }
    public void getAndSetIntendData(ImageView pictureAdd, TextView nameAdd, TextView categoryAdd,
                                    TextView priceAdd, TextView countryAdd, TextView resultAdd, Button buttonAdd){
        pictureAdd.setImageResource(product.getPictureResource());
        nameAdd.setText(product.getName());
        categoryAdd.setText(product.getCategory());
        priceAdd.setText(String.valueOf(product.getPrice()));
        countryAdd.setText(product.getCountry());
        resultAdd.setText("Взвесьте товар");
        buttonAdd.setText("Взвесьте товар");
    }
    public void addToBasket(double weight, double sell, Context context) {
        MyDataBaseHelper myDB = new MyDataBaseHelper(context);
        if (myDB.isProductInBasket(product.getName())) {
            myDB.updateProductInBasket(product.getName(), weight, sell);
        } else {
            myDB.addProductToBasket(product, weight, sell);
        }
    }
}
