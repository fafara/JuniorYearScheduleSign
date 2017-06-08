package com.example.wyz.schedulesign.Mvp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.R;

import java.util.List;

/**
 * Created by WYZ on 2017/6/7.
 */


public class StudioAdapter extends BaseAdapter {
    private Context mContext;
    public static List<StudioEntity.MDetail> sMDetails;
    private LayoutInflater mLayoutInflater;
    MyViewHolder mViewHolder=null;

    public StudioAdapter(Context context) {
        this(context,null);
    }
    public  StudioAdapter(Context context,List<StudioEntity.MDetail> mDetails){
        this.mContext=context;
        sMDetails=mDetails;
        mLayoutInflater=LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return sMDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return sMDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            mViewHolder=new MyViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.item_studio,null);
            mViewHolder.mName=(TextView)convertView.findViewById(R.id.name);
            mViewHolder.mRow=(TextView)convertView.findViewById(R.id.row);
            mViewHolder.mCol=(TextView)convertView.findViewById(R.id.col);
            mViewHolder.mFlag=(TextView)convertView.findViewById(R.id.flag);

            convertView.setTag(mViewHolder);
        }else {
            mViewHolder=(MyViewHolder) convertView.getTag();
        }

        mViewHolder.mName.setText(sMDetails.get(position).getStudio_name());
        mViewHolder.mRow.setText(String.valueOf(sMDetails.get(position).getStudio_row_count()));
        mViewHolder.mCol.setText(String.valueOf(sMDetails.get(position).getStudio_col_count()));
        mViewHolder.mFlag.setText(sMDetails.get(position).getStudio_flag());

        return convertView;
    }
    private  class  MyViewHolder{
        public TextView mName;
        public TextView mRow;
        public TextView mCol;
        public TextView mFlag;

    }

}
