package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;

import java.io.IOException;

import okhttp3.FormBody;

public class loginActivity extends BaseActivity implements View.OnClickListener {
    private EditText ed_name,ed_password;
    private TextView wjma,kszc;
    private Button bt_login;
    private CustomVideoView videoview;
    private boolean last = false;
    //private String url="http://39.105.37.77:8080/android/login";
    @Override
    public int getXml() {
        return R.layout.activity_login;
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
        ed_name=findViewById(R.id.ed_name);
        ed_password=findViewById(R.id.ed_password);
        videoview=findViewById(R.id.videoview);
        bt_login=findViewById(R.id.bt_login);
        wjma=findViewById(R.id.wjma);
        wjma.setOnClickListener(this);
        kszc=findViewById(R.id.kszc);
        kszc.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        ed_name.setHintTextColor(getResources().getColor(R.color.bai_1));
        ed_password.setHintTextColor(getResources().getColor(R.color.bai_1));
        videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.video));
//        videoview.setVideoPath(url.getLogin_mp4_url());

        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });

    }
    @Override
   protected void onRestart() {
        super.onRestart();
      initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoview.stopPlayback();
   }

    @Override
    public void initData() {
        //184654
        //123456

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                //登录逻辑
                final String name = ed_name.getText().toString();
                final String password = ed_password.getText().toString();
                if (!name.equals("")&&!password.equals("")){
                    new Thread(){
                        @Override
                        public void run() {
                            Okhttp_utils utils=new Okhttp_utils();
                            utils.setMode("post");
                            utils.seturl(url.getLogin_url());
                            FormBody.Builder builder = utils.getBuilder();
                            builder.add("id",name);
                            builder.add("password",password);
                            utils.setBody(builder);
                            try {
                                String doing = utils.doing();

                                if (doing.equals("\"result\":\"true\"")){


                                    SharedPreferences sp=getSharedPreferences("user",0);
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putString("name",name);
                                    edit.putString("password",password);
                                    edit.commit();
                                    SharedPreferences sp1=getSharedPreferences("isLogin",0);
                                    SharedPreferences.Editor edit1 = sp1.edit();
                                    edit1.putBoolean("key",true);
                                    edit1.commit();
                                    final SharedPreferences sp2=getSharedPreferences("isfirst",0);
                                    last = sp2.getBoolean("key01", false);
                                    user.setId(name);
                                    user.setPassword(password);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (last){
                                                startActivity(new Intent(loginActivity.this,HomeActivity.class));
                                            }else {
                                                startActivity(new Intent(loginActivity.this,EdActivity.class));
                                                SharedPreferences.Editor editor=sp2.edit();
                                                editor.putBoolean("key01",true);
                                                editor.commit();
                                            }

                                            finish();
                                        }
                                    });
                                }else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(loginActivity.this,"账户或密码有误！",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();

                }else {
                    Toast.makeText(loginActivity.this,"账户或密码不能为空！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.wjma:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
            case R.id.kszc:
                startActivity(new Intent(this,registerActivity.class));
                break;
        }


    }
}
