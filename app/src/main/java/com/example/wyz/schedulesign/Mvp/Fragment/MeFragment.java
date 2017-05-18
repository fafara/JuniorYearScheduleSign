package com.example.wyz.schedulesign.Mvp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Activity.ModifyPeopleActivity;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;
import com.example.wyz.schedulesign.Mvp.Fragment.base.BaseFragment;
import com.example.wyz.schedulesign.NetWork.HttpMethods;
import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.MyLog;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

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
    String loginName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view=inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.inject(this,view);
        Bundle bundle=getArguments();
        loginName=bundle.getString("loginName");
        if (loginName != null ) {
            initViews();
        }
        return view;
    }


    @Override
    public void initViews() {
        loadData();
    }

    private void loadData() {
        mSubscriber=new Subscriber<PeopleEntity>() {
            @Override
            public void onCompleted() {

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
                mID.setText(String.valueOf(mDetail.getEmp_id()));
                mName.setText(mDetail.getEmp_name());
                mTel.setText(mDetail.getEmp_tel_num());
                mAddr.setText(mDetail.getEmp_addr());
                mEmail.setText(mDetail.getEmp_email());
                mNumber.setText(String.valueOf(mDetail.getEmp_no()));
            }
        };
        HttpMethods.getInstance().getLoginUserInfo(mSubscriber,loginName);
    }
    @OnClick({R.id.one,R.id.two,R.id.three,R.id.four,R.id.five})
    public  void onClick(View view){
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putInt("code",2);
        bundle.putInt("id",Integer.valueOf(mID.getText().toString()));
        bundle.putString("name",mName.getText().toString());
        bundle.putString("tel",mTel.getText().toString());
        bundle.putString("addr",mAddr.getText().toString());
        bundle.putString("email",mEmail.getText().toString());
        intent.putExtras(bundle);
        intent.setClass(getContext(), ModifyPeopleActivity.class);
        startActivityForResult(intent,2);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 2:
                switch (resultCode)
                {
                    case 1:
                        Bundle bundle=data.getExtras();
                        mID.setText(String.valueOf(bundle.getInt("id")));
                        mName.setText(bundle.getString("name"));
                        mTel.setText(bundle.getString("tel"));
                        mAddr.setText(bundle.getString("addr"));
                        mEmail.setText(bundle.getString("email"));
                        break;

                }

                break;
        }
    }
}
