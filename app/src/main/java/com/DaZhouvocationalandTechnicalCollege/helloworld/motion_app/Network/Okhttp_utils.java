package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @作者：yangshijie
 *
 * @QQ: 102245912
 *
 * @时间：20:33 2018/6/11
 *
 * @说明：Okhttp_utils 获得用户基本数据的类
 */

public class Okhttp_utils {
    private OkHttpClient client;
    private FormBody.Builder builder;
    private RequestBody body;
    private String url;
    private String mode;
    public Okhttp_utils(){
        client=new OkHttpClient();
    }
    public void seturl(String url){
        this.url=url;
    }
    public FormBody.Builder getBuilder(){
        return builder = new FormBody.Builder();
    }

    public void setBody(FormBody.Builder builder){
        this.body=builder.build();
    }
    public void setMode(String mode){
        this.mode=mode;
    }

    /**
     *  @ 作用：登陆注册验证
     *
     *  @ 时间： 2018/6/11 20:57
     */

    //


    public String doing() throws IOException {
        if (mode!=null){
            if (mode.equals("post")){
                Request request=new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response=client.newCall(request).execute();
                String string = response.body().string();
                Log.i("request", "post "+string);
                return string;
            }else if (mode.equals("get")){
                Request request=new Request.Builder()
                        .url(url)
                        .build();
                Response response=client.newCall(request).execute();
                String string = response.body().string();
                Log.i("request", "get "+string);
                return string;
            }else {
                return null;
            }
        }else {
            return null;
        }


    }
/**
  * @时间 ：2018/6/14  9:25
  *
  * @说明 ：普通的GET和POST请求
  */

    public String getData() throws IOException {
        if (mode!=null){
            if (mode.equals("post")){
                Request request=new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response=client.newCall(request).execute();
                String string = response.body().string();
                Log.i("request", "doing "+string);
                return string;
            }else if (mode.equals("get")){
                Request request=new Request.Builder()
                        .url(url)
                        .build();
                Response response=client.newCall(request).execute();
                String string = response.body().string();
                Log.i("request", "doing "+string);
                return string;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }


    /**
     *  @ 作用： 获取图片
     *
     *  @ 时间： 2018/6/17 14:58
     */


    public Bitmap getBitmap() throws IOException {
        if (mode!=null){
            if (mode.equals("post")){
                Request request=new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response=client.newCall(request).execute();
                final byte[] bytes = response.body ().bytes ();
                final Bitmap bitmap = BitmapFactory.decodeByteArray (bytes, 0, bytes.length);

                Log.i("request", "doing "+bitmap);
                return bitmap;
            }else if (mode.equals("get")){
                Request request=new Request.Builder()
                        .url(url)
                        .build();
                Response response=client.newCall(request).execute();
                final byte[] bytes = response.body ().bytes ();
                final Bitmap bitmap = BitmapFactory.decodeByteArray (bytes, 0, bytes.length);
                Log.i("request", "doing "+bitmap);
                return bitmap;
            }else {
                return null;
            }
        }else {
            return null;
        }

    }





}
