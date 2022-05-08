package com.example.quan_ly_resort.networkCallback;

import android.content.Context;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.DichVu;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.network.ServerSender;

import org.json.JSONException;
import org.json.JSONObject;

public class DangKiDichVuAdder implements VolleyCallback {
    private String url_string = MainActivity.WEBSERVER_IP +"/add-dang-ky-dich-vu.php";
    private VolleyCallback callbackResult;

    public DangKiDichVuAdder(Context context, VolleyCallback callbackResult, String MADV, String MATK, int soDuMoi) {
        this.callbackResult = callbackResult;
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put(DichVu.MADV_COLUMN,MADV);
            jsonObj.put(TaiKhoan.MATK_COLUMN,MATK);
            jsonObj.put(TaiKhoan.SODUTRONGVI_COLUMN,soDuMoi);
            new ServerSender(url_string,jsonObj,context,this);
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
