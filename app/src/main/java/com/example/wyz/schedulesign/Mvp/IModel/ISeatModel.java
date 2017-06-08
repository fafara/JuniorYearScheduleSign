package com.example.wyz.schedulesign.Mvp.IModel;

import com.example.wyz.schedulesign.Mvp.Entity.SeatEntity;

import java.util.List;

/**
 * Created by WYZ on 2017/6/7.
 */

public interface ISeatModel {
    void ticketSeat(List<SeatEntity> seatEntities);
    void getSeatStatus(String studio_id);
}
