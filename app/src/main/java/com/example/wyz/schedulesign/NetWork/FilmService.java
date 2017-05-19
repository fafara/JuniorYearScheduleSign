package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by WYZ on 2017/5/19.
 */

public interface FilmService {
    @GET("querys")
    Observable<FilmEntity> getAllFilmInfo();
}
