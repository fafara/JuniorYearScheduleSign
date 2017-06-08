package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.IStudioAddView;
import com.example.wyz.schedulesign.Mvp.Model.StudioAddModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

/**
 * Created by WYZ on 2017/6/7.
 */

public class StudioAddPresenter implements BasePresenter {
    static IStudioAddView sIStudioAddView;
    static StudioAddModel  sStudioAddModel;
    public StudioAddPresenter(IStudioAddView view) {
        sIStudioAddView= view;
        sStudioAddModel=new StudioAddModel();
    }
    public StudioAddPresenter() {
    }

    @Override
    public void snackBarError() {
        sIStudioAddView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sIStudioAddView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sIStudioAddView.snackBarSuccess();
    }


    public  void getAddStudioResult(StudioEntity.MDetail detail){
        sStudioAddModel.getAddStudioResult(detail);
    }

    public  void onNetCompleted(){
        sIStudioAddView.setNetCompleted();
    }
}
