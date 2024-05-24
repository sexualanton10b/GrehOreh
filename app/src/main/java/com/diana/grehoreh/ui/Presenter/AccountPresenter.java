package com.diana.grehoreh.ui.Presenter;

import com.diana.grehoreh.ui.Model.UserAccount;
import com.diana.grehoreh.ui.Presenter.MyDataBaseHelper;
import com.diana.grehoreh.ui.account.AccountContract;

public class AccountPresenter implements AccountContract.Presenter {

    private final AccountContract.View view;
    private final MyDataBaseHelper myDB;

    public AccountPresenter(AccountContract.View view, MyDataBaseHelper myDB) {
        this.view = view;
        this.myDB = myDB;
    }

    @Override
    public void loadUserData() {
        UserAccount userAccount = myDB.getUserAccount();
        if (userAccount != null) {
            view.showUserData(userAccount);
        }
    }

    @Override
    public void updateUserData(int debetCard, int cash, int bonusCard) {
        UserAccount userAccount = new UserAccount(debetCard, cash, bonusCard);
        boolean isUpdated = myDB.updateUserAccount(userAccount);
        if (isUpdated) {
            view.showUpdateSuccess();
        } else {
            view.showUpdateError();
        }
    }
}