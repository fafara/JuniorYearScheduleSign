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

import com.example.wyz.schedulesign.Mvp.Activity.base.BaseActivity;
import com.example.wyz.schedulesign.Mvp.Entity.StudioEntity;
import com.example.wyz.schedulesign.Mvp.IView.IStudioModifyView;
import com.example.wyz.schedulesign.Mvp.Presenter.StudioModifyPresenter;
import com.example.wyz.schedulesign.R;

import butterknife.InjectView;
import butterknife.OnClick;

public class StudioModifyActivity extends BaseActivity implements IStudioModifyView {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.btn)
    Button mButton;
    @InjectView(R.id.del_btn)
    Button mDelButton;
    @InjectView(R.id.name)
    EditText mName;
    @InjectView(R.id.row)
    EditText mRow;
    @InjectView(R.id.col)
    EditText mCol;
    @InjectView(R.id.flag)
    Spinner mFlag;


    StudioEntity.MDetail mDetail;
    StudioModifyPresenter mStudioModifyPresenter=new StudioModifyPresenter(this);
    private static final Integer[] sIntegers={0,1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_modify);
        initInject();
        initActionBar();
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
        mName.setText(mDetail.getStudio_name());
        mRow.setText(String.valueOf(mDetail.getStudio_row_count()));
        mCol.setText(String.valueOf(mDetail.getStudio_col_count()));
        mStudioModifyPresenter.getSpinner(sIntegers,Integer.parseInt(mDetail.getStudio_flag()));
    }



    @Override
    public void getIntentData() {
        Intent intent=getIntent();
        mDetail=(StudioEntity.MDetail)intent.getSerializableExtra("studio");
    }
    @OnClick({R.id.btn,R.id.del_btn})
    public  void OnClick(View view){
        switch (view.getId()){
            case R.id.btn:
                if(isEmpty(mName)&&isEmpty(mCol)&&isEmpty(mRow)){
                    setDetailEntity();
                    mStudioModifyPresenter.getModifyStudioResult(mDetail);
                }
                break;
            case R.id.del_btn:
                mStudioModifyPresenter.getDeleteStudioResult(mDetail.getStudio_id());
                break;
        }
    }
    public  void setDetailEntity(){
        mDetail.setStudio_name(mName.getText().toString());
        mDetail.setStudio_row_count(Integer.parseInt(mRow.getText().toString()));
        mDetail.setStudio_col_count(Integer.parseInt(mCol.getText().toString()));
        mDetail.setStudio_flag(mFlag.getSelectedItem().toString());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            StudioModifyActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean isEmpty(EditText editText) {
        if(editText.getText().toString().equals(""))
            return false;
        else
            return true;
    }

    @Override
    public void setSpinner(int i) {
        mFlag.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,sIntegers));
        mFlag.setSelection(i);
    }

    @Override
    public void setNetCompleted() {
        setResult(RESULT_OK);
        StudioModifyActivity.this.finish();
    }


}
