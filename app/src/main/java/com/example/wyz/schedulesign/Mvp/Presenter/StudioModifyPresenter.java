package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.IStudioModifyView;
import com.example.wyz.schedulesign.Mvp.Model.StudioModifyModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;


/**
 * Created by WYZ on 2017/6/7.
 */

public class StudioModifyPresenter implements BasePresenter {
    static IStudioModifyView sIStudioModifyView;
    static StudioModifyModel  sStudioModifyModel;
    public StudioModifyPresenter(IStudioModifyView view) {
        sIStudioModifyView= view;
        sStudioModifyModel=new StudioModifyModel();
    }
    public StudioModifyPresenter() {
    }

    @Override
    public void snackBarError() {
        sIStudioModifyView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sIStudioModifyView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sIStudioModifyView.snackBarSuccess();
    }


    public  void getSpinner(Integer[] integers,int flag){
        sStudioModifyModel.dealWithSpinner(integers,flag);
    }
    public  void setSpinner(int i){
        sIStudioModifyView.setSpinner(i);
    }
    public  void completed(){
        sIStudioModifyView.setNetCompleted();
    }
    public  void getModifyStudioResult(StudioEntity.MDetail detail){
        sStudioModifyModel.getModifyStudioResult(detail);
    }
    public  void getDeleteStudioResult(String studio_id){
        sStudioModifyModel.getDeleteStudioResult(studio_id);
    }




}
