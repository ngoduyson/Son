package com.example.sonyama.dayseeson.ui.activity.interfaces;

/**
 * Created by sonyama on 3/14/16.
 */
public interface SplashActivityInterface {
    void init();
    void checkPlayServices();
    void onLogin();
    void onLoginSuccess();
    void onLoginFail(String error);
    void showLoading();
    void hideLoading();
}
