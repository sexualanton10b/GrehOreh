package com.diana.grehoreh.ui.Presenter;

public interface PaymentContract {
    interface View {
        boolean isBonusChecked();
        String getPaymentMethod();
    }

    interface Presenter {
        boolean onPayButtonClick();
    }
}
