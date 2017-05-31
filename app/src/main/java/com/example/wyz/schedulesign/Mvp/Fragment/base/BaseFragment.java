package com.example.wyz.schedulesign.Mvp.Fragment.base;

import android.support.v4.app.Fragment;
import android.view.View;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

/**
 * Created by WYZ on 2017/5/17.
 */

public abstract class BaseFragment extends Fragment {
    public  abstract  void initViews();

    public  abstract  void refreshViews();

    public  abstract void initInject(View view);

    public  abstract  void initPresenter();


    public void snackBarError() {
        SnackbarManager.show(Snackbar.with(getActivity()).text("请求出错"));
    }

    public void snackBarError(String msg) {
        SnackbarManager.show(Snackbar.with(getActivity()).text(msg));
    }

    public void snackBarSuccess() {
        SnackbarManager.show(Snackbar.with(getActivity()).text("请求成功"));
    }

}
