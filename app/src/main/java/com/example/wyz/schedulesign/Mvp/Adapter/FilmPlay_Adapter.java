package com.example.wyz.schedulesign.Mvp.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.RecyclerView.OnItemClickListenerInterface;
import com.example.wyz.schedulesign.R;

import java.util.List;

/**
 * Created by WYZ on 2017/6/5.
 */

public class FilmPlay_Adapter extends android.support.v7.widget.RecyclerView.Adapter<FilmPlay_Adapter.MyViewHolder> {

    static  Activity sActivity;
    public static List<PlayEntity.MDetail>  sFilmPlayEntities;
    public FilmPlay_Adapter(Activity activity,List<PlayEntity.MDetail> filmPlayEntity) {
        sActivity=activity;
        sFilmPlayEntities=filmPlayEntity;
    }

    public FilmPlay_Adapter() {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(sActivity).inflate(R.layout.item_film_play,null));
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mFormTime.setText(sFilmPlayEntities.get(position).getPlay_start());
        holder.mEndTime.setText(sFilmPlayEntities.get(position).getPlay_end());
        holder.mStudioId.setText(sFilmPlayEntities.get(position).getStudio_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getPosition();
                mClickListenerInterface.OnItemClick(v,pos);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos=holder.getPosition();
                mClickListenerInterface.OnItemLongClick(v,pos);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(sFilmPlayEntities!=null){
            return sFilmPlayEntities.size();
        }
        return  0;

    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        public TextView mFormTime;
        public TextView mEndTime;
        public TextView mStudioId;
        public MyViewHolder(View itemView) {
            super(itemView);
            mFormTime=(TextView) itemView.findViewById(R.id.form_time);
            mEndTime=(TextView) itemView.findViewById(R.id.end_time);
            mStudioId=(TextView) itemView.findViewById(R.id.stduio_id);
        }
    }
    private OnItemClickListenerInterface mClickListenerInterface;
    public void setOnItemClickListener(OnItemClickListenerInterface clickListenerInterface){
        mClickListenerInterface=clickListenerInterface;
    }
}
