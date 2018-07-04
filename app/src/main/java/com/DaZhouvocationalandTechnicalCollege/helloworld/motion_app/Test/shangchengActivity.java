package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;

import java.io.IOException;

import okhttp3.FormBody;

public class shangchengActivity extends BaseActivity implements View.OnClickListener {

    private TextView myJifengtv;
    private Button bt600;
    private Button bt1600;
    private Button bt1800;
    private Button bt2100;




    @Override
    public int getXml() {
        return R.layout.activity_shangcheng;
    }

    @Override
    public void initView() {
        myJifengtv = (TextView) findViewById(R.id.my_jifengtv);
        bt600 = (Button) findViewById(R.id.bt600);
        bt1600 = (Button) findViewById(R.id.bt1600);
        bt1800 = (Button) findViewById(R.id.bt1800);
        bt2100 = (Button) findViewById(R.id.bt2100);
        query ();
        bt600.setOnClickListener (this);
        bt1600.setOnClickListener (this);
        bt1800.setOnClickListener (this);
        bt2100.setOnClickListener (this);


    }

    @Override
    public void initData() {

    }


    /**
     *  @ 作用： 兑换的方法，前台直接调用
     *
     *  @ 时间： 2018/6/23 16:15
     */




    public void exchange(String s){

        final Okhttp_utils utils=new Okhttp_utils ();
        utils.seturl ("http://39.105.37.77:8080/sports/exchangeIntegral");
        utils.setMode ("post");
        final FormBody.Builder builder = utils.getBuilder ();
        builder.add ("user", user.getId ());
        builder.add ("integral",s);//扣除的积分
        utils.setBody (builder);
        new Thread (){
            @Override
            public void run() {
                try {
                    final String doing = utils.doing ();
                    runOnUiThread (new Runnable () {
                        @Override
                        public void run() {
                            Toast.makeText (shangchengActivity.this,"您的积分不足",Toast.LENGTH_SHORT).show ();
                        }
                    });



                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }.start ();



    }

    /**
     *  @ 作用： 积分查询
     *
     *  @ 时间： 2018/6/23 16:21
     */



    public void query(){
       final Okhttp_utils utils=new Okhttp_utils ();
        utils.seturl ("http://39.105.37.77:8080/sports/queryIntegral"+"?user="+user.getId ());
        utils.setMode ("get");
        new Thread (){
            @Override
            public void run() {
                try {
                    final String doing = utils.doing ();
                    runOnUiThread (new Runnable () {
                        @Override
                        public void run() {
                            myJifengtv.setText (doing);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }.start ();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.bt600:
                exchange("600");
                break;
            case R.id.bt1600:
                exchange("1600");
                break;
            case R.id.bt1800:
                exchange("1800");
                break;
            case R.id.bt2100:
                exchange("2100");
                break;
        }
    }
}
