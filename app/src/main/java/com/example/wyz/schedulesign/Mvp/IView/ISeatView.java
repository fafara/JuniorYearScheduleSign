package com.example.wyz.schedulesign.Mvp.IView;

import com.example.wyz.schedulesign.Mvp.Entity.SeatAllStatusEntity;
import com.example.wyz.schedulesign.Mvp.IView.base.BaseView;

/**
 * Created by WYZ on 2017/6/7.
 */

public interface ISeatView  extends BaseView{
    void getIntentData();
    void initGridView();
    void initSeatView();
    void intoTicket();

    void  getSeatSold(String studio_id);

    void setSeatStatus(SeatAllStatusEntity seatAllStatusEntity);
    void startLoadView();
    void endLoadView();
}
