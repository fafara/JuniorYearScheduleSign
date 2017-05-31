package com.example.wyz.schedulesign.Mvp.IView;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.wyz.schedulesign.Mvp.IView.base.BaseView;

/**
 * Created by WYZ on 2017/5/27.
 */

public interface IMeView extends BaseView {
    void setUserIcon();
    void setUserIcon(String path);
    void setUserInfo();
    void setExitOperation();
    boolean isInitID();
    void cancelChoiceView();
    void take_photoView(Uri uri);
    void select_albumView(Uri uri);
    void setUploadImageIcon(Bitmap bitmap);
    void setUploadImageIcon();
}
