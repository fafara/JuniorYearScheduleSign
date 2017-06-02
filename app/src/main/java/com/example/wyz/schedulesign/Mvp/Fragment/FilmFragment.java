package com.example.wyz.schedulesign.Mvp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.wyz.schedulesign.Mvp.Activity.FilmSearchActivity;
import com.example.wyz.schedulesign.Mvp.Adapter.Film_Adapter;
import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.Fragment.base.BaseFragment;
import com.example.wyz.schedulesign.Mvp.IView.IFilmView;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmPresenter;
import com.example.wyz.schedulesign.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends BaseFragment implements  IFilmView{
    final  String TAG="FilmFragment";
    @InjectView(R.id.listView)
    ListView mListView;
    @InjectView(R.id.fab_menu)
    FloatingActionMenu mMenu;
    @InjectView(R.id.fab_add)
    FloatingActionButton  mAddFab;
    @InjectView(R.id.fab_delete)
    FloatingActionButton mDeleteFab;
    @InjectView(R.id.fab_modify)
    FloatingActionButton mModifyFab;
    @InjectView(R.id.fab_search)
    FloatingActionButton mSearchFab;

    FilmPresenter mFilmPresenter=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_film, container, false);
        initInject(view);
        initViews();
        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        mFilmPresenter.getAllFilm();
    }


    @Override
    public void initViews() {
        ImageView imageView=(ImageView)mMenu.getChildAt(5);
        imageView.setOnTouchListener(new FabOnTouchListener());
    }

    @Override
    public void refreshViews() {

    }

    @Override
    public void initInject(View view) {
        ButterKnife.inject(this,view);
    }

    @Override
    public void initPresenter() {
        mFilmPresenter=new FilmPresenter(this);
    }


    @Override
    public void initListView( List<FilmEntity.MDetail> mFilmEntityList) {
        Film_Adapter film_adapter=new Film_Adapter(getContext(),mFilmEntityList);
        mListView.setAdapter(film_adapter);
    }

    @OnClick({R.id.fab_add,R.id.fab_modify,R.id.fab_delete,R.id.fab_search})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.fab_add:
                break;
            case R.id.fab_delete:
                break;
            case R.id.fab_modify:
                break;
            case R.id.fab_search:
                Intent intent=new Intent();
                intent.setClass(getContext(), FilmSearchActivity.class);
                startActivity(intent);
                break;
                //mMenu.hideMenu(true);
                //mMenu.hideMenuButton(true);
                /*if(mMenu.isOpened()){
                    mMenu.getChildCount();
                    //mMenu.clearFocus();
                    mMenu.close(true);
                }

                else {
                    mMenu.getChildCount();
                    //mMenu.setFocusable(true);
                    mMenu.open(true);
                }*/

            default:
                break;
        }
    }
    private  class  FabOnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case  MotionEvent.ACTION_DOWN:
                {
                    // true: 1.告诉Android，MotionEvent对象已被使用，不能再提供给其他方法。
                    // 2.还告诉Android，继续将此触摸序列的触摸事件(move,up)发送到此方法。
                    // false:1.告诉Android，onTouch()方法未使用该事件，所以Android寻找要调用的下一个方法。
                    // 2.告诉Android。不再将此触摸序列的触摸事件（move,up）发送到此方法。
                    //fabMenu打开着
                    if(mMenu.isOpened()) {
                        mMenu.close(true);
                        return false;
                    }
                    else{
                        mMenu.open(true);
                        return false;
                    }
                }
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return  false;
        }

    }
}
