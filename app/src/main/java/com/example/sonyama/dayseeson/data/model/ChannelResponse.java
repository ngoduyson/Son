package com.example.sonyama.dayseeson.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sonyama on 3/15/16.
 */
public class ChannelResponse implements Serializable {
    private int total;
    private List<ChannelCategory> channelCategories;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ChannelCategory> getChannelCategories() {
        return channelCategories;
    }

    public void setChannelCategories(List<ChannelCategory> channelCategories) {
        this.channelCategories = channelCategories;
    }
}
