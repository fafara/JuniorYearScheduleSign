package com.example.wyz.schedulesign.Mvp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Entity.Item_PeopleEntity;
import com.example.wyz.schedulesign.R;

import java.util.List;

/**
 * Created by WYZ on 2017/5/18.
 */

public class People_Adapter extends  BaseAdapter {
    private  Context mContext;
    private List<Item_PeopleEntity> mItem_peopleEntities;
    private LayoutInflater  mLayoutInflater;
    MyViewHolder mViewHolder=null;
    public People_Adapter(Context context,List<Item_PeopleEntity> item_peopleEntities) {
        this.mContext=context;
        this.mItem_peopleEntities=item_peopleEntities;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            mViewHolder=new MyViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.item_people,null);
            mViewHolder.mName=(TextView)convertView.findViewById(R.id.name);
            mViewHolder.mTel=(TextView)convertView.findViewById(R.id.tel);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder=(MyViewHolder) convertView.getTag();
        }
        mViewHolder.mName.setText(mItem_peopleEntities.get(position).getName());
        mViewHolder.mTel.setText(mItem_peopleEntities.get(position).getTel());
        return convertView;
    }
    private  class  MyViewHolder{
        public TextView mName;
        public TextView mTel;

    }

}
