package com.example.wyz.schedulesign.Mvp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Entity.SeatEntity;
import com.example.wyz.schedulesign.Mvp.Presenter.SeatPresenter;
import com.example.wyz.schedulesign.R;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.example.wyz.schedulesign.Mvp.Activity.SeatActivity.sSeatEntities;

public class TicketActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.btn)
    Button mButton;
    @InjectView(R.id.studio)
    EditText mStudio;
    @InjectView(R.id.seat)
    EditText mSeat;

    SeatPresenter mSeatPresenter=new SeatPresenter();
    @Override
    public void initActionBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView() {
        List<SeatEntity> seatEntities =getIntent().getParcelableArrayListExtra("seat");
        mStudio.setText(getIntent().getStringExtra("studio_name"));
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<seatEntities.size();i++){
            int row=seatEntities.get(i).getSeat_row()+1;
            int column=seatEntities.get(i).getSeat_column()+1;
            stringBuilder.append(row+"排"+"  "+column+"座"+"  ");
        }
        mSeat.setText(stringBuilder.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        initInject();
        initActionBar();
        initView();
    }
    @OnClick(R.id.btn)
    public  void OnClick(View view){
        if(view.getId()==R.id.btn){
            mSeatPresenter.ticketSeat(sSeatEntities);

            snackBarError("购票成功");
            Intent intent=new Intent();
            intent.setClass(TicketActivity.this, MainViewActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            TicketActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
