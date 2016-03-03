package com.example.sonyama.dayseeson.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;

import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ngoson on 2016/03/03.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.switch_mobile_data)
    SwitchCompat mSwitchMobileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.linear_channel_list, R.id.linear_recipe_list, R.id.linear_policy, R.id.linear_qa})
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.linear_channel_list:
                break;
            case R.id.linear_recipe_list:
                break;
            case R.id.linear_policy:
                break;
            case R.id.linear_qa:
                break;
            default:
                break;
        }
    }
}
