package com.example.wyz.schedulesign.Mvp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wyz.schedulesign.Mvp.Fragment.FilmFragment;
import com.example.wyz.schedulesign.Mvp.Fragment.MeFragment;
import com.example.wyz.schedulesign.Mvp.Fragment.PeopleFragment;
import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.MyExit;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainViewActivity extends AppCompatActivity {
    @InjectView(R.id.film)
    LinearLayout mFilm;
    @InjectView(R.id.people)
    LinearLayout mPeople;
    @InjectView(R.id.me)
    LinearLayout mMe;
    @InjectView(R.id.viewpager)
    ViewPager mViewPager;
    @InjectView(R.id.split)
    View splitLine;

    FilmFragment mFilmFragment;
    MeFragment mMeFragment;
    PeopleFragment mPeopleFragment;
    private List<Fragment> mFragmentList;
    private  String loginName;
    private  int currentPage=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainview);
        ButterKnife.inject(this);
        enterHandle();
        initViewPager();

    }
    @OnClick({R.id.film,R.id.people,R.id.me})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.film:
                PageClick(0);
                break;
            case R.id.people:
                PageClick(1);
                break;
            case R.id.me:
                PageClick(2);
                break;
        }
    }

    private  void loadFragmentData(Fragment fragment){
        Bundle bundle=new Bundle();
        bundle.putString("loginName",loginName);
        fragment.setArguments(bundle);
    }
    private  void enterHandle(){
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name=bundle.getString("name");
        loginName=name;
        String password=bundle.getString("password");
        Boolean isRemember=bundle.getBoolean("isRemember");
        SavePassWord(name,password,isRemember);
        Toast.makeText(this,name+password,Toast.LENGTH_LONG).show();
    }
    private void SavePassWord(String name,String password,Boolean isRemember){
        //SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor=getSharedPreferences("schedule_info", Context.MODE_PRIVATE).edit();
        editor.putString("loginName",name);
        editor.putString("loginPassword",password);
        editor.putBoolean("loginStatus",isRemember);
        editor.apply();
    }
    private  void initViewPager(){
        mFragmentList=new ArrayList<>();
        mFilmFragment=new FilmFragment();
        mPeopleFragment=new PeopleFragment();
        loadFragmentData(mPeopleFragment);
        mMeFragment=new MeFragment();
        loadFragmentData(mMeFragment);
        mFragmentList.add(mFilmFragment);
        mFragmentList.add(mPeopleFragment);
        mFragmentList.add(mMeFragment);

        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new MyViewPagerPageChangeListener());

    }
    private  class  MyViewPagerPageChangeListener implements  ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            RelativeLayout.LayoutParams splitParams = (RelativeLayout.LayoutParams) splitLine.getLayoutParams();
            int screenWidth= getResources().getDisplayMetrics().widthPixels;

            splitParams.height = splitLine.getMeasuredHeight();
            splitParams.width = screenWidth / mFragmentList.size();
            //RelativeLayout.LayoutParams splitParams=new RelativeLayout.LayoutParams(screenWidth/FragmentCount,5);

            //splitParams.addRule(RelativeLayout.ALIGN_BASELINE);
            splitLine.setLayoutParams(splitParams);
            //向右滑动多个
            if (currentPage > position && (currentPage - position == 1)) {
                int xOffset = (int) (-(1 - positionOffset) * screenWidth * 1.0 / mFragmentList.size()) + currentPage * (screenWidth / mFragmentList.size());
                splitLine.setX(xOffset);
            } else if (currentPage == position) {
                int xOffset = (int) (positionOffset * (screenWidth * 1.0 / mFragmentList.size()) + currentPage * (screenWidth / mFragmentList.size()));
                splitLine.setX(xOffset);
            }
        }

        @Override
        public void onPageSelected(int position) {
            currentPage=position;
            PageClick(currentPage);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    /**
     * 切换影片管理，人员管理，信息页面
     * @param index 第index个界面
     */
    public void PageClick(int index) {


        ImageView film_imageView;
        ImageView people_imageView;
        ImageView me_imageView;
        mViewPager.setCurrentItem(index, true);

        film_imageView = (ImageView) mFilm.getChildAt(0);
        people_imageView = (ImageView) mPeople.getChildAt(0);
        me_imageView = (ImageView) mMe.getChildAt(0);
        switch (index) {
            case 0:
                film_imageView .setImageResource(R.mipmap.film);
                people_imageView.setImageResource(R.mipmap.people);
                me_imageView.setImageResource(R.mipmap.me);
                break;
            case 1:
                film_imageView .setImageResource(R.mipmap.film);
                people_imageView.setImageResource(R.mipmap.people);
                me_imageView.setImageResource(R.mipmap.me);
                break;
            case 2:
                film_imageView .setImageResource(R.mipmap.film);
                people_imageView.setImageResource(R.mipmap.people);
                me_imageView.setImageResource(R.mipmap.me);
                break;
        }
    }
    private  class  MyViewPagerAdapter extends FragmentStatePagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            MyExit.exitBy2Click(MainViewActivity.this);
        }
        return  false;
    }
}
