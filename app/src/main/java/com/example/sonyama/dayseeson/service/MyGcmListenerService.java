package com.example.sonyama.dayseeson.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.ui.activity.MainActivity;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by sonyama on 3/11/16.
 */
public class MyGcmListenerService extends GcmListenerService {
    /**
     * Called when message is received
     * @param from SenderID iof the sender
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        StringBuilder message = new StringBuilder("");
        for (String key : data.keySet()) {
            String line = String.format("%s=%s", key, data.getString(key));
            message.append(line);
        }
        sendNofitication(message.toString());
    }

    /**
     * Create and show a simple notification containing the received GCM message
     * @param message GCM message received
     */
    private void sendNofitication(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0 /* Request Code */,
                intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("GCM Message")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}

