package com.example.wyz.schedulesign.Mvp.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.wyz.schedulesign.Mvp.IView.IMeView;
import com.example.wyz.schedulesign.Mvp.Model.MeModel;

import java.io.FileNotFoundException;

/**
 * Created by WYZ on 2017/5/27.
 */

public class MePresenter {
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
    public  void   snackBarError()
    {
        mIMeView.snackBarError();
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
    public void saveSuccessIconBitmap(Uri uri){
        try {
            Bitmap bitmap= BitmapFactory.decodeStream(sContext.getContentResolver().openInputStream(uri));
            mMeModel.save_icon_bitmap(bitmap);
            mIMeView.setSuccessCropIcon(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public  void saveErrorIconBitmap(Uri uri) throws  FileNotFoundException{
        Bitmap bitmap = BitmapFactory.decodeStream(sContext.getContentResolver().openInputStream(uri));
        mIMeView.setDefeatCropIcon(bitmap);

    }


}
