package com.example.sonyama.dayseeson.data.model;

/**
 * Created by sonyama on 3/9/16.
 */
import java.io.Serializable;

public class Token implements Serializable {

    private String id;
    private String registeredAt;

    public Token() {
        this.id = "";
        this.registeredAt = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id='" + id + "\'" +
                ", registeredAt='" + registeredAt + "\'" +
                "}";
    }
}
