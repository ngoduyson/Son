package com.example.sonyama.dayseeson.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sonyama on 3/15/16.
 */
public class ChannelCategory implements Serializable {
    private int id;
    private String name;
    private String iconUrl;
    private int total;
    private List<Channel> channels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
