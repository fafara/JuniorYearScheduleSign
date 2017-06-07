package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PlayStatusEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IPlayAddModel;
import com.example.wyz.schedulesign.Mvp.Presenter.PlayAddPresenter;
import com.example.wyz.schedulesign.NetWork.FilmPlayHttpMethods;
import com.example.wyz.schedulesign.NetWork.StudioHttpMethods;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/6/6.
 */

public class PlayAddModel implements IPlayAddModel {
    PlayAddPresenter mPlayAddPresenter=new PlayAddPresenter();
    @Override
    public void addPlayForFilm(PlayEntity.MDetail detail) {
        Subscriber<PlayStatusEntity> subscriber=new Subscriber<PlayStatusEntity>() {
            @Override
            public void onCompleted() {
                mPlayAddPresenter.addCompleted();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(PlayStatusEntity playStatusEntity) {

            }
        };
        FilmPlayHttpMethods.getInstance().getAddPlayforFilm(subscriber, detail.getStudio_id(),
                detail.getPlay_start(),detail.getPlay_end(),detail.getFilm_id(),detail.getFilm_name());
    }

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
                mPlayAddPresenter.initStudioSpinner(studioEntity);
            }
        };
        StudioHttpMethods.getInstance().getAllStudio(subscriber);

    }


}
