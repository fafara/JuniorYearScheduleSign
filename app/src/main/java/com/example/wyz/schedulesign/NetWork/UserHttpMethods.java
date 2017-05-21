package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.ApiManager.ApiManager;
import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginSingleton;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
    public  void getLoginUserInfo(Subscriber<PeopleEntity.MDetail> subscriber,String name){
        mPeopleService.getLoginUserInfo(name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<PeopleEntity,  PeopleEntity.MDetail>() {
                    @Override
                    public  PeopleEntity.MDetail call(PeopleEntity peopleEntity) {
                        PeopleEntity.MDetail mDetail=peopleEntity.getDetail().get(0);
                        LoginSingleton.getInstance().setId(mDetail.getEmp_id());
                        LoginSingleton.getInstance().setAddr(mDetail.getEmp_addr());
                        LoginSingleton.getInstance().setUsername(mDetail.getEmp_no());
                        LoginSingleton.getInstance().setEmail(mDetail.getEmp_email());
                        LoginSingleton.getInstance().setTel(mDetail.getEmp_tel_num());
                        LoginSingleton.getInstance().setName(mDetail.getEmp_name());
                        return mDetail;
                    }
                })
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
                .observeOn(Schedulers.io())
               /* .map(new Func1<PeopleEntity, List<PeopleEntity.MDetail>>() {
                    @Override
                    public List<PeopleEntity.MDetail> call(PeopleEntity peopleEntity) {
                       return peopleEntity.getDetail();
                    }
                })
                .map(new Func1<List<PeopleEntity.MDetail>, List<Item_PeopleEntity>>() {
                    @Override
                    public List<Item_PeopleEntity> call(List<PeopleEntity.MDetail> mDetails) {
                        List<Item_PeopleEntity> item_peopleEntities=new ArrayList<>();
                        for(int i=0;i<mDetails.size();i++){
                            item_peopleEntities.add();
                        }
                        return null;
                    }
                })*/
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
    public  void getIsAddSuccess(Subscriber<LoginEntity> subscriber,String username,String name,String tel,String addr,String email){
        mPeopleService.getIsAddSuccess(username,name,tel,addr,email)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
