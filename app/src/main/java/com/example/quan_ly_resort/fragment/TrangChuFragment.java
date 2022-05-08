package com.example.quan_ly_resort.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_resort.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class TrangChuFragment extends Fragment {
    CarouselView carouselView;
    int[] sampleImages = {
            R.drawable.resort_1,
            R.drawable.restaurant_1,
            R.drawable.room_1,
            R.drawable.gym_1
    };

    LinearLayout scrollViewKhachSan;
    int[] khachSanImages = {
            R.drawable.room_1,
            R.drawable.room_2,
            R.drawable.room_3,
            R.drawable.room_4,
            R.drawable.bathroom_1,
            R.drawable.bathroom_2,
            R.drawable.bathroom_3,
            R.drawable.bathroom_4,
    };

    LinearLayout scrollViewDichVu;
    int[] dichVuImages = {
            R.drawable.bar_1,
            R.drawable.bar_2,
            R.drawable.gym_1,
            R.drawable.gym_2,
            R.drawable.massage_1,
            R.drawable.massage_2,
            R.drawable.resort_1,
            R.drawable.resort_2,
            R.drawable.resort_3,
    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu,container,false);

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener(){
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });



        scrollViewKhachSan = view.findViewById(R.id.scrollViewKhachSan);
        for(int i = 0; i < khachSanImages.length; i++){
            View singleFrame = inflater.inflate(R.layout.horizontal_scroll_view_item,null,false);
            singleFrame.setId(i);
            ImageView thumbnail = singleFrame.findViewById(R.id.ivThumbnail);
            thumbnail.setImageResource(khachSanImages[i]);

            if(thumbnail.getParent() != null)
                ((ViewGroup)thumbnail.getParent()).removeView(thumbnail); // <- fix

            scrollViewKhachSan.addView(thumbnail);
        }

        scrollViewDichVu = view.findViewById(R.id.scrollViewDichVu);
        for(int i = 0; i < dichVuImages.length; i++){
            View singleFrame = inflater.inflate(R.layout.horizontal_scroll_view_item,null,false);
            singleFrame.setId(i);
            ImageView thumbnail = singleFrame.findViewById(R.id.ivThumbnail);
            thumbnail.setImageResource(dichVuImages[i]);

            if(thumbnail.getParent() != null)
                ((ViewGroup)thumbnail.getParent()).removeView(thumbnail); // <- fix

            scrollViewDichVu.addView(thumbnail);
        }

        return view;
    }
}
