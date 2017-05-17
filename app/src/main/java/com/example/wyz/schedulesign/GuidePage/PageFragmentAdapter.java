package com.example.wyz.schedulesign.GuidePage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYZ on 2017/5/12.
 */

public class PageFragmentAdapter extends FragmentPagerAdapter {
    private  List<PageFragment> mPageFragments=new ArrayList<>();
    public PageFragmentAdapter(FragmentManager fm,List<PageFragment> pageFragments) {
        super(fm);
        this.mPageFragments=pageFragments;
    }



    @Override
    public int getCount() {
        return mPageFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mPageFragments.get(position);
    }
}
