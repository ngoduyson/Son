package com.example.sonyama.dayseeson.service;

import com.example.sonyama.dayseeson.data.local.Constants;
import com.example.sonyama.dayseeson.data.model.Token;
import com.example.sonyama.dayseeson.util.DayseeServiceUtil;
import com.example.sonyama.dayseeson.util.L;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.gcm.TaskParams;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by sonyama on 3/14/16.
 */
public class RegistrationTokenService extends GcmTaskService {

    int result;

    @Override
    public int onRunTask(TaskParams taskParams) {
        L.i("onRunTask -> ", taskParams.getTag());
        String tag = taskParams.getTag();
        if (tag.equals(Constants.TASK_TAG_REGISTER_TOKEN)) {
            InstanceID instanceID =  InstanceID.getInstance(this);
            String token = null;
            try {
                token = instanceID.getToken(Constants.PROJECT_NUMBER,
                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                L.e("GCM Registration Token : " + token);
                result = sendRegistrationToServer(token);
            } catch (IOException e) {
                e.printStackTrace();
                result = GcmNetworkManager.RESULT_RESCHEDULE;
            }
        } else {
            result = GcmNetworkManager.RESULT_RESCHEDULE;
        }
        return result;
    }

    private int sendRegistrationToServer(String token) {
        Call<Token> call = DayseeServiceUtil.getInstance().registToken(Constants.APP_OS, token);
        try {
            Token mToken = call.execute().body();
            L.e("Registered -> " + mToken);
            if (mToken != null) {
                result = GcmNetworkManager.RESULT_SUCCESS;
            } else {
                result = GcmNetworkManager.RESULT_RESCHEDULE;
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = GcmNetworkManager.RESULT_RESCHEDULE;
        }
        return result;
    }
}
