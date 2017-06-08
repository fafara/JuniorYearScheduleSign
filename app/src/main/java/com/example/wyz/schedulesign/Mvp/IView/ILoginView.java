package com.example.wyz.schedulesign.Mvp.IView;

import com.example.wyz.schedulesign.Mvp.IView.base.BaseView;

/**
 * Created by WYZ on 2017/5/1.
 */

public interface ILoginView  extends BaseView{
    void setLoginEditText(String username,String password,boolean isRemember);
    void setEnterAnimation();
    void initPermission();
    void snackBar_permission_error();

    void startLoadView();
    void endLoadView();
}
