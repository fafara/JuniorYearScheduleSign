package com.example.wyz.schedulesign.Mvp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.NetWork.HttpMethods;
import com.example.wyz.schedulesign.R;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;

import static com.example.wyz.schedulesign.R.id.toolbar;

public class ModifyPeopleActivity extends BaseActivity {
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
    @InjectView(toolbar)
    Toolbar mToolbar;
    int id;
    int requestcode;
    int pos=-5;
    String name,tel,addr,email;


    Subscriber<LoginEntity> mSubscriber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_people);
        ButterKnife.inject(this);
        initActionBar();
        loadData();
    }
    private  void modData(){
        mSubscriber=new Subscriber<LoginEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                SnackbarManager.show(Snackbar.with(ModifyPeopleActivity.this).text("修改失败:"+e.getMessage()));
            }

            @Override
            public void onNext(LoginEntity loginEntity) {
                switch (loginEntity.getDetail().getStatus()){
                    case 1:
                        SnackbarManager.show(Snackbar.with(ModifyPeopleActivity.this).text("修改成功"));
                        ModifyPeopleActivity.this.finish();
                        break;
                    case  0:
                        SnackbarManager.show(Snackbar.with(ModifyPeopleActivity.this).text("修改失败"));
                        break;
                }

            }
        };
        HttpMethods.getInstance().getIsModSuccess(mSubscriber,id,name,tel,addr,email);
    }
    private void loadData() {
       Bundle bundle=getIntent().getExtras();
        requestcode=bundle.getInt("code");
        if(requestcode==1){
            pos=bundle.getInt("pos");
        }
        mID.setText(String.valueOf(bundle.getInt("id")));
        mName.setText(bundle.getString("name"));
        mTel.setText(bundle.getString("tel"));
        mAddr.setText(bundle.getString("addr"));
        mEmail.setText(bundle.getString("email"));
    }

    @OnClick({R.id.btn})
    public  void onClick(){
        if(mID.getText().toString().equals("")||mName.getText().toString().equals("")||mTel.getText().toString().equals("")||mAddr.getText().toString().equals("")||mEmail.getText().toString().equals(""))
        {
            Toast.makeText(this,"请检查信息的完整性",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent=new Intent();
            Bundle bundle=new Bundle();
            id=Integer.valueOf(mID.getText().toString());
            name=mName.getText().toString();
            tel=mTel.getText().toString();
            addr=mAddr.getText().toString();
            email=mEmail.getText().toString();
            bundle.putInt("pos",pos);
            bundle.putInt("id",id);
            bundle.putString("name",name);
            bundle.putString("tel",tel);
            bundle.putString("addr",addr);
            bundle.putString("email",email);
            intent.putExtras(bundle);
            if(requestcode==1){
                setResult(1,intent);
            }else if(requestcode==2){
                setResult(2,intent);
            }

            modData();

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            ModifyPeopleActivity.this.finish();
        }
        return  false;
    }
}
