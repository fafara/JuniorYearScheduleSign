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
    Subscriber<FilmEntity> mSubscriber;
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
        mSubscriber=new Subscriber<FilmEntity>() {
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
            public void onNext(FilmEntity filmEntity) {
                for(int i=0;i<filmEntity.getDetail().size();i++){
                    FilmEntity.MDetail mDetail=new FilmEntity.MDetail();
                    mDetail.setFilm_id(filmEntity.getDetail().get(i).getFilm_id());
                    mDetail.setFilm_name(filmEntity.getDetail().get(i).getFilm_name());
                    mDetail.setFilm_tostar(filmEntity.getDetail().get(i).getFilm_tostar());
                    mDetail.setFilm_release(filmEntity.getDetail().get(i).getFilm_release());
                    mDetail.setFilm_hourlong(filmEntity.getDetail().get(i).getFilm_hourlong());
                    mDetail.setFilm_type(filmEntity.getDetail().get(i).getFilm_type());
                    mDetail.setFilm_price(filmEntity.getDetail().get(i).getFilm_price());
                    mFilmEntityList.add(mDetail);
                }
            }
        };
        FilmHttpMethods.getInstance().getAllFilmInfo(mSubscriber);
    }
}
