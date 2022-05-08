package com.example.quan_ly_resort.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.adapter.action.IOnItemClickAdapter;
import com.example.quan_ly_resort.model.PhongRS;
import com.example.quan_ly_resort.util.Helper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterPhong extends RecyclerView.Adapter<AdapterPhong.ViewHolder> {
    private List<PhongRS> listItems = new ArrayList<>();
    private IOnItemClickAdapter onItemClickAdapter;

    public AdapterPhong(IOnItemClickAdapter callback) {
        this.onItemClickAdapter = callback;
    }

    public void submitList(List<PhongRS> list) {
        listItems.clear();
        listItems.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTrangThai;
        private TextView txtIdPhong;
        private Helper helper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            helper = new Helper(itemView.getContext());
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtIdPhong = itemView.findViewById(R.id.txtIdPhong);
            itemView.findViewById(R.id.btnTrangThai).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickAdapter.onClick(listItems.get(getAdapterPosition()));
                }
            });
        }

        public void bind(PhongRS phongRS) {
            txtTrangThai.setText("Trạng Thái: " + helper.getString(phongRS));
            txtIdPhong.setText("Phòng: " + phongRS.id);
        }
    }
}
