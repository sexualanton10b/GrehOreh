package com.diana.grehoreh.ui.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.diana.grehoreh.AddActivity;
import com.diana.grehoreh.ui.Model.Product;

public class AddPresenter {
    private Product product;


    public AddPresenter(Product product) {
        this.product = product;
    }
    public void getAndSetIntendData(ImageView pictureAdd, TextView nameAdd,
                                    TextView priceAdd, TextView countryAdd, TextView resultAdd, Button buttonAdd){
        pictureAdd.setImageResource(product.getPictureResource());
        nameAdd.setText(product.getName());
        priceAdd.setText(String.valueOf(product.getPrice()));
        countryAdd.setText(product.getCountry());
        resultAdd.setText("Взвесьте товар");
        buttonAdd.setText("Взвесьте товар");
    }
    public void addToBasket(double weight, double sell, Context context){
        MyDataBaseHelper myDB=new MyDataBaseHelper(context);

    }

}
