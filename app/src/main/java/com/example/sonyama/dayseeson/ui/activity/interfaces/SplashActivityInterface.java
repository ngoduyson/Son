package com.example.sonyama.dayseeson.ui.activity.interfaces;

import com.example.sonyama.dayseeson.data.remote.DayseeError;

import java.util.List;

/**
 * Created by sonyama on 3/14/16.
 */
public interface SplashActivityInterface {
    void init();
    void checkPlayServices();
    void onLogin();
    void onLoginSuccess();
    void onLoginFail(List<DayseeError> errors);
    void showLoading();
    void hideLoading();
}
