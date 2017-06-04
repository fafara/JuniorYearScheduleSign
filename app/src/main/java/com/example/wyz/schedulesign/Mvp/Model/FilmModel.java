package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.Entity.FilmStatusEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IFilmModel;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmPresenter;
import com.example.wyz.schedulesign.NetWork.FilmHttpMethods;
import com.example.wyz.schedulesign.Util.MyLog;

import java.util.List;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/5/29.
 */

public class FilmModel implements IFilmModel{

    final String TAG="FilmModel";
    Subscriber<List<FilmEntity.MDetail>> mSubscriber;
    FilmPresenter mFilmPresenter=new FilmPresenter();
    List<FilmEntity.MDetail>  mDetails=null;
    @Override
    public void getAllFilm() {
        mSubscriber=new Subscriber<List<FilmEntity.MDetail>>() {
            @Override
            public void onCompleted() {
                mFilmPresenter.initListView(mDetails);
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
                mFilmPresenter.snackBarError(e.getMessage());
            }

            @Override
            public void onNext(List<FilmEntity.MDetail> FilmEntity) {
                mDetails=FilmEntity;


            }
        };
        FilmHttpMethods.getInstance().getAllFilmInfo(mSubscriber);
    }

    @Override
    public void deleteFilm(List<Integer> integers) {
        Subscriber<FilmStatusEntity> subscriber=new Subscriber<FilmStatusEntity>() {
            @Override
            public void onCompleted() {
                mFilmPresenter.refreshView();
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
                mFilmPresenter.snackBarError(e.getMessage());
            }

            @Override
            public void onNext(FilmStatusEntity filmStatusEntity) {
                switch (filmStatusEntity.getDetail().getStatus()){
                    case 1:
                        mFilmPresenter.snackBarSuccess();
                        break;
                    case  0:
                        mFilmPresenter.snackBarError();
                        break;
                }
            }
        };
        FilmHttpMethods.getInstance().getdeleteFilmResult(subscriber,integers);
    }

}
