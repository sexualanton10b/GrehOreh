package com.diana.grehoreh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.diana.grehoreh.ui.Model.Purchase;
import com.diana.grehoreh.ui.Presenter.EditPresenter;

public class EditActivity extends AppCompatActivity {
    private ImageView pictureAdd;
    private TextView nameAdd, priceAdd, countryAdd, resultAdd, categoryAdd;
    private Button buttonAdd;
    private SeekBar seekBar;
    private EditPresenter presenter;
    double weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        pictureAdd = findViewById(R.id.pictureAdd);
        nameAdd = findViewById(R.id.nameAdd);
        priceAdd = findViewById(R.id.priceAdd);
        countryAdd = findViewById(R.id.countryAdd);
        resultAdd = findViewById(R.id.resultAdd);
        categoryAdd=findViewById(R.id.categotyAdd);
        buttonAdd = findViewById(R.id.buttonAdd);
        seekBar=findViewById(R.id.seekBar);
        // Получение переданного объекта Product
        Purchase purchase = (Purchase) getIntent().getSerializableExtra("purchase");
        EditPresenter presenter=new EditPresenter(purchase);
        seekBar.setMax(100000); // 100 кг в тысячных
        presenter.getAndSetIntendData(pictureAdd, nameAdd,categoryAdd, priceAdd, countryAdd, resultAdd, buttonAdd, seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weight = progress / 1000.0; // переводим в килограммы
                resultAdd.setText(String.format("Вес: %.3f кг", weight));
                buttonAdd.setText(String.format("Стоимость %.2f рублей", weight*purchase.getPrice()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToBasket(weight, weight*purchase.getPrice(), EditActivity.this);
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent); // Установка результата
                finish(); // Завершение активити
            }
        });
    }

}
