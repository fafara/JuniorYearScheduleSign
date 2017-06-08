package com.example.wyz.schedulesign.Mvp.Model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.Entity.FilmStatusEntity;
import com.example.wyz.schedulesign.Mvp.IModel.IFilmAddModel;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmAddPresenter;
import com.example.wyz.schedulesign.NetWork.FilmHttpMethods;
import com.example.wyz.schedulesign.Util.MyLog;
import com.example.wyz.schedulesign.Util.RxPartMapUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import rx.Subscriber;

import static android.content.ContentValues.TAG;

/**
 * Created by WYZ on 2017/6/4.
 */

public class FilmAddModel implements IFilmAddModel {
    private final  String ALBUM_PATH= Environment.getExternalStorageDirectory().getPath()+"/schedulesign";
    FilmAddPresenter mFilmAddPresenter=new FilmAddPresenter();
    static  Bitmap sBitmap;

    @Override
    public void saveFile(final  String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream ins=null;
                OutputStream os=null;
                File file=null;
                try {
                    file = new File(ALBUM_PATH+"/film.png/");  //新建图片
                    File folder = new File(ALBUM_PATH);
                    if (!folder.exists()) {  //如果文件夹不存在，创建文件夹
                        folder.mkdirs();
                    }
                    URL url=new URL(path);
                    // 打开URL链接
                    URLConnection ucon = url.openConnection();
                    // 使用InputStream，从URLConnection读取数据
                    ins = ucon.getInputStream();
                    os = new FileOutputStream(file);
                    int bytesRead = 0;
                    byte[] buffer = new byte[1024];
                    while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.close();
                    ins.close();
                    mFilmAddPresenter.setFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void saveFile(Bitmap bitmap) {
        BufferedOutputStream os = null;
        sBitmap=bitmap;
        String _file="/film.png/";
        try {
            File file = new File(ALBUM_PATH+_file);  //新建图片
            File folder = new File(ALBUM_PATH);
            if (!folder.exists()) {  //如果文件夹不存在，创建文件夹
                folder.mkdirs();
            }
            file.createNewFile(); //创建图片文件
            os = new BufferedOutputStream(new FileOutputStream(file));
            sBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);  //图片存成png格式。
            mFilmAddPresenter.setFile(file);
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

    @Override
    public void select_album() {
        File file = new File(ALBUM_PATH+"/film.png/");  //新建图片
        File filePath = new File(ALBUM_PATH);
        if (!filePath.exists()) {  //如果文件夹不存在，创建文件夹
            filePath.mkdirs();
        }
        Uri iconUri=Uri.fromFile(file);
        mFilmAddPresenter.setSelect_album();
    }
    @Override
    public void addFilm(File file, FilmEntity.MDetail detail) {
        Subscriber<FilmStatusEntity> subscriber=new Subscriber<FilmStatusEntity>() {
            @Override
            public void onCompleted() {
                mFilmAddPresenter.backFragment();
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d("sada","ada");
            }

            @Override
            public void onNext(FilmStatusEntity filmStatusEntity) {
                MyLog.d("sada","ada");
            }
        };
        FilmHttpMethods.getInstance().getAddFilmResult(subscriber, RxPartMapUtils.toMultipartBodyOfFile(file),
                RxPartMapUtils.toMultipartBodyOfText("film_name",detail.getFilm_name()),
                RxPartMapUtils.toMultipartBodyOfText("film_tostar",detail.getFilm_tostar()),
                RxPartMapUtils.toMultipartBodyOfText("film_release",detail.getFilm_release()),
                RxPartMapUtils.toMultipartBodyOfText("film_hourlong",String.valueOf(detail.getFilm_hourlong())),
                RxPartMapUtils.toMultipartBodyOfText("film_type",detail.getFilm_type()),
                RxPartMapUtils.toMultipartBodyOfText("film_price",detail.getFilm_price())
        );
    }

}
