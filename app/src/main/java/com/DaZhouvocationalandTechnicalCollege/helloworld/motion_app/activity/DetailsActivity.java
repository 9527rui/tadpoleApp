package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;

public class DetailsActivity extends AppCompatActivity {
    private TextView bjzl,zl_name_tv,zl_sex_tv,zl_age_tv,zl_h_tv,zl_w_tv,zl_ymd,
            zl_school_tv,zl_x_tv,zl_class_tv,zl_snumber_tv,zl_xz_tv,zl_hometown_tv,zl_ydxy_tv;
    private LinearLayout ll_age,ll_ymd,ll_xz,ll_hometown,ll_ydxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initview();
        Log.i("0000000000000", "onCreate: "+"qidong");

    }
    private void initview() {
        bjzl=findViewById(R.id.bjzl);
        bjzl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,EdActivity.class);
                startActivity(intent);
            }
        });
        zl_name_tv=findViewById(R.id.zl_name_tv);
        zl_sex_tv=findViewById(R.id.zl_sex_tv);
        zl_age_tv=findViewById(R.id.zl_age_tv);
        zl_h_tv=findViewById(R.id.zl_h_tv);
        zl_w_tv=findViewById(R.id.zl_w_tv);
        zl_ymd=findViewById(R.id.zl_ymd);
        zl_school_tv=findViewById(R.id.zl_school_tv);
        zl_x_tv=findViewById(R.id.zl_x_tv);
        zl_snumber_tv=findViewById(R.id.zl_snumber_tv);
        zl_class_tv=findViewById(R.id.zl_class_tv);
        zl_xz_tv=findViewById(R.id.zl_xz_tv);
        zl_hometown_tv=findViewById(R.id.zl_hometown_tv);
        zl_ydxy_tv=findViewById(R.id.zl_ydxy_tv);
        ll_age=findViewById(R.id.ll_age);
        ll_ymd=findViewById(R.id.ll_ymd);
        ll_xz=findViewById(R.id.ll_xz);
        ll_hometown=findViewById(R.id.ll_hometown);
        ll_ydxy=findViewById(R.id.ll__ydxy);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("11111111111111111", "onStart: ");
        SharedPreferences sp01 = getSharedPreferences("tjzl", 0);
        zl_name_tv.setText(sp01.getString("name_z",""));
        zl_sex_tv.setText(sp01.getString("sex_z",""));
        if (sp01.getString("age_z","")!=""){
            ll_age.setVisibility(View.VISIBLE);
            zl_age_tv.setText(sp01.getString("age_z","")+"岁");
        }
        zl_h_tv.setText(sp01.getString("h_z","")+"cm");
        zl_w_tv.setText(sp01.getString("w_z","")+"kg");
        if (sp01.getString("y_z","")!=""){
            ll_ymd.setVisibility(View.VISIBLE);
            zl_ymd.setText(sp01.getString("y_z","")+"."+sp01.getString("m_z","")+"."+sp01.getString("d_z",""));
        }
        zl_school_tv.setText(sp01.getString("school_z",""));
        zl_x_tv.setText(sp01.getString("x_z",""));
        zl_snumber_tv.setText(sp01.getString("sunber_z",""));
        zl_class_tv.setText(sp01.getString("class_z",""));
        if (sp01.getString("xz_z","")!=""){
            ll_xz.setVisibility(View.VISIBLE);
            zl_xz_tv.setText(sp01.getString("xz_z",""));
        }
        if (sp01.getString("hometown_z","")!=""){
            ll_hometown.setVisibility(View.VISIBLE);
            zl_hometown_tv.setText(sp01.getString("hometown_z",""));
        }
        if (sp01.getString("ydxy_z","")!=""){
            ll_ydxy.setVisibility(View.VISIBLE);
            zl_ydxy_tv.setText(sp01.getString("ydxy_z",""));
        }

    }
}
