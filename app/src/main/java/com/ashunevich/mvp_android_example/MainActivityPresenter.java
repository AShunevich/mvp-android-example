package com.ashunevich.mvp_android_example;

import android.view.View;

public class MainActivityPresenter {
    private User user;
    private iView view;

    public MainActivityPresenter(iView view) {
        this.user = new User();
        this.view = view;
    }
    void updateName(String fullName){
        user.setFullName(fullName);
        view.updateUserInfoTextView(user.toString());
    }

    void updateTelephone(String telephone){
        user.setTelephone (telephone);
        view.updateUserInfoTextView(user.toString());

    }

}
