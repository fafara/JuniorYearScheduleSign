package com.example.wyz.schedulesign.Mvp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wyz.schedulesign.Mvp.Activity.StudioAddActivity;
import com.example.wyz.schedulesign.Mvp.Activity.StudioModifyActivity;
import com.example.wyz.schedulesign.Mvp.Adapter.StudioAdapter;
import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.Fragment.base.BaseFragment;
import com.example.wyz.schedulesign.Mvp.IView.IStudioView;
import com.example.wyz.schedulesign.Mvp.Presenter.StudioPresenter;
import com.example.wyz.schedulesign.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudioFragment extends BaseFragment implements IStudioView {

    @InjectView(R.id.listView)
    ListView mListView;
    @InjectView(R.id.fab)
    FloatingActionButton mButton;

    List<StudioEntity.MDetail> mMDetails=new ArrayList<>();
    static  StudioAdapter mStudioAdapter;
    StudioPresenter mStudioPresenter=new StudioPresenter(this);
    final int STUDIO_MODIFY=2;
    final int STUDIO_ADD=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_studio2, container, false);
        initInject(view);
        initViews();
        return view;
    }

    public  void initListView(){
        mStudioAdapter=new StudioAdapter(getContext(),mMDetails);
        mListView.setAdapter(mStudioAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudioEntity.MDetail mDetail=mMDetails.get(position);
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("studio",mDetail);
                intent.putExtras(bundle);
                intent.setClass(getContext(), StudioModifyActivity.class);
                startActivityForResult(intent,STUDIO_MODIFY);
            }
        });
    }

    @Override
    public void initViews() {
        mStudioPresenter.getAllStudio();
    }

    @Override
    public void refreshViews() {
        mMDetails.clear();
        if(StudioAdapter.sMDetails!=null){
            StudioAdapter.sMDetails.clear();
        }
        mStudioPresenter.getAllStudio();
    }
    @OnClick({R.id.fab})
    public  void OnClick(View view){
        switch ( view.getId()){
            case  R.id.fab:
                Intent intent=new Intent();
                intent.setClass(getContext(), StudioAddActivity.class);
                startActivityForResult(intent,STUDIO_ADD);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            initViews();
        }
    }

    @Override
    public void setListView(List<StudioEntity.MDetail> list) {
        mMDetails=list;
        if(StudioAdapter.sMDetails==null){
            initListView();
        }else if(StudioAdapter.sMDetails!=null){
            StudioAdapter.sMDetails.clear();
            StudioAdapter.sMDetails.addAll(list);
            mStudioAdapter.notifyDataSetChanged();
        }
    }
}
