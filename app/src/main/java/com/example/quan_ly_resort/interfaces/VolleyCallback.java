package com.example.quan_ly_resort.interfaces;

import org.json.JSONObject;

public interface VolleyCallback {
    /*
    onSuccess chạy khi kết nối được với server, tham số là jsonObj chứa
    thông tin trả về từ server.
    Lúc này có 2 TH:
    - status = true => thao tác thành công
    - status = false => thao tác thất bại (có thể do Email đã có người đăng ki,...)
     */
    void onSuccess(JSONObject jsonObj);
    /*
    onFail chạy khi không kết nối được với server, tham số là error chứa
    thông tin lỗi trả về từ server.
     */
    void onFail(String error);
}
