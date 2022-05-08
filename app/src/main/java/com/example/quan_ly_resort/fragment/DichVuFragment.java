package com.example.quan_ly_resort.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.activity.DichVuDetailActivity;
import com.example.quan_ly_resort.activity.NapTienActivity;
import com.example.quan_ly_resort.adapter.MyListViewAdapter;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.DichVu;
import com.example.quan_ly_resort.model.Phong;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.model.User;
import com.example.quan_ly_resort.networkCallback.DichVuGetter;
import com.example.quan_ly_resort.util.ObjectSerializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DichVuFragment extends Fragment implements VolleyCallback{
    ArrayList<DichVu> listDichVu;
    ListView lvDichVu;
    MyListViewAdapter myListViewAdapter;
    SearchView svDichVu;

    ArrayList<String> listMaDichVuDaDangKy;
    String MATK;
    int soDuTrongVi;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listMaDichVuDaDangKy = new ArrayList<>();
        listDichVu = new ArrayList<>();
        myListViewAdapter = new MyListViewAdapter(requireActivity().getApplicationContext(),R.layout.dich_vu_item,listDichVu);
        new DichVuGetter(requireActivity().getApplicationContext(),this);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateThongTinUserCanThiet();
    }

    private void updateThongTinUserCanThiet() {
        SharedPreferences sharedPrefs = requireActivity().getSharedPreferences("Shared_Preferences", Activity.MODE_PRIVATE);
        try {
            listMaDichVuDaDangKy = (ArrayList<String>) ObjectSerializer.deserialize(sharedPrefs.getString(User.DICH_VU_DA_DANG_KY, ObjectSerializer.serialize(new ArrayList<>())));
            soDuTrongVi = sharedPrefs.getInt(TaiKhoan.SODUTRONGVI_COLUMN,0);
            MATK = sharedPrefs.getString(TaiKhoan.MATK_COLUMN,"");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dich_vu,container,false);
        lvDichVu = view.findViewById(R.id.lvDichVu);
        lvDichVu.setAdapter(myListViewAdapter);
        lvDichVu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DichVu dv = (DichVu) parent.getItemAtPosition(position);
                Intent myIntent = null;
                Bundle myBundle = new Bundle();

                myIntent = new Intent(requireActivity(), DichVuDetailActivity.class);

                myBundle.putString(DichVu.TENDV_COLUMN, dv.getTenDichVu());
                myBundle.putString(DichVu.MOTA_COLUMN,dv.getMoTa());
                myBundle.putInt(DichVu.ID_HINH,dv.getIdHinh());
                myBundle.putInt(DichVu.GIA_COLUMN,dv.getGia());
                myBundle.putString(DichVu.STR_KEYWORD,dv.getStrKeyword());
                myBundle.putBoolean(DichVu.PHAIDANGKI_COLUMN,dv.isPhaiDangKi());
                boolean boolDaDangKy = listMaDichVuDaDangKy.contains(dv.getMaDichVu());
                myBundle.putBoolean(DichVu.DA_DANG_KY,boolDaDangKy);
                myBundle.putString(DichVu.MADV_COLUMN,dv.getMaDichVu());
                myBundle.putInt(TaiKhoan.SODUTRONGVI_COLUMN,soDuTrongVi);
                myBundle.putString(TaiKhoan.MATK_COLUMN,MATK);

                myIntent.putExtras(myBundle);
                startActivity(myIntent);

            }
        });
        svDichVu = view.findViewById(R.id.svDichVu);
        svDichVu.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return false;}

            @Override
            public boolean onQueryTextChange(String newText) {
                myListViewAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSuccess(JSONObject jsonObj) {
        JSONObject root = jsonObj;
        try {
            boolean status = root.getBoolean("status");
            String message = root.getString("message");
            if(status){
                ArrayList<String> listMADV = new ArrayList<>();

                JSONArray data = root.getJSONArray("data");
                for(int i = 0;i<data.length();i++){
                    JSONObject dataItem = data.getJSONObject(i);
                    String MADV = dataItem.getString("MADV");
                    String TENDV = dataItem.getString("TENDV");
                    String MOTA = dataItem.getString("MOTA");
                    int GIA = dataItem.getInt("GIA");
                    String TENHINH = dataItem.getString("TENHINH");
                    String KEYWORD = dataItem.getString("KEYWORD");
                    int PHAIDANGKI = dataItem.getInt("PHAIDANGKI");

                    String tenFileHinh = TENHINH+"_1";//luôn lấy hình đầu tiên của dịch vụ
                    boolean boolPhaiDangKi = (PHAIDANGKI == 1) ? true : false;

                    int idHinh = requireActivity().getApplicationContext().getResources().getIdentifier(tenFileHinh,"drawable",requireActivity().getPackageName());

                    if (listMADV.indexOf(MADV) == -1){// MaDivhVu xuất hiện lần đầu tiên
                        listMADV.add(MADV);
                        listDichVu.add(new DichVu(MADV,TENDV,MOTA,GIA,idHinh,KEYWORD,boolPhaiDangKi));
                    }else{
                        for(DichVu dichVu : listDichVu){
                            if(dichVu.getMaDichVu().equals(MADV))
                                dichVu.addKeyWord(KEYWORD);
                        }
                    }

                }

                myListViewAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFail(String error) {
        Log.e("DichVuFragment","onFail "+error);
    }
}