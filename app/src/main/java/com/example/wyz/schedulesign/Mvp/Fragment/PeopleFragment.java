package com.example.wyz.schedulesign.Mvp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.wyz.schedulesign.Mvp.Activity.AddPeopleActivity;
import com.example.wyz.schedulesign.Mvp.Activity.ModifyPeopleActivity;
import com.example.wyz.schedulesign.Mvp.Adapter.People_Adapter;
import com.example.wyz.schedulesign.Mvp.Entity.Item_PeopleEntity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
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
import butterknife.OnClick;
import rx.Subscriber;

import static com.example.wyz.schedulesign.R.id.fab;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends BaseFragment  {

    final  String TAG="PeopleFragment";
    @InjectView(R.id.listView)
    ListView mListView;
    @InjectView(R.id.editText)
    EditText mEditText;
    @InjectView(R.id.search)
    ImageView mSearch;
    @InjectView(R.id.add)
    ImageView mAdd;
    @InjectView(R.id.fab)
    FloatingActionButton mButton;

    Subscriber<PeopleEntity> mSubscriber;
    List<Item_PeopleEntity> mItem_peopleEntities=new ArrayList<>();

    People_Adapter myAdapter=null;
    int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_people, container, false);
        ButterKnife.inject(this,view);
        initViews();
        return view;
    }

    public  void initListView(){
        myAdapter=new People_Adapter(getContext(),mItem_peopleEntities,mButton);
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
                bundle.putString("name",item_peopleEntity.getEmp_name());
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
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mEditText.getText().toString().equals("")){
                    refreshViews();
                }
            }
        });
        allUserNetRequest();

    }

    @Override
    public void refreshViews() {
        mItem_peopleEntities.clear();
        People_Adapter.mItem_peopleEntities.clear();
        allUserNetRequest();
    }
    public  void allUserNetRequest(){
        mSubscriber=new Subscriber<PeopleEntity>() {
            @Override
            public void onCompleted() {
                if(mItem_peopleEntities!=null){
                    if(myAdapter==null){
                        initListView();
                    }else {
                        myAdapter.notifyDataSetChanged();
                    }

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
                          mItem_peopleEntity.setEmp_name(peopleEntity.getDetail().get(i).getEmp_name());
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
    @OnClick({R.id.search,R.id.add, fab})
    public  void OnClick(View view){
        String name=mEditText.getText().toString();
        switch (view.getId()){
            case R.id.search:
                if(!name.equals("")){
                    mItem_peopleEntities.clear();
                    People_Adapter.mItem_peopleEntities.clear();
                    searchRequest(name);
                }else{
                    SnackbarManager.show(Snackbar.with(getContext()).text("请输入搜索姓名"));
                }
                break;
            case R.id.add:
                Intent intent=new Intent();
                intent.setClass(getContext(), AddPeopleActivity.class);
                startActivityForResult(intent,2);
                break;
            case fab:
                if(People_Adapter.sIntegers!=null){
                    Subscriber<LoginEntity> subscriber=new Subscriber<LoginEntity>() {
                        @Override
                        public void onCompleted() {
                            refreshViews();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(LoginEntity loginEntity) {
                            switch (loginEntity.getDetail().getStatus()){
                                case 1:
                                    SnackbarManager.show(Snackbar.with(getContext()).text("操作成功"));
                                    break;
                                case  0:
                                    SnackbarManager.show(Snackbar.with(getContext()).text("操作失败"));
                                    break;
                            }
                        }
                    };
                    for(int i=0;i<People_Adapter.sIntegers.size();i++){
                        String username=mItem_peopleEntities.get((int)People_Adapter.sIntegers.get(i)).getEmp_no();
                        UserHttpMethods.getInstance().getIsDeleteSuccess(subscriber,username);
                    }
                }

                break;
        }
    }

    private  void searchRequest(String loginName){
      mSubscriber=new Subscriber<PeopleEntity>() {
          @Override
          public void onCompleted() {
              if(mItem_peopleEntities!=null){
                 myAdapter.notifyDataSetChanged();
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
              for(int i=0;i<peopleEntity.getDetail().size();i++){
                  Item_PeopleEntity mItem_peopleEntity=new Item_PeopleEntity();
                  if(id!=peopleEntity.getDetail().get(i).getEmp_id()){
                      mItem_peopleEntity.setEmp_name(peopleEntity.getDetail().get(i).getEmp_name());
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
      UserHttpMethods.getInstance().getLoginUserInfo(mSubscriber,loginName);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case  1:
                refreshViews();
                break;
            case 0:
                break;
        }
    }

}
