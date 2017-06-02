package com.example.wyz.schedulesign.Mvp.Activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import butterknife.ButterKnife;

/**
 * Created by WYZ on 2017/5/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public  abstract  void initActionBar();

    public  abstract  void initView();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void snackBarError() {
        SnackbarManager.show(Snackbar.with(this).text("请求出错"));
    }

    public void snackBarError(String msg) {
        SnackbarManager.show(Snackbar.with(this).text(msg));
    }

    public void snackBarSuccess() {
        SnackbarManager.show(Snackbar.with(this).text("请求成功"));
    }
    public  void initInject(){
        ButterKnife.inject(this);
    }
}
