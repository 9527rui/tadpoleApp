package com.example.media_module.utils;


import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.example.media_module.R;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.IOException;

public class picture_utils extends Activity{


    private Activity activity;


    public picture_utils(Activity activity,int REQUEST_CODE_CHOOSE){


        this.activity=activity;





        Matisse
                .from(activity)
                .choose(MimeType.ofAll ())//照片视频全部显示
                .countable(true)//有序选择图片
                .maxSelectable(9)//最大选择数量为9
                .gridExpectedSize(360)//图片显示表格的大小getResources()
                //.getDimensionPixelSize(R.dimen.media_grid_size)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//图像选择和预览活动所需的方向。
                .thumbnailScale(0.85f)//缩放比例
                .theme(R.style.Matisse_Dracula)//主题  暗色主题 R.style.Matisse_Dracula
                .capture(true) //是否提供拍照功能
                .captureStrategy(new CaptureStrategy(true,"com.example.media_module.fileprovider"))//存储到哪里
                .imageEngine(new GlideEngine())//加载方式
                .forResult(REQUEST_CODE_CHOOSE);//请求码





    }
}
