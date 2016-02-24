package com.example.sonyama.dayseeson;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.example.sonyama.dayseeson.core.AppSettings;
import com.example.sonyama.dayseeson.util.DayseeServiceUtil;

import io.fabric.sdk.android.Fabric;

/**
 * Created by sonyama on 3/3/16.
 */
public class DayseeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        AppSettings.init(this);
        DayseeServiceUtil.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}
