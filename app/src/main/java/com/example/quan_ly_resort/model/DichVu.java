package com.example.quan_ly_resort.model;

import java.util.ArrayList;

public class DichVu {
    public static final String MADV_COLUMN = "MADV";
    public static final String TENDV_COLUMN = "TENDV";
    public static final String MOTA_COLUMN = "MOTA";
    public static final String GIA_COLUMN = "GIA";
    public static final String PHAIDANGKI_COLUMN = "PHAIDANGKI";

    public static final String ID_HINH = "idHinh";
    public static final String STR_KEYWORD = "strKeyword";
    public static final String DA_DANG_KY = "daDangKy";
    String maDichVu;
    String tenDichVu;
    String moTa;
    int gia;
    int idHinh;
    ArrayList<String> listKeyword;
    String strKeyword;
    boolean phaiDangKi;

    public DichVu(String maDichVu, String tenDichVu, String moTa, int gia, int idHinh, String firstKeyword, boolean phaiDangKi) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.moTa = moTa;
        this.gia = gia;
        this.idHinh = idHinh;

        this.listKeyword = new ArrayList<>();
        strKeyword = "";
        addKeyWord(firstKeyword);

        this.phaiDangKi = phaiDangKi;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getIdHinh() {
        return idHinh;
    }

    public void setIdHinh(int idHinh) {
        this.idHinh = idHinh;
    }

    public ArrayList<String> getListKeyword() {
        return listKeyword;
    }

    public void setListKeyword(ArrayList<String> listKeyword) {
        this.listKeyword = listKeyword;
    }

    public void addKeyWord(String keyword){
        this.listKeyword.add(keyword);
        this.strKeyword = this.strKeyword.concat(keyword+", ");
    }

    public String getStrKeyword() {
        return strKeyword;
    }

    public void setStrKeyword(String strKeyword) {
        this.strKeyword = strKeyword;
    }

    public boolean isPhaiDangKi() {
        return phaiDangKi;
    }

    public void setPhaiDangKi(boolean phaiDangKi) {
        this.phaiDangKi = phaiDangKi;
    }
}
