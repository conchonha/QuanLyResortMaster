package com.example.quan_ly_resort.model;

import java.util.ArrayList;

public class Phong {
    public static final String TENLP_COLUMN = "TENLP";
    public static final String MALP_COLUMN = "MALP";
    public static final String MAP_COLUMN = "MAP";
    public static final String NGAYDEN_COLUMN = "NGAYDEN";
    public static final String NGAYDI_COLUMN = "NGAYDI";

    public static final String STANDARD = "standard";
    public static final String DELUXE = "deluxe";
    public static final String SUPERIO = "superio";

    private String maLoaiPhong;
    private String tenLoaiPhong;
    private String maPhong;
    private String maDuKhach;

    private ArrayList<KhungGio> listKhungGio;

    public Phong(String maLoaiPhong, String tenLoaiPhong, String maPhong, String maDuKhach, String ngayDen, String ngayDi) {
        this.maLoaiPhong = maLoaiPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.maPhong = maPhong;
        this.maDuKhach = maDuKhach;
        listKhungGio = new ArrayList<>();
        listKhungGio.add(new KhungGio(ngayDen,ngayDi));
    }

    public Phong(String tenLoaiPhong, String maPhong) {// dùng cho DatPhongActivity
        this.maLoaiPhong = null;
        this.tenLoaiPhong = tenLoaiPhong;
        this.maPhong = maPhong;
        this.maDuKhach = null;
        listKhungGio = new ArrayList<>();
    }

    public ArrayList<KhungGio> getListKhungGio() {
        return listKhungGio;
    }

    public void addKhungGio(String ngayDen, String ngayDi){
        listKhungGio.add(new KhungGio(ngayDen,ngayDi));
    }

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String maTenPhong) {
        this.tenLoaiPhong = maTenPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaDuKhach() {
        return maDuKhach;
    }

    public void setMaDuKhach(String maDuKhach) {
        this.maDuKhach = maDuKhach;
    }


}
