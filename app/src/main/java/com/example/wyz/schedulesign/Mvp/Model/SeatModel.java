package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.SeatEntity;
import com.example.wyz.schedulesign.Mvp.Entity.SeatStatusEntity;
import com.example.wyz.schedulesign.Mvp.IModel.ISeatModel;
import com.example.wyz.schedulesign.Mvp.Presenter.SeatPresenter;
import com.example.wyz.schedulesign.NetWork.SeatHttpMethods;
import com.example.wyz.schedulesign.Util.MyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;

import static android.content.ContentValues.TAG;


/**
 * Created by WYZ on 2017/6/7.
 */

public class SeatModel implements ISeatModel {

    SeatPresenter mSeatPresenter=new SeatPresenter();
    @Override
    public void ticketSeat(List<SeatEntity> seatEntities) {
        JSONArray jsonArray = new JSONArray();
        try {
            for(SeatEntity s : seatEntities){
                JSONObject jo = new JSONObject();
                    jo.put("studio_id", s.getStudio_id());
                    jo.put("seat_row", s.getSeat_row());
                    jo.put("seat_column", s.getSeat_column());
                    jo.put("seat_status", s.getSeat_status());
                    jsonArray.put(jo);
                }
            }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Subscriber<SeatStatusEntity> subscriber=new Subscriber<SeatStatusEntity>() {
            @Override
            public void onCompleted() {
                mSeatPresenter.completed();
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,e.getMessage());
            }

            @Override
            public void onNext(SeatStatusEntity seatStatusEntity) {
                MyLog.d(TAG,"sdfds");
            }
        };
        SeatHttpMethods.getInstance().ticketsSeat(subscriber,jsonArray.toString());



    }
}
