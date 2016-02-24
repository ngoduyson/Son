package com.example.sonyama.dayseeson.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sonyama.dayseeson.util.L;

/**
 * Created by sonyama on 3/3/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d(getLocalClassName(), "--onCreate--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d(getCallingPackage(), "--onDestroy--");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}
