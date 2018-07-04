package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Head_portrait;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Gson_user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.record_last;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.FileUtils;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;

import static android.support.constraint.Constraints.TAG;

//用于监听系统服务（服务器数据）

public class system_service extends Service {
    private   SharedPreferences sp ;
    private Object users;
    private Object head;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        sp = getSharedPreferences("user", 0);
        Log.i("系统服务已启动！", "onCreate: ");
        initData();
        Monitor_user_steps();




        super.onCreate();



    }
//监听用户步数
    private void Monitor_user_steps() {




    }
    /**
      * @时间 ：2018/6/14  8:50
      *
      * @说明 ：初始化数据、定时器
      */

    private void initData() {



        new Thread(){
            @Override
            public void run() {
                super.run ();
                try {
                    updata ();
                    updatastep ();
                    getUsers ();

                    getHead ();

                    Thread.sleep (3600000);

                } catch (InterruptedException e) {
                    e.printStackTrace ();
                } catch (IOException e) {
                    e.printStackTrace ();
                } catch (JSONException e) {
                    e.printStackTrace ();
                }

            }
        }.start();




    }
    /**
     *  @ 作用： 更新7天历史步数
     *
     *  @ 时间： 2018/6/16 17:23
     */



    private void updatastep() throws IOException {
        if (sp.getString ("name","")==""){
            return;
        }

        Okhttp_utils utils=new Okhttp_utils ();
        utils.setMode ("get");
        utils.seturl (url.getHistorical_data_url ()+"?id="+sp.getString ("name",""));
        Log.i (TAG, "updatastep: "+url.getHistorical_data_url ()+"?id="+sp.getString ("name",""));
        final String doing = utils.doing ();


/**
 *  @ 作用： BUG  如果用户没有历史数据，解析会出现错误！
 *
 *  @ 时间： 2018/6/17 15:14
 */


        record_last[] record_last = new Gson ().fromJson (doing, record_last[].class);
        String[] step=new String[7];
        String[] data=new String[7];
        Log.i (TAG, "updatastep: "+record_last.length);
        for (int a =0;a<record_last.length;a++){
            step[a]=record_last[a].getStepNumber ();
            data[a]=record_last[a].getTime ();
            Log.i ("7天历史数据", "updatastep: "+record_last[a].getTime ());
        }
        user.setUser_Motion_record (step);
        user.setUser_data (data);




    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        return super.onStartCommand(intent, flags, startId);
    }
    /**
      * @时间 ：2018/6/14  10:50
      *
      * @说明 ：更新步数
      */
    public void updata() throws IOException {

        pay_service.mybinder mybinder=new pay_service.mybinder();
        int step_number = mybinder.getStep_number();
        Okhttp_utils utils=new Okhttp_utils();
        utils.seturl(url.getMotion_data_uploading()+"?"+"user="+sp.getString("name","")+"&stepNumber="+step_number);
      //  utils.seturl(url.getMotion_data_uploading()+"?"+"user="+"15729888990"+"&stepNumber="+step_number);
       // utils.seturl("http://www.baidu.com");
        utils.setMode("get");
        String doing = utils.doing();
        Log.i("更新链接", "updata: "+url.getMotion_data_uploading()+"?"+"user="+ sp.getString("name","")+"&stepNumber="+step_number);
        Log.i("步数更新！！！", "updata: "+doing);




    }

    /**
     *  @ 作用： 获取用户信息
     *
     *  @ 时间： 2018/6/16 18:38
     */



    public void getUsers() throws IOException, JSONException {
        if (sp.getString ("name","")==""){
            return;
        }
        Okhttp_utils utils=new Okhttp_utils ();
        utils.setMode ("get");
        utils.seturl (url.getUser_data()+"?id="+sp.getString ("name",""));

        final String doing = utils.doing ();
        Gson gson=new Gson ();



        final Gson_user[] gson_user = gson.fromJson (doing, Gson_user[].class);
        if (gson_user.length==0){
            return;
        }

        Log.i (TAG, "toString: "+ gson_user[0].toString ());
        user.setName (gson_user[0].getName ());


        /**
         *  @ 作用： 获取头像 设置默认头像
         *
         *  @ 时间： 2018/6/17 15:23
         */
    }
    public void getHead() throws IOException {
        new Thread(){
            @Override
            public void run() {

                String sdcardFolder = getExternalFilesDir(null).getAbsolutePath();
                File sdcardLicenceFile = new File(sdcardFolder + File.separator + "timg.png");
                if(sdcardLicenceFile.exists()){
                    return;
                }
                try {
                    FileUtils.copyFromAssetToSdcard(getApplication (), "timg.png", sdcardFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        if (sp.getString ("name","")==""){

            Log.i ("头像上传停止", "getHead: ");
            return;
        }
        Okhttp_utils utils=new Okhttp_utils ();
        utils.setMode ("get");
        utils.seturl (url.getGet_picture()+"?id="+sp.getString ("name",""));
        Bitmap bitmap=null;
       // bitmap = utils.getBitmap ();
        if (bitmap==null){
            /**
             *  @ 作用： 设置默认头像
             *
             *  @ 时间： 2018/6/17 23:40
             */



            Head_portrait headPortrait=new Head_portrait (getApplication (),getExternalFilesDir(null).getAbsolutePath()+ File.separator +"/timg.png") {
                @Override
                public void Success(File result) {
                    Log.i ("头像上传结果", "Success: ");

                }

                @Override
                public void Error() {
                    Log.i ("头像上传结果", "Error: ");

                }
            };
        }else {

            /**
             *  @ 作用： 通过url获得头像
             *
             *  @ 时间： 2018/6/17 23:39
             */


            return;


        }



    }
}
