package com.example.wyz.schedulesign.Mvp.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.IView.ILoginView;
import com.example.wyz.schedulesign.Mvp.Presenter.LoginPresenter;
import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.MyExit;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by WYZ on 2017/5/12.
 */

public class LoginActivity extends BaseActivity implements ILoginView{
    private final String TAG="LoginActivity";
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.bt_go)
    Button btGo;
    @InjectView(R.id.cv)
    CardView cv;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.bt_remember)
    CheckBox mCheckBox;
    @InjectView(R.id.avi)
    AVLoadingIndicatorView avi;
    @InjectView(R.id.loading)
    FrameLayout mFrameLayout;

    String username,password;
    private static final int PERMISSION_OK= 1;

    LoginPresenter mLoginPresenter=new LoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initInject();
        initPermission();
        initActionBar();
        initView();

    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());

                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                startLoadView();
                username=etUsername.getText().toString();
                password=etPassword.getText().toString();
                if(!username.equals("")&&!password.equals("")){
                    mLoginPresenter.getLoginResult(username,password,mCheckBox.isChecked());
                }
                else{
                    snackBarError("用户名或密码不能为空");
                }
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            MyExit.exitBy2Click(LoginActivity.this);
        }
        return  false;
    }


    @Override
    public void initActionBar() {

    }

    @Override
    public void initView() {
        mLoginPresenter.ReadPassword(LoginActivity.this);
    }



    @Override
    public void setLoginEditText(String username,String password,boolean isRemember) {
        if(isRemember){
            etPassword.setText(password);
        }
        etUsername.setText(username);
        mCheckBox.setChecked(isRemember);
    }

    @Override
    public void setEnterAnimation() {
        endLoadView();
        Explode explode = new Explode();
        explode.setDuration(500);
        //设置退出效果
        getWindow().setExitTransition(explode);
        //设置进入动画
        getWindow().setEnterTransition(explode);
        //共享元素跳转
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
        Intent i2 = new Intent(LoginActivity.this,MainViewActivity.class);
        startActivity(i2, oc2.toBundle());
        LoginActivity.this.finish();
    }

    @Override
    public void initPermission() {
        mLoginPresenter.permissionSetting();
    }

    @Override
    public void snackBar_permission_error() {
        SnackbarManager.show(Snackbar.with(LoginActivity.this).text("获取权限失败,请手动设置权限"));
    }

    @Override
    public void snackBarError() {
        SnackbarManager.show(Snackbar.with(LoginActivity.this).text("登录出错"));
    }

    @Override
    public void snackBarError(String msg) {
        SnackbarManager.show(Snackbar.with(LoginActivity.this).text(msg));
    }

    @Override
    public void snackBarSuccess() {
        SnackbarManager.show(Snackbar.with(LoginActivity.this).text("登录成功"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  PERMISSION_OK:
                    for(int i=0;i<grantResults.length;i++){
                        if(grantResults[i]== PackageManager.PERMISSION_GRANTED){

                        }else{
                            snackBar_permission_error();
                        }
                    }

                break;
            default:
                break;
        }
    }
    @Override
    public void startLoadView() {
        avi.show();
        mFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoadView() {
        avi.hide();
        mFrameLayout.setVisibility(View.GONE);
    }

}
