package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.IPlayAddView;
import com.example.wyz.schedulesign.Mvp.Model.PlayAddModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

/**
 * Created by WYZ on 2017/6/6.
 */

public class PlayAddPresenter implements BasePresenter {
    static IPlayAddView mIPlayAddView;
    static  PlayAddModel mPlayAddModel;
    public PlayAddPresenter(IPlayAddView playAddView) {
        mIPlayAddView= playAddView;
        mPlayAddModel=new PlayAddModel();
    }
    public PlayAddPresenter() {
    }

    @Override
    public void snackBarError() {
        mIPlayAddView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        mIPlayAddView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        mIPlayAddView.snackBarSuccess();
    }

    public  void addPlayForFilm(PlayEntity.MDetail detail){
        mPlayAddModel.addPlayForFilm(detail);
    }
    public  void addCompleted(){
        mIPlayAddView.addCompleted();
    }
    public  void getAllStudio(){
        mPlayAddModel.getAllStudio();
    }

    public  void initStudioSpinner(StudioEntity studioEntity){
        mIPlayAddView.initAllStudioSpinnerView(studioEntity);
    }


}

