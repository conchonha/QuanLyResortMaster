package com.example.quan_ly_resort.networkCallback;

import android.content.Context;

import com.android.volley.Request;
import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.network.ServerReceiverHerokuIP;

import org.json.JSONObject;

public class PhongGetter implements VolleyCallback {
    private String url_string = MainActivity.HEROKU_IP +"/rooms";
    private VolleyCallback callbackResult;

    public PhongGetter(Context context, VolleyCallback callbackResult) {
        this.callbackResult = callbackResult;
        new ServerReceiverHerokuIP(url_string,Request.Method.GET,context,this);
    }

    @Override
    public void onSuccess(JSONObject jsonObj) {
        callbackResult.onSuccess(jsonObj);
    }

    @Override
    public void onFail(String error) {
        callbackResult.onFail(error);
    }
}
