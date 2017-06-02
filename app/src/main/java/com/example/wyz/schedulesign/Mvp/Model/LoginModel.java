package com.example.wyz.schedulesign.Mvp.Model;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;
import com.example.wyz.schedulesign.Mvp.IModel.ILoginModel;
import com.example.wyz.schedulesign.Mvp.Presenter.LoginPresenter;
import com.example.wyz.schedulesign.NetWork.UserHttpMethods;
import com.example.wyz.schedulesign.Util.MyLog;

import rx.Subscriber;

import static android.content.ContentValues.TAG;

/**
 * Created by WYZ on 2017/5/1.
 */

public class LoginModel implements ILoginModel {

    LoginPresenter mLoginPresenter=new LoginPresenter();
    private static final int PERMISSION_OK= 1;
    public LoginModel() {
    }

    Subscriber<LoginEntity> mSubscriber;
    @Override
    public void getLoginResult(final String username,final String password,final boolean isRemember) {
        mSubscriber=new Subscriber<LoginEntity>() {
            @Override
            public void onCompleted() {

                getLoginInfo(username);
                MyLog.d(TAG,"登录完成");
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"登录出错:"+e.getMessage());
                mLoginPresenter.snackBarError();
            }

            @Override
            public void onNext(LoginEntity loginEntity) {
                switch (loginEntity.getDetail().getStatus()){
                    case 0:
                        mLoginPresenter.snackBarError("登录账号或密码错误");
                        break;
                    case 1:
                        mLoginPresenter.SavePassWord(username,password,isRemember);
                        mLoginPresenter.setLoginImage(loginEntity.getDetail().getImage());
                        //mLoginPresenter.netRequestView();


                        break;
                }

            }
        };
        UserHttpMethods.getInstance().getIsLoginSuccess(mSubscriber,username,password);
    }

    @Override
    public void getLoginInfo(String username) {
        Subscriber<PeopleEntity.MDetail> subscriber=new Subscriber<PeopleEntity.MDetail>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
                mLoginPresenter.snackBarError("请求出错");
            }

            @Override
            public void onNext(PeopleEntity.MDetail detail) {
                MyLog.d(TAG,"onNext");
                mLoginPresenter.setEnterAnimation();
            }
        };
        UserHttpMethods.getInstance().getLoginUserInfo(subscriber,username);
    }

    @Override
    public void permissionSetting(Activity activity) {
        //检查权限
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //没有权限，请求开启权限
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_OK);
        }
    }


}
