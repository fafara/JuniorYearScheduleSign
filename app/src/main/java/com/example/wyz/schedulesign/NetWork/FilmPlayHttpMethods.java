package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PlayStatusEntity;

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
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by WYZ on 2017/6/5.
 */

public class FilmPlayHttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private  FilmPlayService mFilmPlayService;

    /**
     * 设置Retrofit网络请求
     */
    private FilmPlayHttpMethods(){
        OkHttpClient okHttpClient=genericClient();
        mRetrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiManager.PLAY_BASE_URL)
                .build();
        mFilmPlayService=mRetrofit.create(FilmPlayService.class);
    }

    /**
     * 单例模式
     */
    private  static  class  SingletonHolder{
        private  static  final FilmPlayHttpMethods INSTANCE=new FilmPlayHttpMethods();
    }
    public  static FilmPlayHttpMethods getInstance(){
        return  SingletonHolder.INSTANCE;
    }


    private  OkHttpClient genericClient(){
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
               /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        BufferedReader bufferedReader=new BufferedReader( response.body().charStream());
                        StringBuffer stringBuffer=new StringBuffer();
                        String line;
                        while (((line=bufferedReader.readLine())!=null))
                        {
                            stringBuffer.append(line);
                        }
                        String html=stringBuffer.toString();
                        return  response;
                    }
                })*/
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return httpClient;
    }
    public void getFilmIdPlay(Subscriber<List<PlayEntity.MDetail>> subscriber, String id){
        mFilmPlayService.getFilmIdPlay(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(new Func1<PlayEntity, List<PlayEntity.MDetail>>() {
                    @Override
                    public List<PlayEntity.MDetail> call(PlayEntity filmPlayEntity) {
                        return  filmPlayEntity.getDetail();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getAddPlayforFilm(Subscriber<PlayStatusEntity> subscriber,String studio_id,String studio_name, String play_start,String play_end, String film_id, String film_name) {
        mFilmPlayService.addPlayforFilm(studio_id,studio_name,play_start,play_end,film_id,film_name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getModifyPlayforFilm(Subscriber<PlayStatusEntity> subscriber, String play_id,String studio_name,String studio_id, String play_start,String play_end, String film_id, String film_name){
        mFilmPlayService.updataPlayforFilm(play_id,studio_name,studio_id,play_start,play_end,film_id,film_name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public  void getDeletePlayforFilm(Subscriber<PlayStatusEntity> subscriber, String play_id){
        mFilmPlayService.deletePlay(play_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
