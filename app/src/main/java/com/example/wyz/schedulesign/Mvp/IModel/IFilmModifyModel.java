package com.example.wyz.schedulesign.Mvp.IModel;

import android.graphics.Bitmap;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;

import java.io.File;

/**
 * Created by WYZ on 2017/6/3.
 */

public interface IFilmModifyModel{
    void modifyFilm(File file,FilmEntity.MDetail detail);
    void saveFile(String path);
    void saveFile(Bitmap bitmap);
    void select_album();


}
