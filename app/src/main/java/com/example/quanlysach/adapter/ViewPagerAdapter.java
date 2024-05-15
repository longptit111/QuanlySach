package com.example.quanlysach.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quanlysach.fragment.FragmentHistory;
import com.example.quanlysach.fragment.FragmentHome;
import com.example.quanlysach.fragment.FragmentStatistic;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new FragmentHome();
            case 1:return new FragmentHistory();
            case 2:return new FragmentStatistic();
            default:return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
