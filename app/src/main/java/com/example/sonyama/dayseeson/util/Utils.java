package com.example.sonyama.dayseeson.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.data.local.Constants;
import com.example.sonyama.dayseeson.service.RegistrationTokenService;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.OneoffTask;
import com.google.android.gms.gcm.Task;

import java.util.UUID;

/**
 * Created by sonyama on 3/7/16.
 */
public class Utils {

    public static void openUrl(Context context, String url) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch(ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmail(Context context, String email, String subject, String body) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, body);
        try {
            context.startActivity(Intent.createChooser(i, context.getString(R.string.title_send_email)));
        } catch(ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void registerGcm(Context context) {
        GcmNetworkManager gcmNetworkManager = GcmNetworkManager.getInstance(context);
        OneoffTask task = new OneoffTask.Builder()
                .setService(RegistrationTokenService.class)
                .setTag(Constants.TASK_TAG_REGISTER_TOKEN)
                .setExecutionWindow(0, 1)
                .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                .build();
        gcmNetworkManager.schedule(task);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString() + System.currentTimeMillis();
    }
}
