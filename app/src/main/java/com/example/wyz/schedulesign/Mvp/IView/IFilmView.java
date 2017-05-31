package com.example.wyz.schedulesign.Mvp.IView;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IView.base.BaseView;

import java.util.List;

/**
 * Created by WYZ on 2017/5/29.
 */

public interface IFilmView  extends BaseView{
    void initListView(List<FilmEntity.MDetail> mFilmEntityList);
}
