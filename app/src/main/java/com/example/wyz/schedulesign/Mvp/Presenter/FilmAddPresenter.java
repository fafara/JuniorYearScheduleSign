package com.example.wyz.schedulesign.Mvp.Presenter;

import android.graphics.Bitmap;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IView.IFilmAddView;
import com.example.wyz.schedulesign.Mvp.Model.FilmAddModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

import java.io.File;

/**
 * Created by WYZ on 2017/6/4.
 */

public class FilmAddPresenter implements BasePresenter {
    private  static FilmAddModel sFilmAddModel;
    private  static IFilmAddView sIFilmAddView;
    public FilmAddPresenter(IFilmAddView addView) {
        sIFilmAddView=addView;
        sFilmAddModel=new FilmAddModel();
    }

    public FilmAddPresenter() {
    }

    @Override
    public void snackBarError() {
        sIFilmAddView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sIFilmAddView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sIFilmAddView.snackBarSuccess();
    }

    public  void saveFile(String path){
        sFilmAddModel.saveFile(path);

    }
    public  void saveFile(Bitmap bitmap){

        sFilmAddModel.saveFile(bitmap);
    }
    public  void setFile(File file){
        sIFilmAddView.setFile(file);
    }
    public void select_album(){
        sFilmAddModel.select_album();
    }
    public  void setSelect_album(){
        sIFilmAddView.setSelect_album();
    }

    public  void backFragment(){
        sIFilmAddView.backFragment();
    }
    public  void addFilm(File file, FilmEntity.MDetail detail){
        sFilmAddModel.addFilm(file,detail);
    }
}
