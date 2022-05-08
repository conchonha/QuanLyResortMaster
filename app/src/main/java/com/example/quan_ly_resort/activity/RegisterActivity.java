package com.example.quan_ly_resort.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.User;
import com.example.quan_ly_resort.networkCallback.TaiKhoanAdder;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements VolleyCallback,View.OnClickListener{

    EditText emailTaiKhoan,matKhauTaiKhoan,chungMinhNhanDan,maTheNganHang;
    Button btnDangki;
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Anhxa();
        btnDangki.setOnClickListener(this);
    }
    public void Anhxa(){
        emailTaiKhoan     = findViewById(R.id.edtEmailRegister);
        chungMinhNhanDan  = findViewById(R.id.edtCMND);
        maTheNganHang     = findViewById(R.id.edtMaTheNH);
        matKhauTaiKhoan   = findViewById(R.id.edtPassRegister);
        btnDangki         = findViewById(R.id.btnDangki);
        tvError = findViewById(R.id.tvError_actRegister);
    }

    @Override
    public void onClick(View v) {
        tvError.setText("");
        String email= emailTaiKhoan.getText().toString().trim();
        String cmnd = chungMinhNhanDan.getText().toString().trim();
        String mathe= maTheNganHang.getText().toString().trim();
        String pass = matKhauTaiKhoan.getText().toString().trim();
        if (TextUtils.isEmpty(emailTaiKhoan.getText().toString())) {
            emailTaiKhoan.setError("Vui lòng nhập email");
            emailTaiKhoan.requestFocus();
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTaiKhoan.setError("Nhập sai định dạng email");
            emailTaiKhoan.requestFocus();
        }
        else if (TextUtils.isEmpty(chungMinhNhanDan.getText().toString())) {
            chungMinhNhanDan.setError("Vui lòng nhập chứng minh nhân dân ");
            chungMinhNhanDan.requestFocus();
        }
        else if(TextUtils.isEmpty(maTheNganHang.getText().toString())){
            maTheNganHang.setError("Vui lòng nhập mã thẻ ngân hàng");
        }
        else if(TextUtils.isEmpty(matKhauTaiKhoan.getText().toString())){
            matKhauTaiKhoan.setError("Vui lòng nhập mật khẩu ");
            matKhauTaiKhoan.requestFocus();
        }
        else {
            try {
                new TaiKhoanAdder(getApplicationContext(),this,email,pass,cmnd,mathe);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void failHandling(String message) {
        tvError.setText(message);
    }

    private void successHandling() {
        Toast.makeText(RegisterActivity.this,"Đăng kí thành công ",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onSuccess(JSONObject jsonObj) {

        JSONObject root = jsonObj;
        try {
            boolean status = root.getBoolean("status");
            String message = root.getString("message");
            if(status){
                successHandling();
            }else{
                failHandling(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(String error) {
        Log.e("RegisterActivity","onFail: " + error);
    }
}





