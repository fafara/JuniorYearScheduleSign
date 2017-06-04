package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.Entity.FilmStatusEntity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
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
        OkHttpClient okHttpClient=genericClient();
        mRetrofit=new Retrofit.Builder()
                .client(okHttpClient)
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
    public  void getFilmInfo(Subscriber<List<FilmEntity.MDetail>> subscriber,String film_name){
        mFilmService.getFilmInfo(film_name)
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
    public void getdeleteFilmResult(final Subscriber<FilmStatusEntity> subscriber, List<Integer> integers){
        Observable.from(integers)
                .map(new Func1<Integer, Object>() {
                    @Override
                    public Object call(Integer integer) {
                        mFilmService.getdeleteFilmResult(integer)
                                .subscribeOn(Schedulers.io())
                                .unsubscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(subscriber);
                        return  null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void getModifyFilmResult(Subscriber<FilmStatusEntity> subscriber, MultipartBody.Part file,MultipartBody.Part name,MultipartBody.Part tostar,MultipartBody.Part release,MultipartBody.Part hourlong,MultipartBody.Part type,MultipartBody.Part price){
        mFilmService.getModifyFilmResult(file,name,tostar,release,hourlong,type,price)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void getAddFilmResult(Subscriber<FilmStatusEntity> subscriber, MultipartBody.Part file,MultipartBody.Part name,MultipartBody.Part tostar,MultipartBody.Part release,MultipartBody.Part hourlong,MultipartBody.Part type,MultipartBody.Part price){
        mFilmService.getAddFilmResult(file,name,tostar,release,hourlong,type,price)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
