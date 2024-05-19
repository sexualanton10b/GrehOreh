package com.diana.grehoreh.ui.Presenter;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.diana.grehoreh.ui.Model.Product;
import com.diana.grehoreh.ui.Model.Purchase;

public class EditPresenter{
    private Purchase purchase;
        public EditPresenter(Purchase purchase) {
            this.purchase = purchase;
        }
        public void getAndSetIntendData(ImageView pictureAdd, TextView nameAdd, TextView categoryAdd,
                                        TextView priceAdd, TextView countryAdd, TextView resultAdd, Button buttonAdd, SeekBar seekbar){
            pictureAdd.setImageResource(purchase.getPictureResource());
            nameAdd.setText(purchase.getName());
            categoryAdd.setText(purchase.getCategory());
            priceAdd.setText(String.valueOf(purchase.getPrice()));
            countryAdd.setText(purchase.getCountry());
            resultAdd.setText(String.format("Вес: %.2f кг", purchase.getWeight()));
            seekbar.setProgress((int) (purchase.getWeight()*1000));
            buttonAdd.setText(String.format("Стоимость %.2f руб", purchase.getSell()));
        }
        public void addToBasket(double weight, double sell, Context context) {
            MyDataBaseHelper myDB = new MyDataBaseHelper(context);
            if (myDB.isProductInBasket(purchase.getName())) {
                myDB.updateProductInBasket(purchase.getName(), weight, sell);
            } else {
                myDB.addProductToBasket(purchase, weight, sell);
            }
        }
}