package com.example.sonyama.dayseeson.ui.activity.interfaces;

import com.example.sonyama.dayseeson.data.model.ChannelResponse;
import com.example.sonyama.dayseeson.data.remote.DayseeError;

import java.util.List;

/**
 * Created by sonyama on 3/16/16.
 */
public interface ChannelActivityInterface {
    void init();
    void loadData();
    void onLoadDataSucess(ChannelResponse data);
    void onLoadDataFail(List<DayseeError> errors);
    void showLoading();
    void hideLoading();
}
