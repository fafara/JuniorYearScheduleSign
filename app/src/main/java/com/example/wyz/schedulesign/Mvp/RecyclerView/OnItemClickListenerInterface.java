package com.example.wyz.schedulesign.Mvp.RecyclerView;

import android.view.View;

/**
 * Created by WYZ on 2017/6/5.
 */

public interface OnItemClickListenerInterface {
    void OnItemClick(View view , int position);
    void OnItemLongClick(View view,int position);
}
