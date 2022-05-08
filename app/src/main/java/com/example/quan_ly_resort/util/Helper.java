package com.example.quan_ly_resort.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.model.PhongRS;

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/8/2022
*/
public class Helper {
    private Context context;
    private ProgressDialog mProgressDialog;

    public Helper(Context context){
        this.context = context;
    }

    public String getString(PhongRS phongRS){
        String result;
        switch ( phongRS.state){
            case Const.AVAILABLE:
                result = "Phòng Trống";
                break;
            case Const.CLEANING:
                result = "Đang Dọn Dẹp";
                break;
            case Const.MAINTENACE:
                result = "Đang Bảo Trì";
                break;
            default:
                result = "Đang Có Khách";
        }
        return result;
    }

    public void showProgressLoading(){
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.lbl_loading));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    public void dismisProgess(){
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
