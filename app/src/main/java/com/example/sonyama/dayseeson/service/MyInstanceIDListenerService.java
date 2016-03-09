package com.example.sonyama.dayseeson.service;

import com.example.sonyama.dayseeson.util.Utils;
import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by sonyama on 3/14/16.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Utils.registerGcm(this);
    }
}
