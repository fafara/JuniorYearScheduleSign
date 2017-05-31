package com.example.wyz.schedulesign.Util;

import java.io.File;
import java.util.UUID;

/**
 * Created by WYZ on 2017/5/31.
 */

public class FileImageUploadUtils {
    final String TAG="FileImageUploadUtils";
    private final  int TIME_OUT=10*10000000;
    private final  String CHARSET="utf-8";
    public  final  String SUCCESS="1";
    public  final  String FAILUSER="0";

    public static   void  uploadFile(File file){
        String BOUNDARY= UUID.randomUUID().toString();  //边界标识，随机生成 String PREFIX = "--" , LINE_END = "\r\n";
        String CONTENT_TYPE="multipart/form-data";

    }
}
