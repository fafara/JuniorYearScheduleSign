package com.example.wyz.schedulesign.Util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wyz.schedulesign.R;

/**
 * Created by WYZ on 2017/5/27.
 */

public class SetIconChoice extends PopupWindow {
    private View view;
    private TextView take_photo_view;
    private  TextView album_view;
    private  TextView cancel_view;
    public SetIconChoice(Context context, View.OnClickListener click ) {
        super(context);
        view= LayoutInflater.from(context).inflate(R.layout.seticon_view,null);
        setupViewComponent(context,click);
    }

    private void setupViewComponent(Context context,View.OnClickListener click) {
        take_photo_view=(TextView)view.findViewById(R.id.take_photo);
        album_view=(TextView)view.findViewById(R.id.album);
        cancel_view=(TextView)view.findViewById(R.id.cancel);
        take_photo_view.setOnClickListener(click);
        album_view.setOnClickListener(click);
        cancel_view.setOnClickListener(click);
        //屏幕尺寸
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        this.setContentView(view);
        this.setWidth((int)(displayMetrics.widthPixels*0.9));
        this.setHeight((int)(181*displayMetrics.density+0.5f));
        this.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.seticon_all_shape));
        this.setFocusable(true);
        this.setAnimationStyle(R.style.PopWindows);
        this.setOutsideTouchable(false);
    }
}
