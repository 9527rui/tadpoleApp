package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.up_load_Dynamic;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.HomeActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment.CommunityFragment;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.FileUtils;
import com.example.media_module.utils.picture_utils;
import com.lwkandroid.rtpermission.RTPermission;
import com.lwkandroid.rtpermission.listener.OnPermissionResultListener;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者：yangshijie
 * @QQ:
 * @时间：0:29 2018/6/18
 * @说明：Dynamic_test_Activity 上传动态测试Activity
 */
public class Dynamic_test_Activity extends BaseActivity implements OnPermissionResultListener, View.OnClickListener {
    private static final String TAG = "日志";
    private boolean isPermission = false;

    private TextView Dynamic_tv1,nummax;
    private Button DynamicBt1;
    private Button DynamicBt2;
    private TextView DynamicBt3;
    private Button DynamicBt4;
    private EditText DynamicEd1;
    private ImageView DynamicImage1;
    private ImageView DynamicImage2;
//    private ImageView DynamicImage3;
//    private ImageView DynamicImage4;
    private String maxnumber;
    private int REQUEST_CODE_CHOOSE = 001;
    private List<Uri> uris;
    private String[] paths;


    @Override
    public int getXml() {
        return R.layout.activity_dynamic_test_;
    }

    @Override
    public void initView() {
        nummax=findViewById(R.id.nummax);
        DynamicBt1 = (Button) findViewById (R.id.Dynamic_bt1);
//        DynamicBt2 = (Button) findViewById (R.id.Dynamic_bt2);
       DynamicBt3 = findViewById (R.id.Dynamic_bt3);
        DynamicBt4 = findViewById (R.id.Dynamic_bt4);
        DynamicEd1 = (EditText) findViewById (R.id.Dynamic_ed1);
        DynamicEd1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                maxnumber=s.length()+"";
                if (s.length() > 120){
                    DynamicEd1.setText(s.toString().substring(0,120));
                    DynamicEd1.setSelection(120);
                    Toast.makeText(Dynamic_test_Activity.this, "亲，输入字数已达上限！", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                nummax.setText(maxnumber);

            }
        });
        DynamicImage1 = (ImageView) findViewById (R.id.Dynamic_image1);
        DynamicImage2 = (ImageView) findViewById (R.id.Dynamic_image2);
//        DynamicImage3 = (ImageView) findViewById (R.id.Dynamic_image3);
//        DynamicImage4 = (ImageView) findViewById (R.id.Dynamic_image4);
//        Dynamic_tv1=(TextView)findViewById (R.id.Dynamic_tv1);//进度
        DynamicBt1.setOnClickListener (this);
//        DynamicBt2.setOnClickListener (this);
        DynamicBt3.setOnClickListener (this);
        DynamicBt4.setOnClickListener(this);
        initPermission ();


    }

    /**
     * @ 作用： 权限申请
     * @ 时间： 2018/6/18 0:21
     */
    private void initPermission() {
        new RTPermission.Builder ()
                .permissions (
                        Manifest.permission.CAMERA,
                        // Manifest.permission.LOCATION_HARDWARE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.BODY_SENSORS,
                        Manifest.permission.READ_EXTERNAL_STORAGE


                )
                .start (this, this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onAllGranted(String[] allPermissions) {
        isPermission = true;
    }

    @Override
    public void onDeined(String[] dinedPermissions) {

        //没有权限 不允许用户进一步操作
        isPermission = false;
    }

    @Override
    public void onClick(View view) {
        if (isPermission) {
            switch (view.getId ()) {
                case R.id.Dynamic_bt1:

                    /**
                     *  @ 作用： BUG  选择图片后，再次选择只会覆盖之前选择的图片
                     *
                     *  @ 时间： 2018/6/18 13:48
                     */


                    paths=new String[4];
                    uris=new ArrayList<> ();
                    picture_utils pictureUtils = new picture_utils (Dynamic_test_Activity.this , REQUEST_CODE_CHOOSE);
                    break;
//                case R.id.Dynamic_bt2:
//                    break;
                case R.id.Dynamic_bt4:
                    finish();
                    break;
                case R.id.Dynamic_bt3:
                    final String s = DynamicEd1.getText ().toString ();
                    if (s.equals ("")){
                        Toast.makeText (Dynamic_test_Activity.this, "标题内容不能为空！", Toast.LENGTH_SHORT).show ();
                        return;
                    }
                    up_load_Dynamic up=new up_load_Dynamic (getApplication (),s,paths) {
                        @Override
                        public void Error() {

                            Log.i (TAG, "Error: ");
                            runOnUiThread (new Runnable () {
                                @Override
                                public void run() {
                                    Toast.makeText (Dynamic_test_Activity.this, "上传失败！", Toast.LENGTH_SHORT).show ();
                                }
                            });
                        }

                        @Override
                        public void Success(File result) {
                            Log.i (TAG, "Success: ");
                            runOnUiThread (new Runnable () {
                                @Override
                                public void run() {
                                    Toast.makeText (Dynamic_test_Activity.this, "发布成功！", Toast.LENGTH_SHORT).show ();
                                }
                            });

                        }

                        @Override
                        public void onLoading(long total, long current, boolean isDownloading) {
                            Log.i (TAG, "onLoading: "+total+"======"+current);
                            final long s=current;
                            runOnUiThread (new Runnable () {
                                @Override
                                public void run() {
                                    Dynamic_tv1.setText ("上传进度"+s);
                                }
                            });

                        }

                        @Override
                        public void Error_data() {
                            Log.i (TAG, "Error_data: ");
                            runOnUiThread (new Runnable () {
                                @Override
                                public void run() {
                                    Toast.makeText (Dynamic_test_Activity.this, "参数错误！", Toast.LENGTH_SHORT).show ();
                                }
                            });


                        }
                    };
                    break;
            }
        } else {
            Toast.makeText (Dynamic_test_Activity.this, "您拒绝了相关权限！", Toast.LENGTH_SHORT).show ();
        }


    }
/**
 *  @ 作用： 获取照片后的回掉
 *
 *  @ 时间： 2018/6/18 1:08
 */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            Log.i (TAG, "onActivityResult: 返回成功！");
            uris = Matisse.obtainResult (data);
            if (uris.size ()>4){
                Toast.makeText (Dynamic_test_Activity.this, "图片数量不能超过4张！", Toast.LENGTH_SHORT).show ();

                return;
            }
            for (int a = 0; a < uris.size (); a++) {
                Log.i (TAG, "onActivityResult: " + uris.get (a));
                Log.i (TAG, "onActivityResult: " + FileUtils.getFilePath (getApplication (), uris.get (a)));
                paths[a]=FileUtils.getFilePath (getApplication (), uris.get (a));
            }
            if (uris.get (0)!=null){
                DynamicImage1.setImageURI (uris.get (0));
            }
            if (uris.size ()==2)
            if (uris.get (1)!=null){
                DynamicImage2.setImageURI (uris.get (1));
            }
//            if (uris.size ()==3)
//            if (uris.get (2)!=null){
//                DynamicImage3.setImageURI (uris.get (2));
//            }
//            if (uris.size ()==4)
//            if (uris.get (3)!=null){
//                DynamicImage4.setImageURI (uris.get (3));
//            }

        }
    }
}
