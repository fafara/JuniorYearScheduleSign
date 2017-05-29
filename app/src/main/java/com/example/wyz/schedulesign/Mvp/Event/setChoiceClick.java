package com.example.wyz.schedulesign.Mvp.Event;

import android.app.Activity;
import android.view.View;

import com.example.wyz.schedulesign.Mvp.Presenter.MePresenter;
import com.example.wyz.schedulesign.R;

/**
 * Created by WYZ on 2017/5/27.
 */

public class setChoiceClick implements View.OnClickListener {
    private  static  Activity  mActivity;
    private static final int MY_PERMISSIONS_REQUEST_CAMER= 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE= 2;
    MePresenter mMePresenter=new MePresenter();
    public setChoiceClick( Activity activity) {
        mActivity=activity;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.take_photo:
                mMePresenter.dealTake_photo();
                break;
            case  R.id.album:
                mMePresenter.dealSelect_album();
                break;
            case R.id.cancel:
                mMePresenter.cancelChoiceView();
                break;
        }
    }
}
