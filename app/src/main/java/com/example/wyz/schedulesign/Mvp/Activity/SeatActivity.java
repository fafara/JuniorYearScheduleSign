package com.example.wyz.schedulesign.Mvp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Entity.SeatAllStatusEntity;
import com.example.wyz.schedulesign.Mvp.Entity.SeatEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.ISeatView;
import com.example.wyz.schedulesign.Mvp.Presenter.SeatPresenter;
import com.example.wyz.schedulesign.R;
import com.qfdqc.views.seattable.SeatTable;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class SeatActivity extends BaseActivity implements ISeatView{
    final String TAG="SeatActivity";
 /*   @InjectView(gridView)
    GridView mGridView;*/
    @InjectView(R.id.seatView)
    SeatTable mSeatTable;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.btn)
    Button mButton;
    @InjectView(R.id.avi)
    AVLoadingIndicatorView avi;
    @InjectView(R.id.loading)
    FrameLayout mFrameLayout;

    StudioEntity.MDetail detail=null;
    SeatPresenter mSeatPresenter=new SeatPresenter(this);
    SeatEntity seatEntity=null;

    static List<SeatEntity> sSeatEntities=new ArrayList<>();

    static List<SeatEntity> sSeatSold=new ArrayList<>();

    static List<SeatEntity> sSeatValid=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        initInject();
        initActionBar();
        startLoadView();
        initView();

    }
    @Override
    public void initActionBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView() {
        getIntentData();
        getSeatSold(detail.getStudio_id());


    }

    @Override
    public void getIntentData() {
        detail=(StudioEntity.MDetail)getIntent().getSerializableExtra("studio");
    }

    @Override
    public void initGridView() {
           /*   //图片数组
         int[] imgs = new int[detail.getStudio_col_count()*detail.getStudio_row_count()];
        for(int i=0;i<imgs.length;i++){
            imgs[i]=R.color.main_theme;
        }
        mGridView.setNumColumns(detail.getStudio_col_count());
        //为GridView设置适配器
        mGridView.setAdapter(new SeatAdapter(this,imgs,detail.getStudio_row_count()));
        //mGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        mGridView.setColumnWidth(50); // 设置列表项宽
        mGridView.setStretchMode(GridView.NO_STRETCH);
        //注册监听事件
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(SeatActivity.this, "pic" + position, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void initSeatView() {
        mSeatTable.setScreenName(detail.getStudio_name());//设置屏幕名称
        mSeatTable.setMaxSelected(3);//设置最多选中
        mSeatTable.setData(detail.getStudio_row_count(),detail.getStudio_col_count());
        mSeatTable.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                for(int i=0;i<sSeatValid.size();i++){
                    if(sSeatValid.get(i).getSeat_row()==row&&sSeatValid.get(i).getSeat_column()==column){
                        return  false;
                    }
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                for(int i=0;i<sSeatSold.size();i++){
                    if(sSeatSold.get(i).getSeat_row()==row&&sSeatSold.get(i).getSeat_column()==column){
                        return  true;
                    }
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

                int status=0;
                seatEntity=new SeatEntity(detail.getStudio_id(),row,column,String.valueOf(1));
                for(int i=0;i<sSeatEntities.size();i++){
                    if(seatEntity.getSeat_row()==sSeatEntities.get(i).getSeat_row()&&seatEntity.getSeat_column()==sSeatEntities.get(i).getSeat_column()){
                        status=1;
                        break;
                    }
                }
                if(status==0){
                    sSeatEntities.add(seatEntity);
                }
            }

            @Override
            public void unCheck(int row, int column) {

                for(int i=0;i<sSeatEntities.size();i++){
                    if(seatEntity.getSeat_row()==sSeatEntities.get(i).getSeat_row()&&seatEntity.getSeat_column()==sSeatEntities.get(i).getSeat_column()){
                       sSeatEntities.remove(i);
                        break;
                    }
                }
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {

                return null;
            }
        });
        endLoadView();

    }

    @Override
    public void intoTicket() {
        Intent intent=new Intent();
        intent.setClass(SeatActivity.this,TicketActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("studio_name",detail.getStudio_name());
        bundle.putParcelableArrayList("seat", (ArrayList<? extends Parcelable>) sSeatEntities);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void getSeatSold(String studio_id) {
        mSeatPresenter.getSeatStatus(studio_id);
    }

    @Override
    public void setSeatStatus(SeatAllStatusEntity seatAllStatusEntity) {
        for(int i=0;i<seatAllStatusEntity.getDetail().size();i++){
            if(Integer.parseInt(seatAllStatusEntity.getDetail().get(i).getSeat_status())==1){
                sSeatSold.add(seatAllStatusEntity.getDetail().get(i));
            }
            else if(Integer.parseInt(seatAllStatusEntity.getDetail().get(i).getSeat_status())==-1){
                sSeatValid.add(seatAllStatusEntity.getDetail().get(i));
            }
        }
        initSeatView();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            SeatActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @OnClick({R.id.btn})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn:
                intoTicket();
                mSeatPresenter.ticketSeat(sSeatEntities);
                break;
        }
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

}
