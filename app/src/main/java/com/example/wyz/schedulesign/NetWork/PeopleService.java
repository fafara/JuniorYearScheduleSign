package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WYZ on 2017/5/15.
 */

public interface PeopleService {
    @GET("login")
    Observable<PeopleEntity> getIsLoginSuccess(@Query("username")String username, @Query("password")String password);
}
