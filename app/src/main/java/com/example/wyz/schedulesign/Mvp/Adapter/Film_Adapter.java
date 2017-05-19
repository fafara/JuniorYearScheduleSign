package com.example.wyz.schedulesign.Mvp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.R;

import java.util.List;

/**
 * Created by WYZ on 2017/5/19.
 */

public class Film_Adapter extends BaseAdapter {
    private Context mContext;
    private List<FilmEntity.MDetail> mMDetails;
    private LayoutInflater mLayoutInflater;
    MyViewHolder mViewHolder=null;
    public Film_Adapter(Context context,List<FilmEntity.MDetail> mDetails) {
        this.mContext=context;
        this.mMDetails=mDetails;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mMDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return mMDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            mViewHolder=new MyViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.item_film,null);
            mViewHolder.mName=(TextView)convertView.findViewById(R.id.name);
            mViewHolder.mTostar=(TextView)convertView.findViewById(R.id.tostar);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder=(MyViewHolder) convertView.getTag();
        }
        mViewHolder.mName.setText(mMDetails.get(position).getFilm_name());
        mViewHolder.mTostar.setText(mMDetails.get(position).getFilm_tostar());
        return convertView;
    }
    private  class  MyViewHolder{
        public TextView mName;
        public TextView mTostar;

    }
}
