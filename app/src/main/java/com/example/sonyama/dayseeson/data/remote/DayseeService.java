package com.example.sonyama.dayseeson.data.remote;

import com.example.sonyama.dayseeson.data.model.Token;
import com.example.sonyama.dayseeson.data.model.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sonyama on 3/2/16.
 */
public interface DayseeService {

    @POST("v1.1/users")
    Call<User> login(@Query("uuid") String uuid);

    @POST("/v1.1/push_notification")
    Call<Token> registToken(@Query("device") String device, @Query("token") String token);
}
