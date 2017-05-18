package com.example.wyz.schedulesign.Util;

import android.app.Activity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WYZ on 2017/5/15.
 */

public class MyExit{
    //是否点击了退出按钮
    private static Boolean isExit = false;
    public static void exitBy2Click(Activity activity) {
        Timer tExit=null;
        if(!isExit)
        {
            //准备退出
            isExit=true;
            Toast.makeText(activity,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            tExit=new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit=false;       //取消退出
                }
            },2000);// 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        }
        else
        {
            activity.finish();
            System.exit(0);
        }
    }

}
