package com.example.wyz.schedulesign.Mvp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Adapter.Film_Adapter;
import com.example.wyz.schedulesign.Mvp.Entity.FilmEntity;
import com.example.wyz.schedulesign.Mvp.IView.IFilmSearchView;
import com.example.wyz.schedulesign.Mvp.Presenter.FilmSearchPresenter;
import com.example.wyz.schedulesign.R;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by WYZ on 2017/6/2.
 */

public class FilmSearchActivity extends BaseActivity implements SearchView.OnQueryTextListener,IFilmSearchView{
    @InjectView(R.id.searchView)
    SearchView mSearchView;
    @InjectView(R.id.listView)
    ListView mListView;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    final  int ACTIVITY_MODIFY=2;
    FilmSearchPresenter mFilmSearchPresenter=new FilmSearchPresenter(this);
    Film_Adapter film_adapter=null;
    static  String lastSearch="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_search);
        initInject();
        initActionBar();
        initView();
    }

    @Override
    public void initActionBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.back_35dp);
    }

    @Override
    public void initView() {
        initSearchView();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2=new Intent();
                intent2.setClass(FilmSearchActivity.this, FilmModifyActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("film", Film_Adapter.mMDetails.get(position));
                intent2.putExtras(bundle);
                startActivityForResult(intent2,ACTIVITY_MODIFY);
            }
        });
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(film_adapter!=null){
            Film_Adapter.mMDetails.clear();
        }
        lastSearch=newText;
        mFilmSearchPresenter.getFilmFindResult(newText);
        return false;
    }

    @Override
    public void initSearchView() {
        Class<?> c=mSearchView.getClass();
        try {
            Field f=c.getDeclaredField("mSearchPlate");//通过反射，获得类对象的一个属性对象
            f.setAccessible(true);//设置此私有属性是可访问的
            View v=(View) f.get(mSearchView);//获得属性的值
            v.setBackgroundResource(R.drawable.search_back_shape);//设置此view的背景
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpannableString spanText = new SpannableString("请输入影片名称");
        // 设置字体大小
        spanText.setSpan(new AbsoluteSizeSpan(15, true), 0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置字体颜色
        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.search_text_hint)), 0, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        int id = mSearchView.getContext().getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) mSearchView.findViewById(id);
        textView.setTextSize(15);// 设置输入字体大小
        textView.setTextColor(getResources().getColor(R.color.search_text));// 设置输入字的显示

        mSearchView.setQueryHint(spanText);
        mSearchView.setOnQueryTextListener(this);
    }
    @Override
    public void updateView(List<FilmEntity.MDetail> details) {
        if(film_adapter==null){
            film_adapter=new Film_Adapter(this,details);
            mListView.setAdapter(film_adapter);
        }else{
            Film_Adapter.mMDetails=details;
            film_adapter.notifyDataSetChanged();
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                    FilmSearchActivity.this.finish();
                break;
        }
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            mFilmSearchPresenter.getFilmFindResult(lastSearch);
        }
    }
}
