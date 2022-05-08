package com.example.quan_ly_resort.networkCallback;

import android.content.Context;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.network.ServerReceiver;

import org.json.JSONObject;

public class DichVuGetter implements VolleyCallback {
    private String url_string =  MainActivity.WEBSERVER_IP +"/get-all-dich-vu.php";
    private VolleyCallback callbackResult;

    public DichVuGetter(Context context, VolleyCallback callbackResult) {
        this.callbackResult = callbackResult;
        new ServerReceiver(url_string,context,this);
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
