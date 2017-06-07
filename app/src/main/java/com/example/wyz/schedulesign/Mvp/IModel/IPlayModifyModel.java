package com.example.wyz.schedulesign.Mvp.IModel;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;

/**
 * Created by WYZ on 2017/6/6.
 */

public interface IPlayModifyModel {
    void modifyPlay(PlayEntity.MDetail detail);
    void getAllStudio();
}
