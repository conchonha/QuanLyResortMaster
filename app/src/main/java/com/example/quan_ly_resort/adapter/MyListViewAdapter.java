package com.example.quan_ly_resort.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.model.DichVu;

import java.util.ArrayList;

public class MyListViewAdapter extends ArrayAdapter<DichVu> implements Filterable {
    private DichVuFilter dichVuFilter;
    private  ArrayList<DichVu> originalData;
    private  ArrayList<DichVu> filteredData;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public MyListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DichVu> objects) {
        super(context, resource, objects);
        originalData = objects;
        filteredData = objects;

        dichVuFilter = new DichVuFilter();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.dich_vu_item,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(itemView);

        myViewHolder.tvItemTitle.setText(getItem(position).getTenDichVu());
        myViewHolder.tvItemMoTa.setText(getItem(position).getMoTa());
        myViewHolder.tvItemKeyword.setText(getItem(position).getStrKeyword());
        myViewHolder.ivItemIamge.setImageResource(getItem(position).getIdHinh());

        return itemView;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public DichVu getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private class MyViewHolder {
        TextView tvItemTitle;
        TextView tvItemMoTa;
        TextView tvItemKeyword;
        ImageView ivItemIamge;

        public MyViewHolder(View itemView) {
            tvItemTitle = itemView.findViewById(R.id.tvItemTitle);
            tvItemMoTa = itemView.findViewById(R.id.tvItemMoTa);
            tvItemKeyword = itemView.findViewById(R.id.tvItemKeyword);
            ivItemIamge = itemView.findViewById(R.id.ivItemIamge);
        }
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return dichVuFilter;
    }

    private class DichVuFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<DichVu> listResult = new ArrayList<>();

                String filterString = constraint.toString().trim().toLowerCase();
                String[] listWord = filterString.split("[\\p{Punct}\\s]+");
                for(String word : listWord){
                    for (DichVu dv: originalData){
                        if(dv.getStrKeyword().contains(word) && !listResult.contains(dv))
                            listResult.add(dv);
                    }
                }

                filterResults.values = listResult;
                filterResults.count = listResult.size();
            } else {
                filterResults.values = originalData;
                filterResults.count = originalData.size();
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<DichVu>) results.values;
            notifyDataSetChanged();
        }
    }
}
