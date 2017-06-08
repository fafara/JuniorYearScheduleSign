package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.IStudioView;
import com.example.wyz.schedulesign.Mvp.Model.StudioModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

import java.util.List;

/**
 * Created by WYZ on 2017/6/7.
 */

public class StudioPresenter implements BasePresenter {
    static IStudioView sIStudioView;
    static StudioModel sStudioModel;
    public StudioPresenter(IStudioView studioView) {
        sIStudioView= studioView;
        sStudioModel=new StudioModel();
    }
    public StudioPresenter() {
    }

    @Override
    public void snackBarError() {
        sIStudioView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sIStudioView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sIStudioView.snackBarSuccess();
    }


    public  void getAllStudio(){
        sStudioModel.getAllStudio();
    }
    public  void setListView(List<StudioEntity.MDetail> lists){
        sIStudioView.setListView(lists);
    }


}
