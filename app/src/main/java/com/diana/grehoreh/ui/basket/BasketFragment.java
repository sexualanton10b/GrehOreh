package com.diana.grehoreh.ui.basket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diana.grehoreh.PaymentActivity;
import com.diana.grehoreh.R;
import com.diana.grehoreh.ui.Model.Purchase;
import com.diana.grehoreh.ui.Presenter.BasketAdapter;
import com.diana.grehoreh.ui.Presenter.BasketPresenter;

import java.util.List;

public class BasketFragment extends Fragment implements BasketPresenter.BasketView {

    private static final int REQUEST_CODE_ACTIVITY = 123;

    private BasketAdapter basketAdapter;
    private BasketPresenter basketPresenter;
    private Button payButton;
    private TextView emptyBasketText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_basket, container, false);

        basketPresenter = new BasketPresenter(this, requireContext());

        RecyclerView recyclerView = root.findViewById(R.id.basket);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        basketAdapter = new BasketAdapter(requireContext());
        recyclerView.setAdapter(basketAdapter);
        basketPresenter.loadBasket();
        payButton = root.findViewById(R.id.pay_button);
        emptyBasketText = root.findViewById(R.id.empty_basket_text);

        // Проверка состояния корзины
        if (basketPresenter.EmptyBasket()) {
            // Если корзина пуста, скрываем кнопку оплаты и делаем видимым текст "Корзина пуста"
            payButton.setVisibility(View.GONE);
            emptyBasketText.setVisibility(View.VISIBLE);
        } else {
            // Если корзина не пуста, оставляем кнопку оплаты видимой и скрываем текст "Корзина пуста"
            payButton.setVisibility(View.VISIBLE);
            emptyBasketText.setVisibility(View.GONE);
        }

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), PaymentActivity.class);
                ((Activity) requireContext()).startActivityForResult(intent, REQUEST_CODE_ACTIVITY);
            }
        });

        return root;
    }

    @Override
    public void displayBasket(List<Purchase> purchases) {
        basketAdapter.setData(purchases);
    }

    public void updateData(){
        basketPresenter.updateData();
    }
}
