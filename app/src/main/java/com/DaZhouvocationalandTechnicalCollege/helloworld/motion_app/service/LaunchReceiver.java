package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LaunchReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1=new Intent(context,pay_service.class);
        Intent intent2=new Intent(context,system_service.class);
        context.startService(intent1);
        context.startService(intent2);
    }
}
