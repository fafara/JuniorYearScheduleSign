package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.IPlayModifyView;
import com.example.wyz.schedulesign.Mvp.Model.PlayModifyModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

/**
 * Created by WYZ on 2017/6/6.
 */

public class PlayModifyPresenter implements BasePresenter {
    static IPlayModifyView sIPlayModifyView;
    static PlayModifyModel sPlayModifyModel;
    public PlayModifyPresenter(IPlayModifyView playAddView) {
        sIPlayModifyView= playAddView;
        sPlayModifyModel=new PlayModifyModel();
    }
    public PlayModifyPresenter() {
    }

    @Override
    public void snackBarError() {
        sIPlayModifyView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sIPlayModifyView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sIPlayModifyView.snackBarSuccess();
    }
    public  void modifyPlay(PlayEntity.MDetail detail){
        sPlayModifyModel.modifyPlay(detail);
    }
    public void modifyCompleted(){
        sIPlayModifyView.modifyCompleted();
    }
    public  void getAllStudio(){
        sPlayModifyModel.getAllStudio();
    }
    public  void initStudioSpinner(StudioEntity studioEntity){
        sIPlayModifyView.initAllStudioSpinnerView(studioEntity);
    }
}
