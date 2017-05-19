package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.ApiManager.ApiManager;
import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WYZ on 2017/5/5.
 */

public class UserHttpMethods {


    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private  PeopleService mPeopleService;

    /**
     * 设置Retrofit网络请求
     */
    private UserHttpMethods(){
        OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit=new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiManager.PEOPLE_BASE_URL)
                .build();
        mPeopleService=mRetrofit.create(PeopleService.class);
    }

    /**
     * 单例模式
     */
    private  static  class  SingletonHolder{
        private  static  final UserHttpMethods INSTANCE=new UserHttpMethods();
    }
    public  static UserHttpMethods getInstance(){
        return  SingletonHolder.INSTANCE;
    }


    public  void getIsLoginSuccess(Subscriber<LoginEntity> subscriber, String name, String pass){
        mPeopleService.getIsLoginSuccess(name,pass)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getLoginUserInfo(Subscriber<PeopleEntity> subscriber,String name){
        mPeopleService.getLoginUserInfo(name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getIsModSuccess(Subscriber<LoginEntity> subscriber,int id,String name,String tel,String addr,String email){
        mPeopleService.getIsModSuccess(id,name,tel,addr,email)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getAllUserInfo(Subscriber<PeopleEntity> subscriber)
    {
        mPeopleService.getAllUserInfo()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getIsDeleteSuccess(Subscriber<LoginEntity> subscriber,String name){
        mPeopleService.getIsDeleteSuccess(name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
