package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;


import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.FormBody;

public class registerActivity extends BaseActivity implements View.OnClickListener {
    private EditText phone,yanzhen,password;
    private TextView hqyangz;
    private Button zhuce;
    private String yzm="";


    @Override
    public int getXml() {
        return R.layout.activity_register;
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
        phone=findViewById(R.id.ed_phone);
        yanzhen=findViewById(R.id.ed_yzmz);
        password=findViewById(R.id.ed_passwordz);
        hqyangz=findViewById(R.id.hq_zym);
        hqyangz.setOnClickListener(this);
        zhuce=findViewById(R.id.zcz);
        zhuce.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        final String zcyzm=yanzhen.getText().toString();
        final String sjhm=phone.getText().toString();
        final String mima=password.getText().toString();
        switch (v.getId()){
            case R.id.hq_zym:
                if (!sjhm.equals ("")){
                    if (sjhm.length ()!=11){
                        Toast.makeText (this,"请输入有效得手机号码！",Toast.LENGTH_SHORT).show ();
                    }else {
                        int a[]=new int[6];
                        for (int x=0;x<a.length;x++){
                            a[x]=(int)(Math.random ()*9);
                            yzm=yzm+a[x];

                        }

                       // yzm=a[0]+a[1]+a[2]+a[3]+a[4]+a[5]+"";
                        Log.i ("", "onClick: "+yzm);
                        String yzm1=null;
                        try {
                            yzm1= URLEncoder.encode ("#code#="+yzm,"GBK");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace ();
                        }
                        final Okhttp_utils utils=new Okhttp_utils ();
                        utils.setMode ("get");
                        utils.seturl (url.getModel_url ()+"?mobile="+sjhm+"&key=8c360238d70ffdf5aaacdfa768e545d8&tpl_id=76926&tpl_value="+yzm1);
                        Log.i ("链接地址", ""+url.getModel_url ()+"?mobile="+sjhm+"&key=8c360238d70ffdf5aaacdfa768e545d8&tpl_id=76926&tpl_value="+yzm1);

                        new Thread (){
                            @Override
                            public void run() {
                                super.run ();
                                String doing = null;
                                try {
                                    doing = utils.doing ();
                                } catch (IOException e) {
                                    e.printStackTrace ();
                                }
                                JSONObject object= null;
                                try {
                                    object = new JSONObject (doing);
                                } catch (JSONException e) {
                                    e.printStackTrace ();
                                }
                                final String reason = object.optString ("reason");
                                if (reason.equals ("操作成功")){
                                    runOnUiThread (new Runnable () {
                                        @Override
                                        public void run() {
                                            Toast.makeText (registerActivity.this,"发送成功！",Toast.LENGTH_SHORT).show ();
                                        }
                                    });
                                }else {
                                    runOnUiThread (new Runnable () {
                                        @Override
                                        public void run() {
                                            Toast.makeText (registerActivity.this,"网络链接失败！",Toast.LENGTH_SHORT).show ();
                                        }
                                    });
                                }
                            }
                        }.start ();



                    }
                }



                break;
            case R.id.zcz:


                final String finalYzm = yzm;
                Log.i ("========", "onClick: "+finalYzm);
                new Thread(){
                    @Override
                    public void run() {
                        if (!sjhm.equals("")&&!mima.equals("")&&zcyzm.equals (finalYzm)){
                            Okhttp_utils utils=new Okhttp_utils();
                            utils.setMode("post");
                            utils.seturl(url.getRegister_url());
                            FormBody.Builder builder = utils.getBuilder();
                            builder.add("id",sjhm);
                            builder.add("password",mima);
                            builder.add("conPwd",mima);
                            utils.setBody(builder);
                            try {
                                String doing = utils.doing();
                                if (doing!=null){
                                    if (doing.equals("\"register\":\"true\"")){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(registerActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        });

                                    }else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(registerActivity.this,"注册失败！",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if(!zcyzm.equals (finalYzm)) {
                            runOnUiThread (new Runnable () {
                                @Override
                                public void run() {
                                    Toast.makeText(registerActivity.this,"验证码错误！",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else if (sjhm.equals("")&&mima.equals("")){
                            runOnUiThread (new Runnable () {
                                @Override
                                public void run() {
                                    Toast.makeText(registerActivity.this,"请输入手机号或密码！",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }.start();





                break;
        }
    }
}
