package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils;

import android.os.Message;
/**
 *  @作者 ：Administrator
 *
 *  @时间 ：2018/6/12 10:17
 *
 *  @说明 ：msg  获取消息工具类
 *
 *  @QQ   ：102245912
 **/

public class msg {
    private static Object object;
    private static int what;
    public static Message  getmsg(int what1,Object object1){
        what=what1;
        object=object1;
        Message msg=new Message();
        msg.what=what;
        msg.obj=object;
        return msg;
    }
}
