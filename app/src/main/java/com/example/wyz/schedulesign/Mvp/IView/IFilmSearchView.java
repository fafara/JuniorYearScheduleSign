package com.example.wyz.schedulesign.Mvp.IView;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IView.base.BaseView;

import java.util.List;

/**
 * Created by WYZ on 2017/6/2.
 */

public interface IFilmSearchView extends BaseView {
    void initSearchView();
    void updateView(List<FilmEntity.MDetail> details);
}
