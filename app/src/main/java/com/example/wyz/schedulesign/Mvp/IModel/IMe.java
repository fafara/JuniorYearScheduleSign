package com.example.wyz.schedulesign.Mvp.IModel;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by WYZ on 2017/5/27.
 */

public interface IMe {
    void upLoadUserIcon(File file);

    void getLoginUserInfo();

    void take_photo();

    void select_album();

    void save_upload_icon_bitmap(Bitmap bitmap);


}

