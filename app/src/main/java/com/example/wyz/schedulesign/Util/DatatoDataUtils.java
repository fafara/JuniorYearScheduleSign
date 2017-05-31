package com.example.wyz.schedulesign.Util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by WYZ on 2017/5/31.
 */

public class DatatoDataUtils {
    /**
     * 把Bitmap转Byte
     */
    public static byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
