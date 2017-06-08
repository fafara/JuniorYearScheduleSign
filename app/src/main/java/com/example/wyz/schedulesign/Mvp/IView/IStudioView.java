package com.example.wyz.schedulesign.Mvp.IView;

import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.base.BaseView;

import java.util.List;

/**
 * Created by WYZ on 2017/6/7.
 */

public interface IStudioView extends BaseView {
    void setListView(List<StudioEntity.MDetail> list);
}
