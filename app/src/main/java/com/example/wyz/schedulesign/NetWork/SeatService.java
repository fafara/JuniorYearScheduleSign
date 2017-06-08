package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.SeatAllStatusEntity;
import com.example.wyz.schedulesign.Mvp.Entity.SeatStatusEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WYZ on 2017/6/7.
 */

public interface SeatService {

    @FormUrlEncoded
    @POST("updataSeat")
    Observable<SeatStatusEntity> ticketSeat(@Field("seat_list") String json);

    @GET("querySeat")
    Observable<SeatAllStatusEntity> getSeatStatus(@Query("studio_id") String id);

}
