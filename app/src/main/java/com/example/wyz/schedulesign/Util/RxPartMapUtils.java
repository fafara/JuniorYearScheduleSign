package com.example.wyz.schedulesign.Util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by WYZ on 2017/5/29.
 */

public class RxPartMapUtils {
    public  static RequestBody toRequestBodyOfText(String value){
        RequestBody body=RequestBody.create(MediaType.parse("text/plain"),value);
        return  body;
    }
    public  static  RequestBody toRequestBodyOfImage(File pFile){
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), pFile);
        return fileBody;
    }
    public  static MultipartBody.Part toMultipartBodyOfFile(File file){
        RequestBody fileBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("inputFile",file.getName(),fileBody);
        return filePart;
    }
    public static Map<String,RequestBody> toMapofMoreFile(String path){
        File file = new File(path);
        //File file1 = new File(path1);
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        //RequestBody requestBody1=RequestBody.create(MediaType.parse("multipart/form-data"),file1);
        Map<String,RequestBody> map=new HashMap<>();
        map.put("file\"; filename=\""+ file.getName(), requestBody);
        //map.put("file\"; filename=\""+file1.getName(),requestBody1);
        return  map;
    }

}
