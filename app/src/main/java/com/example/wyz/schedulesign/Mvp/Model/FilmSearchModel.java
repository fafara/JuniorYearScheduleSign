package com.example.wyz.schedulesign.Mvp.Model;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IFilmSearchModel;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmSearchPresenter;
import com.example.wyz.schedulesign.NetWork.FilmHttpMethods;

import java.util.List;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/6/2.
 */

public class FilmSearchModel implements IFilmSearchModel {
    FilmSearchPresenter mFilmSearchPresenter=new FilmSearchPresenter();
    @Override
    public void getFilmFindResult(String name) {
        Subscriber<List<FilmEntity.MDetail>> subscriber=new Subscriber<List<FilmEntity.MDetail>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //mFilmSearchPresenter.snackBarError(e.getMessage());
            }

            @Override
            public void onNext(List<FilmEntity.MDetail> mDetails) {
                    mFilmSearchPresenter.setFilmFindResult(mDetails);
            }
        };
        FilmHttpMethods.getInstance().getFilmInfo(subscriber,name);
    }
}
