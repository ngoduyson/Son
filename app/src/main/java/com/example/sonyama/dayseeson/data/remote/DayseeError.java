package com.example.sonyama.dayseeson.data.remote;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sonyama on 3/3/16.
 */
public class DayseeError implements Serializable {
    private int code;
    private String message;
    private JSONObject invalidFields;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(JSONObject invalidFields) {
        this.invalidFields = invalidFields;
    }

    @Override
    public String toString() {
        return "DayseeError{" +
                "code=" + code +
                ", message='" + message + "\'" +
                ", invalidFields=" + invalidFields +
                "}";
    }
}
