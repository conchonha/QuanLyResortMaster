package com.example.quan_ly_resort.model;

public class DuKhach {
    public static final String MADK_COLUMN = "MADK";
    public static final String MATK_COLUMN = "MATK";
    public static final String MADKDAIDIEN_COLUMN = "MADKDAIDIEN";
    public static final String HOTENDK_COLUMN = "HOTENDK";
    public static final String SDT_COLUMN = "SDT";
    public static final String NGAYSINH_COLUMN = "NGAYSINH";
    public static final String CMND_COLUMN = "CMND";
    String maDuKhach;
    String maTaiKhoan;
    String maDuKhachDaiDien ;
    String hoTenDuDhack;
    String soDienThoai;
    String ngaySinh;
    String chungMinhNhanDan;

    public DuKhach( String hoTenDuDhack, String soDienThoai, String ngaySinh, String chungMinhNhanDan) {
        this.maDuKhach = null;
        this.maTaiKhoan = null;
        this.maDuKhachDaiDien = null;
        this.hoTenDuDhack = hoTenDuDhack;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.chungMinhNhanDan = chungMinhNhanDan;
    }

    public DuKhach(String maDuKhach, String maTaiKhoan, String maDuKhachDaiDien, String hoTenDuDhack, String soDienThoai, String ngaySinh, String chungMinhNhanDan) {
        this.maDuKhach = maDuKhach;
        this.maTaiKhoan = maTaiKhoan;
        this.maDuKhachDaiDien = maDuKhachDaiDien;
        this.hoTenDuDhack = hoTenDuDhack;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.chungMinhNhanDan = chungMinhNhanDan;
    }

    public String getMaDuKhach() {
        return maDuKhach;
    }

    public void setMaDuKhach(String maDuKhach) {
        this.maDuKhach = maDuKhach;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMaDuKhachDaiDien() {
        return maDuKhachDaiDien;
    }

    public void setMaDuKhachDaiDien(String maDuKhachDaiDien) {
        this.maDuKhachDaiDien = maDuKhachDaiDien;
    }

    public String getHoTenDuDhack() {
        return hoTenDuDhack;
    }

    public void setHoTenDuDhack(String hoTenDuDhack) {
        this.hoTenDuDhack = hoTenDuDhack;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getChungMinhNhanDan() {
        return chungMinhNhanDan;
    }

    public void setChungMinhNhanDan(String chungMinhNhanDan) {
        this.chungMinhNhanDan = chungMinhNhanDan;
    }
}
