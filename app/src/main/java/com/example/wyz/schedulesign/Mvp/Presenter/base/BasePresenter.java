package com.example.wyz.schedulesign.Mvp.Presenter.base;

/**
 * Created by WYZ on 2017/5/29.
 */

public interface BasePresenter {
    void   snackBarError();
    void   snackBarError(String msg);
    void   snackBarSuccess();
}
