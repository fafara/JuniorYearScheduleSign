package com.example.wyz.schedulesign.Mvp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.DensityUtils;

/**
 * Created by WYZ on 2017/6/6.
 */

public class SeatAdapter extends BaseAdapter {
    private  static Context sContext;
    private  int[] imgId=null;
    static int sColumns;
    @Override
    public int getCount() {
        return imgId.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(sContext).inflate(R.layout.item_seat,null);
            holder=new ViewHolder();
            holder.mImageView=(ImageView)convertView.findViewById(R.id.item_img);
            //holder.mImageView.setLayoutParams(new GridView.LayoutParams(75, 75));
            //holder.mImageView.setLayoutParams(new GridView.LayoutParams(DensityUtils.dip2px(sContext,DensityUtils.getScreenWidth(sContext)/10),DensityUtils.dip2px(sContext,DensityUtils.getScreenHeight(sContext)/10)));//设置ImageView对象布局
            holder.mImageView.setLayoutParams(new LinearLayout.LayoutParams(DensityUtils.dip2px(sContext,DensityUtils.getScreenHeight(sContext)/(5*sColumns)),DensityUtils.dip2px(sContext,DensityUtils.getScreenHeight(sContext)/(5*sColumns))));
            //holder.mImageView.setAdjustViewBounds(false);//设置边界对齐
            holder.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置刻度的类型
            //holder.mImageView.setPadding(, 8, 8, 8);//设置间距
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.mImageView.setImageResource(imgId[position]);

        return convertView;
    }
    private class  ViewHolder{
        private  ImageView mImageView;

        public ImageView getImageView() {
            return mImageView;
        }

        public void setImageView(ImageView imageView) {
            mImageView = imageView;
        }
    }

    public SeatAdapter(Context contexts,int[] imgId,int column) {
        this.imgId = imgId;
        sContext=contexts;
        sColumns=column;
    }
    public SeatAdapter()  {

    }
}
