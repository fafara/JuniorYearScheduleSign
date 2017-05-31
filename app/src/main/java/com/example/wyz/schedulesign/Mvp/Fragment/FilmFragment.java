package com.example.wyz.schedulesign.Mvp.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wyz.schedulesign.Mvp.Adapter.Film_Adapter;
import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.Fragment.base.BaseFragment;
import com.example.wyz.schedulesign.Mvp.IView.IFilmView;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmPresenter;
import com.example.wyz.schedulesign.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends BaseFragment implements  IFilmView{
    final  String TAG="FilmFragment";
    @InjectView(R.id.listView)
    ListView mListView;
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
}
