package com.example.sonyama.dayseeson.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.ui.activity.interfaces.MainActivityInterface;
import com.example.sonyama.dayseeson.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sonyama on 3/3/16.
 */
public class MainActivity extends BaseActivity implements MainActivityInterface {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : for testing ChannelActivity
                startActivity(new Intent(MainActivity.this, ChannelActivity.class));
                overridePendingTransition(R.anim.slide_up, R.anim.no_change);
            }
        });
    }

    @Override
    public void loadData() {
    }

    @Override
    public void fillData() {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }
}
