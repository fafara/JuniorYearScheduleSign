package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioStatusEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IStudioModifyModel;
import com.example.wyz.schedulesign.Mvp.Presenter.StudioModifyPresenter;
import com.example.wyz.schedulesign.NetWork.StudioHttpMethods;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/6/7.
 */

public class StudioModifyModel implements IStudioModifyModel {

    StudioModifyPresenter mStudioModifyPresenter=new StudioModifyPresenter();
    @Override
    public void dealWithSpinner(Integer[] integers, int a) {
        int i=0;
        for(i=0;i<integers.length;i++){
            if(a==integers[i])
                break;
        }
        mStudioModifyPresenter.setSpinner(i);
    }

    @Override
    public void getModifyStudioResult(StudioEntity.MDetail detail) {
        Subscriber<StudioStatusEntity> subscriber=new Subscriber<StudioStatusEntity>() {
            @Override
            public void onCompleted() {
                mStudioModifyPresenter.completed();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StudioStatusEntity studioStatusEntity) {

            }
        };
        StudioHttpMethods.getInstance().getModifyStudioResult(subscriber,detail.getStudio_id(),detail.getStudio_name(),detail.getStudio_row_count(),detail.getStudio_col_count(),detail.getStudio_flag());
    }

    @Override
    public void getDeleteStudioResult(String studio_id) {
        Subscriber<StudioStatusEntity> subscriber=new Subscriber<StudioStatusEntity>() {
            @Override
            public void onCompleted() {
                mStudioModifyPresenter.completed();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StudioStatusEntity studioStatusEntity) {

            }
        };
        StudioHttpMethods.getInstance().getDeleteStudioResult(subscriber,studio_id);

    }
}
