package com.example.wyz.schedulesign.Mvp.IModel;

import android.graphics.Bitmap;

/**
 * Created by WYZ on 2017/5/27.
 */

public interface IMe {
    void upLoadUserIcon(byte[] bytes);

    void getLoginUserInfo();

    void take_photo();

    void select_album();

    void save_icon_bitmap(Bitmap bitmap);


}

