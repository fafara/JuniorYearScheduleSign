package com.example.wyz.schedulesign.Mvp.IModel;

import android.app.Activity;

/**
 * Created by WYZ on 2017/5/1.
 */

public interface ILoginModel {
    void getLoginResult(String name,String password,boolean isRemember);
    void getLoginInfo(String username);
    void permissionSetting(Activity activity);
}
