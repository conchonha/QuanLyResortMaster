package com.example.quan_ly_resort.networkCallback;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.DichVu;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.model.User;
import com.example.quan_ly_resort.network.ServerSender;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserGetter implements VolleyCallback {
    private String url_string = MainActivity.WEBSERVER_IP +"/get-user.php";
    private VolleyCallback callbackResult;

    public UserGetter(Context context, VolleyCallback callbackResult, String emailTaiKhoan) throws JSONException {
        this.callbackResult = callbackResult;

        JSONObject jsonObj = new JSONObject();
        jsonObj.put(TaiKhoan.EMAILTK_COLUMN, emailTaiKhoan);

        new ServerSender(url_string,jsonObj,context,this);
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
