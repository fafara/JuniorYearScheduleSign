package com.example.wyz.schedulesign.Mvp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Entity.PlayEntity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.IPlayModifyView;
import com.example.wyz.schedulesign.Mvp.Presenter.PlayModifyPresenter;
import com.example.wyz.schedulesign.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.InjectView;
import butterknife.OnClick;

public class PlayModifyActivity extends BaseActivity implements IPlayModifyView,DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.ID)
    EditText mID;
    @InjectView(R.id.name)
    EditText mName;
    @InjectView(R.id.studio_spinner)
    Spinner mSpinner;
    @InjectView(R.id.play_start)
    EditText mStart;
    @InjectView(R.id.play_end)
    EditText mEnd;
    @InjectView(R.id.btn)
    Button mButton;


    PlayModifyPresenter mModifyPresenter=new PlayModifyPresenter(this);
    PlayEntity.MDetail mMDetail=new PlayEntity.MDetail();
    StudioEntity mStudioEntity=new StudioEntity();
    int pos;

    private Calendar calendar;
    private DateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private static final String TIME_PATTERN = "HH:mm";
    int film_hourlong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_modify);
        initInject();
        initActionBar();
        initView();
        initDatePicker();
    }

    @Override
    public boolean isEmpty(EditText editText) {
        if(editText.getText().toString().equals(""))
            return false;
        else
            return true;
    }
    @Override
    public void initActionBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView() {
        Bundle bundle=getIntent().getExtras();
        pos=bundle.getInt("pos");
        film_hourlong=Integer.parseInt(bundle.getString("film_hourlong"));
        mMDetail=(PlayEntity.MDetail)bundle.getSerializable("play");
        mName.setText(mMDetail.getFilm_name());
        mID.setText(mMDetail.getFilm_id());
        mStart.setText(mMDetail.getPlay_start());
        mEnd.setText(mMDetail.getPlay_end());
        initAllStudioSpinner();
    }
    @Override
    public void initAllStudioSpinner() {
        mModifyPresenter.getAllStudio();
    }

    @Override
    public void initAllStudioSpinnerView(StudioEntity studioEntity) {
        List<String> strings=new ArrayList<>();
        mStudioEntity=studioEntity;
        for(int i=0;i<studioEntity.getDetail().size();i++){
            if(studioEntity.getDetail().get(i).getStudio_flag().equals(String.valueOf(1))){
                strings.add(studioEntity.getDetail().get(i).getStudio_name());
            }

        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,strings);
        mSpinner.setAdapter(adapter);
        mSpinner.setSelection(pos);
    }

    @Override
    public void modifyCompleted() {
        setResult(RESULT_OK);
        PlayModifyActivity.this.finish();
    }


    @Override
    public void getInputData() {
        mMDetail.setStudio_id(mStudioEntity.getDetail().get(mSpinner.getSelectedItemPosition()).getStudio_id());
        mMDetail.setPlay_start(mStart.getText().toString());
        mMDetail.setPlay_end(mEnd.getText().toString());
        mMDetail.setFilm_id(mID.getText().toString());
        mMDetail.setFilm_name(mName.getText().toString());
    }

    @Override
    public void initDatePicker() {
        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());
        setDatePicker();
    }

    @Override
    public void setDatePicker() {
        mStart.setText(dateFormat.format(calendar.getTime())+timeFormat.format(calendar.getTime()));
        calendar.add(Calendar.MINUTE,film_hourlong);
        mEnd.setText(dateFormat.format(calendar.getTime())+timeFormat.format((calendar.getTime())));

    }

    @OnClick({R.id.btn,R.id.datePicker,R.id.timePicker,R.id.tickets})
    public  void OnClick(View view){
        switch (view.getId()){
            case R.id.btn:
                if(isEmpty(mStart)){
                    getInputData();
                    mModifyPresenter.modifyPlay(mMDetail);
                }else{
                    snackBarError("请检查输入是否完整");
                }
                break;
            case R.id.datePicker:
                DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");
                break;
            case R.id.timePicker:
                TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show(getFragmentManager(), "timePicker");
                break;
            case R.id.tickets:
                Intent intent=new Intent();
                intent.setClass(PlayModifyActivity.this,SeatActivity.class);
                StudioEntity.MDetail detail=mStudioEntity.getDetail().get(pos);
                Bundle bundle=new Bundle();
                bundle.putSerializable("studio",detail);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            PlayModifyActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        setDatePicker();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        setDatePicker();
    }
}
