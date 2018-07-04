package com.example.ai_speech;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

public class Voice_service extends Service implements SpeechSynthesizerListener {

    private static final String TAG = "日志";
    protected String appId = "11337528";
    protected String appKey = "1sQBFwlRE7T7GcKr2O4eWI6x";
    protected String secretKey = "3083018d36ec48118c71ede31650cb28";
    int s=0;

    protected SpeechSynthesizer synthesizer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        init();

        super.onCreate();
    }

    private void init() {
        synthesizer = SpeechSynthesizer.getInstance();
        synthesizer.setContext(getApplicationContext());
        synthesizer.setSpeechSynthesizerListener(this);//监听回调
        synthesizer.setAppId(appId);
        synthesizer.setApiKey(appKey,secretKey);
        synthesizer.auth(TtsMode.ONLINE);
        synthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "4");
        synthesizer.initTts(TtsMode.ONLINE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        s= (int) extras.get("run");
        Log.i(TAG, "onStartCommand: "+s);
        switch (s){
            case 1:
                new Thread(){
                    @Override
                    public void run() {

                        try {
                            synthesizer.speak(""+3);
                            Thread.sleep(1000);
                            synthesizer.speak(""+2);
                            Thread.sleep(1000);
                            synthesizer.speak(""+1);
                            synthesizer.speak("开始跑步!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();


                break;
            case 1000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油");
                break;
            case 2000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;
            case 3000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;
            case 4000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;
            case 5000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;
            case 6000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;
            case 7000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;
            case 8000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;
            case 9000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;
            case 10000:
                synthesizer.speak("您已经跑了"+s+"米，继续加油！");
                break;


        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onSynthesizeStart(String s) {

    }

    @Override
    public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {

    }

    @Override
    public void onSynthesizeFinish(String s) {

    }

    @Override
    public void onSpeechStart(String s) {

    }

    @Override
    public void onSpeechProgressChanged(String s, int i) {

    }

    @Override
    public void onSpeechFinish(String s) {

    }

    @Override
    public void onError(String s, SpeechError speechError) {

    }
}
