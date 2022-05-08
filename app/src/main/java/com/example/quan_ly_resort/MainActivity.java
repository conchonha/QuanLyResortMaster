package com.example.quan_ly_resort;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.quan_ly_resort.activity.DatPhongActivity;
import com.example.quan_ly_resort.activity.LoginActivity;
import com.example.quan_ly_resort.model.Phong;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quan_ly_resort.adapter.SectionsPagerAdapter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, View.OnFocusChangeListener {

    public static final String WEBSERVER_IP = "https://quanlyresort.000webhostapp.com";
    public static final String HEROKU_IP = "https://quanlyresort.herokuapp.com";
    Button btn_DatPhong_actMain;
    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
    EditText etNgayDen;
    EditText etNgayDi;
    TabLayout tabs;

    public static boolean boolAlreadyLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNgayDen = findViewById(R.id.etNgayDen_actMain);
        etNgayDi = findViewById(R.id.etNgayDi_actMain);
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
        btn_DatPhong_actMain = findViewById(R.id.btn_DatPhong_actMain);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        tabs.setupWithViewPager(viewPager);
        btn_DatPhong_actMain.setOnClickListener(this);
        etNgayDen.setOnFocusChangeListener(this);
        etNgayDen.setInputType(InputType.TYPE_NULL);
        etNgayDi.setOnFocusChangeListener(this);
        etNgayDi.setInputType(InputType.TYPE_NULL);

    }

    @Override
    public void onClick(View v) {
        Intent myIntent = null;
        Bundle myBundle = new Bundle();
        switch (v.getId()){
            case R.id.btn_DatPhong_actMain:
                myIntent = new Intent(MainActivity.this, DatPhongActivity.class);
                myBundle.putString(Phong.NGAYDEN_COLUMN,etNgayDen.getText().toString());
                myBundle.putString(Phong.NGAYDI_COLUMN,etNgayDi.getText().toString());
                etNgayDen.setText("");
                etNgayDi.setText("");
                myIntent.putExtras(myBundle);
                startActivity(myIntent);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        boolAlreadyLogin = alreadyLogin();
        if(sectionsPagerAdapter.getPageTitle(position) != null){
            String title = sectionsPagerAdapter.getPageTitle(position).toString();
            switch (title){
                case "Tài khoản":
                    if(!boolAlreadyLogin){
                        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(myIntent);
                    }
                    break;
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private boolean alreadyLogin() {
        SharedPreferences sharedPrefs = getSharedPreferences("Shared_Preferences", Activity.MODE_PRIVATE);
        if(sharedPrefs.getString(TaiKhoan.MATK_COLUMN,"").equals(""))
            return false;
        return true;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.etNgayDen_actMain:
                    showDatePickerDialog(R.id.etNgayDen_actMain);
                    break;
                case R.id.etNgayDi_actMain:
                    showDatePickerDialog(R.id.etNgayDi_actMain);
                    break;
            }
        }
    }

    private void showDatePickerDialog(final int etNgayID) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText etNgay = findViewById(etNgayID);
                String msg = year + "-" + (month + 1) + "-" + dayOfMonth;
                etNgay.setText(msg);
            }
        },year,month,date);

        datePickerDialog.setCanceledOnTouchOutside(false);
        datePickerDialog.show();
    }
}