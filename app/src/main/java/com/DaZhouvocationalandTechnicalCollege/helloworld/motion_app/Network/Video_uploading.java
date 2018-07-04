package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network;

import android.app.Application;

import org.xutils.x;

import static com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url.getPublishVideo;

/**
 * @作者 ：Administrator
 * @时间 ：2018/6/12 8:36
 * @说明 ：Video_uploading  视频上传的类
 * @QQ ：102245912
 **/

public class Video_uploading {

    private String url=null;


    /**
     *  @ 作用： 小视频动态上传
     *
     *  @ 时间： 2018/6/17 14:47
     */


    public Video_uploading(Application application, String[] paths) {

        url=getPublishVideo();
        x.Ext.init (application);





    }


}
