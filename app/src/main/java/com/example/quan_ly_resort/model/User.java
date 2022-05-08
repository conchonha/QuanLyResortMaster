package com.example.quan_ly_resort.model;

import java.util.ArrayList;

public class
User {
    public static final String DICH_VU_DA_DANG_KY = "dichVuDaDangKy";

    DuKhach duKhach;
    TaiKhoan taiKhoan;
    ArrayList<String> listMaDichVuDaDangKy;

    public User(String maTaiKhoan, String emailTaiKhoan, String matkhauTaiKhoan, String maTheNganHgan, String maDuKhach, String maDuKhachDaiDien, String hoTenDuKhach, String soDienThoai, String ngaySinh, String chungMinhNhanDan,int soDuTrongVi){
        duKhach = new DuKhach(maDuKhach,maTaiKhoan,maDuKhachDaiDien,hoTenDuKhach,soDienThoai,ngaySinh,chungMinhNhanDan);
        taiKhoan = new TaiKhoan(maTaiKhoan,maDuKhach,emailTaiKhoan,matkhauTaiKhoan,maTheNganHgan,soDuTrongVi);
        listMaDichVuDaDangKy = new ArrayList<>();
    }

    public User(DuKhach duKhach, TaiKhoan taiKhoan){
        setDuKhach(duKhach);
        setTaiKhoan(taiKhoan);
        listMaDichVuDaDangKy = new ArrayList<>();
    }

    public ArrayList<String> getListMaDichVuDaDangKy() {
        return listMaDichVuDaDangKy;
    }

    public void addMaDichVuDaDangKi(String maDichVu){
        listMaDichVuDaDangKy.add(maDichVu);
    }
    // setter getter du khach
    public DuKhach getDuKhach() {
        return duKhach;
    }

    public void setDuKhach(DuKhach duKhach) {
        this.duKhach = duKhach;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    // getter setter thông tin tài khoản
    public int getSoDuTrongVi() {
        return taiKhoan.getSoDuTrongVi();
    }

    public void setSoDuTrongVi(int soDuTrongVi) {
        taiKhoan.setSoDuTrongVi(soDuTrongVi);
    }

    public String getMaTaiKhoan() {
        return taiKhoan.getMaTaiKhoan();
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        taiKhoan.setMaTaiKhoan(maTaiKhoan);
        duKhach.setMaTaiKhoan(maTaiKhoan);
    }

    public String getEmailTaiKhoan() {
        return taiKhoan.getEmailTaiKhoan();
    }

    public void setEmailTaiKhoan(String emailTaiKhoan) {
        taiKhoan.setEmailTaiKhoan(emailTaiKhoan);
    }

    public String getMatKhauTaiKhoan() {
        return taiKhoan.getMatKhauTaiKhoan();
    }

    public void setMatKhauTaiKhoan(String matKhauTaiKhoan) {
        taiKhoan.setMatKhauTaiKhoan(matKhauTaiKhoan);
    }

    public String getMaTheNganHang() {
        return taiKhoan.getMaTheNganHang();
    }

    public void setMaTheNganHang(String maTheNganHang) {
        taiKhoan.setMaTheNganHang(maTheNganHang);
    }


    // getter setter thông tin du khách
    public String getMaDuKhach() {
        return duKhach.getMaDuKhach();
    }

    public void setMaDuKhach(String maDuKhach) {
        taiKhoan.setMaDuKhach(maDuKhach);
        duKhach.setMaDuKhach(maDuKhach);
    }

    public String getMaDuKhachDaiDien() {
        return duKhach.getMaDuKhachDaiDien();
    }

    public void setMaDuKhachDaiDien(String maDuKhachDaiDien) {
        duKhach.setMaDuKhachDaiDien(maDuKhachDaiDien);
    }

    public String getHoTenDuDhack() {
        return duKhach.getHoTenDuDhack();
    }

    public void setHoTenDuDhack(String hoTenDuDhack) {
        duKhach.setHoTenDuDhack(hoTenDuDhack);
    }

    public String getSoDienThoai() {
        return duKhach.getSoDienThoai();
    }

    public void setSoDienThoai(String soDienThoai) {
        duKhach.setSoDienThoai(soDienThoai);
    }

    public String getNgaySinh() {
        return duKhach.getNgaySinh();
    }

    public void setNgaySinh(String ngaySinh) {
        duKhach.setNgaySinh(ngaySinh);
    }

    public String getChungMinhNhanDan() {
        return duKhach.getChungMinhNhanDan();
    }

    public void setChungMinhNhanDan(String chungMinhNhanDan) {
        duKhach.setChungMinhNhanDan(chungMinhNhanDan);
    }
}
