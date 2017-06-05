package com.example.wyz.schedulesign.Mvp.Presenter;

import android.graphics.Bitmap;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.Entity.FilmPlayEntity;
import com.example.wyz.schedulesign.Mvp.IView.IFilmModifyView;
import com.example.wyz.schedulesign.Mvp.Model.FilmModifyModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

import java.io.File;
import java.util.List;

/**
 * Created by WYZ on 2017/6/3.
 */

public class FilmModifyPresenter implements BasePresenter {
    private  static FilmModifyModel sFilmModifyModel;
    private  static IFilmModifyView sIFilmModifyView;
    public FilmModifyPresenter(IFilmModifyView modifyView) {
        sIFilmModifyView=modifyView;
        sFilmModifyModel=new FilmModifyModel();
    }

    public FilmModifyPresenter() {
    }

    @Override
    public void snackBarError() {
        sIFilmModifyView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sIFilmModifyView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sIFilmModifyView.snackBarSuccess();
    }

    public  void modifyFilm(File file,FilmEntity.MDetail detail){
        sFilmModifyModel.modifyFilm(file,detail);
    }
    public  void saveFile(String path){
        sFilmModifyModel.saveFile(path);

    }
    public  void saveFile(Bitmap bitmap){

        sFilmModifyModel.saveFile(bitmap);
    }
    public  void setFile(File file){
        sIFilmModifyView.setFile(file);
    }
    public void select_album(){
        sFilmModifyModel.select_album();
    }
    public  void setSelect_album(){
        sIFilmModifyView.setSelect_album();
    }

    public  void backFragment(){
        sIFilmModifyView.backFragment();
    }

    public void getFilmIdPlay(String film_id){
        sFilmModifyModel.getFilmIdPlay(film_id);
    }

    public void setRecyclerViewData(List<FilmPlayEntity.MDetail> filmPlayEntities){
        sIFilmModifyView.setRecyclerView(filmPlayEntities);
    }
}
