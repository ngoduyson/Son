package com.example.sonyama.dayseeson.core.model;

import com.example.sonyama.dayseeson.util.DayseeServiceUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sonyama on 3/3/16.
 */
public class DayseeCallback<T> implements Callback<T> {

    public void onResponse(T data) {

    }

    public void onFailure(List<DayseeError> errors) {

    }

    @Override
    public void onResponse(Response<T> response) {
        if (response.isSuccess()) {
            onResponse(response.body());
        } else {
            if (response.errorBody() != null) {
                try {
                    JSONObject jsonObjectError = new JSONObject(response.errorBody().string());
                    JSONArray arrayError = jsonObjectError.getJSONArray("errors");
                    Gson gson = DayseeServiceUtil.getGsonDefault();
                    Type listType = new TypeToken<ArrayList<DayseeError>>() {}.getType();
                    List<DayseeError> dayseeErrorList = gson.fromJson(arrayError.toString(), listType);
                    for (DayseeError error: dayseeErrorList) {
                        error.setInvalidFields(getInvalidFieldsFromCodeId(error.getCode(), arrayError));
                    }
                    onFailure(dayseeErrorList);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            List<DayseeError> dayseeErrorList = new ArrayList<>();
            DayseeError dayseeError = new DayseeError();
            dayseeError.setCode(400);
            dayseeError.setMessage("Bad Request");
            dayseeErrorList.add(dayseeError);
            onFailure(dayseeErrorList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        List<DayseeError> dayseeErrorList = new ArrayList<>();
        DayseeError dayseeError = new DayseeError();
        dayseeError.setCode(400);
        dayseeError.setMessage("Bad Request");
        dayseeErrorList.add(dayseeError);
        onFailure(dayseeErrorList);
    }

    private JSONObject getInvalidFieldsFromCodeId(int pCodeId, JSONArray arrayError) {
        int size = arrayError.length();
        for (int i = 0; i < size; i++) {
            JSONObject object = arrayError.optJSONObject(i);
            if (object.optInt("code", -1) == pCodeId) {
                return object.optJSONObject("invalid_fields");
            }
        }
        return null;
    }
}

