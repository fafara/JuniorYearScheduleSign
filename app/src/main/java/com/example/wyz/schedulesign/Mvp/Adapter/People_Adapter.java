package com.example.wyz.schedulesign.Mvp.Adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;
import com.example.wyz.schedulesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYZ on 2017/5/18.
 */

public class People_Adapter extends  BaseAdapter {
    private  Context mContext;
    public static List<PeopleEntity.MDetail> mItem_peopleEntities;
    private LayoutInflater  mLayoutInflater;
    private  FloatingActionButton mFab;
    public  static  List<Integer> sIntegers=new ArrayList<>();
    MyViewHolder mViewHolder=null;

    public People_Adapter(Context context,List<PeopleEntity.MDetail> item_peopleEntities) {
       this(context,item_peopleEntities,null);
    }
    public  People_Adapter(Context context, List<PeopleEntity.MDetail> item_peopleEntities, FloatingActionButton fab){
        this.mContext=context;
        mItem_peopleEntities=item_peopleEntities;
        mFab=fab;
        mLayoutInflater=LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mItem_peopleEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return mItem_peopleEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            mViewHolder=new MyViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.item_people,null);
            mViewHolder.mName=(TextView)convertView.findViewById(R.id.name);
            mViewHolder.mTel=(TextView)convertView.findViewById(R.id.tel);
            mViewHolder.mUsername=(TextView)convertView.findViewById(R.id.username);
            mViewHolder.mCheckBox=(CheckBox)convertView.findViewById(R.id.checkbox);

            convertView.setTag(mViewHolder);
        }else {
            mViewHolder=(MyViewHolder) convertView.getTag();
        }

        mViewHolder.mName.setText(mItem_peopleEntities.get(position).getEmp_name());
        mViewHolder.mTel.setText(mItem_peopleEntities.get(position).getEmp_tel_num());
        mViewHolder.mUsername.setText(mItem_peopleEntities.get(position).getEmp_no());
        mViewHolder.mCheckBox.setChecked(false);
        mViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sIntegers.add(Integer.valueOf(position));
                }else{
                    sIntegers.remove(Integer.valueOf(position));
                }
                if(sIntegers.size()==0){
                    mFab.setVisibility(View.GONE);
                }else if(sIntegers.size()>0){
                    mFab.setVisibility(View.VISIBLE);
                }
            }
        });
        return convertView;
    }
    private  class  MyViewHolder{
        public TextView mUsername;
        public TextView mName;
        public TextView mTel;
        private CheckBox mCheckBox;

    }

}
