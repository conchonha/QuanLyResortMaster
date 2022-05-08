package com.example.quan_ly_resort.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.interfaces.VolleyCallback;;
import com.example.quan_ly_resort.model.DuKhach;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.model.User;
import com.example.quan_ly_resort.networkCallback.UserGetter;
import com.example.quan_ly_resort.util.ObjectSerializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements VolleyCallback, View.OnClickListener {
    public static final int REQUEST_CODE = 4;

    EditText edtEmail,edtPassword;
    Button btnLogin,btnRegister;
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail    = findViewById(R.id.edtEmailLogin);
        edtPassword = findViewById(R.id.edtPassword);
        tvError = findViewById(R.id.tvError_actLogin);
        btnLogin    = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }


    public void onClick(View v) {
        tvError.setText("");
        Intent myIntent = null;
        switch (v.getId()) {
            case R.id.btnLogin:
                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    edtEmail.setError("Please enter your email");
                    edtEmail.requestFocus();
                } else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                    edtPassword.setError("Please enter your Password");
                    edtPassword.requestFocus();
                } else {

                    //------------ check is Admin -------
                    if(edtEmail.getText().toString().equals("admin") && edtPassword.getText().toString().equals("admin123")){
                        Toast.makeText(LoginActivity.this,"ADMIN Đăng nhập thành công",Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPrefs = getSharedPreferences("Shared_Preferences", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPrefs.edit();
                        editor.putBoolean("ISADMIN",true);
                        MainActivity.boolAlreadyLogin = true;

                        Intent resultIntent = new Intent(LoginActivity.this, MainActivity.class);
                        setResult(RESULT_OK,resultIntent);
                        finish();
                    }
                    //-----------------------------------

                    try {
                        new UserGetter(getApplicationContext(), this, edtEmail.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.btnRegister:
                myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent);
                break;
        }
    }

    private void successHandling(User user) {
        String email = user.getEmailTaiKhoan();
        String matKhau = user.getMatKhauTaiKhoan();
        if(edtEmail.getText().toString().equals(email) && edtPassword.getText().toString().equals(matKhau)){
            Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
            updateSharedPreferences(user);
            MainActivity.boolAlreadyLogin = true;

            Intent resultIntent = new Intent(LoginActivity.this, MainActivity.class);
            setResult(RESULT_OK,resultIntent);
            finish();
        } else {
            tvError.setText("Sai email hoặc mật khẩu !!!");
        }

    }
    private void failHandling(String message) {
        tvError.setText(message);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(this, MainActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    //xử lý lấy user thành công
    @Override
    public void onSuccess(JSONObject jsonObj) {
        JSONObject root = jsonObj;
        try {
            boolean status = root.getBoolean("status");
            String message = root.getString("message");
            if(status){
                JSONArray data = root.getJSONArray("data");
                if(data.length() >0){
                    User user = null;
                    for(int i = 0;i<data.length();i++){
                        JSONObject dataItem = data.getJSONObject(i);
                        String MATK = dataItem.getString("MATK");
                        String EMAILTK = dataItem.getString("EMAILTK");
                        String MATKHAUTK = dataItem.getString("MATKHAUTK");
                        String MATHENGANHANG = dataItem.getString("MATHENGANHANG");
                        String MADK = dataItem.getString("MADK");
                        String MADKDAIDIEN = dataItem.getString("MADKDAIDIEN");
                        String HOTENDK = dataItem.getString("HOTENDK");
                        String SDT = dataItem.getString("SDT");
                        String NGAYSINH = dataItem.getString("NGAYSINH");
                        String CMND = dataItem.getString("CMND");
                        int SODUTRONGVI = dataItem.getInt("SODUTRONGVI");
                        String MADV = dataItem.getString("MADV");

                        if(user == null)
                            user = new User(MATK, EMAILTK, MATKHAUTK, MATHENGANHANG, MADK, MADKDAIDIEN, HOTENDK, SDT, NGAYSINH, CMND, SODUTRONGVI);

                        user.addMaDichVuDaDangKi(MADV);
                    }
                    successHandling(user);
                }else{
                    message = "Không tìm thấy email, xin hãy nhập chính xác email";
                    failHandling(message);
                }

            }else{
                Log.e("LoginActivity","onSuccess: kết nối được với server. nhưng: " + message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //xử lý lấy user thất bại
    @Override
    public void onFail(String error) {
        Log.e("LoginActivity","onFail: " + error);
    }

    private void updateSharedPreferences(User user){
        SharedPreferences sharedPrefs = getSharedPreferences("Shared_Preferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(DuKhach.MADK_COLUMN,user.getMaDuKhach());
        editor.putString(DuKhach.MADKDAIDIEN_COLUMN,user.getMaDuKhachDaiDien());
        editor.putString(DuKhach.HOTENDK_COLUMN,user.getHoTenDuDhack());
        editor.putString(DuKhach.SDT_COLUMN,user.getSoDienThoai());
        editor.putString(DuKhach.NGAYSINH_COLUMN,user.getNgaySinh());
        editor.putString(DuKhach.CMND_COLUMN,user.getChungMinhNhanDan());
        editor.putString(TaiKhoan.MATK_COLUMN,user.getMaTaiKhoan());
        editor.putString(TaiKhoan.EMAILTK_COLUMN,user.getEmailTaiKhoan());
        editor.putString(TaiKhoan.MATKHAUTK_COLUMN,user.getMatKhauTaiKhoan());
        editor.putString(TaiKhoan.MATHENGANHANG_COLUMN,user.getMaTheNganHang());
        editor.putInt(TaiKhoan.SODUTRONGVI_COLUMN,user.getSoDuTrongVi());
        try {
            editor.putString(User.DICH_VU_DA_DANG_KY, ObjectSerializer.serialize(user.getListMaDichVuDaDangKy()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        editor.commit();
    }
}
