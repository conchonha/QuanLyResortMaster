package com.example.quan_ly_resort.model;

public class KhungGio {
    private String ngayDen;
    private String ngayDi;

    public KhungGio(String ngayDen, String ngayDi) {
        this.ngayDen = ngayDen.equals("null") ? null: ngayDen;
        this.ngayDi = ngayDi.equals("null") ? null: ngayDi;
    }

    public String getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(String ngayDen) {
        this.ngayDen = ngayDen;
    }

    public String getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(String ngayDi) {
        this.ngayDi = ngayDi;
    }
}