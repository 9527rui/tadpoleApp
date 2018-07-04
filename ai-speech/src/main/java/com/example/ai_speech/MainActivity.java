package com.example.ai_speech;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.example.ai_speech.control.InitConfig;
import com.example.ai_speech.control.MySyntherizer;
import com.example.ai_speech.control.NonBlockSyntherizer;
import com.example.ai_speech.listener.UiMessageListener;
import com.example.ai_speech.util.AutoCheck;
import com.example.ai_speech.util.OfflineResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SpeechSynthesizerListener {
    private static final String TAG = "日志";
    protected String appId = "11337528";
    protected String appKey = "1sQBFwlRE7T7GcKr2O4eWI6x";
    protected String secretKey = "3083018d36ec48118c71ede31650cb28";
    private EditText ed_1;

    protected TtsMode ttsMode = TtsMode.MIX;

    // 离线发音选择，VOICE_FEMALE即为离线女声发音。
    // assets目录下bd_etts_common_speech_m15_mand_eng_high_am-mix_v3.0.0_20170505.dat为离线男声模型；
    // assets目录下bd_etts_common_speech_f7_mand_eng_high_am-mix_v3.0.0_20170512.dat为离线女声模型
    protected String offlineVoice = OfflineResource.VOICE_MALE;
    // ===============初始化参数设置完毕，更多合成参数请至getParams()方法中设置 =================
    // 主控制类，所有合成控制方法从这个类开始
    protected SpeechSynthesizer synthesizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        synthesizer = SpeechSynthesizer.getInstance();
        synthesizer.setContext(this);
        synthesizer.setSpeechSynthesizerListener(this);//监听回调
        synthesizer.setAppId(appId);
        synthesizer.setApiKey(appKey,secretKey);
        synthesizer.auth(TtsMode.ONLINE);
        synthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "4");
        synthesizer.initTts(TtsMode.ONLINE);
    }
    public void test(View view) {
        Log.i("====", "test: ");

        ed_1=(EditText)findViewById(R.id.ed_1);
        String s = ed_1.getText().toString();
        int speak = synthesizer.speak(s);
        if (speak!=0){
            Log.i("", "test: 播放出错！");
        }
    }

    @Override
    public void onSynthesizeStart(String s) {
        Log.i(TAG, "onSynthesizeStart: ");
    }

    @Override
    public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {

        Log.i(TAG, "onSynthesizeDataArrived: ");
    }

    @Override
    public void onSynthesizeFinish(String s) {
        Log.i(TAG, "onSynthesizeFinish: ");
    }

    @Override
    public void onSpeechStart(String s) {
        Log.i(TAG, "onSpeechStart: ");
    }

    @Override
    public void onSpeechProgressChanged(String s, int i) {
        Log.i(TAG, "onSpeechProgressChanged: ");
    }

    @Override
    public void onSpeechFinish(String s) {
        Log.i(TAG, "onSpeechFinish: ");
    }

    @Override
    public void onError(String s, SpeechError speechError) {
        Log.i(TAG, "onError: "+speechError.code+"====="+speechError.description);
    }
}
