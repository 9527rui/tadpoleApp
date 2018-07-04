package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import static android.support.constraint.Constraints.TAG;
import static com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url.getUploadDynamic;

/**
 * @作者：yangshijie
 *
 * @QQ: 102245912
 *
 * @时间：0:13 2018/6/18
 *
 * @说明：up_load_Dynamic  用户上传动态的类
 */



public abstract class up_load_Dynamic {
    /**
     *  @ 作用： text:动态标题    fliepath：图片路径
     *
     *  @ 时间： 2018/6/18 0:05
     */



    public up_load_Dynamic(Application application,String text,String[] fliepath){


        SharedPreferences sp=application.getSharedPreferences ("user",0);

        String url=getUploadDynamic();
        x.Ext.init (application);
        final RequestParams entity=new RequestParams (url);
        entity.setMultipart (true);
        entity.addBodyParameter ("user", sp.getString ("name",""));
        entity.addBodyParameter ("content",text);
//        switch (fliepath.length){
//            case 1:
//                entity.addBodyParameter ("img1",new File (fliepath[0]),null);
//
//                break;
//            case 2:
//                entity.addBodyParameter ("img1",new File (fliepath[0]),null);
//                entity.addBodyParameter ("img2",new File (fliepath[1]),null);
//                break;
//            case 3:
//                entity.addBodyParameter ("img1",new File (fliepath[0]),null);
//                entity.addBodyParameter ("img2",new File (fliepath[1]),null);
//                entity.addBodyParameter ("img3",new File (fliepath[2]),null);
//                break;
//            case 4:
//                entity.addBodyParameter ("img1",new File (fliepath[0]),null);
//                entity.addBodyParameter ("img2",new File (fliepath[1]),null);
//                entity.addBodyParameter ("img3",new File (fliepath[2]),null);
//                entity.addBodyParameter ("img4",new File (fliepath[3]),null);
//                break;
//
//            default:
//                Error_data();
//
//                break;
//
//
//        }
        if (fliepath[0]!=null){
            entity.addBodyParameter ("img1",new File (fliepath[0]),null);
        }if (fliepath[1]!=null){
            entity.addBodyParameter ("img1",new File (fliepath[0]),null);
            entity.addBodyParameter ("img2",new File (fliepath[1]),null);
        }if (fliepath[2]!=null){
            entity.addBodyParameter ("img1",new File (fliepath[0]),null);
            entity.addBodyParameter ("img2",new File (fliepath[1]),null);
            entity.addBodyParameter ("img3",new File (fliepath[2]),null);
        }if (fliepath[3]!=null){
            entity.addBodyParameter ("img1",new File (fliepath[0]),null);
            entity.addBodyParameter ("img2",new File (fliepath[1]),null);
            entity.addBodyParameter ("img3",new File (fliepath[2]),null);
            entity.addBodyParameter ("img4",new File (fliepath[3]),null);
        }

        x.http ().post (entity, new Callback.ProgressCallback<File> () {

            @Override
            public void onSuccess(File result) {
                Success(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i (TAG, "onError: "+ex.toString ());
                Error();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                onLoading(total,current,isDownloading);
            }
        });


    }
    /**
     *  @ 作用： 错误回掉
     *
     *  @ 时间： 2018/6/18 0:09
     */


    public abstract void Error();
    /**
     *  @ 作用： 成功回掉
     *
     *  @ 时间： 2018/6/18 0:09
     */


    public abstract void Success(File result);


    /**
     *  @ 作用： 上传进度回掉
     *
     *  @ 时间： 2018/6/18 0:10
     */


    public abstract void onLoading(long total, long current, boolean isDownloading);

    /**
     *  @ 作用： 参数设置错误回掉
     *
     *  @ 时间： 2018/6/18 0:12
     */

    public abstract void Error_data();



}
