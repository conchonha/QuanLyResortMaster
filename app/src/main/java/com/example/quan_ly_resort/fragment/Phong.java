package com.example.quan_ly_resort.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.activity.DetailPhongRS;
import com.example.quan_ly_resort.adapter.AdapterPhong;
import com.example.quan_ly_resort.adapter.action.IOnItemClickAdapter;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.PhongRS;
import com.example.quan_ly_resort.networkCallback.PhongGetter;
import com.example.quan_ly_resort.util.Const;
import com.example.quan_ly_resort.util.Helper;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

public class Phong extends Fragment implements VolleyCallback, IOnItemClickAdapter {
    private View view;
    private PhongRS phongRS = new PhongRS();
    private AdapterPhong adapterPhong = new AdapterPhong(this);
    private Helper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phong, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new PhongGetter(requireContext(), this);
        helper.showProgressLoading();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("SangTB", "onViewCreated: ");
        init();
        listenerData();
    }

    private void listenerData() {
        phongRS.getListLive().observe(getViewLifecycleOwner(), new Observer<List<PhongRS>>() {
            @Override
            public void onChanged(List<PhongRS> phongRS) {
                helper.dismisProgess();
                adapterPhong.submitList(phongRS);
            }
        });
    }

    private void init() {
        helper = new Helper(requireContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerPhong);
        recyclerView.setAdapter(adapterPhong);
    }

    @Override
    public void onSuccess(JSONObject jsonObj) {
        Log.d("Phong", "onSuccess: " + jsonObj);
        helper.dismisProgess();
        phongRS.convertJson(jsonObj);
    }

    @Override
    public void onFail(String error) {
        helper.dismisProgess();
        Log.e("Phong", "onFail: " + error);
    }

    public void onClick(PhongRS phongRS) {
        Log.d("Phong", "onClick: "+phongRS);
        Intent intent = new Intent(requireContext(), DetailPhongRS.class);
        intent.putExtra(Const.PHONG_RS_ID,phongRS);
        startActivity(intent);
    }

}
