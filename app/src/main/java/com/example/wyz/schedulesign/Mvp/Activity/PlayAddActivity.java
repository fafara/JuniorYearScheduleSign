package com.example.wyz.schedulesign.Mvp.Activity;

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
import com.example.wyz.schedulesign.Mvp.IView.IPlayAddView;
import com.example.wyz.schedulesign.Mvp.Presenter.PlayAddPresenter;
import com.example.wyz.schedulesign.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.InjectView;
import butterknife.OnClick;


public class PlayAddActivity extends BaseActivity implements IPlayAddView,DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

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

    private Calendar calendar;
    private DateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private static final String TIME_PATTERN = "HH:mm";
    PlayAddPresenter mPlayAddPresenter=new PlayAddPresenter(this);
    PlayEntity.MDetail mMDetail=new PlayEntity.MDetail();
    StudioEntity mStudioEntity=new StudioEntity();
    int film_hourlong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_add);

        initInject();
        initActionBar();
        initView();
        initDatePicker();
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
        String film_name=bundle.getString("film_name");
        String film_id=bundle.getString("film_id");
        film_hourlong=Integer.parseInt(bundle.getString("film_hourlong"));
        mName.setText(film_name);
        mID.setText(film_id);
        initAllStudioSpinner();
    }
    @OnClick({R.id.btn,R.id.datePicker,R.id.timePicker})
    public  void OnClick(View view){
        switch (view.getId()){
            case R.id.btn:
                if(isEmpty(mStart)&&isEmpty(mEnd)){
                    getInputData();
                    mPlayAddPresenter.addPlayForFilm(mMDetail);
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
        }
    }

    @Override
    public boolean isEmpty(EditText editText) {
        if(editText.getText().toString().equals(""))
            return false;
        else
            return true;
    }

    @Override
    public void initAllStudioSpinner() {
        mPlayAddPresenter.getAllStudio();
    }

    @Override
    public void initAllStudioSpinnerView(StudioEntity studioEntity) {
        List<String> strings=new ArrayList<>();
        mStudioEntity=studioEntity;
        for(int i=0;i<studioEntity.getDetail().size();i++){
            strings.add(studioEntity.getDetail().get(i).getStudio_name());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,strings);
        mSpinner.setAdapter(adapter);
    }
    @Override
    public void addCompleted() {
        setResult(RESULT_OK);
        PlayAddActivity.this.finish();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            PlayAddActivity.this.finish();
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
