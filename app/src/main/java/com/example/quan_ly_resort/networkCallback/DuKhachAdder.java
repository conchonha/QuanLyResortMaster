package com.example.quan_ly_resort.networkCallback;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.DuKhach;
import com.example.quan_ly_resort.model.Phong;
import com.example.quan_ly_resort.network.ServerSender;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DuKhachAdder implements VolleyCallback {
    public static final int CALLBACK_CODE = 0;
    /*
    TH1: (JSONArray NHOM_DU_KHACH chỉ có 1 người)
    thêm 1 du khách và đăng kí phòng cho người đó

    TH2: (JSONArray NHOM_DU_KHACH nhiều hơn 1 người. người đầu tiên là người đại diện)
    thêm nhóm du khách và đăng kí phòng cho người đại diện
     */

    private static final String DU_KHACH = "duKhach";
    private static final String PHONG = "phong";
    private static final String THOI_GIAN = "thoiGian";
    private String url_string = MainActivity.WEBSERVER_IP +"/add-du-khach-va-phong.php";

    private ArrayList<String> listMaPhong = new ArrayList<>();//list chứa mã phòng

    private VolleyCallback callbackResult;

    public DuKhachAdder(Context context,VolleyCallback callbackResult,ArrayList<DuKhach> listDuKhach, ArrayList<String> listMaPhong,String ngayDen, String ngayDi){
        this.callbackResult = callbackResult;
        try {

            JSONObject json_obj = null;
            json_obj = convertToJSONObject (listDuKhach,listMaPhong,ngayDen,ngayDi);
            new ServerSender(url_string,json_obj,context,this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private JSONObject convertToJSONObject(ArrayList<DuKhach> listDuKhach, ArrayList<String> listMaPhong,String ngayDen, String ngayDi) throws JSONException {
        JSONObject jsonObjectResult = new JSONObject();
        // add nhóm du khách
        JSONArray jsonArrayNhomDuKhach= new JSONArray();
        for(DuKhach duKhach : listDuKhach){
            JSONObject jsonObj = new JSONObject();

            jsonObj.put(DuKhach.HOTENDK_COLUMN, duKhach.getHoTenDuDhack());

            if(duKhach.getSoDienThoai().trim().isEmpty())
                jsonObj.put(DuKhach.SDT_COLUMN, JSONObject.NULL);
            else
                jsonObj.put(DuKhach.SDT_COLUMN, duKhach.getSoDienThoai());

            jsonObj.put(DuKhach.NGAYSINH_COLUMN, duKhach.getNgaySinh());

            if(duKhach.getChungMinhNhanDan().trim().isEmpty())
                jsonObj.put(DuKhach.CMND_COLUMN, JSONObject.NULL);
            else
                jsonObj.put(DuKhach.CMND_COLUMN, duKhach.getChungMinhNhanDan());

            jsonArrayNhomDuKhach.put(jsonObj);
        }
        jsonObjectResult.put(DU_KHACH, jsonArrayNhomDuKhach);

        // add Phòng do người đại diện đặt
        JSONArray jsonArrayPhong= new JSONArray();
        for(String MAP : listMaPhong){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put(Phong.MAP_COLUMN,MAP);
            jsonArrayPhong.put(jsonObj);
        }
        jsonObjectResult.put(PHONG, jsonArrayPhong);

        //add ngay den di
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(Phong.NGAYDEN_COLUMN,ngayDen);
        jsonObj.put(Phong.NGAYDI_COLUMN,ngayDi);
        jsonObjectResult.put(THOI_GIAN, jsonObj);

        return jsonObjectResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSuccess(JSONObject jsonObj) {//hàm sẽ được gọi khi SendJSONObject thành công
        try {
            jsonObj.put("callbackCode",CALLBACK_CODE);
            callbackResult.onSuccess(jsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFail(String error) {//hàm sẽ được gọi khi SendJSONObject thất bại
        callbackResult.onFail(error);
    }
}
