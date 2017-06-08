package com.example.wyz.schedulesign.Mvp.Presenter;

import com.example.wyz.schedulesign.Mvp.Entity.SeatAllStatusEntity;
import com.example.wyz.schedulesign.Mvp.Entity.SeatEntity;
import com.example.wyz.schedulesign.Mvp.IView.ISeatView;
import com.example.wyz.schedulesign.Mvp.Model.SeatModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

import java.util.List;

/**
 * Created by WYZ on 2017/6/7.
 */

public class SeatPresenter implements BasePresenter {
    static ISeatView sISeatView;
    static SeatModel sSeatModel;
    public SeatPresenter(ISeatView seatView) {
        sISeatView= seatView;
        sSeatModel=new SeatModel();
    }
    public SeatPresenter() {
    }

    @Override
    public void snackBarError() {
        sISeatView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        sISeatView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        sISeatView.snackBarSuccess();
    }
    public  void ticketSeat(List<SeatEntity> seatEntities){
        sSeatModel.ticketSeat(seatEntities);
    }

    public  void getSeatStatus(String studio_id){
        sSeatModel.getSeatStatus(studio_id);
    }
    public  void setSeatStatus(SeatAllStatusEntity seatAllStatusEntity){
        sISeatView.setSeatStatus(seatAllStatusEntity);
    }


}
