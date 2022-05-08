package com.example.quan_ly_resort.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.PhongRS;
import com.example.quan_ly_resort.networkCallback.CapNhatTrangThaiPhong;
import com.example.quan_ly_resort.util.Const;
import com.example.quan_ly_resort.util.Helper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailPhongRS extends AppCompatActivity implements View.OnClickListener, VolleyCallback {
    private PhongRS phongRS;
    private TextView txtPhong, txtTrangThai;
    private Button btnPhongTrong, btnDangCoKhach, btnDangDonPhong, btnDangBaoTri;
    private Helper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phong_rs);

        initView();
        checkButton();
        onClickView();
    }

    private void onClickView() {
        btnPhongTrong.setOnClickListener(this);
        btnDangDonPhong.setOnClickListener(this);
        btnDangCoKhach.setOnClickListener(this);
        btnDangBaoTri.setOnClickListener(this);
    }

    private void initView() {
        helper = new Helper(this);
        phongRS = getIntent().getParcelableExtra(Const.PHONG_RS_ID);
        txtPhong = findViewById(R.id.txtPhong);
        txtTrangThai = findViewById(R.id.txtTrangThai);
        btnPhongTrong = findViewById(R.id.btnPhongTrong);
        btnDangCoKhach = findViewById(R.id.btnDangCoKhach);
        btnDangDonPhong = findViewById(R.id.btnDangDonPhong);
        btnDangBaoTri = findViewById(R.id.btnDangBaoTri);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtPhong.setText("Phòng: " + phongRS.id);
        txtTrangThai.setText("Trạng Thái: " + helper.getString(phongRS));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }



    private void checkButton() {
        switch (phongRS.state) {
            case Const.CLEANING:
                btnPhongTrong.setVisibility(View.VISIBLE);
                btnDangCoKhach.setVisibility(View.GONE);
                btnDangDonPhong.setVisibility(View.GONE);
                btnDangBaoTri.setVisibility(View.GONE);
                break;
            case Const.OCCUPY:
                btnPhongTrong.setVisibility(View.GONE);
                btnDangCoKhach.setVisibility(View.GONE);
                btnDangDonPhong.setVisibility(View.VISIBLE);
                btnDangBaoTri.setVisibility(View.VISIBLE);
                break;
            case Const.MAINTENACE:
                btnPhongTrong.setVisibility(View.GONE);
                btnDangCoKhach.setVisibility(View.GONE);
                btnDangDonPhong.setVisibility(View.VISIBLE);
                btnDangBaoTri.setVisibility(View.GONE);
                break;
            case Const.AVAILABLE:
                btnPhongTrong.setVisibility(View.GONE);
                btnDangCoKhach.setVisibility(View.VISIBLE);
                btnDangDonPhong.setVisibility(View.VISIBLE);
                btnDangBaoTri.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPhongTrong:
                callAPiSever(Const.AVAILABLE);
                break;
            case R.id.btnDangCoKhach:
                callAPiSever(Const.OCCUPY);
                break;
            case R.id.btnDangDonPhong:
                callAPiSever(Const.CLEANING);
                break;
            case R.id.btnDangBaoTri:
                callAPiSever(Const.MAINTENACE);
                break;

        }
    }

    private void callAPiSever(String value) {
        helper.showProgressLoading();
        Map<String, String> maps = new HashMap<>();
        maps.put(Const.STATE, value);
        new CapNhatTrangThaiPhong(this, maps, this, phongRS.id);
    }

    @Override
    public void onSuccess(JSONObject jsonObj) {
        helper.dismisProgess();
        setResult(Activity.RESULT_OK);
        finish();
        Log.d("DetailPhongRS", "onSuccess: " + jsonObj);
    }

    @Override
    public void onFail(String error) {
        helper.dismisProgess();
        Log.d("DetailPhongRS", "onFail: " + error);
    }
}
