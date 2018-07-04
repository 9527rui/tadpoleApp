package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.service.pay_service;

import java.io.IOException;

import okhttp3.FormBody;

public class IntegralmallActivity extends BaseActivity implements View.OnClickListener {
    private Button bsBt1;
    private Button bsBt2;
    private TextView bsBt3;
    private  final pay_service.mybinder mybinder=new pay_service.mybinder();




    @Override
    public int getXml() {
        return R.layout.activity_integralmall;
    }

    @Override
    public void initView() {
        bsBt1 = findViewById(R.id.bs_bt1);
        bsBt3 = findViewById(R.id.bs_bt3);
        bsBt1.setOnClickListener (this);
        bsBt3.setOnClickListener (this);



    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.bs_bt1:
                signup();
                break;

            case R.id.bs_bt3:
                Datasubmission();
                break;


        }

    }

    /**
     *  @ 作用： 每日比赛数据的提交
     *
     *  @ 时间： 2018/6/23 13:56
     */



    private void Datasubmission() {
       final Okhttp_utils utils=new Okhttp_utils ();
        utils.setMode ("post");
        utils.seturl ("http://39.105.37.77:8080/sports/uploadMatchData");
        final FormBody.Builder builder = utils.getBuilder ();
        builder.add ("user",user.getId ());
        builder.add ("stepNumber",mybinder.getStep_number ()+"");
        utils.setBody (builder);
        new Thread (){
            @Override
            public void run() {
                try {
                    final String doing = utils.doing ();
                    runOnUiThread (new Runnable () {
                        @Override
                        public void run() {
                            Toast.makeText (IntegralmallActivity.this,"已提交！",Toast.LENGTH_SHORT).show ();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }.start ();



    }

    /**
     *  @ 作用： 比赛报名
     *
     *  @ 时间： 2018/6/23 13:53
     */



    private void signup() {
        final Okhttp_utils utils=new Okhttp_utils ();
        utils.setMode ("get");
        utils.seturl ("http://39.105.37.77:8080/sports/signUp"+"?user="+user.getId ());

        Log.i ("===", "signup: "+ user.getId ());

        new Thread (){
            @Override
            public void run() {
                try {
                    final String doing = utils.doing ();
                    if (doing.equals ("true")){
                        runOnUiThread (new Runnable () {
                            @Override
                            public void run() {
                                Toast.makeText (IntegralmallActivity.this,"报名成功！",Toast.LENGTH_SHORT).show ();
                            }
                        });
                    }else {
                        runOnUiThread (new Runnable () {
                            @Override
                            public void run() {
                                Toast.makeText (IntegralmallActivity.this,"您已经报名！",Toast.LENGTH_SHORT).show ();
                            }
                        });

                    }


                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }.start ();

    }
/**
 *  @ 作用： 排名
 *
 *  @ 时间： 2018/6/23 18:03
 */


    public void ranking(){



    }


}
