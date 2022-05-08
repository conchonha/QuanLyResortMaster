package com.example.quan_ly_resort.model;

public class TaiKhoan {
    public static final String MATK_COLUMN = "MATK";
    public static final String MADK_COLUMN = "MADK";
    public static final String EMAILTK_COLUMN = "EMAILTK";
    public static final String MATKHAUTK_COLUMN = "MATKHAUTK";
    public static final String MATHENGANHANG_COLUMN = "MATHENGANHANG";
    public static final String SODUTRONGVI_COLUMN = "SODUTRONGVI";
    String maTaiKhoan;
    String maDuKhach;
    String emailTaiKhoan;
    String matKhauTaiKhoan;
    String maTheNganHang;
    int soDuTrongVi;

    public TaiKhoan(String maTaiKhoan, String maDuKhach, String emailTaiKhoan, String matKhauTaiKhoan, String maTheNganHang,int soDuTrongVi) {
        this.maTaiKhoan = maTaiKhoan;
        this.maDuKhach = maDuKhach;
        this.emailTaiKhoan = emailTaiKhoan;
        this.matKhauTaiKhoan = matKhauTaiKhoan;
        this.maTheNganHang = maTheNganHang;
        this.soDuTrongVi = soDuTrongVi;
    }

    public int getSoDuTrongVi() {
        return soDuTrongVi;
    }

    public void setSoDuTrongVi(int soDuTrongVi) {
        this.soDuTrongVi = soDuTrongVi;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMaDuKhach() {
        return maDuKhach;
    }

    public void setMaDuKhach(String maDuKhach) {
        this.maDuKhach = maDuKhach;
    }

    public String getEmailTaiKhoan() {
        return emailTaiKhoan;
    }

    public void setEmailTaiKhoan(String emailTaiKhoan) {
        this.emailTaiKhoan = emailTaiKhoan;
    }

    public String getMatKhauTaiKhoan() {
        return matKhauTaiKhoan;
    }

    public void setMatKhauTaiKhoan(String matKhauTaiKhoan) {
        this.matKhauTaiKhoan = matKhauTaiKhoan;
    }

    public String getMaTheNganHang() {
        return maTheNganHang;
    }

    public void setMaTheNganHang(String maTheNganHang) {
        this.maTheNganHang = maTheNganHang;
    }
}
