package com.example.wyz.schedulesign.Mvp.Entity;

/**
 * Created by WYZ on 2017/5/17.
 */

public class LoginEntity {
    private boolean Result;
    private MDetail  Detail;


    public  class MDetail {

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public MDetail getDetail() {
        return Detail;
    }

    public void setDetail(MDetail detail) {
        Detail = detail;
    }

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean result) {
        Result = result;
    }
}
