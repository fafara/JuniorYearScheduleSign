package com.example.wyz.schedulesign.Mvp.IModel;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;

/**
 * Created by WYZ on 2017/6/7.
 */

public interface IStudioModifyModel {
    void dealWithSpinner(Integer[] integers,int a);
    void getModifyStudioResult(StudioEntity.MDetail detail);

    void getDeleteStudioResult(String studio_id);
}
