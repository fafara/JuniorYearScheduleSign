package com.example.wyz.schedulesign.Mvp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by WYZ on 2017/5/20.
 */

public   class  MyViewPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> mFragmentList;
    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragmentList=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}