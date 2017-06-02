package com.example.wyz.schedulesign.Mvp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wyz.schedulesign.GuidePage.PageFrameLayout;
import com.example.wyz.schedulesign.R;

public class SplashActivity extends AppCompatActivity {

    private PageFrameLayout mPageFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPageFrameLayout=(PageFrameLayout)findViewById(R.id.contentLayout);
        mPageFrameLayout.setUpViews(new int[]{R.layout.fragment_guide1,R.layout.fragment_guide2,R.layout.fragment_guide4},
                R.mipmap.banner_on,R.mipmap.banner_off);
        mPageFrameLayout.setPageTransformer();

    }
}
