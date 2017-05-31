package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by WYZ on 2017/5/31.
 */

public interface ImageUploadService {
    //Service方法, 普通form表单使用RequestBody, 并且@Part注解要写表单字段名
    //文件用MultipartBody.Part
    //多文件用多个MultipartBody.Part或者@PartMap
    @Multipart
    @POST("updataImage")
    Observable<LoginEntity> uploadImage( @Part MultipartBody.Part file);

    @Multipart
    @POST("updataImage")
    Observable<LoginEntity> uploadImage(@PartMap Map<String, RequestBody> bodyMap);
}
