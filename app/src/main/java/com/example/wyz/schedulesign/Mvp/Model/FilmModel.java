package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IFilm;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmPresenter;
import com.example.wyz.schedulesign.NetWork.FilmHttpMethods;
import com.example.wyz.schedulesign.Util.MyLog;

import java.util.List;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/5/29.
 */

public class FilmModel implements IFilm{

    final String TAG="FilmModel";
    Subscriber<List<FilmEntity.MDetail>> mSubscriber;
    FilmPresenter mFilmPresenter=new FilmPresenter();
    @Override
    public void getAllFilm() {
        mSubscriber=new Subscriber<List<FilmEntity.MDetail>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
                mFilmPresenter.snackBarError(e.getMessage());
            }

            @Override
            public void onNext(List<FilmEntity.MDetail> FilmEntity) {

                mFilmPresenter.initListView(FilmEntity);

            }
        };
        FilmHttpMethods.getInstance().getAllFilmInfo(mSubscriber);
    }
}
