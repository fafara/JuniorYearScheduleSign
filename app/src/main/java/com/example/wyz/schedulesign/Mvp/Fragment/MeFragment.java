package com.example.wyz.schedulesign.Mvp.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wyz.schedulesign.Mvp.Activity.ModifyLoginPeopleActivity;
import com.example.wyz.schedulesign.Mvp.Entity.LoginSingleton;
import com.example.wyz.schedulesign.Mvp.Event.setChoiceClick;
import com.example.wyz.schedulesign.Mvp.Fragment.base.BaseFragment;
import com.example.wyz.schedulesign.Mvp.IView.IMeView;
import com.example.wyz.schedulesign.Mvp.Presenter.MePresenter;
import com.example.wyz.schedulesign.R;
import com.example.wyz.schedulesign.Util.CircleImageView;
import com.example.wyz.schedulesign.Util.MyLog;
import com.example.wyz.schedulesign.Util.SetIconChoice;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements IMeView {
    final String TAG="MeFragment";
    @InjectView(R.id.ID)
    TextView mID;
    @InjectView(R.id.name)
    TextView mName;
    @InjectView(R.id.tel)
    TextView mTel;
    @InjectView(R.id.addr)
    TextView mAddr;
    @InjectView(R.id.email)
    TextView mEmail;
    @InjectView(R.id.number)
    TextView mNumber;
    @InjectView(R.id.image)
    CircleImageView mImageView;

    MePresenter mMePresenter=new MePresenter(this);
    private SetIconChoice mSetIconChoice;
    private  static  final  int TAKE_PHOTO=1;
    private  static  final  int CROP_PHOTO=3;
    private  static  final  int MODIFY_OK=2;
    private static Uri mUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view=inflater.inflate(R.layout.fragment_me, container, false);
        initInject(view);
        initViews();
        return view;
    }
    @Override
    public void initViews() {
        setUserInfo();
        setUserIcon();
        mMePresenter.setContext(getContext());
    }

    @Override
    public void refreshViews() {
        mMePresenter.getLoginUserInfo();
    }

    @Override
    public void initInject(View view) {
        ButterKnife.inject(this,view);
    }

    @OnClick({R.id.one,R.id.two,R.id.three,R.id.four,R.id.five,R.id.image})
    public  void onClick(View view){
        switch ( view.getId()){
            case R.id.image:
                mSetIconChoice=new SetIconChoice(getActivity(),new setChoiceClick(getActivity()));
                mSetIconChoice.showAtLocation(view, Gravity.BOTTOM,0,0);
                break;
            default:
                setExitOperation();
                break;

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case  MODIFY_OK:
                refreshViews();
                break;
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(mUri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,mUri);
                    startActivityForResult(intent,CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    mMePresenter.saveSuccessIconBitmap(mUri);
                }
                else
                {
                    try {
                        mMePresenter.saveErrorIconBitmap(mUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        MyLog.d(TAG,e.toString());
                    }


                }
            case 0:
                break;
        }
    }
    @Override
    public void setUserIcon() {
        if(LoginSingleton.getInstance()!=null){
            Picasso.with(getActivity())
                    .load(LoginSingleton.getInstance().getImage())
                    .placeholder(R.mipmap.film)
                    .error(R.mipmap.delete)
                    .into(mImageView);
        }
    }

    @Override
    public void setUserIcon(String path) {
        if(LoginSingleton.getInstance()!=null){
            Picasso.with(getActivity())
                    .load(path)
                    .placeholder(R.mipmap.film)
                    .error(R.mipmap.delete)
                    .into(mImageView);
        }
    }


    @Override
    public void setUserInfo() {
        if(LoginSingleton.getInstance()!=null) {
            mID.setText(String.valueOf(LoginSingleton.getInstance().getId()));
            mName.setText(LoginSingleton.getInstance().getName());
            mTel.setText(LoginSingleton.getInstance().getTel());
            mAddr.setText(LoginSingleton.getInstance().getAddr());
            mEmail.setText(LoginSingleton.getInstance().getEmail());
            mNumber.setText(String.valueOf(LoginSingleton.getInstance().getUsername()));
        }
    }

    @Override
    public void setExitOperation() {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putInt("code",2);
        bundle.putInt("id",Integer.valueOf(mID.getText().toString()));
        if(LoginSingleton.getInstance()!=null){
            bundle.putString("no",LoginSingleton.getInstance().getUsername());
        }
        bundle.putString("name",mName.getText().toString());
        bundle.putString("tel",mTel.getText().toString());
        bundle.putString("addr",mAddr.getText().toString());
        bundle.putString("email",mEmail.getText().toString());
        intent.putExtras(bundle);
        intent.setClass(getContext(),ModifyLoginPeopleActivity.class);
        startActivityForResult(intent,MODIFY_OK);
    }

    @Override
    public boolean isInitID() {
        if(mID!=null){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public void cancelChoiceView() {
        mSetIconChoice.dismiss();
    }

    @Override
    public void take_photoView(Uri uri) {
        mUri=uri;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,TAKE_PHOTO);

    }
    @Override
    public void select_albumView(Uri uri) {
        mUri=uri;
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setType("image/*");
        intent.putExtra("crop","true");
        intent.putExtra("scale",true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent, CROP_PHOTO);
    }

    @Override
    public void setSuccessCropIcon(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
        bitmap.recycle();
    }

    @Override
    public void setDefeatCropIcon(Bitmap bitmap) {
        if(bitmap==null) {
            mImageView.setImageResource(R.mipmap.film);
        }
        else
        {
            mImageView.setImageBitmap(bitmap);
            bitmap.recycle();
        }
    }


    @Override
    public void snackBarError() {
        SnackbarManager.show(Snackbar.with(getActivity()).text("请求出错"));
    }

    @Override
    public void snackBarError(String msg) {
        SnackbarManager.show(Snackbar.with(getActivity()).text(msg));
    }

    @Override
    public void snackBarSuccess() {
        SnackbarManager.show(Snackbar.with(getActivity()).text("请求出错"));
    }


}
