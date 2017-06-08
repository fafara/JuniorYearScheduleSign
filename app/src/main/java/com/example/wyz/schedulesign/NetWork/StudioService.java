package com.example.wyz.schedulesign.NetWork;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioStatusEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WYZ on 2017/6/6.
 */

public interface StudioService {
    @GET("queryStudio")
    Observable<StudioEntity> getAllStudio();
    @GET("addStudio")
    Observable<StudioStatusEntity> getAddStudioResult(@Query("studio_name")String name,@Query("studio_row_count")int row,@Query("studio_col_count")int studio_col_count,@Query("studio_flag")String studio_flag);
    @GET("deleteStudio")
    Observable<StudioStatusEntity> getDeleteStudioResult(@Query("studio_id") String studio_id);
    @GET("updataStudio")
    Observable<StudioStatusEntity> getModifyStudioResult(@Query("studio_id") String id,@Query("studio_name")String name,@Query("studio_row_count")int row,@Query("studio_col_count")int studio_col_count,@Query("studio_flag")String studio_flag);
}
