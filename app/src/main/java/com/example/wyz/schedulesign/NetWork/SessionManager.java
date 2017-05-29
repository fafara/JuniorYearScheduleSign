package com.example.wyz.schedulesign.NetWork;

/**
 * Created by WYZ on 2017/5/27.
 */

public class SessionManager {
    private  static  String mSession="";
    private static  long mTime;
    private  SessionManager(){

    }
    public  static  void  setSession(String session){

        mSession=dealWithSession(session);

    }


    public  static void setLastApiCallTime(long time){
        mTime=time;
    }
    private static String dealWithSession(String session){
        int index=session.indexOf(";");
        session=session.substring(0,index);
        return  session;
    }


    public static long getmTime() {
        return mTime;
    }

    public static String getmSession() {
        return mSession;
    }
}
