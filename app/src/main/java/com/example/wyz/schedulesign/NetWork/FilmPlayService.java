package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.FilmPlayEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WYZ on 2017/6/5.
 */

public interface FilmPlayService {
    @GET("queryPlay")
    Observable<FilmPlayEntity> getFilmIdPlay(@Query("film_id")String id);
}
