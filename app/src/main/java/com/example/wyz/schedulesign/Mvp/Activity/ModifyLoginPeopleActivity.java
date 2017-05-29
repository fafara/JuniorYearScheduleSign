package com.example.wyz.schedulesign.Mvp.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
public class ModifyLoginPeopleActivity extends BaseActivity {
    @InjectView(R.id.ID)
    EditText mID;
    @InjectView(R.id.name)
    EditText mName;
    @InjectView(R.id.tel)
    EditText mTel;
    @InjectView(R.id.addr)
    EditText mAddr;
    @InjectView(R.id.email)
    EditText mEmail;
    @InjectView(R.id.btn)
    Button mBtn;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    int id;
    String name,tel,addr,email,username;

    Subscriber<LoginEntity> mSubscriber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_login_people);
        initInject();
        initActionBar();
        initView();
    }
    private  void modNetRequest(){
        mSubscriber=new Subscriber<LoginEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                SnackbarManager.show(Snackbar.with(ModifyLoginPeopleActivity.this).text("修改失败:"+e.getMessage()));
            }

            @Override
            public void onNext(LoginEntity loginEntity) {
                switch (loginEntity.getDetail().getStatus()){
                    case 1:

                        SnackbarManager.show(Snackbar.with(ModifyLoginPeopleActivity.this).text("修改成功"));
                        setResult(2);
                        ModifyLoginPeopleActivity.this.finish();
                        break;
                    case  0:
                        SnackbarManager.show(Snackbar.with(ModifyLoginPeopleActivity.this).text("修改失败"));
                        break;
                }

            }
        };
        UserHttpMethods.getInstance().getIsModSuccess(mSubscriber,id,name,tel,addr,email);
    }

    @OnClick({R.id.btn})
    public  void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                if(mID.getText().toString().equals("")||mName.getText().toString().equals("")||mTel.getText().toString().equals("")||mAddr.getText().toString().equals("")||mEmail.getText().toString().equals(""))
                {
                    Toast.makeText(this,"请检查信息的完整性",Toast.LENGTH_SHORT).show();
                }else{

                    id=Integer.valueOf(mID.getText().toString());
                    name=mName.getText().toString();
                    tel=mTel.getText().toString();
                    addr=mAddr.getText().toString();
                    email=mEmail.getText().toString();
                    modNetRequest();
                }
                break;
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void initActionBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView() {
        Bundle bundle=getIntent().getExtras();
        mID.setText(String.valueOf(bundle.getInt("id")));
        mName.setText(bundle.getString("name"));
        mTel.setText(bundle.getString("tel"));
        mAddr.setText(bundle.getString("addr"));
        mEmail.setText(bundle.getString("email"));
        username=bundle.getString("no");
    }

    @Override
    public void initInject() {
        ButterKnife.inject(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            ModifyLoginPeopleActivity.this.finish();
        }
        return  false;
    }


}
