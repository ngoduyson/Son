package com.example.sonyama.dayseeson.util;


import android.content.Context;

import com.example.sonyama.dayseeson.BuildConfig;
import com.example.sonyama.dayseeson.data.local.AppSettings;
import com.example.sonyama.dayseeson.data.remote.DayseeService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by sonyama on 3/1/16.
 */
public class DayseeServiceUtil {

    private static DayseeService sDayseeService;

    public static void init(Context context) {
        if (sDayseeService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(getGsonDefault()))
                    .client(getHttpClientDefault(context))
                    .build();
            sDayseeService = retrofit.create(DayseeService.class);
        }
    }

    public static DayseeService getInstance() {
        if(sDayseeService == null) {
            try {
                throw new Exception("Before, please call init() in Application");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sDayseeService;
    }

    private static OkHttpClient getHttpClientDefault(Context context) {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = HttpClientUtil.getOkHttpClient(context.getApplicationContext())
                .newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request requestWithHeader = request.newBuilder()
                                .header("X-Benlly-Api-Token", AppSettings.getInstance().getUser().getToken())
                                .header("X-Benlly-Api-Version", BuildConfig.VERSION_NAME)
                                .header("X-Benlly-Api-OS", "Android")
                                .build();

                        Response originalResponse = chain.proceed(requestWithHeader);
                        return originalResponse;
                    }
                })
                .addInterceptor(logger)
                .build();
        return okHttpClient;
    }

    public static Gson getGsonDefault() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }

}
