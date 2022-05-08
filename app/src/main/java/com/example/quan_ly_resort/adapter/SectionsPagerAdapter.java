package com.example.quan_ly_resort.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.fragment.DichVuFragment;
import com.example.quan_ly_resort.fragment.KhachSanFragment;
import com.example.quan_ly_resort.fragment.LienHeFragment;
import com.example.quan_ly_resort.fragment.Phong;
import com.example.quan_ly_resort.fragment.TaiKhoanFragment;
import com.example.quan_ly_resort.fragment.TrangChuFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.trang_chu_string,
            R.string.khach_san_string,
            R.string.dich_vu_string,
            R.string.phong,
            R.string.lien_he_string,
            R.string.tai_khoan_string
    };
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {// tạo fragment tương ứng với tab được chọn
        Fragment fragment = null;
        switch (TAB_TITLES[position]) {
            case R.string.trang_chu_string:
                fragment = new TrangChuFragment();
                break;
            case R.string.khach_san_string:
                fragment = new KhachSanFragment();
                break;
            case R.string.dich_vu_string:
                fragment = new DichVuFragment();
                break;
            case R.string.phong:
                fragment = new Phong();
                break;
            case R.string.lien_he_string:
                fragment = new LienHeFragment();
                break;
            case R.string.tai_khoan_string:
                fragment = new TaiKhoanFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

}