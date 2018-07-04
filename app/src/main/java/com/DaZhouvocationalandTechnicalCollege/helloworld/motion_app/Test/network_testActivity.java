package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.FileUtils;

import com.example.media_module.utils.picture_utils;
import com.lwkandroid.rtpermission.RTPermission;
import com.lwkandroid.rtpermission.listener.OnPermissionResultListener;
import com.zhihu.matisse.Matisse;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * @作者：yangshijie
 *
 * @QQ: 102245912
 *
 * @时间：20:33 2018/6/11
 *
 * @说明：network_testActivity
 */

public class network_testActivity extends BaseActivity implements OnPermissionResultListener {
    private static final String TAG ="   " ;
    public OkHttpClient client;
    private ImageView image;
    private List<Uri> uris;
    private int REQUEST_CODE_CHOOSE=1;
    private LinearLayout layout;



    @Override
    public int getXml() {
        return R.layout.activity_network_test;
    }

    @Override
    public void initView() {
        x.Ext.init(getApplication ());

        client=new OkHttpClient();
        image=(ImageView)findViewById(R.id.image);
        uris=new ArrayList<>();
        layout=(LinearLayout)findViewById(R.id.line1);


        new Thread(){
            @Override
            public void run() {
                String sdcardFolder = getExternalFilesDir(null).getAbsolutePath();
                File sdcardLicenceFile = new File(sdcardFolder + File.separator + "timg.jpg");
                if(sdcardLicenceFile.exists()){
                    return;
                }
                try {
                    FileUtils.copyFromAssetToSdcard(network_testActivity.this, "timg.jpg", sdcardFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    @Override
    public void initData() {

    }
    public void onClick(View view){

//        File file=new File(this.getFilesDir().getPath()+"/timg.jpg");
//        if (file!=null){
////            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
//////
//////            image.setImageBitmap(bitmap);
//            Bitmap bitmap = BitmapFactory.decodeFile(getExternalFilesDir("").getAbsolutePath() + "/timg.jpg");
//            image.setImageBitmap(bitmap);
//
//
//
//        }

        switch (view.getId()){
            case R.id.bt_1:


                break;
            case R.id.bt_2:
                new RTPermission.Builder()
                        .permissions( Manifest.permission.CAMERA,
                                // Manifest.permission.LOCATION_HARDWARE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.BODY_SENSORS,
                                Manifest.permission.READ_EXTERNAL_STORAGE



                        )
                        .start(this,this);
                break;
            case R.id.bt_3:
//                OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .addInterceptor(new LoggerInterceptor("TAG"))
//                        .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                        .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                        //其他配置
//                        .build();
//                OkHttpUtils.initClient(okHttpClient);
//                File file=new File(String.valueOf(uris.get(0)));
//                OkHttpUtils.postFile()
//                        .file(file)
//                        .url(url.getBitmap_url())
//                        .build()
//                        .execute(new Callback() {
//                            @Override
//                            public Object parseNetworkResponse(Response response, int id) throws Exception {
//                                Log.i(TAG, "parseNetworkResponse: ");
//                                return null;
//                            }
//
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//                                Log.i(TAG, "onError: ");
//
//                            }
//
//                            @Override
//                            public void onResponse(Object response, int id) {
//                                Log.i(TAG, "onResponse: ");
//
//                            }
//                        });

                RequestParams params=new RequestParams (url.getBitmap_url ());
                params.setMultipart (true);
                params.addBodyParameter ("file",new File (FileUtils.getFilePath (getApplication (),uris.get (0))),null);
                x.http ().post (params, new org.xutils.common.Callback.ProgressCallback<File> () {
                  

                    @Override
                    public void onSuccess(File result) {
                        Log.i (TAG, "onSuccess: ");
                        //上传成功回掉
                        
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i (TAG, "onError: ");
                        //出错回掉


                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.i (TAG, "onCancelled: ");

                    }

                    @Override
                    public void onFinished() {
                        Log.i (TAG, "onFinished: ");

                    }

                    @Override
                    public void onWaiting() {
                        Log.i (TAG, "onWaiting: ");
                        
                    }

                    @Override
                    public void onStarted() {
                        Log.i (TAG, "onStarted: ");

                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        //上传进度

                        Log.i (TAG, "onLoading: "+current);
                    }
                });
                
                break;

        }






//startActivity(new Intent(this, MainActivity.class));






    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: ");

        if (requestCode==REQUEST_CODE_CHOOSE&&resultCode==RESULT_OK){
            Log.i(TAG, "onActivityResult: 返回成功！");
            uris= Matisse.obtainResult(data);
            for (int a =0;a<uris.size();a++){
                Log.i(TAG, "onActivityResult: "+uris.get(a));
                ImageView imageView=new ImageView(network_testActivity.this);
                imageView.setImageURI(uris.get(a));
                imageView.setMaxHeight(200);
                imageView.setMaxWidth(200);
                layout.addView(imageView);
                Log.i (TAG, "onActivityResult: "+FileUtils.getFilePath (getApplication (),uris.get (0)));








            }
        }
    }

    @Override
    public void onAllGranted(String[] allPermissions) {
        Log.i("权限回调", "onAllGranted: ");

        picture_utils pictureUtils=new picture_utils(this,REQUEST_CODE_CHOOSE);
    }

    @Override
    public void onDeined(String[] dinedPermissions) {
        Log.i("权限回调", "onDeined: ");

    }
}
