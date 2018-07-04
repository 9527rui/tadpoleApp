package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;

public class NewsContent extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newscontent);
        Intent intent = getIntent();
        String url1=intent.getStringExtra("url");
        WebView myWebview = findViewById(R.id.webview);
        myWebview.loadUrl(url1);

    }

}
