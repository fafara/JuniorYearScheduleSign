package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioStatusEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IStudioAddModel;
import com.example.wyz.schedulesign.Mvp.Presenter.StudioAddPresenter;
import com.example.wyz.schedulesign.NetWork.StudioHttpMethods;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/6/7.
 */

public class StudioAddModel implements IStudioAddModel {

    StudioAddPresenter mStudioAddPresenter=new StudioAddPresenter();
    @Override
    public void getAddStudioResult(StudioEntity.MDetail detail) {
        Subscriber<StudioStatusEntity> subscriber=new Subscriber<StudioStatusEntity>() {
            @Override
            public void onCompleted() {
                mStudioAddPresenter.onNetCompleted();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StudioStatusEntity studioStatusEntity) {

            }
        };
        StudioHttpMethods.getInstance().getAddStudioResult(subscriber,detail.getStudio_name(),detail.getStudio_row_count(),detail.getStudio_col_count(),detail.getStudio_flag());
    }
}
