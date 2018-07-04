package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;

public class guideActivity extends BaseActivity implements View.OnClickListener{
    private Button zczh,dlnew;


    @Override
    public int getXml() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Log.i ("", "引导界面启动: ");
        SharedPreferences sp = getSharedPreferences("mbbs", 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("number","10000");
        edit.putString("Bodyweight","50");
        edit.commit();
        zczh=findViewById(R.id.zczh);
        zczh.setOnClickListener(this);
        dlnew=findViewById(R.id.dlnew);
        dlnew.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zczh:
                startActivity(new Intent(this,registerActivity.class));
                break;
            case R.id.dlnew:
                startActivity(new Intent(this,loginActivity.class));
                finish();
                break;
        }
    }
}
