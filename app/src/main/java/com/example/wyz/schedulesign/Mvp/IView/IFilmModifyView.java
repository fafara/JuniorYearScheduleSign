package com.example.wyz.schedulesign.Mvp.IView;

import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.IView.base.BaseView;

import java.io.File;
import java.util.List;

/**
 * Created by WYZ on 2017/6/3.
 */

public interface IFilmModifyView extends BaseView{
    void getIntentData() ;
    void setFile(File file);
    void setSelect_album();
    void setImageView(Uri uri);
    void backFragment();
    void getModifyData();
    boolean isEmpty(EditText editText);
    boolean isChoiceImage(ImageView imageView);

    void setRecyclerView(List<PlayEntity.MDetail> filmPlayEntities);
    void initRecyclerView();
    void initRecyclerViewData(String film_id);
    void refreshView();
    void startLoadView();
    void endLoadView();
}
