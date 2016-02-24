package com.example.sonyama.dayseeson.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.example.sonyama.dayseeson.data.model.User;
import com.google.gson.Gson;

/**
 * Created by sonyama on 3/2/16.
 */
public class AppSettings {
    private static AppSettings sAppSettings;
    private static SharedPreferences sPreferences;
    private static final String PREF_USER = "user";
    private User mUser;

    public AppSettings(Context context) {
        sPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        loadData();
    }

    public static void init(Context context) {
        if (sAppSettings == null) {
            sAppSettings = new AppSettings(context);
        }
    }

    public static AppSettings getInstance() {
        if (sAppSettings == null) {
            try {
                throw new Exception("Before, please call init() in Application");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sAppSettings;
    }

    private void loadData() {
        String jsonUser = sPreferences.getString(PREF_USER, "");
        if (!TextUtils.isEmpty(jsonUser))
            this.mUser = new Gson().fromJson(jsonUser, User.class);
    }

    public User getUser() {
        return mUser == null ? new User() : mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
        sPreferences.edit().putString(PREF_USER, new Gson().toJson(user)).apply();
    }
}
