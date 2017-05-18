package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WYZ on 2017/5/15.
 */

public interface PeopleService {
    @GET("login")
    Observable<LoginEntity> getIsLoginSuccess(@Query("username")String username, @Query("password")String password);
    @GET("queryUser")
    Observable<PeopleEntity> getLoginUserInfo(@Query("username")String username);
    @GET("updataUser")
    Observable<LoginEntity> getIsModSuccess(@Query("emp_id")int id,@Query("emp_name")String name,@Query("emp_tel_num")String tel,@Query("emp_addr")String addr,@Query("emp_email") String email);
    @GET("queryUser")
    Observable<PeopleEntity> getAllUserInfo();

}
