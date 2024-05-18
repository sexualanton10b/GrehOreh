package com.diana.grehoreh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.diana.grehoreh.ui.Model.Product;
import com.diana.grehoreh.ui.Presenter.AddPresenter;
import com.diana.grehoreh.ui.Presenter.MyDataBaseHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddActivity extends AppCompatActivity {
    private ImageView pictureAdd;
    private TextView nameAdd, priceAdd, countryAdd, resultAdd;
    private Button buttonAdd;
    private SeekBar seekBar;
    private AddPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        pictureAdd = findViewById(R.id.pictureAdd);
        nameAdd = findViewById(R.id.nameAdd);
        priceAdd = findViewById(R.id.priceAdd);
        countryAdd = findViewById(R.id.countryAdd);
        resultAdd = findViewById(R.id.resultAdd);
        buttonAdd = findViewById(R.id.buttonAdd);
        seekBar=findViewById(R.id.seekBar);
        // Получение переданного объекта Product
        Product product = (Product) getIntent().getSerializableExtra("product");
        AddPresenter presenter=new AddPresenter(product);
        presenter.getAndSetIntendData(pictureAdd, nameAdd,priceAdd, countryAdd, resultAdd, buttonAdd);
        seekBar.setMax(100000); // 100 кг в тысячных
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double weight = progress / 1000.0; // переводим в килограммы
                resultAdd.setText(String.format("Вес: %.3f кг", weight));
                buttonAdd.setText(String.format("Стоимость %.2f рублей", weight*product.getPrice()));
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

            }
        });
    }


}
