package com.example.wyz.schedulesign.Mvp.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYZ on 2017/6/8.
 */

public class SeatAllStatusEntity {
    private boolean Result;
    private List<SeatEntity>  Detail=new ArrayList<>();


    public List<SeatEntity> getDetail() {
        return Detail;
    }

    public void setDetail(List<SeatEntity> detail) {
        Detail = detail;
    }

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean result) {
        Result = result;
    }
}
