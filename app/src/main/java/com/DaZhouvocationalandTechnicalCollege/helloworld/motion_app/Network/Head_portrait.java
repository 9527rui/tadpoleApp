package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import static android.content.ContentValues.TAG;

/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：20:33 2018/6/11
 *
 * @说明：Head_portrait  用户上传头像的类
 */

public abstract class Head_portrait {
   // private Context context;
    


    public Head_portrait(Application application,String filepath){
        Log.i ("头像路径", ""+filepath);
        Log.i ("上传接口", ""+url.getPicture_upload ());
        x.Ext.init (application);
        RequestParams entity=new RequestParams (url.getPicture_upload ());
        entity.setMultipart (true);
        SharedPreferences sp=application.getSharedPreferences ("user",0);

        final String name = sp.getString ("name", "");
        Log.i ("用户ID", ""+name);
        if (name.equals ("")){
            return;
        }



        entity.addBodyParameter ("file",new File (filepath),null);
        entity.addBodyParameter ("userName",name,null);
        
        x.http ().post (entity, new Callback.ProgressCallback<File> () {
           

            @Override
            public void onSuccess(File result) {
                //上传成功回掉
                Success(result);
                Log.i ("成功回掉", "onSuccess: ");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Error();

                Log.i ("失败回掉", "onError: "+ex.toString ());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i (TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.i (TAG, "onFinished: ");
            }

            @Override
            public void onWaiting() {
                Log.i (TAG, "onWaiting: ");
            }

            @Override
            public void onStarted() {
                Log.i (TAG, "onStarted: ");
            }

            @Override
            public  void onLoading(long total, long current, boolean isDownloading) {
                //进度

                Log.i (TAG, "onLoading: ");
            }
        });

        
    }

    public abstract void Success(File result);

    public abstract void Error();

}
