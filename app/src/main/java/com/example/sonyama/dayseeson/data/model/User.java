package com.example.sonyama.dayseeson.data.model;

import android.text.TextUtils;
import java.io.Serializable;

/**
 * Created by sonyama on 3/1/16.
 */
public class User implements Serializable {
    private String publicId;
    private String uuid;
    private String token;

    public User() {
        this.publicId = "";
        this.uuid = "";
        this.token = "";
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLive() {
        return !TextUtils.isEmpty(this.publicId)
                || !TextUtils.isEmpty(this.uuid)
                || !TextUtils.isEmpty(this.token);
    }

    @Override
    public String toString() {
        return "User{"
                + "publicId='" + publicId + "\'"
                + ", uuid='" + uuid + "\'"
                + ", token='" + token + "\'"
                + "}";
    }
}
