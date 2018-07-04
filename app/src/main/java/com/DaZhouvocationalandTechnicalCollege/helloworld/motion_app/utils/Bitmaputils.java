package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.io.File;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 *  @作者 ：Administrator
 *
 *  @时间 ：2018/6/12 9:38
 *
 *  @说明 ：Bitmaputils 图片压缩工具类
 *
 *  @QQ   ：102245912
 **/

public abstract class Bitmaputils {

    public Bitmaputils(Context context,File file, int size, String path){
        Luban.with(context)
                .load(file)
                .ignoreBy(size)
                .setTargetDir(path)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                        Start();
                    }

                    @Override
                    public void onSuccess(File file) {

                        Success(file);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Error(e);
                    }
                }).launch();
    }

    /**
      * @时间 ：2018/6/12  9:49
      *
      * @说明 ：开始、成功、错误回调
      */

    public abstract void Start();
    public abstract void Success(File file);
    public abstract void Error(Throwable e);

}
