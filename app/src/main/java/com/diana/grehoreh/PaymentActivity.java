package com.diana.grehoreh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.diana.grehoreh.ui.Presenter.PaymentContract;
import com.diana.grehoreh.ui.Presenter.PaymentPresenter;

public class PaymentActivity extends AppCompatActivity implements PaymentContract.View {

    private TextView totalPriceView;
    private TextView pointsTextView;
    private Switch bonusSwitch;
    private RadioGroup paymentRadioGroup;
    private RadioButton cardRadioButton;
    private RadioButton cashRadioButton;
    private Button payButton;

    private PaymentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_basket);
        // Initialize views
        totalPriceView = findViewById(R.id.totalPriceView);
        pointsTextView = findViewById(R.id.pointsTextView);
        bonusSwitch = findViewById(R.id.bonusSwitch);
        paymentRadioGroup = findViewById(R.id.paymentRadioGroup);
        cardRadioButton = findViewById(R.id.cardRadioButton);
        cashRadioButton = findViewById(R.id.cashRadioButton);
        payButton = findViewById(R.id.payButton);
        // Initialize presenter
        presenter = new PaymentPresenter(this, getApplicationContext());
        totalPriceView.setText(presenter.TotalPriceView());
        // Set listeners
        payButton.setOnClickListener(view -> {
            if (presenter.onPayButtonClick()){
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent); // Установка результата
                finish(); // Завершение активити
            }
        });

    }
    @Override
    public boolean isBonusChecked() {
        return bonusSwitch.isChecked();
    }

    @Override
    public String getPaymentMethod() {
        return paymentRadioGroup.getCheckedRadioButtonId() == R.id.cardRadioButton ? "Card" : "Cash";
    }
}