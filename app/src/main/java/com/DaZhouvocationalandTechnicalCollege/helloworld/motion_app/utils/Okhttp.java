package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/5/6.
 */

public class Okhttp {
    public static String getSting(String url){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        try {
            Response response=client.newCall(request).execute();
            String data=response.body().string();
            Log.i(TAG, "getSting: "+data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Bitmap getBitmap(String url){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        try {
            Response response=client.newCall(request).execute();
            byte[] bytes = response.body().bytes();

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Log.i(TAG, "getBitmap: "+bitmap);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "getBitmap: 返回NUll了");
        return null;
    }
}
