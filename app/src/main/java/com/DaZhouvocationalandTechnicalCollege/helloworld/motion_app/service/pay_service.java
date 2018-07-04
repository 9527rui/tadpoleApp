package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


public class pay_service extends Service {
    private static final String TAG = "日志";
    private SensorManager sensorManager;
    private Sensor stepCounter;//步伐总数传感器
    private Sensor stepDetector;//单次步伐传感器
    private int x = 0;//初始化步数
    private int z = 0;//标识
    private static int y = 0;//实际步数
    private SensorEventListener stepCounterListener;//步伐总数传感器事件监听器
    private SensorEventListener stepDetectorListener;//单次步伐传感器事件监听器
    private int First;
    private int End;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    public static class mybinder {
        public int getStep_number() {
            return y;
        }
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: 服务已启动");
        super.onCreate();
        initData();
        initListener();
        sensorManager.registerListener(stepDetectorListener, stepDetector, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(stepCounterListener, stepCounter, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void initListener() {
        stepCounterListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                /**
                  * @时间 ：2018/6/14  9:56
                  *
                  * @说明 ：步数核心算法
                  */
                Log.i(TAG, "onSensorChanged: " + event.values[0]);
                SharedPreferences sp = getSharedPreferences("App_Step_number", 0);
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("app", (int) event.values[0]);
                Log.i(TAG, "onSensorChanged: 退出的时候储存的步数" + sp.getInt("app", 0));
                SimpleDateFormat sdf = new SimpleDateFormat("dd");
                String s = sdf.format(new Date());
                SharedPreferences sp_data=getSharedPreferences("data",0);
                if (sp_data.getString("data","").equals(s)){
                    Log.i(TAG, "onSensorChanged: 天数一样");


                }else {
                    if (sp_data.getString("data","").equals("")){
                        SharedPreferences.Editor edit_data = sp_data.edit();
                        edit_data.putString("data",s);
                        edit_data.commit();
                        Log.i(TAG, "onSensorChanged: 天数第一次");

                    }
                    SharedPreferences.Editor edit_data = sp_data.edit();
                    edit_data.putString("data",s);
                    edit_data.commit();
                    x = (int) event.values[0];
                    SharedPreferences.Editor edit1 = sp.edit();
                    edit1.putInt("app",x);
                    SharedPreferences sp1 = getSharedPreferences("App_Step_number1", 0);
                    SharedPreferences.Editor edit2 = sp1.edit();
                    edit2.putInt("app1",0);
                    edit1.commit();
                    edit2.commit();
                   // y=0;
                    Log.i(TAG, "onSensorChanged:天数改变 ");



                }
                if (sp.getInt("app", 0) == 0) {
                    if (z == 0) {
                        Log.i(TAG, "onSensorChanged: 第一次启动");
                        edit.commit();
                        x = (int) event.values[0];
                        z = 1;
                    } else {
                        SharedPreferences sp1 = getSharedPreferences("App_Step_number1", 0);
                        SharedPreferences.Editor edit1 = sp1.edit();
                        y = (int) event.values[0] - x;
                        edit1.putInt("app1", y);
                        edit1.commit();
                    }
                } else {
                    Log.i(TAG, "onSensorChanged: 第二次启动");
                    SharedPreferences sp1 = getSharedPreferences("App_Step_number1", 0);
                    int app = sp.getInt("app", 0);
                    if (((int) event.values[0] - app) >= 0) {
                        y = (int) event.values[0] - app + sp1.getInt("app1", 0);
                    } else {
                        y = (int) event.values[0] + sp1.getInt("app1", 0);
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        stepDetectorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    First = y;
                    try {
                        Thread.sleep(10000);
                        End = y;
                        if ((End - First) < 5) {
                            //系统判断此时没有运动
                            Log.i(TAG, "run: " + "系统判断此时没有运动");
                            //  motion.setTYEP("系统判断此时没有运动");
                        } else if ((End - First) < 15) {
                            Log.i(TAG, "run: " + "系统判断此时在散步");
                            //  motion.setTYEP("系统判断此时在散步");
                            //系统判断此时在散步
                        } else if ((End - First > 15)) {
                            Log.i(TAG, "run: " + "系统判断此时在跑步");
                            //  motion.setTYEP("系统判断此时在跑步");
                            //系统判断此时在跑步
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();
    }

    private void initData() {


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器系统服务
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);//获取计步总数传感器
        stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);//获取单次计步传感器
    }

    @Override
    public void onDestroy() {

        stopForeground(true);
        Intent intent = new Intent("com.example.demo.destroy");
        sendBroadcast(intent);
        super.onDestroy();

    }

}
