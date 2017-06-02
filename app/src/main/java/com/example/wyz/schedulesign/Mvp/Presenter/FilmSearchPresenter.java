package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IView.IFilmSearchView;
import com.example.wyz.schedulesign.Mvp.Model.FilmSearchModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

import java.util.List;

/**
 * Created by WYZ on 2017/6/2.
 */

public class FilmSearchPresenter implements BasePresenter {
    private  static FilmSearchModel sFilmSearchModel;
    private static IFilmSearchView sIFilmSearchView;

    public FilmSearchPresenter() {
    }

    public FilmSearchPresenter(IFilmSearchView view) {
        sIFilmSearchView= view;
        sFilmSearchModel=new FilmSearchModel();
    }

    @Override
    public void snackBarError() {
        sIFilmSearchView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sIFilmSearchView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sIFilmSearchView.snackBarSuccess();
    }
    public void getFilmFindResult(String name){
        sFilmSearchModel.getFilmFindResult(name);
    }
    public  void setFilmFindResult(List<FilmEntity.MDetail> mDetails ){
        sIFilmSearchView.updateView(mDetails);
    }

}
