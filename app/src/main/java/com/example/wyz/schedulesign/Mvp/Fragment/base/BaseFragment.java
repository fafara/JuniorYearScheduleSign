package com.example.wyz.schedulesign.Mvp.Fragment.base;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by WYZ on 2017/5/17.
 */

public abstract class BaseFragment extends Fragment {
    public  abstract  void initViews();

    public  abstract  void refreshViews();

    public  abstract void initInject(View view);
}
