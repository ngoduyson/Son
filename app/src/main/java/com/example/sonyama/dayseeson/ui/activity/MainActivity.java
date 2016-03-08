package com.example.sonyama.dayseeson.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.data.local.AppSettings;
import com.example.sonyama.dayseeson.data.remote.DayseeCallback;
import com.example.sonyama.dayseeson.data.remote.DayseeError;
import com.example.sonyama.dayseeson.data.model.User;
import com.example.sonyama.dayseeson.ui.base.BaseActivity;
import com.example.sonyama.dayseeson.util.DayseeServiceUtil;
import com.example.sonyama.dayseeson.util.L;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sonyama on 3/3/16.
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up, R.anim.no_change);
            }
        });

        // Test API Create User
        if (AppSettings.getInstance().getUser().isLive()) {
            L.d("User live and public id is" + AppSettings.getInstance().getUser().getPublicId());
        } else {
            DayseeServiceUtil.getInstance()
                    .login("android-id-" + new Random().nextInt(999999))
                    .enqueue(new DayseeCallback<User>() {
                        @Override
                        public void onResponse(User data) {
                            super.onResponse(data);
                            L.d("User created ->" + data);
                            AppSettings.getInstance().setUser(data);
                        }

                        @Override
                        public void onFailure(List<DayseeError> errors) {
                            super.onFailure(errors);
                            for(DayseeError error: errors) {
                                L.d("onFailure ->" + error);
                            }
                        }
            });
        }
    }

}
