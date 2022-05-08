package com.example.quan_ly_resort.networkCallback;


import android.content.Context;

import com.android.volley.Request;
import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.network.ServerReceiverHerokuIP;
import com.example.quan_ly_resort.network.ServerSenderHerokuIP;

import org.json.JSONObject;

import java.util.Map;

public class CapNhatTrangThaiPhong  implements VolleyCallback {
    private VolleyCallback callbackResult;
    private String url_string = MainActivity.HEROKU_IP +"/rooms/";

    public CapNhatTrangThaiPhong(Context context, final Map<String, String> jsonObject,VolleyCallback callbackResult, String idPhong) {
        this.callbackResult = callbackResult;
        new ServerSenderHerokuIP(url_string + idPhong,jsonObject,context,callbackResult);
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
