package com.example.wyz.schedulesign.Mvp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Activity.ModifyLoginPeopleActivity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginSingleton;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;
import com.example.wyz.schedulesign.Mvp.Fragment.base.BaseFragment;
import com.example.wyz.schedulesign.NetWork.UserHttpMethods;
import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.MyLog;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {
    final String TAG="MeFragment";
    @InjectView(R.id.ID)
    TextView mID;
    @InjectView(R.id.name)
    TextView mName;
    @InjectView(R.id.tel)
    TextView mTel;
    @InjectView(R.id.addr)
    TextView mAddr;
    @InjectView(R.id.email)
    TextView mEmail;
    @InjectView(R.id.number)
    TextView mNumber;
    Subscriber<PeopleEntity> mSubscriber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view=inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.inject(this,view);
        initViews();
        return view;
    }
    @Override
    public void initViews() {
        if(LoginSingleton.getInstance()!=null){
            mID.setText(String.valueOf(LoginSingleton.getInstance().getId()));
            mName.setText(LoginSingleton.getInstance().getName());
            mTel.setText(LoginSingleton.getInstance().getTel());
            mAddr.setText(LoginSingleton.getInstance().getAddr());
            mEmail.setText(LoginSingleton.getInstance().getEmail());
            mNumber.setText(String.valueOf(LoginSingleton.getInstance().getUsername()));
        }
    }

    @Override
    public void refreshViews() {
        loadData();
    }

    private void loadData() {
        mSubscriber=new Subscriber<PeopleEntity>() {
            @Override
            public void onCompleted() {
                if(mID!=null){
                    initViews();
                }
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
                SnackbarManager.show(Snackbar.with(getActivity()).text("请求出错："+e.getMessage()));
            }

            @Override
            public void onNext(PeopleEntity peopleEntity) {
                MyLog.d(TAG,"next");
                PeopleEntity.MDetail mDetail=peopleEntity.getDetail().get(0);
                LoginSingleton.getInstance().setId(mDetail.getEmp_id());
                LoginSingleton.getInstance().setAddr(mDetail.getEmp_addr());
                LoginSingleton.getInstance().setUsername(mDetail.getEmp_no());
                LoginSingleton.getInstance().setEmail(mDetail.getEmp_email());
                LoginSingleton.getInstance().setTel(mDetail.getEmp_tel_num());
                LoginSingleton.getInstance().setName(mDetail.getEmp_name());
            }
        };
        if(!Objects.equals(LoginSingleton.getInstance().getUsername(), "")){
            UserHttpMethods.getInstance().getLoginUserInfo(mSubscriber,LoginSingleton.getInstance().getUsername());
        }

    }
    @OnClick({R.id.one,R.id.two,R.id.three,R.id.four,R.id.five})
    public  void onClick(View view){
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putInt("code",2);
        bundle.putInt("id",Integer.valueOf(mID.getText().toString()));
        if(LoginSingleton.getInstance()!=null){
            bundle.putString("no",LoginSingleton.getInstance().getUsername());
        }
        bundle.putString("name",mName.getText().toString());
        bundle.putString("tel",mTel.getText().toString());
        bundle.putString("addr",mAddr.getText().toString());
        bundle.putString("email",mEmail.getText().toString());
        intent.putExtras(bundle);
        intent.setClass(getContext(),ModifyLoginPeopleActivity.class);
        startActivityForResult(intent,2);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case  2:
                refreshViews();
                break;
            case 0:
                break;
        }
    }
}
