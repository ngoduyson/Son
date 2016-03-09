package com.example.sonyama.dayseeson.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.data.local.AppSettings;
import com.example.sonyama.dayseeson.data.local.Constants;
import com.example.sonyama.dayseeson.data.model.User;
import com.example.sonyama.dayseeson.data.remote.DayseeCallback;
import com.example.sonyama.dayseeson.data.remote.DayseeError;
import com.example.sonyama.dayseeson.ui.activity.interfaces.SplashActivityInterface;
import com.example.sonyama.dayseeson.ui.base.BaseActivity;
import com.example.sonyama.dayseeson.util.DayseeServiceUtil;
import com.example.sonyama.dayseeson.util.L;
import com.example.sonyama.dayseeson.util.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by sonyama on 3/14/16.
 */
public class SplashActivity extends BaseActivity implements SplashActivityInterface {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    @Bind(R.id.pdLoading)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        checkPlayServices();
    }

    @Override
    public void checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                showGooglePlayServiceNotSupportDialog();
            }
        } else {
            onLogin();
        }
    }

    @Override
    public void onLogin() {
        if (AppSettings.getInstance().getUser().isLive()) {
            L.d("User live and public id is " + AppSettings.getInstance().getUser().getPublicId());
            onLoginSuccess();
        } else {
            showLoading();
            DayseeServiceUtil.getInstance()
                    .login(Utils.getUUID())
                    .enqueue(new DayseeCallback<User>() {
                        @Override
                        public void onResponse(User data) {
                            super.onResponse(data);
                            L.d("User Created -> " + data);
                            AppSettings.getInstance().setUser(data);
                            onLoginSuccess();
                        }

                        @Override
                        public void onFailure(List<DayseeError> errors) {
                            super.onFailure(errors);
                            StringBuilder errorMessage = new StringBuilder("");
                            for (DayseeError error : errors) {
                                L.d("onFailure -> " + error);
                                if (!TextUtils.isEmpty(error.getMessage())) {
                                    errorMessage.append(error.getMessage() + "¥n");
                                }
                            }
                            onLoginFail(errorMessage.toString());
                        }

                    });
        }
    }

    @Override
    public void onLoginSuccess() {
        hideLoading();
        Utils.registerGcm(this);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFail(String error) {
        hideLoading();
        showErrorDialog(error);
    }

    @Override
    public void showLoading() {
        if (mProgressBar != null && mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressBar != null && mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_SERVICES_RESOLUTION_REQUEST) {
            if (resultCode == RESULT_OK) {
                onLogin();
            } else {
                showGooglePlayServiceNotSupportDialog();
            }
        }
    }

    private void showGooglePlayServiceNotSupportDialog() {
        Resources resources = getResources();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(resources.getString(R.string.msg_google_play_service_not_support));
        builder.setCancelable(true);
        builder.setPositiveButton(resources.getString(R.string.msg_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Utils.openUrl(SplashActivity.this, Constants.URL_GOOGLE_PLAY_SERVICES);
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showErrorDialog(String errorMessage) {
        Resources resources = getResources();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(errorMessage);
        builder.setCancelable(true);
        builder.setPositiveButton(resources.getString(R.string.msg_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
