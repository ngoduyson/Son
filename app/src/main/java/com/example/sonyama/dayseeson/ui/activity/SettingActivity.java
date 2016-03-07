package com.example.sonyama.dayseeson.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.core.AppSettings;
import com.example.sonyama.dayseeson.core.Constants;
import com.example.sonyama.dayseeson.ui.base.BaseActivity;
import com.example.sonyama.dayseeson.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ngoson on 2016/03/03.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.switch_mobile_data)
    SwitchCompat mSwitchMobileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mToolbar.inflateMenu(R.menu.menu_close);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSwitchMobileData.setChecked(AppSettings.getInstance().isMobileData());
        mSwitchMobileData.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                finish();
                overridePendingTransition(R.anim.no_change, R.anim.slide_down);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.linear_channel_list, R.id.linear_recipe_list, R.id.linear_policy, R.id.linear_qa})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_channel_list:
                break;
            case R.id.linear_recipe_list:
                break;
            case R.id.linear_policy:
                Utils.openUrl(this, Constants.POLICY_URL);
                break;
            case R.id.linear_qa:
                Utils.sendEmail(this, getString(R.string.title_email_support),
                                getString(R.string.title_email_subject),
                        getEmailBody());
                break;
            default:
                break;
        }
    }

    private String getEmailBody() {
        StringBuilder emailBody = new StringBuilder("");
        emailBody.append(getString(R.string.title_body) + "¥n");
        emailBody.append("¥n¥n¥n");
        emailBody.append(getString(R.string.title_line) + "¥n");
        emailBody.append(getString(R.string.title_not_remove_below) + "¥n");
        emailBody.append(getString(R.string.title_os) + "¥n");
        emailBody.append(getString(R.string.title_benlly_version)
                + getDeviceName()
                + "/" + Build.VERSION.RELEASE + "¥n");
        emailBody.append(getString(R.string.title_user_id));
        return emailBody.toString();
    }

    private String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(manufacturer);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0)
            return "";
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.switch_mobile_data:
                if (isChecked) {
                    showMobileDataDialog();
                } else {
                    AppSettings.getInstance().setMobileData(false);
                }
                break;
            default:
                break;
        }
    }

    private void showMobileDataDialog() {
        Resources resources = getResources();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(resources.getString(R.string.msg_message));
        builder.setCancelable(true);
        builder.setPositiveButton(resources.getString(R.string.msg_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AppSettings.getInstance().setMobileData(true);
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
