package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.ApiManager.ApiManager;
import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginSingleton;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
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
    private  int i=0;
    int j=0;

    /**
     * 设置Retrofit网络请求
     */
    private UserHttpMethods(){
        OkHttpClient okHttpClient=genericClient();
        mRetrofit=new Retrofit.Builder()
                .client(okHttpClient)
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

    private  OkHttpClient genericClient(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        //存入Session
                        if (response.header("Set-Cookie") != null) {
                            if(SessionManager.getmSession().equals("")){
                                SessionManager.setSession(response.header("Set-Cookie"));
                            }

                        }
                        //刷新API调用时间
                        SessionManager.setLastApiCallTime(System.currentTimeMillis());
                        return  response;
                    }

                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder1 = request.newBuilder();
                        Request build = builder1.addHeader("Cookie", SessionManager.getmSession()).build();
                        return chain.proceed(build);

                    }
                })
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return httpClient;
    }

    public  void getIsLoginSuccess(Subscriber<LoginEntity> subscriber, String name, String pass){
        mPeopleService.getIsLoginSuccess(name,pass)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getLoginUserInfo(Subscriber<PeopleEntity.MDetail> subscriber, String name){
        mPeopleService.getLoginUserInfo(name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                  .map(new Func1<PeopleEntity, PeopleEntity.MDetail>() {
                      @Override
                      public PeopleEntity.MDetail call(PeopleEntity peopleEntity) {
                          LoginSingleton.getInstance().setLoginSingleton(peopleEntity.getDetail().get(0).getEmp_id(),peopleEntity.getDetail().get(0).getEmp_no(),
                                  peopleEntity.getDetail().get(0).getEmp_name(),peopleEntity.getDetail().get(0).getEmp_tel_num(),peopleEntity.getDetail().get(0).getEmp_addr(),
                                  peopleEntity.getDetail().get(0).getEmp_email());
                          return peopleEntity.getDetail().get(0);
                      }
                  })
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getUserInfo(Subscriber<List<PeopleEntity.MDetail>> subscriber,String name){
        mPeopleService.getLoginUserInfo(name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<PeopleEntity, List<PeopleEntity.MDetail>>() {
                    @Override
                    public List<PeopleEntity.MDetail> call(PeopleEntity peopleEntity) {
                        return peopleEntity.getDetail();
                    }
                })
                .observeOn(Schedulers.io())
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
    public  void getAllUserInfo(Subscriber<List<PeopleEntity.MDetail>> subscriber)
    {
        mPeopleService.getAllUserInfo()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<PeopleEntity, List<PeopleEntity.MDetail>>() {
                    @Override
                    public List<PeopleEntity.MDetail> call(PeopleEntity peopleEntity) {
                       return peopleEntity.getDetail();
                    }
                })
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getIsDeleteSuccess(final Subscriber<LoginEntity> subscriber, List<String> names){
        Observable.from(names)
                .map(new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        mPeopleService.getIsDeleteSuccess(s)
                                .subscribeOn(Schedulers.io())
                                .unsubscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(subscriber);
                        return null;
                    }
                })

                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }
    public  void getIsAddSuccess(Subscriber<LoginEntity> subscriber,String username,String name,String tel,String addr,String email){
        mPeopleService.getIsAddSuccess(username,name,tel,addr,email)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void getIsUpdateImage(Subscriber<LoginEntity> subscriber,byte[] bytes){
        mPeopleService.isUpdateImage(bytes)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }



}
