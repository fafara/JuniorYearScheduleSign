package com.example.wyz.schedulesign.Mvp.Activity.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by WYZ on 2017/5/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public  abstract  void initActionBar();

    public  abstract  void initView();
    public  abstract  void initInject();
}
