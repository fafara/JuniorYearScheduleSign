package com.example.wyz.schedulesign.Mvp.Activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;
import com.example.wyz.schedulesign.NetWork.UserHttpMethods;
import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.MyExit;
import com.example.wyz.schedulesign.Util.MyLog;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by WYZ on 2017/5/12.
 */

public class LoginActivity extends BaseActivity{
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

    Subscriber<LoginEntity> mSubscriber;

    private  String username;
    private  String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initActionBar();
        initView();
    }

    private void ReadPassword() {
        Boolean isRemember;
        SharedPreferences sp=getSharedPreferences("schedule_info", Context.MODE_PRIVATE);
        isRemember=sp.getBoolean("loginStatus",false);
        if(isRemember){
            etPassword.setText(sp.getString("loginPassword",""));
        }
        etUsername.setText(sp.getString("loginName",""));
        mCheckBox.setChecked(isRemember);


    }
    private void SavePassWord(String name,String password,Boolean isRemember){
        //SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor=getSharedPreferences("schedule_info", Context.MODE_PRIVATE).edit();
        editor.putString("loginName",name);
        editor.putString("loginPassword",password);
        editor.putBoolean("loginStatus",isRemember);
        editor.apply();
    }
    private  void  getLoginResult(){
        mSubscriber=new Subscriber<LoginEntity>() {
            @Override
            public void onCompleted() {
                MyLog.d(TAG,"登录完成");
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"登录出错:"+e.getMessage());
                SnackbarManager.show(Snackbar.with(LoginActivity.this).text("登录出错:"+e.getMessage()));
            }

            @Override
            public void onNext(LoginEntity loginEntity) {
                switch (loginEntity.getDetail().getStatus()){
                    case 0:
                        SnackbarManager.show(Snackbar.with(LoginActivity.this).text("登录账号或密码错误"));
                        break;
                    case 1:
                        SavePassWord(username,password,mCheckBox.isChecked());
                        getLoginInfo();
                        enterMainView();
                        break;
                }

            }
        };
        UserHttpMethods.getInstance().getIsLoginSuccess(mSubscriber,username,password);
    }

    private  void getLoginInfo(){
       Subscriber<PeopleEntity.MDetail> subscriber=new Subscriber<PeopleEntity.MDetail>() {
           @Override
           public void onCompleted() {

           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onNext(PeopleEntity.MDetail mDetail) {

           }
       };
       UserHttpMethods.getInstance().getLoginUserInfo(subscriber,username);
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
                username=etUsername.getText().toString();
                password=etPassword.getText().toString();
                if(!username.equals("")&&!password.equals("")){
                    getLoginResult();
                }
                else{
                    SnackbarManager.show(com.nispok.snackbar.Snackbar.with(this).text("用户名或密码不能为空"));
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
        ReadPassword();
    }
    private  void enterMainView(){
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

}
