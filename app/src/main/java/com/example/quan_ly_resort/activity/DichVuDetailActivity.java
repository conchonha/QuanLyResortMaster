package com.example.quan_ly_resort.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.DichVu;
import com.example.quan_ly_resort.model.DuKhach;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.model.User;
import com.example.quan_ly_resort.networkCallback.DangKiDichVuAdder;
import com.example.quan_ly_resort.util.ObjectSerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class DichVuDetailActivity extends AppCompatActivity implements VolleyCallback, View.OnClickListener {

    ImageView ivThumnail;
    TextView tvTitle;
    TextView tvMoTa;
    TextView tvKeyword;
    TextView tvGia;
    Button btDatDichVu;
    Bundle receivedBundle;
    boolean boolDaDangKy;

    int soDuMoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_vu_detail);

        Intent receivedIntent = getIntent();
        receivedBundle = receivedIntent.getExtras();
        boolDaDangKy = receivedBundle.getBoolean(DichVu.DA_DANG_KY);
        soDuMoi = receivedBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN);

        ivThumnail = findViewById(R.id.ivDetailThumbnail);
        tvTitle = findViewById(R.id.tvDetailTitle);
        tvMoTa = findViewById(R.id.tvDetailMoTa);
        tvKeyword = findViewById(R.id.tvDetailKeyword);
        tvGia = findViewById(R.id.tvDetailGia);
        btDatDichVu = findViewById(R.id.btDatDichVu);

        ivThumnail.setImageResource(receivedBundle.getInt(DichVu.ID_HINH));
        tvTitle.setText(receivedBundle.getString(DichVu.TENDV_COLUMN));
        tvMoTa.setText(receivedBundle.getString(DichVu.MOTA_COLUMN));
        tvKeyword.setText(receivedBundle.getString(DichVu.STR_KEYWORD));
        tvGia.setText("Giá: "+ receivedBundle.getInt(DichVu.GIA_COLUMN) +" đ");

        if(boolDaDangKy){
            btDatDichVu.setText(R.string.da_dang_ky);
            btDatDichVu.setEnabled(false);
        }else{
            btDatDichVu.setText(R.string.dat_dich_vu);
            btDatDichVu.setEnabled(true);
        }

        if(MainActivity.boolAlreadyLogin && receivedBundle.getBoolean(DichVu.PHAIDANGKI_COLUMN)){
            btDatDichVu.setVisibility(View.VISIBLE);
            btDatDichVu.setOnClickListener(this);
        }else {
            btDatDichVu.setVisibility(View.GONE);
        }

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int soDuTrongVi = receivedBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN);
        int gia = receivedBundle.getInt(DichVu.GIA_COLUMN);
        if(soDuTrongVi < gia){
            showFailDialog("Không đủ tiền trong ví");
        }else {
            soDuMoi = soDuTrongVi - gia;
            String MADV = receivedBundle.getString(DichVu.MADV_COLUMN);
            String MATK = receivedBundle.getString(TaiKhoan.MATK_COLUMN);
            new DangKiDichVuAdder(getApplicationContext(),this,MADV,MATK,soDuMoi);
        }


    }

    private void showFailDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dat_dich_vu_that_bai);

        builder.setMessage(msg);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSuccesDialog(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dat_dich_vu_thanh_cong);

        builder.setMessage(msg);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onSuccess(JSONObject jsonObj) {
        JSONObject root = jsonObj;
        try {
            boolean status = root.getBoolean("status");
            String message = root.getString("message");
            if(status){
                updateUserListDichVuDaDangKy();
                showSuccesDialog(message,DichVuDetailActivity.this);
                btDatDichVu.setText(R.string.da_dang_ky);
                btDatDichVu.setEnabled(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void updateUserListDichVuDaDangKy() {
        SharedPreferences sharedPrefs = getSharedPreferences("Shared_Preferences", Activity.MODE_PRIVATE);
        ArrayList<String> listMaDichVuDaDangKy = null;
        try {
            listMaDichVuDaDangKy = (ArrayList<String>) ObjectSerializer.deserialize(sharedPrefs.getString(User.DICH_VU_DA_DANG_KY, ObjectSerializer.serialize(new ArrayList<>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        listMaDichVuDaDangKy.add(receivedBundle.getString(DichVu.MADV_COLUMN));
        SharedPreferences.Editor editor = sharedPrefs.edit();
        try {
            editor.putString(User.DICH_VU_DA_DANG_KY, ObjectSerializer.serialize(listMaDichVuDaDangKy));
            editor.putInt(TaiKhoan.SODUTRONGVI_COLUMN,soDuMoi);
        } catch (IOException e) {
            e.printStackTrace();
        }

        editor.commit();
    }

    @Override
    public void onFail(String error) {
        Log.e("DichVuDetailActivity","onFail "+ error);
    }


}
