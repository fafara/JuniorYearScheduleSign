package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PlayStatusEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WYZ on 2017/6/5.
 */

public interface FilmPlayService {
    @GET("queryPlay")
    Observable<PlayEntity> getFilmIdPlay(@Query("film_id")String id);
    @GET("addPlay")
    Observable<PlayStatusEntity> addPlayforFilm(@Query("studio_id")String studio_id,@Query("studio_name")String studio_name,@Query("play_start")String play_start,
                    @Query("play_end")String play_end,@Query("film_id")String film_id,@Query("film_name")String film_name);
    @GET("updataPlay")
    Observable<PlayStatusEntity> updataPlayforFilm(@Query("play_id")String play_id,@Query("studio_name")String studio_name,@Query("studio_id")String studio_id,@Query("play_start")String play_start,
                                                @Query("play_end")String play_end,@Query("film_id")String film_id,@Query("film_name")String film_name);

    @GET("deletePlay")
    Observable<PlayStatusEntity> deletePlay(@Query("play_id")String play_id);
}
