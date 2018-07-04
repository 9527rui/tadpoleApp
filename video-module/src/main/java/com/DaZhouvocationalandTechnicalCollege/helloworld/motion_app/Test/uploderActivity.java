package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.qiniu.pili.droid.shortvideo.demo.R;


import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

public class uploderActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "xutils";
    private VideoView uploadVideoView;
    private EditText uploadEditText;
    private Button uploadButton;
    private String VideoPath;
    private String url="http://39.105.37.77:8080/sports/publishVideo";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_uploder);
        init();
        initView();

    }

    private void initView() {
        uploadVideoView = (VideoView) findViewById(R.id.upload_videoView);
        uploadEditText = (EditText) findViewById(R.id.upload_editText);
        uploadButton = (Button) findViewById(R.id.upload_button);
        uploadButton.setOnClickListener (this);
        uploadVideoView.setVideoPath (VideoPath);
        uploadVideoView.pause ();
        uploadVideoView.start ();



    }

    public void init(){
        final Intent intent = getIntent ();
        VideoPath= intent.getStringExtra ("path");
        Log.i ("上传的路径", "initView: "+VideoPath);
    }

    @Override
    public void onClick(View v) {
        final String s = uploadEditText.getText ().toString ();
        if (s.equals ("")){
            Toast.makeText (uploderActivity.this,"标题不能为空！",Toast.LENGTH_SHORT).show ();
            return;
        }
        SharedPreferences sp=getSharedPreferences ("user",0);
        final String name = sp.getString ("name", "");
       // File file=new File (VideoPath,"record.mp4");

        x.Ext.init (getApplication ());
        RequestParams entiity=new RequestParams (url);
        entiity.setMultipart (true);
        entiity.addBodyParameter ("file",new File (VideoPath));
        entiity.addBodyParameter ("user",name);
        entiity.addBodyParameter ("content",s);

        x.http ().post (entiity, new org.xutils.common.Callback.ProgressCallback<File> () {
            @Override
            public void onSuccess(File result) {
                Log.i (TAG, "onSuccess: ");
                runOnUiThread (new Runnable () {
                    @Override
                    public void run() {
                        Toast.makeText (uploderActivity.this,"上传成功！",Toast.LENGTH_SHORT).show ();

                        finish ();
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i (TAG, "onError: ");
                runOnUiThread (new Runnable () {
                    @Override
                    public void run() {
                        Toast.makeText (uploderActivity.this,"服务器繁忙，请稍后再试！",Toast.LENGTH_SHORT).show ();
                    }
                });

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i (TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.i (TAG, "onFinished: ");
            }

            @Override
            public void onWaiting() {
                Log.i (TAG, "onWaiting: ");
            }

            @Override
            public void onStarted() {
                Log.i (TAG, "onStarted: ");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Log.i (TAG, "onLoading: "+current);
            }
        });


    }
}
