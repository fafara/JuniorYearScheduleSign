package com.example.wyz.schedulesign.Mvp.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.wyz.schedulesign.Mvp.IView.IMeView;
import com.example.wyz.schedulesign.Mvp.Model.MeModel;
import com.example.wyz.schedulesign.Mvp.Presenter.base.BasePresenter;

import java.io.FileNotFoundException;

/**
 * Created by WYZ on 2017/5/27.
 */

public class MePresenter implements BasePresenter{
    private static IMeView mIMeView;
    private  static MeModel mMeModel;
    public static  Context sContext;
    public MePresenter(){

    }
    public MePresenter(IMeView IMeView) {
        mIMeView = IMeView;
        mMeModel=new MeModel();
    }
    public   void getLoginUserInfo(){
        mMeModel.getLoginUserInfo();
    }
    public  void netCompleteView(){
        if(mIMeView.isInitID()) {
            mIMeView.setUserInfo();
            mIMeView.setUserIcon();
        }
    }
    public void setContext(Context context){
        sContext=context;
    }

    @Override
    public void snackBarError() {
        mIMeView.snackBarError();
    }

    @Override
    public void snackBarError(String msg) {
        mIMeView.snackBarError(msg);
    }

    @Override
    public void snackBarSuccess() {
        mIMeView.snackBarSuccess();
    }

    public  void cancelChoiceView(){
        mIMeView.cancelChoiceView();
    }

    public  void dealTake_photo(){
        mMeModel.take_photo();
    }
    public  void dealSelect_album(){
        mMeModel.select_album();
    }
    public  void setTake_photo(Uri uri){
        mIMeView.take_photoView(uri);
    }

    public void setSelect_album(Uri icoUri){
        mIMeView.select_albumView(icoUri);
    }
    public  void save_upload_Image(Uri uri) {

        try {
            Bitmap bitmap= BitmapFactory.decodeStream(sContext.getContentResolver().openInputStream(uri));
            if(bitmap!=null){
                mMeModel.save_upload_icon_bitmap(bitmap);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    public  void updateIcon(){
        mIMeView.setUploadImageIcon();
    }
    public  void updateIcon(Bitmap bitmap){
        mIMeView.setUploadImageIcon(bitmap);
    }



}
