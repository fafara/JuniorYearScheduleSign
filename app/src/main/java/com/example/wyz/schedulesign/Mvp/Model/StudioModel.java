package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IStudioModel;
import com.example.wyz.schedulesign.Mvp.Presenter.StudioPresenter;
import com.example.wyz.schedulesign.NetWork.StudioHttpMethods;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/6/6.
 */

public class StudioModel implements IStudioModel {
    StudioPresenter mStudioPresenter=new StudioPresenter();
    @Override
    public void getAllStudio() {
        Subscriber<StudioEntity> subscriber=new Subscriber<StudioEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StudioEntity studioEntity) {
                mStudioPresenter.setListView(studioEntity.getDetail());
            }
        };
        StudioHttpMethods.getInstance().getAllStudio(subscriber);
    }
}
