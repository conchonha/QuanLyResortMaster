package com.example.quan_ly_resort.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.DuKhach;
import com.example.quan_ly_resort.model.Phong;
import com.example.quan_ly_resort.networkCallback.DuKhachAdder;
import com.example.quan_ly_resort.networkCallback.DatPhongChecker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatPhongActivity extends AppCompatActivity implements VolleyCallback, AdapterView.OnItemSelectedListener, View.OnClickListener, View.OnFocusChangeListener {
    private static final int MAX_PHONG = 5;
    private static final int MAX_NGUOI = 5;

    TableLayout tableLayoutNguoiDiCung;
    TableLayout tableLayoutPhong;
    Spinner spnSoNguoi;
    Spinner spnSoPhong;
    Button btDatPhong;
    EditText etHoTen;
    EditText etSDT;
    EditText etNgaySinh;
    EditText etCMND;
    EditText etNgayDen;
    EditText etNgayDi;
    TextView tvError;

    int[] etHoTenNDCID = {
            R.id.etHoTenNDC1,
            R.id.etHoTenNDC2,
            R.id.etHoTenNDC3,
            R.id.etHoTenNDC4
    };
    int[] etSDTNDCID = {
            R.id.etSDTNDC1,
            R.id.etSDTNDC2,
            R.id.etSDTNDC3,
            R.id.etSDTNDC4
    };
    int[] etNgaySinhNDCID = {
            R.id.etNgaySinhNDC1,
            R.id.etNgaySinhNDC2,
            R.id.etNgaySinhNDC3,
            R.id.etNgaySinhNDC4
    };
    int[] etCMNDNDCID = {
            R.id.etCMNDNDC1,
            R.id.etCMNDNDC2,
            R.id.etCMNDNDC3,
            R.id.etCMNDNDC4
    };
    int[] tableRowCMNDNDCID = {
            R.id.tableRowCMNDNDC1,
            R.id.tableRowCMNDNDC2,
            R.id.tableRowCMNDNDC3,
            R.id.tableRowCMNDNDC4
    };
    int[] spnLoaiPhongID = {
            R.id.spnLoaiPhong1,
            R.id.spnLoaiPhong2,
            R.id.spnLoaiPhong3,
            R.id.spnLoaiPhong4,
            R.id.spnLoaiPhong5
    };

    ArrayList<Phong> listPhongConTrong; // chỉ chứa tên loại phòng và mã phòng trống
    ArrayList<String> listLoaiPhong;
    ArrayList<String> listLoaiPhongCanDat;
    ArrayList<DuKhach> listDuKhach;
    ArrayList<String> listMaPhong;

    Bundle receivedBundle;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_phong);

        Intent receivedIntent = getIntent();
        receivedBundle = receivedIntent.getExtras();
        
        tableLayoutNguoiDiCung = findViewById(R.id.tableLayoutNguoiDiCung);
        tableLayoutPhong = findViewById(R.id.tableLayoutPhong);
        spnSoNguoi = findViewById(R.id.spnSoNguoi);
        spnSoPhong = findViewById(R.id.spnSoPhong);
        btDatPhong = findViewById(R.id.btDatPhong_actDatPhong);
        etHoTen = findViewById(R.id.etHoTen_actDatPhong);
        etSDT = findViewById(R.id.etSDT_actDatPhong);
        etNgaySinh = findViewById(R.id.etNgaySinh_actDatPhong);
        etCMND = findViewById(R.id.etCMND_actDatPhong);
        etNgayDen = findViewById(R.id.etNgayDen);
        etNgayDi = findViewById(R.id.etNgayDi);
        tvError = findViewById(R.id.tvError_actDatPhong);

        etNgayDen.setText(receivedBundle.getString(Phong.NGAYDEN_COLUMN));
        etNgayDi.setText(receivedBundle.getString(Phong.NGAYDI_COLUMN));

        ArrayList<Integer> listSpinnerItemSoLuong = new ArrayList<>();
        for (int i = 0; i <= MAX_NGUOI - 1; i++){//[0-3]
            listSpinnerItemSoLuong.add(i);
        }
        ArrayAdapter<Integer> arrAdapterSoNguoi = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listSpinnerItemSoLuong);
        spnSoNguoi.setAdapter(arrAdapterSoNguoi);
        spnSoNguoi.setOnItemSelectedListener(this);

        ArrayList<Integer> listSpinnerItemSoPhong = new ArrayList<>();
        for (int i = 1; i <= MAX_PHONG; i++){//[0-4]
            listSpinnerItemSoPhong.add(i);
        }
        ArrayAdapter<Integer> arrAdapterSoPhong = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listSpinnerItemSoPhong);
        spnSoPhong.setAdapter(arrAdapterSoPhong);
        spnSoPhong.setOnItemSelectedListener(this);

        listLoaiPhong = new ArrayList<>();
        listLoaiPhong.add(Phong.STANDARD);
        listLoaiPhong.add(Phong.DELUXE);
        listLoaiPhong.add(Phong.SUPERIO);

        listPhongConTrong = new ArrayList<>(); // chỉ chứa tên loại phòng và mã phòng trống
        listLoaiPhongCanDat = new ArrayList<>();
        listDuKhach = new ArrayList<>();
        listMaPhong = new ArrayList<>();

        btDatPhong.setOnClickListener(this);
        etNgaySinh.setOnFocusChangeListener(this);
        etNgaySinh.setInputType(InputType.TYPE_NULL);
        etNgayDen.setOnFocusChangeListener(this);
        etNgayDen.setInputType(InputType.TYPE_NULL);
        etNgayDi.setOnFocusChangeListener(this);
        etNgayDi.setInputType(InputType.TYPE_NULL);
    }

    //<<<<<<<<<< Chức năng chính
    private void datPhong() {
        String ngayDen = etNgayDen.getText().toString();
        String ngayDi = etNgayDi.getText().toString();
        new DuKhachAdder(getApplicationContext(),this,listDuKhach,listMaPhong,ngayDen,ngayDi);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void kiemTraPhongTrong(){
        listPhongConTrong.clear();
        listLoaiPhongCanDat.clear();
        listDuKhach.clear();

        listDuKhach.add(new DuKhach(
                etHoTen.getText().toString(),
                etSDT.getText().toString(),
                etNgaySinh.getText().toString(),
                etCMND.getText().toString()
        ));
        int soNDC = Integer.parseInt(spnSoNguoi.getSelectedItem().toString());
        for(int i=0; i < soNDC;i++){
            EditText etHoTenNDC = findViewById(etHoTenNDCID[i]);
            EditText etSDTNDC = findViewById(etSDTNDCID[i]);
            EditText etNgaySinhNDC = findViewById(etNgaySinhNDCID[i]);
            EditText etCMNDNDC = findViewById(etCMNDNDCID[i]);
            listDuKhach.add(new DuKhach(
                    etHoTenNDC.getText().toString(),
                    etSDTNDC.getText().toString(),
                    etNgaySinhNDC.getText().toString(),
                    etCMNDNDC.getText().toString()
            ));
        }

        String ngayDen = etNgayDen.getText().toString();
        String ngayDi = etNgayDi.getText().toString();
        int soPhong = Integer.parseInt(spnSoPhong.getSelectedItem().toString());
        for(int i=0; i < soPhong; i++){
            Spinner spnLoaiPhong = findViewById(spnLoaiPhongID[i]);
            listLoaiPhongCanDat.add(spnLoaiPhong.getSelectedItem().toString());
        }

        if(inputHopLe()){
            new DatPhongChecker(getApplicationContext(),this,ngayDen,ngayDi,listLoaiPhongCanDat);
        }
    }
    //<<<<<<<<<< end of chức năng chính

    //<<<<<<<< UI handling
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btDatPhong_actDatPhong:
                kiemTraPhongTrong();
                break;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()){
            case R.id.spnSoNguoi:
                ShowViewDangKiNDC(parent.getItemAtPosition(pos).toString());
                break;
            case R.id.spnSoPhong:
                ShowViewPhong(parent.getItemAtPosition(pos).toString());
                break;
            case R.id.spnLoaiPhong1: case R.id.spnLoaiPhong2: case R.id.spnLoaiPhong3: case R.id.spnLoaiPhong4: case R.id.spnLoaiPhong5:

                break;
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.etNgaySinh_actDatPhong:
                    showDatePickerDialog(R.id.etNgaySinh_actDatPhong);
                    break;
                case R.id.etNgaySinhNDC1:
                    showDatePickerDialogForNDC(etNgaySinhNDCID[0],tableRowCMNDNDCID[0]);
                    break;
                case R.id.etNgaySinhNDC2:
                    showDatePickerDialogForNDC(etNgaySinhNDCID[1],tableRowCMNDNDCID[1]);
                    break;
                case R.id.etNgaySinhNDC3:
                    showDatePickerDialogForNDC(etNgaySinhNDCID[2],tableRowCMNDNDCID[2]);
                    break;
                case R.id.etNgaySinhNDC4:
                    showDatePickerDialogForNDC(etNgaySinhNDCID[3],tableRowCMNDNDCID[3]);
                    break;
                case R.id.etNgayDi:
                    showDatePickerDialog(R.id.etNgayDi);
                    break;
                case R.id.etNgayDen:
                    showDatePickerDialog(R.id.etNgayDen);
                    break;
            }
        }
    }
    //>>>>>>>> end of UI handling

    //<<<<<<<<< Validate input
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean du14Tuoi(String ngaySinh) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate dob = LocalDate.parse(ngaySinh,formatter);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(dob,now);
        if(diff.getYears() >= 14)
            return true;
        return false;
    }
    //<<<<<<<<< Validate input
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean du18Tuoi(String ngaySinh) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate dob = LocalDate.parse(ngaySinh,formatter);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(dob,now);
        if(diff.getYears() >= 18)
            return true;
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean inputHopLe() {
        tvError.setText("");
        for (int i = 0; i<listDuKhach.size();i++){
            DuKhach duKhach = listDuKhach.get(i);
            String hoTen = duKhach.getHoTenDuDhack().trim();
            String ngaySinh = duKhach.getNgaySinh().trim();
            String SDT = duKhach.getSoDienThoai().trim();
            String CMND = duKhach.getChungMinhNhanDan().trim();
            String strNgayDen = etNgayDen.getText().toString().trim();
            String strNgayDi = etNgayDi.getText().toString().trim();
            try {
                Date ngayDen = simpleDateFormat.parse(strNgayDen);
                Date ngayDi = simpleDateFormat.parse(strNgayDi);
                if(ngayDen.compareTo(ngayDi) >= 0){
                    tvError.setText("Ngày đến phải sớm hơn ngày đi");
                    return false;
                }
                Date c = Calendar.getInstance().getTime();
                String formattedDate = simpleDateFormat.format(c);
                Date currentDate = simpleDateFormat.parse(formattedDate);
                if(ngayDen.compareTo(currentDate) < 0 || ngayDi.compareTo(currentDate) < 0){
                    tvError.setText("Ngày đến và ngày đi phải ở tương lai");
                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (hoTen.isEmpty()){
                tvError.setText("Xin hãy đủ tên du khách");
                return false;
            }else if(ngaySinh.isEmpty()){
                tvError.setText("Xin hãy đủ ngày sinh du khách");
                return false;
            }
            if(i==0){// người đại diện hoặc người đi một mình
                if(SDT.isEmpty()){
                    tvError.setText("Xin hãy nhập số điện thoại");
                    return false;
                }else if(CMND.isEmpty()){
                    tvError.setText("Xin hãy nhập số chứng minh nhân dân");
                    return false;
                }else if(! du18Tuoi(ngaySinh)){
                    tvError.setText("Người đặt phòng phải đủ 18");
                    return false;
                }
            }else{
                if(du14Tuoi(ngaySinh) && CMND.isEmpty()){
                    tvError.setText("Xin hãy nhập số chứng minh nhân dân");
                    return false;
                }
            }

        }

        return true;
    }
    //>>>>>>>>>> end of Validate input

    //<<<<<<<< UI create
    @SuppressLint("ResourceAsColor")
    private TextView createTextView (int textResId){
        TextView textView = new TextView(this);
        textView.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,1.0f));
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        textView.setText(textResId);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        return textView;
    }
    private EditText createEditText (int id){
        EditText editText = new EditText(this);
        TableRow.LayoutParams lo = new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,3.0f);
        lo.setMargins(5,5,5,5);
        editText.setLayoutParams(lo);
        editText.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        editText.setId(id);
        return editText;
    }
    @SuppressLint("ResourceAsColor")
    private void ShowViewDangKiNDC (String numberStr) {
        int numNguoiDiCung = Integer.parseInt(numberStr);
        tableLayoutNguoiDiCung.removeAllViews();
        for (int i = 0; i < numNguoiDiCung; i++){
            TableRow tableRow;
            TableRow.LayoutParams lo = new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,1.0f);
            lo.setMargins(0,15,0,0);
            // title
            tableRow = new TableRow(this);
            TextView textViewTitle = new TextView(this);
            textViewTitle.setLayoutParams(lo);
            textViewTitle.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
            textViewTitle.setGravity(Gravity.CENTER);
            textViewTitle.setText("Người "+(i+1));
            textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,28);
            tableRow.addView(textViewTitle);
            tableLayoutNguoiDiCung.addView(tableRow);

            // Ho ten
            tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(createTextView(R.string.ho_ten_prompt));
            tableRow.addView(createEditText(etHoTenNDCID[i]));
            tableLayoutNguoiDiCung.addView(tableRow);

            // SDT
            tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(createTextView(R.string.so_dien_thoai_prompt));
            tableRow.addView(createEditText(etSDTNDCID[i]));
            tableLayoutNguoiDiCung.addView(tableRow);

            // Ngay sinh
            tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(createTextView(R.string.ngay_sinh_prompt));
            EditText etNgaySinh = createEditText(etNgaySinhNDCID[i]);
            etNgaySinh.setInputType(InputType.TYPE_NULL);
            etNgaySinh.setOnFocusChangeListener(this);
            tableRow.addView(etNgaySinh);
            tableLayoutNguoiDiCung.addView(tableRow);

            // CMND
            tableRow = new TableRow(this);
            tableRow.setId(tableRowCMNDNDCID[i]);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(createTextView(R.string.cmnd_prompt));
            tableRow.addView(createEditText(etCMNDNDCID[i]));
            tableLayoutNguoiDiCung.addView(tableRow);
        }
    }
    private void ShowViewPhong(String numberStr) {
        int numPhong = Integer.parseInt(numberStr);
        tableLayoutPhong.removeAllViews();
        for (int i = 0; i < numPhong; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));

            // Phong thứ
            TextView textView = new TextView(this);
            textView.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,1.0f));
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
            textView.setText("Phòng "+ (i+1));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            tableRow.addView(textView);

            //spinner loại phòng
            Spinner spnLoaiPhong = new Spinner(this);
            spnLoaiPhong.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,3.0f));
            ArrayAdapter<String> arrAdapterLoaiPhong= new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listLoaiPhong);
            spnLoaiPhong.setAdapter(arrAdapterLoaiPhong);
            spnLoaiPhong.setOnItemSelectedListener(this);
            spnLoaiPhong.setId(spnLoaiPhongID[i]);
            tableRow.addView(spnLoaiPhong);

            tableLayoutPhong.addView(tableRow);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.thong_tin_khach_hang);
        String msg = "";
        for(int i = 0; i<listDuKhach.size();i++){
            DuKhach duKhach = listDuKhach.get(i);
            if(listDuKhach.size()>1){
                if(i==0)
                    msg = msg.concat("\tNgười đại diện:\n");
                else
                    msg = msg.concat("\tNgười đi cùng thứ "+i+":\n");
            }
            msg = msg.concat("Họ tên: "+ duKhach.getHoTenDuDhack()+"\n");
            msg = msg.concat("SĐT: "+ duKhach.getSoDienThoai()+"\n");
            msg = msg.concat("Ngày sinh: "+ duKhach.getNgaySinh()+"\n");
            if(du14Tuoi(duKhach.getNgaySinh()))
                msg = msg.concat("CMND: "+ duKhach.getChungMinhNhanDan()+"\n");
            msg = msg.concat("\n");
        }
        msg = msg.concat("\tThông tin phòng: \n");
        for(int i = 0; i<listLoaiPhongCanDat.size();i++){
            String loaiPhong = listLoaiPhongCanDat.get(i);
            msg = msg.concat("Phòng "+(i+1)+": "+ loaiPhong +"\n");
        }
        msg = msg.concat("\tNgày đến: "+etNgayDen.getText().toString()+"\n");
        msg = msg.concat("\tNgày đi: "+etNgayDi.getText().toString()+"\n");
        builder.setMessage(msg);

        builder.setPositiveButton(R.string.dat_phong_string, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                datPhong();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showDatePickerDialog(final int etNgayID) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DatPhongActivity.this, new DatePickerDialog.OnDateSetListener() {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePickerDialogForNDC(final int etNgaySinhID, final int tableRowToHideID) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DatPhongActivity.this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText etNgaySinh = findViewById(etNgaySinhID);
                TableRow tableRowToHide = findViewById(tableRowToHideID);
                String msg = year + "-" + (month + 1) + "-" + dayOfMonth;
                etNgaySinh.setText(msg);
                if(! du14Tuoi(msg))
                    tableRowToHide.setVisibility(View.GONE);
                else
                    tableRowToHide.setVisibility(View.VISIBLE);
            }
        },year,month,date);

        datePickerDialog.setCanceledOnTouchOutside(false);
        datePickerDialog.show();
    }
    private void showNotEnoughRoom(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Không đủ phòng");
        message = message.concat("\nHãy chọn loại phòng khác hoặc khung thời gian khác");
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showDatPhongThanhCong() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dăng kí phòng thành công");
        String message = "Kết quả đăng kí:\n";
        for (Phong phong : listPhongConTrong){
            message = message.concat(phong.getTenLoaiPhong() +" - "+phong.getMaPhong()+"\n");
        }
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    //>>>>>>>>>> end of UI create

    //<<<<<<<<< network callback
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void successHandling(boolean duPhong, ArrayList<Phong> listPhongConTrong, String messge) {
        if(duPhong){//đủ phòng
            listMaPhong.clear();
            for(Phong phongTrong: listPhongConTrong){
                listMaPhong.add(phongTrong.getMaPhong());
            }
            showConfirmDialog();
        }else{//ko đủ phòng
            showNotEnoughRoom(messge);
        }
    }
    private void failHandling(String messge) {
        tvError.setText(messge);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSuccess(JSONObject jsonObj) {
        try {
            JSONObject root = jsonObj;
            int callbackCode = root.getInt("callbackCode");
            if(callbackCode == DuKhachAdder.CALLBACK_CODE){
                boolean status = root.getBoolean("status");
                String message = root.getString("message");
                if(status){
                    showDatPhongThanhCong();
                }else {
                    failHandling(message);// CMND đã được đăng kí
                }

            }else if (callbackCode == DatPhongChecker.CALLBACK_CODE){
                boolean status = root.getBoolean("status");
                String message = root.getString("message");
                JSONArray data = root.getJSONArray("data");
                for(int i = 0;i<data.length();i++){
                    JSONObject dataItem = data.getJSONObject(i);
                    String TENLP = dataItem.getString(Phong.TENLP_COLUMN);
                    String MAP = dataItem.getString(Phong.MAP_COLUMN);

                    listPhongConTrong.add(new Phong(TENLP,MAP));
                }
                successHandling(status,listPhongConTrong,message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onFail(String error) {//hàm sẽ được gọi khi SendJSONObject thất bại
        Log.e("DatPhongActivity","onFail: "+ error);
    }
    //>>>>>>>>>>> end of network callback
}

