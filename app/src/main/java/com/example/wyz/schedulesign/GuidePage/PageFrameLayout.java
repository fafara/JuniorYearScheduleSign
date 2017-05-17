package com.example.wyz.schedulesign.GuidePage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.wyz.schedulesign.Mvp.Activity.SplashActivity;
import com.example.wyz.schedulesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYZ on 2017/5/12.
 */

public class PageFrameLayout extends FrameLayout implements ViewPager.OnPageChangeListener {

    private List<PageFragment> mPageFragmentList=new ArrayList<>();
    private  ImageView[] mImagearray=null;

    private LinearLayout mLinearLayout;
    private int dot_on,dot_off;
    private ViewPager mViewPager;
    public PageFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * dpתpx
     */
    public int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 设置资源文件并初始化控件
     *
     * @param layoutIds
     */
    public void setUpViews(int layoutIds[], int dot_on, int dot_off) {
        this.dot_on = dot_on;
        this.dot_off = dot_off;
        mImagearray = new ImageView[layoutIds.length];
        mLinearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                dip2px(getContext(), 100));
        mLinearLayout.setGravity(Gravity.CENTER);
        params.gravity = Gravity.BOTTOM;
        mLinearLayout.setLayoutParams(params);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                15,
                15);
        lp.setMargins(8, 0, 8, 0);

        for (int i = 0; i < layoutIds.length; i++) {
            PageFragment pageFragment = new PageFragment();
            Bundle args = new Bundle();
            args.putInt("index", i);
            args.putInt("count", layoutIds.length);
            args.putInt("layoutId", layoutIds[i]);

            pageFragment.setArguments(args);
            mPageFragmentList.add(pageFragment);
            ImageView view = new ImageView(getContext());
            view.setImageResource(dot_on);
            view.setLayoutParams(lp);

            mImagearray[i] = view;
            mLinearLayout.addView(mImagearray[i]);
        }

        setSelectVp(0);
        SplashActivity activity = (SplashActivity) getContext();
        mViewPager = new ViewPager(getContext());
        mViewPager.setId(R.id.id_page);
        mViewPager.setAdapter(new PageFragmentAdapter(activity.getSupportFragmentManager(), mPageFragmentList));
        mViewPager.addOnPageChangeListener(this);
        addView(mViewPager);
        addView(mLinearLayout);
    }
    /**
     * 切换轮播图点
     *
     * @param index
     */
    public void setSelectVp(int index) {
        for (int i = 0; i < mImagearray.length; i++) {
            if (i == index) {
                mImagearray[i].setImageResource(dot_on);
            } else {
                mImagearray[i].setImageResource(dot_off);
            }
        }
    }
    public void setPageTransformer(){

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position==mPageFragmentList.size()-1){
            mLinearLayout.setAlpha(0);
        }else{
            mLinearLayout.setAlpha(1);
        }
    }

    @Override
    public void onPageSelected(int position) {
        setSelectVp(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
