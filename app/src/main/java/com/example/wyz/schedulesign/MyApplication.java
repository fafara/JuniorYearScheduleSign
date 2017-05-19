package com.example.wyz.schedulesign;

import android.app.Application;

import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;

/**
 * Created by WYZ on 2017/5/19.
 */

public class MyApplication extends Application {
    public static  PeopleEntity.MDetail mDetail;


    @Override
    public void onCreate() {
        mDetail=null;
        super.onCreate();
    }
}
