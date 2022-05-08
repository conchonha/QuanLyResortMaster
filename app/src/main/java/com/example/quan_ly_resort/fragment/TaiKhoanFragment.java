package com.example.quan_ly_resort.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.activity.LoginActivity;
import com.example.quan_ly_resort.activity.NapTienActivity;
import com.example.quan_ly_resort.activity.RutTienActivity;
import com.example.quan_ly_resort.activity.ThanhToanActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.DuKhach;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.model.User;
import com.example.quan_ly_resort.networkCallback.UserSetter;
import com.example.quan_ly_resort.util.ObjectSerializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class TaiKhoanFragment extends androidx.fragment.app.Fragment implements View.OnClickListener, VolleyCallback {
    Button btNapTien;
    Button btRutTien;
    Button btThanhToan;
    Button btLogout;

    TextView tvSoDu;
    ToggleButton toggleButton;
    User user = null;

    TextView tvHoTen;
    TextView tvEmail;
    TextView tvNgaySinh;
    TextView tvSDT;
    TextView tvCMND;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        updateTextView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tai_khoan,container,false);

        btNapTien = view.findViewById(R.id.btNapTien);
        btRutTien = view.findViewById(R.id.btRutTien_actRutTien);
        btLogout = view.findViewById(R.id.btLogout);
        tvSoDu = view.findViewById(R.id.tvSoDu_fragTaiKhoan);
        btThanhToan = view.findViewById(R.id.btThanhToan);
        toggleButton = view.findViewById(R.id.toggleButtonEye);
        tvHoTen = view.findViewById(R.id.tvHoTen);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        tvSDT = view.findViewById(R.id.tvSDT);
        tvCMND = view.findViewById(R.id.tvCMND);


        btNapTien.setOnClickListener(this);
        btRutTien.setOnClickListener(this);
        btThanhToan.setOnClickListener(this);
        toggleButton.setOnClickListener(this);
        btLogout.setOnClickListener(this);

        updateTextView();

        return view;
    }


    private void updateTextView(){
        SharedPreferences sharedPrefs = requireActivity().getSharedPreferences("Shared_Preferences", Activity.MODE_PRIVATE);

        String maDuKhach = sharedPrefs.getString(DuKhach.MADK_COLUMN,"");
        String maDuKhackDaiDien = sharedPrefs.getString(DuKhach.MADKDAIDIEN_COLUMN,"");
        String hoTenDuKhach = sharedPrefs.getString(DuKhach.HOTENDK_COLUMN,"");
        String soDienThoai = sharedPrefs.getString(DuKhach.SDT_COLUMN,"");
        String ngaySinh = sharedPrefs.getString(DuKhach.NGAYSINH_COLUMN,"");
        String CMND = sharedPrefs.getString(DuKhach.CMND_COLUMN,"");
        String maTaiKhoan = sharedPrefs.getString(TaiKhoan.MATK_COLUMN,"");
        String emailTaiKhoan = sharedPrefs.getString(TaiKhoan.EMAILTK_COLUMN,"");
        String matKhauTaiKHoan = sharedPrefs.getString(TaiKhoan.MATKHAUTK_COLUMN,"");
        String maTheNganHang = sharedPrefs.getString(TaiKhoan.MATHENGANHANG_COLUMN,"");
        int soDuTrongVi = sharedPrefs.getInt(TaiKhoan.SODUTRONGVI_COLUMN,0);

        DuKhach duKhach = new DuKhach(maDuKhach,maTaiKhoan,maDuKhackDaiDien,hoTenDuKhach,soDienThoai,ngaySinh,CMND);
        TaiKhoan taiKhoan = new TaiKhoan(maTaiKhoan,maDuKhach,emailTaiKhoan,matKhauTaiKHoan,maTheNganHang,soDuTrongVi);
        user = new User(duKhach,taiKhoan);

        if (toggleButton.isChecked())
            tvSoDu.setTransformationMethod(null);
        else
            tvSoDu.setTransformationMethod(new PasswordTransformationMethod());
        tvSoDu.setText(user.getSoDuTrongVi() + " đ");
        tvHoTen.setText(user.getHoTenDuDhack());
        tvEmail.setText(user.getEmailTaiKhoan());
        tvNgaySinh.setText(user.getNgaySinh());
        tvSDT.setText(user.getSoDienThoai());
        tvCMND.setText(user.getChungMinhNhanDan());
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        Intent myIntent ;
        Bundle myBundle = new Bundle();
        switch (v.getId()){
            case R.id.btNapTien:
                myIntent = new Intent(requireActivity(),NapTienActivity.class);

                myBundle.putInt(TaiKhoan.SODUTRONGVI_COLUMN, user.getSoDuTrongVi());
                myBundle.putString(TaiKhoan.MATHENGANHANG_COLUMN, user.getTaiKhoan().getMaTheNganHang());

                myIntent.putExtras(myBundle);
                startActivityForResult(myIntent,NapTienActivity.REQUEST_CODE);
                break;

            case R.id.btRutTien_actRutTien:
                myIntent = new Intent(requireActivity(), RutTienActivity.class);

                myBundle.putInt(TaiKhoan.SODUTRONGVI_COLUMN, user.getSoDuTrongVi());
                myBundle.putString(TaiKhoan.MATHENGANHANG_COLUMN, user.getTaiKhoan().getMaTheNganHang());

                myIntent.putExtras(myBundle);
                startActivityForResult(myIntent,RutTienActivity.REQUEST_CODE);
                break;
            case R.id.btThanhToan:
                myIntent = new Intent(requireActivity(), ThanhToanActivity.class);

                myBundle.putString(TaiKhoan.MATK_COLUMN,user.getMaTaiKhoan());
                myBundle.putInt(TaiKhoan.SODUTRONGVI_COLUMN,user.getSoDuTrongVi());

                myIntent.putExtras(myBundle);
                startActivityForResult(myIntent,ThanhToanActivity.REQUEST_CODE);
                break;
            case R.id.toggleButtonEye:
                if (toggleButton.isChecked())
                    tvSoDu.setTransformationMethod(null);
                else
                    tvSoDu.setTransformationMethod(new PasswordTransformationMethod());
                break;
            case R.id.btLogout:
                SharedPreferences sharedPrefs = requireActivity().getSharedPreferences("Shared_Preferences", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.clear();
                editor.commit();
                MainActivity.boolAlreadyLogin = false;

                myIntent = new Intent(requireActivity(), LoginActivity.class);
                startActivityForResult(myIntent,LoginActivity.REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NapTienActivity.REQUEST_CODE && resultCode == RESULT_OK){
            Bundle resultBundle = data.getExtras();
            int soDuMoi = resultBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN);
            user.setSoDuTrongVi(soDuMoi);
            new UserSetter(requireContext(),this,user);

        }
        if(requestCode == RutTienActivity.REQUEST_CODE && resultCode == RESULT_OK){
            Bundle resultBundle = data.getExtras();
            int soDuMoi = resultBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN);
            user.setSoDuTrongVi(soDuMoi);
            new UserSetter(requireContext(),this,user);
        }
        if(requestCode == ThanhToanActivity.REQUEST_CODE && resultCode == RESULT_OK){
            Bundle resultBundle = data.getExtras();
            int soDuMoi = resultBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN);
            user.setSoDuTrongVi(soDuMoi);
            new UserSetter(requireContext(),this,user);
        }
    }

    @Override
    public void onSuccess(JSONObject jsonObj) {// sửa thông tin tái khoản ( thay đổi số dư)
        JSONObject root = jsonObj;
        try {
            boolean status = root.getBoolean("status");
            String message = root.getString("message");
            if (status) {
                JSONArray data = root.getJSONArray("data");
                User user = null;
                for (int i = 0; i < data.length(); i++) {
                    JSONObject dataItem = data.getJSONObject(i);
                    String MATK = dataItem.getString("MATK");
                    String EMAILTK = dataItem.getString("EMAILTK");
                    String MATKHAUTK = dataItem.getString("MATKHAUTK");
                    String MATHENGANHANG = dataItem.getString("MATHENGANHANG");
                    String MADK = dataItem.getString("MADK");
                    String MADKDAIDIEN = dataItem.getString("MADKDAIDIEN");
                    String HOTENDK = dataItem.getString("HOTENDK");
                    String SDT = dataItem.getString("SDT");
                    String NGAYSINH = dataItem.getString("NGAYSINH");
                    String CMND = dataItem.getString("CMND");
                    int SODUTRONGVI = dataItem.getInt("SODUTRONGVI");
                    String MADV = dataItem.getString("MADV");

                    if(user == null)
                        user = new User(MATK, EMAILTK, MATKHAUTK, MATHENGANHANG, MADK, MADKDAIDIEN, HOTENDK, SDT, NGAYSINH, CMND, SODUTRONGVI);

                    user.addMaDichVuDaDangKi(MADV);
                }
                updateSharedPreferences(user);
                updateTextView();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(String error) {
        Log.e("LoginActivity","onFail: " + error);
    }

    private void updateSharedPreferences(User user){
        SharedPreferences sharedPrefs = requireActivity().getSharedPreferences("Shared_Preferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(DuKhach.MADK_COLUMN,user.getMaDuKhach());
        editor.putString(DuKhach.MADKDAIDIEN_COLUMN,user.getMaDuKhachDaiDien());
        editor.putString(DuKhach.HOTENDK_COLUMN,user.getHoTenDuDhack());
        editor.putString(DuKhach.SDT_COLUMN,user.getSoDienThoai());
        editor.putString(DuKhach.NGAYSINH_COLUMN,user.getNgaySinh());
        editor.putString(DuKhach.CMND_COLUMN,user.getChungMinhNhanDan());
        editor.putString(TaiKhoan.MATK_COLUMN,user.getMaTaiKhoan());
        editor.putString(TaiKhoan.EMAILTK_COLUMN,user.getEmailTaiKhoan());
        editor.putString(TaiKhoan.MATKHAUTK_COLUMN,user.getMatKhauTaiKhoan());
        editor.putString(TaiKhoan.MATHENGANHANG_COLUMN,user.getMaTheNganHang());
        editor.putInt(TaiKhoan.SODUTRONGVI_COLUMN,user.getSoDuTrongVi());
        try {
            editor.putString(User.DICH_VU_DA_DANG_KY,ObjectSerializer.serialize(user.getListMaDichVuDaDangKy()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        editor.commit();
    }
}
