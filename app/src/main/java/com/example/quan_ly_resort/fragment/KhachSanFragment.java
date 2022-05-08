package com.example.quan_ly_resort.fragment;

import android.content.Intent;
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
import com.example.quan_ly_resort.activity.DeluxeActivity;
import com.example.quan_ly_resort.activity.NapTienActivity;
import com.example.quan_ly_resort.activity.StandardActivity;
import com.example.quan_ly_resort.activity.SuperiorActivity;

import java.util.zip.Inflater;

import static android.app.Activity.RESULT_OK;

public class KhachSanFragment extends Fragment {
    LinearLayout scrollViewStandard;
    int[] standardImages = {
            R.drawable.standard_1,
            R.drawable.standard_2,
            R.drawable.standard_3,
            R.drawable.standard_4,
            R.drawable.standard_5,
    };

    LinearLayout scrollViewSuperior;
    int[] superiorImages = {
            R.drawable.superior_1,
            R.drawable.superior_2,
            R.drawable.superior_3,
            R.drawable.superior_4,
            R.drawable.superior_5,
    };

    LinearLayout scrollViewDeluxe;
    int[] deluxeImages = {
            R.drawable.deluxe_1,
            R.drawable.deluxe_2,
            R.drawable.deluxe_3,
            R.drawable.deluxe_4,
            R.drawable.deluxe_5,
    };

    Button btStandard;
    Button btSuperior;
    Button btDeluxe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_san,container,false);
        btStandard = view.findViewById(R.id.btStandard);
        btSuperior = view.findViewById(R.id.btSuperior);
        btDeluxe = view.findViewById(R.id.btDeluxe);
        btStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), StandardActivity.class);
                startActivity(intent);
            }
        });
        btSuperior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), SuperiorActivity.class);
                startActivity(intent);
            }
        });
        btDeluxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DeluxeActivity.class);
                startActivity(intent);
            }
        });
        scrollViewStandard = view.findViewById(R.id.scrollViewStandard);
        for (int i = 0; i < standardImages.length; i++) {
            View singleFrame = inflater.inflate(R.layout.horizontal_scroll_view_item, null, false);
            singleFrame.setId(i);
            ImageView thumbnail = singleFrame.findViewById(R.id.ivThumbnail);
            thumbnail.setImageResource(standardImages[i]);

            if(thumbnail.getParent() != null)
                ((ViewGroup)thumbnail.getParent()).removeView(thumbnail);
            scrollViewStandard.addView(thumbnail);
        }

        scrollViewSuperior = view.findViewById(R.id.scrollViewSuperior);
        for (int i = 0; i < superiorImages.length; i++) {
            View singleFrame = inflater.inflate(R.layout.horizontal_scroll_view_item, null, false);
            singleFrame.setId(i);
            ImageView thumbnail = singleFrame.findViewById(R.id.ivThumbnail);
            thumbnail.setImageResource(superiorImages[i]);

            if(thumbnail.getParent() != null)
                ((ViewGroup)thumbnail.getParent()).removeView(thumbnail);

            scrollViewSuperior.addView(thumbnail);
        }

        scrollViewDeluxe = view.findViewById(R.id.scrollViewDeluxe);
        for (int i = 0; i < deluxeImages.length; i++) {
            View singleFrame = inflater.inflate(R.layout.horizontal_scroll_view_item, null, false);
            singleFrame.setId(i);
            ImageView thumbnail = singleFrame.findViewById(R.id.ivThumbnail);
            thumbnail.setImageResource(deluxeImages[i]);

            if(thumbnail.getParent() != null)
                ((ViewGroup)thumbnail.getParent()).removeView(thumbnail);

            scrollViewDeluxe.addView(thumbnail);
        }
        return view;
    }

}


