package com.example.ai_speech;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //initXunFei();
    }
    /**
     * 获取全局上下文*/
    public static Context getContext() {
        return context;
    }
}
