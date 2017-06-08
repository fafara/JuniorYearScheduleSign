package com.example.wyz.schedulesign.Mvp.IView;

import android.widget.EditText;

import com.example.wyz.schedulesign.Mvp.IView.base.BaseView;

/**
 * Created by WYZ on 2017/6/7.
 */

public interface IStudioAddView extends BaseView {
    boolean isEmpty(EditText editText);
    void setSpinner();
    void setNetCompleted();
}
