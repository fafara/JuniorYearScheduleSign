package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.ApiManager.ApiManager;
import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;

import java.util.List;
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
 * Created by WYZ on 2017/5/19.
 */

public class FilmHttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private  FilmService mFilmService;

    /**
     * 设置Retrofit网络请求
     */
    private FilmHttpMethods(){
        OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit=new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiManager.FILM_BASE_URL)
                .build();
        mFilmService=mRetrofit.create(FilmService.class);
    }

    /**
     * 单例模式
     */
    private  static  class  SingletonHolder{
        private  static  final FilmHttpMethods INSTANCE=new FilmHttpMethods();
    }
    public  static FilmHttpMethods getInstance(){
        return  SingletonHolder.INSTANCE;
    }
    public  void getAllFilmInfo(Subscriber<List<FilmEntity.MDetail>> subscriber){
        mFilmService.getAllFilmInfo()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<FilmEntity, List<FilmEntity.MDetail>>() {
                    @Override
                    public List<FilmEntity.MDetail> call(FilmEntity filmEntity) {
                        return filmEntity.getDetail();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
