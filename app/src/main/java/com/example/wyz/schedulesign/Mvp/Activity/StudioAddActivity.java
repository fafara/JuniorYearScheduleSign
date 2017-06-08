package com.example.wyz.schedulesign.Mvp.Activity;

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
import com.example.wyz.schedulesign.Mvp.IView.IStudioAddView;
import com.example.wyz.schedulesign.Mvp.Presenter.StudioAddPresenter;
import com.example.wyz.schedulesign.R;

import butterknife.InjectView;
import butterknife.OnClick;

public class StudioAddActivity extends BaseActivity implements IStudioAddView {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.btn)
    Button mButton;
    @InjectView(R.id.name)
    EditText mName;
    @InjectView(R.id.row)
    EditText mRow;
    @InjectView(R.id.col)
    EditText mCol;
    @InjectView(R.id.flag)
    Spinner mFlag;


    StudioEntity.MDetail mDetail=new StudioEntity.MDetail();
    StudioAddPresenter mAddPresenter=new StudioAddPresenter(this);
    private static final Integer[] sIntegers={0,1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_add);
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

        setSpinner();
    }




    @OnClick({R.id.btn})
    public  void OnClick(View view){
        switch (view.getId()){
            case R.id.btn:
                if(isEmpty(mName)&&isEmpty(mCol)&&isEmpty(mRow)){
                    setDetailEntity();
                    mAddPresenter.getAddStudioResult(mDetail);
                }
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
            StudioAddActivity.this.finish();
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
    public void setSpinner() {
        mFlag.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,sIntegers));
    }

    @Override
    public void setNetCompleted() {
        setResult(RESULT_OK);
        StudioAddActivity.this.finish();
    }

}
