package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment.HomeFragment;

import java.io.IOException;


public class EdActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText zl_name,zl_age,zl_h,zl_w,zl_y,zl_m,zl_d,zl_school,zl_x,zl_class,zl_snumber,zl_xz,zl_hometown,zl_ydxy;
    private RadioGroup zl_sex;
    private RadioButton radioButton;
    private TextView bczl;
    private boolean last01 = false;
    private String tj_name,tj_age,tj_sex,tj_h,tj_w,tj_y,tj_m,tj_d,tj_scholl,tj_x,tj_class,tj_sunmber,tj_xz,tj_hometown,tj_ydxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initview();
    }

    private void initview() {
        bczl=findViewById(R.id.bczl);
        zl_name=findViewById(R.id.zl_name);
        zl_age=findViewById(R.id.zl_age);
        zl_h=findViewById(R.id.zl_h);
        zl_w=findViewById(R.id.zl_w);
        zl_y=findViewById(R.id.zl_y);
        zl_m=findViewById(R.id.zl_m);
        zl_d=findViewById(R.id.zl_d);
        zl_school=findViewById(R.id.zl_school);
        zl_x=findViewById(R.id.zl_x);
        zl_class=findViewById(R.id.zl_class);
        zl_snumber=findViewById(R.id.zl_snumber);
        zl_xz=findViewById(R.id.zl_xz);
        zl_hometown=findViewById(R.id.zl_hometown);
        zl_ydxy=findViewById(R.id.zl_ydxy);
        zl_sex=findViewById(R.id.zl_sex);
        zl_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radiobuttonId=radioGroup.getCheckedRadioButtonId();
                radioButton=EdActivity.this.findViewById(radiobuttonId);
                tj_sex= (String) radioButton.getText();

                Log.i("7777777777", "onCheckedChanged: "+tj_sex);
            }
        });
        bczl.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bczl:
                SharedPreferences sp01 = getSharedPreferences("tjzl", 0);
                tj_name=zl_name.getText().toString();//昵称
                tj_age=zl_age.getText().toString();//年龄
                tj_h=zl_h.getText().toString();//身高
                tj_w=zl_w.getText().toString();//体重
                tj_y=zl_y.getText().toString();//年
                tj_m=zl_m.getText().toString();//月
                tj_d=zl_d.getText().toString();//日
                tj_scholl=zl_school.getText().toString();//学校
                tj_x=zl_x.getText().toString();//系部
                tj_class=zl_class.getText().toString();//班级
                tj_sunmber=zl_snumber.getText().toString();//学号
                tj_xz=zl_xz.getText().toString();
                tj_hometown=zl_hometown.getText().toString();
                tj_ydxy=zl_ydxy.getText().toString();
                cacheName(sp01);
                updata(tj_name,tj_sex,tj_h,tj_w,tj_y,tj_m,tj_d,tj_scholl,tj_x,tj_class);
                SharedPreferences sp2=getSharedPreferences("isfirst",0);
                last01 = sp2.getBoolean("key02", false);
                if (last01){
                    startActivity(new Intent(this,DetailsActivity.class));
                }else {
                    startActivity(new Intent(this,startActivity.class));
                    SharedPreferences.Editor editor01=sp2.edit();
                    editor01.putBoolean("key02",true);
                    editor01.commit();
                }

                finish();
                break;

        }

    }
    /**
     *  @ 作用：更新服务器上用户得数据
     *
     *  @ 时间： 2018/6/16 17:07
     */
    
    

    private void updata(String name, String tj_sex, String tj_w, String tj_w1, String tj_y, String tj_m, String tj_d, String tj_scholl, String tj_x, String tj_class) {
        final String url= com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url.getInformation_change ()
                +"?id="+user.getId ()+"&name="+name+"&sex="+tj_sex+"&phone="+user.getId ()+"&email="+user.getId ()+"&height="+tj_w+"&weight="
                +tj_w1+"&birthday="+tj_y+tj_m+tj_d+"&school="+tj_scholl+"&college="+tj_x+"&className="+tj_class;
        Log.i ("", "updata: "+url);

        new Thread (){
            @Override
            public void run() {
               Okhttp_utils utils=new Okhttp_utils ();
               utils.setMode ("get");
               utils.seturl (url);
                try {
                    final String doing = utils.doing ();

                } catch (IOException e) {
                    e.printStackTrace ();
                }

            }
        }.start ();


    }


    /**
     *  @ 作用： 缓存用户信息
     *
     *  @ 时间： 2018/6/16 16:36
     */

    private void cacheName(SharedPreferences sp01) {
        SharedPreferences.Editor editor=sp01.edit();
        if (tj_name.equals("")){
        }else {
            editor.putString("name_z",tj_name);
            editor.commit();
        }
        if (tj_age.equals("")){
        }else {
            editor.putString("age_z",tj_age);
            editor.commit();
        }
        if (tj_sex==null){
            editor.putString("sex_z","男");
            editor.commit();
        }else {
            editor.putString("sex_z",tj_sex);
            editor.commit();
        }
        if (tj_h.equals("")){
        }else {
            editor.putString("h_z",tj_h);
            editor.commit();
        }
        if (tj_w.equals("")){
        }else {
            editor.putString("w_z",tj_w);
            editor.commit();
        }
        if (tj_y.equals("")){

        }else {
            editor.putString("y_z",tj_y);
            editor.commit();
        }
        if (tj_m.equals("")){

        }else {
            editor.putString("m_z",tj_m);
            editor.commit();
        }
        if (tj_d.equals("")){

        }else {
            editor.putString("d_z",tj_d);
            editor.commit();
        }
        if (tj_scholl.equals("")){

        }else {
            editor.putString("school_z",tj_scholl);
            editor.commit();
        }
        if (tj_x.equals("")){

        }else {
            editor.putString("x_z",tj_x);
            editor.commit();
        }
        if (tj_class.equals("")){

        }else {
            editor.putString("class_z",tj_class);
            editor.commit();
        }
        if (tj_sunmber.equals("")){

        }else {
            editor.putString("sunber_z",tj_sunmber);
            editor.commit();
        }
        if (tj_xz.equals("")){

        }else {
            editor.putString("xz_z",tj_xz);
            editor.commit();
        }
        if (tj_hometown.equals("")){

        }else {
            editor.putString("hometown_z",tj_hometown);
            editor.commit();
        }
        if (tj_ydxy.equals("")){

        }else {
            editor.putString("ydxy_z",tj_ydxy);
            editor.commit();
        }

    }
}
