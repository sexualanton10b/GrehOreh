package com.diana.grehoreh.ui.account;

import com.diana.grehoreh.ui.Model.UserAccount;

public interface AccountContract {

    interface View {
        void showUserData(UserAccount userAccount);
        void showUpdateSuccess();
        void showUpdateError();
    }

    interface Presenter {
        void loadUserData();
        void updateUserData(int debetCard, int cash, int bonusCard);
    }
}
