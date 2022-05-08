package com.example.quan_ly_resort.networkCallback;

import android.content.Context;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.DuKhach;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.network.ServerSender;

import org.json.JSONException;
import org.json.JSONObject;

public class TaiKhoanAdder implements VolleyCallback {
    private String url_string = MainActivity.WEBSERVER_IP +"/add-tai-khoan.php";
    private VolleyCallback callbackResult;

    public TaiKhoanAdder(Context context,VolleyCallback callbackResult, String emailTaiKhoan, String matKhauTaiKhoan, String chungMinhNhanDan, String maTheNganHang) throws JSONException {
        this.callbackResult = callbackResult;
        JSONObject json_obj = new JSONObject();

        json_obj.put(TaiKhoan.EMAILTK_COLUMN,emailTaiKhoan);
        json_obj.put(TaiKhoan.MATKHAUTK_COLUMN,matKhauTaiKhoan);
        json_obj.put(DuKhach.CMND_COLUMN,chungMinhNhanDan);
        json_obj.put(TaiKhoan.MATHENGANHANG_COLUMN,maTheNganHang);

        new ServerSender(url_string,json_obj,context,this);
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
