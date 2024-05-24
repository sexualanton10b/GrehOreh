package com.diana.grehoreh.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diana.grehoreh.R;
import com.diana.grehoreh.ui.Model.UserAccount;
import com.diana.grehoreh.ui.Presenter.AccountAdapter;
import com.diana.grehoreh.ui.Presenter.AccountPresenter;
import com.diana.grehoreh.ui.Presenter.BasketAdapter;
import com.diana.grehoreh.ui.Presenter.MyDataBaseHelper;

public class AccountFragment extends Fragment implements AccountContract.View {

    private EditText debetCardEditText;
    private EditText cashEditText;
    private EditText bonusCardEditText;
    private AccountAdapter accountAdapter;
    private AccountContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        debetCardEditText = view.findViewById(R.id.debet_card);
        cashEditText = view.findViewById(R.id.cash);
        bonusCardEditText = view.findViewById(R.id.bonus_card);
        Button updateButton = view.findViewById(R.id.update_button);
        Button clearButton=view.findViewById(R.id.clear_button);
        MyDataBaseHelper myDB = new MyDataBaseHelper(getContext());
        presenter = new AccountPresenter(this, myDB);
        // Initialize RecyclerView and set up adapter
        RecyclerView purchaseRecyclerView = view.findViewById(R.id.purchase);
        purchaseRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        accountAdapter = new AccountAdapter(requireContext());
        purchaseRecyclerView.setAdapter(accountAdapter);
        // Загрузка данных пользователя
        presenter.loadUserData();
        // Обновление данных пользователя
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int debetCard = Integer.parseInt(debetCardEditText.getText().toString());
                    int cash = Integer.parseInt(cashEditText.getText().toString());
                    int bonusCard = Integer.parseInt(bonusCardEditText.getText().toString());
                    presenter.updateUserData(debetCard, cash, bonusCard);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Пожалуйста, введите корректные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearHistory();
            }
        });

        return view;
    }

    @Override
    public void showUserData(UserAccount userAccount) {
        debetCardEditText.setText(String.valueOf(userAccount.getDebetCard()));
        cashEditText.setText(String.valueOf(userAccount.getCash()));
        bonusCardEditText.setText(String.valueOf(userAccount.getBonusCard()));
    }

    @Override
    public void showUpdateSuccess() {
        Toast.makeText(getContext(), "Данные обновлены", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpdateError() {
        Toast.makeText(getContext(), "Ошибка обновления данных", Toast.LENGTH_SHORT).show();
    }
}
