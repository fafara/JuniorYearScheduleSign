package com.example.wyz.schedulesign.Util;

import android.util.Log;

/**
 * Created by WYZ on 2017/5/15.
 */

public class MyLog {
    public  static  boolean isDebug=true;

    public  static void e(String tag,String msg){
        if(isDebug){
            Log.e(tag,msg);
        }
    }
    public  static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }
    public  static void i(String tag,String msg){
        if(isDebug){
            Log.i(tag,msg);
        }
    }
    public  static void v(String tag,String msg){
        if(isDebug){
            Log.v(tag,msg);
        }
    }
    public  static void w(String tag,String msg){
        if(isDebug){
            Log.w(tag,msg);
        }
    }
}
