package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Adapter.Film_Adapter;
import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IView.IFilmView;
import com.example.wyz.schedulesign.Mvp.Model.FilmModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import static com.example.wyz.schedulesign.Mvp.Adapter.Film_Adapter.mMDetails;

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

    public  void deleteFilm(){
        List<Integer> lists=new ArrayList<>();
        for(int i = 0; i< Film_Adapter.sIntegers.size(); i++){

            int id=mMDetails.get(Film_Adapter.sIntegers.get(i)).getFilm_id();
            lists.add(id);
        }
        sFilmModel.deleteFilm(lists);
    }
    public  void initListView(List<FilmEntity.MDetail> filmEntity){
        sIFilmView.initListView(filmEntity);
    }
    public  void refreshView(){
        sIFilmView.refreshView();
    }
    public  void refreshListView(List<FilmEntity.MDetail>  mDetails){
        sIFilmView.refreshListView(mDetails);
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
