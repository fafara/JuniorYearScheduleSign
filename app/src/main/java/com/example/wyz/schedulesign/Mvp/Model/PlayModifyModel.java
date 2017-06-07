package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PlayStatusEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IPlayModifyModel;
import com.example.wyz.schedulesign.Mvp.Presenter.PlayModifyPresenter;
import com.example.wyz.schedulesign.NetWork.FilmPlayHttpMethods;
import com.example.wyz.schedulesign.NetWork.StudioHttpMethods;
import com.example.wyz.schedulesign.Util.MyLog;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/6/6.
 */

public class PlayModifyModel implements IPlayModifyModel{
    final  String TAG="PlayModifyModel结果";
    PlayModifyPresenter mModifyPresenter=new PlayModifyPresenter();
    @Override
    public void modifyPlay( PlayEntity.MDetail detail) {
        Subscriber<PlayStatusEntity> subscriber=new Subscriber<PlayStatusEntity>() {
            @Override
            public void onCompleted() {
                mModifyPresenter.modifyCompleted();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(PlayStatusEntity playStatusEntity) {
                MyLog.d(TAG,String.valueOf(playStatusEntity.getDetail().getStatus()));
            }
        };
        FilmPlayHttpMethods.getInstance().getModifyPlayforFilm(subscriber,detail.getPlay_id(),detail.getStudio_id(),detail.getPlay_start(),detail.getPlay_end(),detail.getFilm_id(),detail.getFilm_name());
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
                mModifyPresenter.initStudioSpinner(studioEntity);
            }
        };
        StudioHttpMethods.getInstance().getAllStudio(subscriber);

    }
}
