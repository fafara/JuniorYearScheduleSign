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
import com.example.wyz.schedulesign.NetWork.FilmHttpMethods;
import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.MyLog;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends BaseFragment {
    final  String TAG="FilmFragment";
    @InjectView(R.id.listView)
    ListView mListView;
    Subscriber<List<FilmEntity.MDetail>> mSubscriber;
    List<FilmEntity.MDetail> mFilmEntityList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_film, container, false);
        ButterKnife.inject(this,view);
        initViews();
        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    public void initViews() {
        //loadData();
    }

    @Override
    public void refreshViews() {

    }

    private  void initListView(){
        Film_Adapter film_adapter=new Film_Adapter(getContext(),mFilmEntityList);
        mListView.setAdapter(film_adapter);

    }
    private  void loadData(){
        mSubscriber=new Subscriber<List<FilmEntity.MDetail>>() {
            @Override
            public void onCompleted() {
                initListView();
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG,"请求出错:"+e.getMessage());
                SnackbarManager.show(Snackbar.with(getActivity()).text("请求出错："+e.getMessage()));
            }

            @Override
            public void onNext(List<FilmEntity.MDetail> FilmEntity) {
                mFilmEntityList=FilmEntity;
            }
        };
        FilmHttpMethods.getInstance().getAllFilmInfo(mSubscriber);
    }
}
