package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.Entity.FilmStatusEntity;

import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WYZ on 2017/5/19.
 */

public interface FilmService {
    @GET("querys")
    Observable<FilmEntity> getAllFilmInfo();
    @GET("querys")
    Observable<FilmEntity> getFilmInfo(@Query("film_name") String film_name);
    @GET("deleteFilm")
    Observable<FilmStatusEntity> getdeleteFilmResult(@Query("film_id")int id);
    /*@Multipart
    @POST("updatafilm")
    Observable<FilmStatusEntity> getModifyFilmResult(@Part MultipartBody.Part image,@Query("film_name")String name,
        @Query("film_tostar") String film_tostar,@Query("film_release") String film_release,@Query("film_hourlong") String film_hourlong,
        @Query("film_type") String film_type,@Query("film_price") String film_price);*/
    @Multipart
    @POST("updatafilm")
    Observable<FilmStatusEntity> getModifyFilmResult(@Part MultipartBody.Part image,@Part MultipartBody.Part name,
                                                     @Part MultipartBody.Part film_tostar,@Part MultipartBody.Part film_release
    ,@Part MultipartBody.Part film_hourlong,@Part MultipartBody.Part film_type,@Part MultipartBody.Part film_price);

    @Multipart
    @POST("addFilm")
    Observable<FilmStatusEntity> getAddFilmResult(@Part MultipartBody.Part image,@Part MultipartBody.Part name,
                                                     @Part MultipartBody.Part film_tostar,@Part MultipartBody.Part film_release
            ,@Part MultipartBody.Part film_hourlong,@Part MultipartBody.Part film_type,@Part MultipartBody.Part film_price);

}
