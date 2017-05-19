package com.example.wyz.schedulesign.Mvp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wyz.schedulesign.Mvp.Activity.ModifyPeopleActivity;
import com.example.wyz.schedulesign.Mvp.Adapter.People_Adapter;
import com.example.wyz.schedulesign.Mvp.Entity.Item_PeopleEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;
import com.example.wyz.schedulesign.Mvp.Fragment.base.BaseFragment;
import com.example.wyz.schedulesign.NetWork.UserHttpMethods;
import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.MyLog;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;

import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;
import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends BaseFragment {

    final  String TAG="PeopleFragment";
    @InjectView(R.id.listView)
    ListView mListView;

    Subscriber<PeopleEntity> mSubscriber;
    List<Item_PeopleEntity> mItem_peopleEntities=new ArrayList<>();

    People_Adapter myAdapter=null;
    String loginName;
    int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_people, container, false);
        ButterKnife.inject(this,view);
        Bundle bundle=getArguments();
        loginName=bundle.getString("loginName");
        if (loginName != null ) {
            initViews();
        }
        //initViews();
        return view;
    }

    public  void initListView(){
        myAdapter=new People_Adapter(getContext(),mItem_peopleEntities);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item_PeopleEntity item_peopleEntity=mItem_peopleEntities.get(position);
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putInt("code",1);
                bundle.putInt("pos",position);
                bundle.putInt("id",item_peopleEntity.getId());
                bundle.putString("no",item_peopleEntity.getEmp_no());
                bundle.putString("name",item_peopleEntity.getName());
                bundle.putString("tel",item_peopleEntity.getTel());
                bundle.putString("addr", item_peopleEntity.getEmp_addr());
                bundle.putString("email",item_peopleEntity.getEmp_email());
                intent.putExtras(bundle);
                intent.setClass(getContext(), ModifyPeopleActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    public void initViews() {
        getLoginInfo();
        getAllUserInfo();

    }
    public void getLoginInfo(){
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
                id=mDetail.getEmp_id();

            }
        };
        UserHttpMethods.getInstance().getLoginUserInfo(mSubscriber,loginName);
    }
    public  void getAllUserInfo(){
        mSubscriber=new Subscriber<PeopleEntity>() {
            @Override
            public void onCompleted() {
                if(mItem_peopleEntities!=null){
                    initListView();
                }
            }
            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
                SnackbarManager.show(Snackbar.with(getContext()).text("请求出错:"+e.getMessage()));
            }
            @Override
            public void onNext(PeopleEntity peopleEntity) {

                  for(int i=0;i<peopleEntity.getDetail().size();i++){


                      Item_PeopleEntity mItem_peopleEntity=new Item_PeopleEntity();
                      if(id!=peopleEntity.getDetail().get(i).getEmp_id()){
                          mItem_peopleEntity.setName(peopleEntity.getDetail().get(i).getEmp_name());
                          mItem_peopleEntity.setTel(peopleEntity.getDetail().get(i).getEmp_tel_num());
                          mItem_peopleEntity.setEmp_no(peopleEntity.getDetail().get(i).getEmp_no());
                          mItem_peopleEntity.setId(peopleEntity.getDetail().get(i).getEmp_id());
                          mItem_peopleEntity.setEmp_addr(peopleEntity.getDetail().get(i).getEmp_addr());
                          mItem_peopleEntity.setEmp_email(peopleEntity.getDetail().get(i).getEmp_email());
                          mItem_peopleEntities.add(mItem_peopleEntity);
                      }

                  }
            }
        };
        UserHttpMethods.getInstance().getAllUserInfo(mSubscriber);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                switch (resultCode)
                {
                    case 1:
                        Bundle bundle=data.getExtras();
                        int pos=bundle.getInt("pos");
                        int btn=bundle.getInt("btn");
                        switch (btn){
                            //修改信息
                            case 0:
                                mItem_peopleEntities.get(pos).setId(bundle.getInt("id"));
                                mItem_peopleEntities.get(pos).setName(bundle.getString("name"));
                                mItem_peopleEntities.get(pos).setEmp_no(bundle.getString("no"));
                                mItem_peopleEntities.get(pos).setTel(bundle.getString("tel"));
                                mItem_peopleEntities.get(pos).setEmp_addr(bundle.getString("addr"));
                                mItem_peopleEntities.get(pos).setEmp_email(bundle.getString("email"));
                                myAdapter.notifyDataSetChanged();
                                break;
                            //删除信息
                            case 1:
                                for(int i=0;i<mItem_peopleEntities.size();i++){
                                    if(bundle.getInt("id")==mItem_peopleEntities.get(i).getId()){
                                        mItem_peopleEntities.remove(i);
                                        break;
                                    }
                                }
                                myAdapter.notifyDataSetChanged();
                                break;
                        }
                }

                break;
        }
    }
}
