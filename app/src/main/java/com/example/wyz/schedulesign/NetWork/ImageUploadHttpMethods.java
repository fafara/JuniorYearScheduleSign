package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginSingleton;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by WYZ on 2017/5/31.
 */

public class ImageUploadHttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private ImageUploadService mImageUploadService;
    public  final MediaType CONTENT_TYPE=MediaType.parse("application/json; charset=utf-8");
    /**
     * 单例模式
     */
    private ImageUploadHttpMethods(){
        OkHttpClient okHttpClient=genericClient();
        mRetrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiManager.IMAGE_UPLOAD_BASE_URL)
                .build();
        mImageUploadService=mRetrofit.create(ImageUploadService.class);
    }



    public  static  ImageUploadHttpMethods getInstance(){
        return  SingletonHolder.INSTANCE;
    }

    private  static  class  SingletonHolder{
        private  static  final ImageUploadHttpMethods INSTANCE=new ImageUploadHttpMethods();
    }
    private OkHttpClient genericClient() {
        /*RequestBody fileBody=RequestBody.create(CONTENT_TYPE,file);
        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition","form-data; name=\"name\""),RequestBody.create(null,"inputFile"))
                .addPart(Headers.of("Content-Disposition","form-data; name=\"filename\""),fileBody)
                .build();
           */
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

    public void getIsUpdateImage(Subscriber<LoginEntity> subscriber, MultipartBody.Part file ){
        mImageUploadService.uploadImage(file)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<LoginEntity, LoginEntity>() {
                    @Override
                    public LoginEntity call(LoginEntity loginEntity) {
                        if(loginEntity.getDetail().getStatus()==1){
                            LoginSingleton.getInstance().setImage(loginEntity.getDetail().getImage());
                        }else{
                            LoginSingleton.getInstance().setImage(null);
                        }
                        return loginEntity;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void uploadMoreFile(Subscriber<LoginEntity> subscriber, Map<String, RequestBody> bodyMap){
        mImageUploadService.uploadImage(bodyMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<LoginEntity, LoginEntity>() {
                    @Override
                    public LoginEntity call(LoginEntity loginEntity) {
                        if(loginEntity.getDetail().getStatus()==1){
                            LoginSingleton.getInstance().setImage(loginEntity.getDetail().getImage());
                        }else{
                            LoginSingleton.getInstance().setImage(null);
                        }
                        return loginEntity;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
