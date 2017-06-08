package com.example.wyz.schedulesign.Mvp.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Adapter.FilmPlay_Adapter;
import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.IView.IFilmModifyView;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmModifyPresenter;
import com.example.wyz.schedulesign.Mvp.RecyclerView.DividerItemDecoration;
import com.example.wyz.schedulesign.Mvp.RecyclerView.OnItemClickListenerInterface;
import com.example.wyz.schedulesign.R;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

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
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.avi)
    AVLoadingIndicatorView avi;
    @InjectView(R.id.loading)
    FrameLayout mFrameLayout;

    FilmEntity.MDetail mDetail=new FilmEntity.MDetail();
    private  static  final  int IMAGE=1;
    FilmModifyPresenter mFilmModifyPresenter=new FilmModifyPresenter(this);
    private  static  File sFile=null;
    static  FilmPlay_Adapter filmPlay_adapter=null;
    private  final  int ADDPLAY=2;
    private final  int MODIFYPLAY=3;

    int isChoice=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_modify);
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
        mhoureLong.setText(String.valueOf(mDetail.getFilm_hourlong()));
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
        startLoadView();
        Intent intent=this.getIntent();
        mDetail= (FilmEntity.MDetail)intent.getSerializableExtra("film");
        initView();
        mFilmModifyPresenter.saveFile(mDetail.getFilm_img());
        initRecyclerView();


    }

    @Override
    public void setFile(File file) {
        sFile=file;
    }

    @OnClick({R.id.btn,R.id.image,R.id.add_play})
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
            case R.id.add_play:
                Intent intent=new Intent();
                intent.setClass(FilmModifyActivity.this,PlayAddActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("film_name",mDetail.getFilm_name());
                bundle.putString("film_id",String.valueOf(mDetail.getFilm_id()));
                bundle.putInt("film_hourlong",mDetail.getFilm_hourlong());
                intent.putExtras(bundle);
                startActivityForResult(intent,ADDPLAY);
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
        mDetail.setFilm_hourlong(Integer.parseInt(mhoureLong.getText().toString()));
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
        }else if(requestCode==ADDPLAY&&resultCode==RESULT_OK){
            refreshView();
        }else if(requestCode==MODIFYPLAY&&resultCode==RESULT_OK){
            refreshView();
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

    @Override
    public void setRecyclerView(List<PlayEntity.MDetail> filmPlayEntities) {
        if(filmPlay_adapter==null){
            filmPlay_adapter=new FilmPlay_Adapter(this,filmPlayEntities);
            filmPlay_adapter.setOnItemClickListener(new OnItemClickListenerInterface() {
                @Override
                public void OnItemClick(View view, int position) {
                    Intent intent=new Intent();
                    intent.setClass(FilmModifyActivity.this,PlayModifyActivity.class);
                    Bundle bundle=new Bundle();
                    PlayEntity.MDetail detail=new PlayEntity.MDetail();
                    detail.setFilm_id(String.valueOf(mDetail.getFilm_id()));
                    detail.setFilm_name(mDetail.getFilm_name());
                    detail.setPlay_id(FilmPlay_Adapter.sFilmPlayEntities.get(position).getPlay_id());
                    detail.setPlay_start(FilmPlay_Adapter.sFilmPlayEntities.get(position).getPlay_start());
                    detail.setPlay_end(FilmPlay_Adapter.sFilmPlayEntities.get(position).getPlay_end());
                    detail.setStudio_name(FilmPlay_Adapter.sFilmPlayEntities.get(position).getStudio_name());
                    bundle.putSerializable("play",detail);
                    bundle.putInt("pos",position);
                    bundle.putInt("film_hourlong",mDetail.getFilm_hourlong());
                    intent.putExtras(bundle);
                    startActivityForResult(intent,MODIFYPLAY);
                }

                @Override
                public void OnItemLongClick(View view, final int position) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(FilmModifyActivity.this);
                    builder.setTitle("确定删除该演出计划?") ;
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mFilmModifyPresenter.deletePlay(FilmPlay_Adapter.sFilmPlayEntities.get(position).getPlay_id());

                        }
                    });
                    builder.show();
                }
            });
            mRecyclerView.setAdapter(filmPlay_adapter);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setHorizontalScrollBarEnabled(false);
        }else{
            FilmPlay_Adapter.sFilmPlayEntities.addAll(filmPlayEntities);
            filmPlay_adapter.notifyDataSetChanged();
        }
        endLoadView();
    }

    @Override
    public void initRecyclerView() {
        LinearLayoutManager linearLayoutManager;
        DividerItemDecoration dividerItemDecoration;
        linearLayoutManager=new LinearLayoutManager(FilmModifyActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //dividerItemDecoration=new DividerItemDecoration(FilmModifyActivity.this, DividerItemDecoration.HORIZONTAL_LIST,0);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //mRecyclerView.addItemDecoration(dividerItemDecoration);
        if(FilmPlay_Adapter.sFilmPlayEntities!=null){
            FilmPlay_Adapter.sFilmPlayEntities.clear();
        }
        initRecyclerViewData(String.valueOf(mDetail.getFilm_id()));
    }

    @Override
    public void initRecyclerViewData(String film_id) {
        mFilmModifyPresenter.getFilmIdPlay(film_id);
    }

    @Override
    public void refreshView() {
        if(FilmPlay_Adapter.sFilmPlayEntities!=null){
            FilmPlay_Adapter.sFilmPlayEntities.clear();
        }
        initRecyclerViewData(String.valueOf(mDetail.getFilm_id()));
    }

    @Override
    public void startLoadView() {
        avi.show();
        mFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoadView() {
        avi.hide();
        mFrameLayout.setVisibility(View.GONE);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        filmPlay_adapter=null;
    }
}
