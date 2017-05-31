package com.example.wyz.schedulesign.Mvp.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.wyz.schedulesign.Mvp.Entity.LoginSingleton;
import com.example.wyz.schedulesign.Mvp.IView.ILoginView;
import com.example.wyz.schedulesign.Mvp.Model.LoginModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

/**
 * Created by WYZ on 2017/5/17.
 */

public class LoginPresenter implements BasePresenter {
    private static LoginModel mLoginModel;
    private static ILoginView mILoginView;
    SharedPreferences sp;
    public  static  Context sContext;
    public LoginPresenter() {

    }
    public LoginPresenter(ILoginView ILoginView) {
        mLoginModel = new LoginModel();
        mILoginView = ILoginView;


    }

    public void ReadPassword(Context context) {
        Boolean isRemember;
        sContext=context;
         sp=context.getSharedPreferences("schedule_info", Context.MODE_PRIVATE);
        isRemember=sp.getBoolean("loginStatus",false);
        mILoginView.setLoginEditText(sp.getString("loginName",""),sp.getString("loginPassword",""),isRemember);
     /*   */


    }
    public void SavePassWord(String name,String password,Boolean isRemember){

        SharedPreferences.Editor editor= sContext.getSharedPreferences("schedule_info", Context.MODE_PRIVATE).edit();;
        editor.putString("loginName",name);
        editor.putString("loginPassword",password);
        editor.putBoolean("loginStatus",isRemember);
        editor.apply();
    }


    public  void getLoginResult(String username,String password,boolean isRemember){
        mLoginModel.getLoginResult(username,password,isRemember);
    }
    public  void setEnterAnimation(){
        mILoginView.setEnterAnimation();
    }
    public  void setLoginImage(String url){
        LoginSingleton.getInstance().setImage(url);
    }

    public  void permissionSetting(){
        mLoginModel.permissionSetting((Activity)mILoginView);
    }

    @Override
    public void snackBarError() {
        mILoginView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        mILoginView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        mILoginView.snackBarSuccess();
    }
}
