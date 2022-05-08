package com.example.quan_ly_resort.networkCallback;

import android.content.Context;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.model.User;
import com.example.quan_ly_resort.network.ServerSender;

import org.json.JSONException;
import org.json.JSONObject;

public class UserSetter implements VolleyCallback {
    private String url_string = "http://"+ MainActivity.WEBSERVER_IP +"/quanlyresort/set-user.php";
    private VolleyCallback callbackResult;

    public UserSetter(Context context, VolleyCallback callbackResult, User user) {
        try {
            this.callbackResult = callbackResult;

            JSONObject jsonObj = new JSONObject();
            jsonObj.put(TaiKhoan.MATK_COLUMN, user.getMaTaiKhoan());
            jsonObj.put(TaiKhoan.EMAILTK_COLUMN, user.getEmailTaiKhoan());
            jsonObj.put(TaiKhoan.MATKHAUTK_COLUMN,user.getMatKhauTaiKhoan());
            jsonObj.put(TaiKhoan.MATHENGANHANG_COLUMN,user.getMaTheNganHang());
            jsonObj.put(TaiKhoan.SODUTRONGVI_COLUMN, user.getSoDuTrongVi());

            new ServerSender(url_string, jsonObj, context, this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
