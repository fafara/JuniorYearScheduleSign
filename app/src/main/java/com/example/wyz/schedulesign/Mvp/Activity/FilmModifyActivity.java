package com.example.wyz.schedulesign.Mvp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IView.IFilmModifyView;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmModifyPresenter;
import com.example.wyz.schedulesign.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by WYZ on 2017/6/3.
 */

public class FilmModifyActivity extends BaseActivity implements IFilmModifyView{
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.name)
    EditText mName;
    @InjectView(R.id.tostar)
    EditText mTostar;
    @InjectView(R.id.release)
    EditText mRelease;
    @InjectView(R.id.hourlong)
    EditText mhoureLong;
    @InjectView(R.id.type)
    EditText mType;
    @InjectView(R.id.price)
    EditText mPrice;
    @InjectView(R.id.image)
    ImageView mImage;
    @InjectView(R.id.btn)
    Button mButton;

    FilmEntity.MDetail mDetail=new FilmEntity.MDetail();
    private  static  final  int IMAGE=1;
    FilmModifyPresenter mFilmModifyPresenter=new FilmModifyPresenter(this);
    private  static  File sFile=null;
    int isChoice=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_add);
        initInject();
        initActionBar();
        getIntentData();
    }

    @Override
    public void initActionBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView() {
        mName.setText(mDetail.getFilm_name());
        mTostar.setText(mDetail.getFilm_tostar());
        mRelease.setText(mDetail.getFilm_release());
        mhoureLong.setText(mDetail.getFilm_hourlong());
        mType.setText(mDetail.getFilm_type());
        mPrice.setText(mDetail.getFilm_price());
        Picasso.with(this)
                .load(mDetail.getFilm_img())
                .placeholder(R.mipmap.icon_loading)
                .error(R.mipmap.icon_error)
                .into(mImage);
        isChoice=0;
    }

    @Override
    public void getIntentData() {
        Intent intent=this.getIntent();
        mDetail= (FilmEntity.MDetail)intent.getSerializableExtra("film");
        initView();
        mFilmModifyPresenter.saveFile(mDetail.getFilm_img());

    }

    @Override
    public void setFile(File file) {
        sFile=file;
    }

    @OnClick({R.id.btn,R.id.image})
    public  void OnClick(View v){
        switch (v.getId()){
            case  R.id.btn:
                if(sFile!=null&&isEmpty(mName)&&isEmpty(mTostar)&&isEmpty(mRelease)&&
                        isEmpty(mhoureLong)&&isEmpty(mType)&&isEmpty(mPrice)&&isChoiceImage(mImage)){
                    getModifyData();
                    mFilmModifyPresenter.modifyFilm(sFile,mDetail);
                }else{
                    snackBarError("请检查信息是否录入完整");
                }
                break;
            case R.id.image:
                mFilmModifyPresenter.select_album();
                break;
        }
    }
    @Override
    public void setSelect_album() {
        //调用相册
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
    }

    @Override
    public void setImageView(Uri uri) {
        if(uri!=null){
            Picasso.with(this)
                    .load(uri)
                    .placeholder(R.mipmap.icon_loading)
                    .error(R.mipmap.icon_error)
                    .into(mImage);
        }
    }

    @Override
    public void backFragment() {
        setResult(RESULT_OK);
        FilmModifyActivity.this.finish();

    }

    @Override
    public void getModifyData() {
        mDetail.setFilm_name(mName.getText().toString());
        mDetail.setFilm_tostar(mTostar.getText().toString());
        mDetail.setFilm_release(mRelease.getText().toString());
        mDetail.setFilm_hourlong(mhoureLong.getText().toString());
        mDetail.setFilm_type(mType.getText().toString());
        mDetail.setFilm_price(mPrice.getText().toString());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMAGE&&resultCode==RESULT_OK&&data!=null){
            Uri selectImage=data.getData();
            isChoice=1;
            setImageView(selectImage);
            Bitmap bitmap= null;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectImage));
                mFilmModifyPresenter.saveFile(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            FilmModifyActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean isEmpty(EditText editText) {
        if(editText.getText().toString().equals(""))
            return false;
        else
            return true;
    }

    @Override
    public boolean isChoiceImage(ImageView imageView) {
        if(imageView.getDrawable()==null&&isChoice==0){
            return false;
        }else{
            return  true;
        }
    }


}
