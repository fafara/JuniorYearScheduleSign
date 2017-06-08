package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioStatusEntity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WYZ on 2017/6/6.
 */

public class StudioHttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private  StudioService mStudioService;

    /**
     * 设置Retrofit网络请求
     */
    private StudioHttpMethods(){
        OkHttpClient okHttpClient=genericClient();
        mRetrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiManager.STUDIO_BASE_URL)
                .build();
        mStudioService=mRetrofit.create(StudioService.class);
    }

    /**
     * 单例模式
     */
    private  static  class  SingletonHolder{
        private  static  final StudioHttpMethods INSTANCE=new StudioHttpMethods();
    }
    public  static StudioHttpMethods getInstance(){
        return  SingletonHolder.INSTANCE;
    }


    private OkHttpClient genericClient(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
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

    public  void getAllStudio(Subscriber<StudioEntity> subscriber){
        mStudioService.getAllStudio()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getAddStudioResult(Subscriber<StudioStatusEntity> subscriber,String name,int row,int col,String flag){
        mStudioService.getAddStudioResult(name,row,col,flag)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getDeleteStudioResult(Subscriber<StudioStatusEntity> subscriber,String studio_id){
        mStudioService.getDeleteStudioResult(studio_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getModifyStudioResult(Subscriber<StudioStatusEntity> subscriber,String id,String name,int row,int col,String flag){
        mStudioService.getModifyStudioResult(id,name,row,col,flag)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
