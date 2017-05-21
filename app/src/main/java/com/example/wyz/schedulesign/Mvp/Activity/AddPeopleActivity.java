package com.example.wyz.schedulesign.Mvp.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.NetWork.UserHttpMethods;
import com.example.wyz.schedulesign.R;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;

public class AddPeopleActivity extends BaseActivity {

    @InjectView(R.id.username)
    EditText mUsername;
    @InjectView(R.id.name)
    EditText mName;
    @InjectView(R.id.tel)
    EditText mTel;
    @InjectView(R.id.addr)
    EditText mAddr;
    @InjectView(R.id.email)
    EditText mEmail;
    @InjectView(R.id.btn)
    Button mButton;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    String username,name,tel,addr,email;
    Subscriber<LoginEntity> mSubscriber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        ButterKnife.inject(this);
        initActionBar();
        initView();
    }
    @Override
    public void initActionBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.btn)
    public  void onClick(View view){
        username= mUsername.getText().toString();
        name=mName.getText().toString();
        tel=mTel.getText().toString();
        addr=mAddr.getText().toString();
        email= mEmail.getText().toString();
        if(username.equals("")||name.equals("")||
                tel.equals("")||addr.equals("")||
                email.equals("")){
            SnackbarManager.show(Snackbar.with(this).text("输入不能为空"));
        }
        else{
            addNetRequest();
        }
    }
    private  void addNetRequest(){
        mSubscriber=new Subscriber<LoginEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                SnackbarManager.show(Snackbar.with(AddPeopleActivity.this).text("操作失败:"+e.getMessage()));

            }

            @Override
            public void onNext(LoginEntity loginEntity) {
                switch (loginEntity.getDetail().getStatus()){
                    case 1:
                        SnackbarManager.show(Snackbar.with(AddPeopleActivity.this).text("操作成功"));
                        setResult(1);
                        AddPeopleActivity.this.finish();
                        break;
                    case  0:
                        SnackbarManager.show(Snackbar.with(AddPeopleActivity.this).text("操作失败:用户名已存在"));
                        break;
                }
            }
        };
        UserHttpMethods.getInstance().getIsAddSuccess(mSubscriber,username,name,tel,addr,email);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            AddPeopleActivity.this.finish();
        }
        return  false;
    }
}
