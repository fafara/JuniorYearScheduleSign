package com.example.wyz.schedulesign.GuidePage;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.wyz.schedulesign.Mvp.Activity.LoginActivity;
import com.example.wyz.schedulesign.R;

/**
 * Created by WYZ on 2017/5/12.
 */

public class PageFragment extends Fragment {
    private  View mView;
    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        final int index=bundle.getInt("index");
        int layoutId=bundle.getInt("layoutId");
        int count=bundle.getInt("count");
        mView=inflater.inflate(layoutId,null);
        if(index==count-1){
            mButton=(Button) mView.findViewById(R.id.btn);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(getActivity(), mButton,mButton.getTransitionName());
                        startActivity(new Intent(getActivity(), LoginActivity.class), options.toBundle());

                        /*Pair<View, String> imagePair = Pair.create(mImageView, getString(R.string.image));
                        Pair<View, String> textPair = Pair.create(mTextView, getString(R.string.name));

                        ActivityOptionsCompat compat = ActivityOptionsCompat
                                .makeSceneTransitionAnimation(this, imagePair, textPair);
                        ActivityCompat.startActivity(this, new Intent(this, Activity2.class),
                                compat.toBundle());*/
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            getActivity().finish();
                                            startActivity(new Intent(getActivity(), LoginActivity.class));
                                        }
                                    });

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }

                }
            });
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent();
                                intent.setClass(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
*/
        }
        return mView;
    }
}
