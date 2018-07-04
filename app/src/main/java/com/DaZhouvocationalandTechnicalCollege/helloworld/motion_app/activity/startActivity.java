package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.service.pay_service;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.service.system_service;
//import com.example.maps_module.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class startActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    private static final int PERMISSON_REQUESTCODE = 0;
    private static final int SETTING_REQUESTCODE = 1;
    private boolean isNeedCheck = true;
    private boolean isInit = false;//是否第一次登陆
    private boolean isLogin = false;//是否登陆
    private Button sbt_tart;

    LocationManager locationManager;
    private boolean isNo1start = false;

    @Override
    public int getXml() {
        return R.layout.activity_start;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

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

        sbt_tart=findViewById (R.id.sbt_tart);

        SharedPreferences sp = getSharedPreferences("isNo1start", 0);

        isNo1start = sp.getBoolean("key", false);
        if (isNo1start){

            //
            sbt_tart.setVisibility (View.GONE);

        }
        SharedPreferences sp1 = getSharedPreferences("isLogin", 0);
        isLogin = sp1.getBoolean("key", false);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
         new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //启动成功
                Log.i("日志", "onServiceConnected: 启动成功！");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("日志", "onServiceConnected: 启动失败！");

            }
        };

        Intent intent = new Intent(this, pay_service.class);
        startService(intent);
        startService(new Intent(this,system_service.class));
        boolean isAuthorize = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isAuthorize) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //boolean isAuthorize1 = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                        boolean isAuthorize1 = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                        Log.i("定位权限", "run: " + isAuthorize1);
                        if (isAuthorize1) {
                            Log.i("GPS权限", "run: " + (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED));
                            if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                if (isNo1start) {



                                    if (isLogin) {
                                        SharedPreferences sp = getSharedPreferences("user", 0);
                                        Okhttp_utils utils = new Okhttp_utils();
                                        utils.setMode("post");
                                        utils.seturl(url.getLogin_url());
                                        FormBody.Builder builder = utils.getBuilder();
                                        builder.add("id", sp.getString("name", ""));
                                        builder.add("password", sp.getString("password", ""));
                                        utils.setBody(builder);
                                        Log.i("自动登录", "name="+sp.getString("name","")+"password="+sp.getString("password",""));

                                        try {
                                            String doing = utils.doing();
                                            Log.i("启动时", "run: "+doing);
                                            if (doing.equals("\"result\":\"true\"")) {

                                                user.setId(sp.getString("name",""));
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        startActivity(new Intent(startActivity.this, HomeActivity.class));
                                                        finish();

                                                    }

                                                });
                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(startActivity.this,"您的密码已过期，请重新登录！",Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(startActivity.this,loginActivity.class));
                                                        finish();
                                                    }
                                                });

                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        //



                                        //


                                        break;
                                    } else {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                startActivity(new Intent(startActivity.this, loginActivity.class));
                                                finish();

                                            }
                                        });
                                        break;
                                    }

                                } else {
                                    SharedPreferences sp = getSharedPreferences("isNo1start", 0);
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putBoolean("key", true);
                                    edit.commit();

                                    break;
                                }


                            } else {
                                Log.i("初始化成功", "run: ");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // startActivity (new Intent (startActivity.this,MainActivity.class));
                                        //finish ();
                                    }
                                });
                                //break;
                            }

                        }
                    }
                }
            }.start();
        } else {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }

        }


    }

    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }


    }

    private List<String> findDeniedPermissions(String[] permissions) {

        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {


                return false;
            }
        }
        return true;
    }


    @Override
    public void initData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(grantResults)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    private void showMissingPermissionDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                        isInit = true;
                        // startActivity (new Intent (startActivity.this,MainActivity.class));
                    }
                });

        builder.setCancelable(false);

        builder.show();

    }

    private void startAppSettings() {

        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SETTING_REQUESTCODE);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_REQUESTCODE) {
            checkPermissions(needPermissions);
        }
    }


    public void onClick(View view){
        startActivity (new Intent (this,guideActivity.class));
        finish ();

    }
}
