package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by WYZ on 2017/6/6.
 */

public interface StudioService {
    @GET("queryStudio")
    Observable<StudioEntity> getAllStudio();
}
