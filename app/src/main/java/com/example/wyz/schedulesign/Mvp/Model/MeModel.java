package com.example.wyz.schedulesign.Mvp.Model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.example.wyz.schedulesign.Mvp.Entity.LoginEntity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginSingleton;
import com.example.wyz.schedulesign.Mvp.Entity.PeopleEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IMe;
import com.example.wyz.schedulesign.Mvp.Presenter.MePresenter;
import com.example.wyz.schedulesign.NetWork.ImageUploadHttpMethods;
import com.example.wyz.schedulesign.NetWork.UserHttpMethods;
import com.example.wyz.schedulesign.Util.MyLog;
import com.example.wyz.schedulesign.Util.RxPartMapUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import rx.Subscriber;

/**
 * Created by WYZ on 2017/5/27.
 */

public class MeModel implements IMe{
    final  String TAG="MeModel";
    private final  String ALBUM_PATH= Environment.getExternalStorageDirectory().getPath()+"/logo";
    private  static  Bitmap sBitmap=null;
    MePresenter mMePresenter = new MePresenter();
    @Override
    public void upLoadUserIcon(File file) {
        Subscriber<LoginEntity> subscriber=new Subscriber<LoginEntity>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
            }

            @Override
            public void onNext(LoginEntity loginEntity) {
                if(loginEntity.getDetail().getImage()!=null&&sBitmap!=null){
                    mMePresenter.updateIcon(sBitmap);
                }
            }
        };
        //ImageUploadHttpMethods.getInstance().uploadMoreFile(subscriber, RxPartMapUtils.toMapofMoreFile(ALBUM_PATH+"/1.png"));
        ImageUploadHttpMethods.getInstance().getIsUpdateImage(subscriber, RxPartMapUtils.toMultipartBodyOfFile(file));
    }

    @Override
    public void getLoginUserInfo() {
        Subscriber<PeopleEntity.MDetail> subscriber=new Subscriber<PeopleEntity.MDetail>() {
            @Override
            public void onCompleted() {
                mMePresenter.netCompleteView();
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
                mMePresenter.snackBarError();
            }

            @Override
            public void onNext(PeopleEntity.MDetail item_peopleEntities) {

            }
        };
        if(!Objects.equals(LoginSingleton.getInstance().getUsername(), "")){
            UserHttpMethods.getInstance().getLoginUserInfo(subscriber,LoginSingleton.getInstance().getUsername());
        }


    }

    @Override
    public void take_photo() {
        File file = new File(ALBUM_PATH+"/logo.png/");  //新建图片
        File folder = new File(ALBUM_PATH);
        if (!folder.exists()) {  //如果文件夹不存在，创建文件夹
            folder.mkdirs();
        }
        Uri iconUri=Uri.fromFile(file);
        mMePresenter.setTake_photo(iconUri);
    }

    @Override
    public void select_album() {
        File file = new File(ALBUM_PATH+"/logo.png/");  //新建图片
        File filePath = new File(ALBUM_PATH);
        if (!filePath.exists()) {  //如果文件夹不存在，创建文件夹
            filePath.mkdirs();
        }
        Uri iconUri=Uri.fromFile(file);
        mMePresenter.setSelect_album(iconUri);
    }

    @Override
    public void save_upload_icon_bitmap(Bitmap bitmap) {
            BufferedOutputStream os = null;
            sBitmap=bitmap;
            String _file="/logo.png/";
            try {
                File file = new File(ALBUM_PATH+_file);  //新建图片
                File folder = new File(ALBUM_PATH);
                if (!folder.exists()) {  //如果文件夹不存在，创建文件夹
                    folder.mkdirs();
                }
                file.createNewFile(); //创建图片文件
                os = new BufferedOutputStream(new FileOutputStream(file));
                sBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);  //图片存成png格式。
                upLoadUserIcon(file);
            }
            catch (Exception e)
            {
                MyLog.d(TAG,e.toString());
            }
            finally {
                if (os != null) {
                    try {
                        os.close();  //关闭流
                    } catch (IOException e) {
                        MyLog.e(TAG, e.getMessage());
                    }
                }
            }
    }
}
