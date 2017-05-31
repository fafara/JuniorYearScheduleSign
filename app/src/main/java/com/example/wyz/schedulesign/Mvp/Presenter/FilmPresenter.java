package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IView.IFilmView;
import com.example.wyz.schedulesign.Mvp.Model.FilmModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

import java.util.List;

/**
 * Created by WYZ on 2017/5/29.
 */

public class FilmPresenter implements BasePresenter{
    private  static IFilmView sIFilmView;
    private  static FilmModel sFilmModel;

    public FilmPresenter() {
    }
    public FilmPresenter(IFilmView  iFilmView){
        sIFilmView=iFilmView;
        sFilmModel=new FilmModel();
    }
    public  void getAllFilm(){
        sFilmModel.getAllFilm();
    }
    public  void initListView(List<FilmEntity.MDetail> filmEntity){
        sIFilmView.initListView(filmEntity);
    }

    @Override
    public void snackBarError() {
        sIFilmView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sIFilmView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sIFilmView.snackBarSuccess();
    }
}
