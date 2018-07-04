package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test.Buddy_dynamics_Activity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test.Dynamic_test_Activity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test.IntegralmallActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test.shangchengActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.CommonActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.CyclActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.MountActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.RunrmActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.WalkActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseFragment;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.GsonVideou;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Name;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.RecyclerViewAdper1;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.RecyclerViewAdpter;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.getUserurl;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.msg;

import com.google.gson.Gson;
import com.qiniu.pili.droid.shortvideo.demo.activity.VideoRecordActivity;
import com.qiniu.pili.droid.shortvideo.demo.utils.PermissionChecker;
import com.qiniu.pili.droid.shortvideo.demo.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import org.lasque.tusdk.core.TuSdk;
import org.lasque.tusdk.core.seles.tusdk.FilterManager;

import java.io.IOException;
import java.util.HashMap;

import static com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url.getGetVideo;

/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：21:45 2018/6/13
 *
 * @说明：CommunityFragment   社区 Fragment
 */


public class CommunityFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_dynamic,upload_vedio,Cancel_02;
    private ImageView tou_p,yd_01,yd_02,yd_03,yd_04;
    private RelativeLayout rl_match,rl_shop,rl_school,rl_consultation;
    private ImageView iv_button;
    private RecyclerView recyclerView;
    private RecyclerViewAdper1 adpter;
    private VideoView videoView;
    private GsonVideou[] gsonVideous1;

    private Handler handler=new Handler (){
        @Override
        public void handleMessage(Message msg) {


            switch (msg.what){
                case 1:
                    gsonVideous1= (GsonVideou[]) msg.obj;
                    videoView.setVideoPath (gsonVideous1[0].getPath ());
                    videoView.pause ();
                    videoView.start ();
                    break;
                case 2:
                    //userurl1= (Name[]) msg.obj;
                    break;
                case 3:
                    Log.i ("数据加载完成", "handleMessage: ");

                    //recyclerView.setAdapter (adpter);
                   // recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()));


                    break;
            }
        }
    };


    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View communityview = inflater.inflate(R.layout.community_fragment,container,false);
        initView (communityview);
        initGlide();
        initData ();
        return communityview;
    }
/**
 *  @ 作用： 初始化控件
 *
 *  @ 时间： 2018/6/13 21:46
 */
    private ImageView imageView1;
    private ImageView imageView2;

    private void initView(View view) {
        videoView=view.findViewById (R.id.cf_video);

       // recyclerView=view.findViewById (R.id.radio1);

        tou_p=view.findViewById(R.id.iv_top_t);
        yd_01=view.findViewById(R.id.yd_01);
        yd_02=view.findViewById(R.id.yd_02);
        yd_03=view.findViewById(R.id.yd_03);
        yd_04=view.findViewById(R.id.yd_04);
        rl_match=view.findViewById(R.id.rl_cf_iv1);
        rl_shop=view.findViewById(R.id.rl_cf_iv2);
        rl_school=view.findViewById(R.id.rl_cf_iv3);
        rl_consultation=view.findViewById(R.id.rl_cf_iv4);
        iv_button=view.findViewById(R.id.cf_button_iv);
        imageView1=view.findViewById (R.id.image_1_);
        imageView2=view.findViewById (R.id.image_2_);
        tou_p.setOnClickListener(this);
        yd_01.setOnClickListener(this);
        yd_02.setOnClickListener(this);
        yd_03.setOnClickListener(this);
        yd_04.setOnClickListener(this);
        rl_match.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_school.setOnClickListener(this);
        rl_consultation.setOnClickListener(this);
        iv_button.setOnClickListener(this);
        imageView1.setOnClickListener (this);
        imageView2.setOnClickListener (this);

    }

    /**
 *  @ 作用： 初始化图片加载框架
 *
 *  @ 时间： 2018/6/13 17:22
 */


    private void initGlide() {
        //Glide.with (getContext ());
        //Glide.with(this).load(url.getCommunityFragment_top_iv_url ()).override (800,300).into(Cf_Top_iv);






    }

    @Override
    protected void initData() {
       final Okhttp_utils okhttp_utils=new Okhttp_utils ();
        okhttp_utils.seturl (getGetVideo()+"?page=0");
        okhttp_utils.setMode ("get");

        new Thread (){
            @Override
            public void run() {
                try {
                    final String doing = okhttp_utils.doing ();
                    if (doing.equals ("[]")){
                        ic=-1;
                        return;
                    }
                    Gson gson=new Gson ();
                    final GsonVideou[] gsonVideous = gson.fromJson (doing, GsonVideou[].class);


                    handler.sendMessage (msg.getmsg (1,gsonVideous));
//                    getUserurl getUserurl=new getUserurl ();
//                    final Name[] userurl = getUserurl.getUserurl (gsonVideous, 4);
//                    Log.i ("name的长度", "run: "+userurl.length);


                  //  Bitmap[] bitmaps=new Bitmap[4];
//                    for (int a=0;a<userurl.length;a++){
//                        ///bitmaps[a]=createVideoThumbnail(gsonVideous[a].getPath (),200,200);
//                    }

//                    adpter=new RecyclerViewAdper1 (getContext (),gsonVideous,userurl);
//                    handler.sendMessage (msg.getmsg (2,userurl));
//
//                    handler.sendMessage (msg.getmsg (3,null));


                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }.start ();




    }



/**
 *  @ 作用： 点击事件
 *
 *  @ 时间： 2018/6/13 21:45
 */


private int ic=0;

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.iv_top_t:
                break;
            case R.id.yd_01:
                startActivity (new Intent (getContext (), RunrmActivity.class));
                break;
            case R.id.yd_02:
                startActivity (new Intent (getContext (), MountActivity.class));
                break;
            case R.id.yd_03:
                startActivity (new Intent (getContext (), WalkActivity.class));
                break;
            case R.id.yd_04:
                startActivity (new Intent (getContext (), CyclActivity.class));
                break;
            case R.id.rl_cf_iv1:
                startActivity (new Intent (getContext (),IntegralmallActivity.class));
                break;
            case R.id.rl_cf_iv2:
                startActivity (new Intent (getContext (), shangchengActivity.class));
                break;
            case R.id.rl_cf_iv3:
                startActivity (new Intent (getContext (), Buddy_dynamics_Activity.class));
                break;
            case R.id.rl_cf_iv4:
                startActivity (new Intent (getContext (), CommonActivity.class));
                break;
            case R.id.cf_button_iv:
                LayoutInflater inflater1 = getLayoutInflater ();
                View dialog02 = inflater1.inflate (R.layout.dialog_dynamic,null);
                tv_dynamic=dialog02.findViewById(R.id.tv_dynamic);
                upload_vedio=dialog02.findViewById(R.id.upload_vedio);
                Cancel_02=dialog02.findViewById(R.id.Cancel_02);
                final Dialog dialog_dynamic = new Dialog(getContext(), R.style.transparentFrameWindowStyle);
                dialog_dynamic.setContentView(dialog02,new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                Window window = dialog_dynamic.getWindow();
                window.setWindowAnimations(R.style.anim_style);
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.x = 0;
                layoutParams.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                dialog_dynamic.onWindowAttributesChanged(layoutParams);
                dialog_dynamic.setCanceledOnTouchOutside(true);
                dialog_dynamic.findViewById(R.id.tv_dynamic).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getContext(), Dynamic_test_Activity.class));
//                        Toast.makeText(getContext(),"发布动态",Toast.LENGTH_SHORT).show();
                        dialog_dynamic.dismiss();
                    }
                });
                dialog_dynamic.findViewById(R.id.upload_vedio).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TuSdk.checkFilterManager(mFilterManagerDelegate);
                        if (isPermissionOK()) {
                            startVideoRecordActivity();
                        }
                       // startActivity (new Intent (getContext (), VideoRecordActivity.class));
                        Toast.makeText(getContext(),"上传小视频",Toast.LENGTH_SHORT).show();
                        dialog_dynamic.dismiss();
                    }
                });
                dialog_dynamic.findViewById(R.id.Cancel_02).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_dynamic.dismiss();
                    }
                });
                dialog_dynamic.show();

                break;
            case R.id.image_2_:
                if (0<=ic&&ic<5){
                    ic++;
                    Toast.makeText (getContext (),"加载中，请勿操作",Toast.LENGTH_SHORT).show ();
                    videoView.setVideoPath (gsonVideous1[ic].getPath ());
                    videoView.pause ();
                    videoView.start ();
                }else if (ic==5){
                    Toast.makeText (getContext (),"没有内容了",Toast.LENGTH_SHORT).show ();
                }
                break;
            case R.id.image_1_:
                if (0<ic&&ic<5){
                    ic--;
                    Toast.makeText (getContext (),"加载中，请勿操作",Toast.LENGTH_SHORT).show ();
                    videoView.setVideoPath (gsonVideous1[ic].getPath ());
                    videoView.pause ();
                    videoView.start ();
                }else if (ic==0){
                    Toast.makeText (getContext (),"没有内容了",Toast.LENGTH_SHORT).show ();
                }
                break;
        }

    }
    /**
     *  @ 作用： 启动视频拍摄
     *
     *  @ 时间： 2018/6/20 10:14
     */



    private void startVideoRecordActivity() {
        Intent intent = new Intent(getContext (), VideoRecordActivity.class);
        intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_RATIO,0);
        intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_LEVEL, 3);
        intent.putExtra(VideoRecordActivity.ENCODING_MODE,0);
        intent.putExtra(VideoRecordActivity.ENCODING_SIZE_LEVEL,7);
        intent.putExtra(VideoRecordActivity.ENCODING_BITRATE_LEVEL,2);
        intent.putExtra(VideoRecordActivity.AUDIO_CHANNEL_NUM,0);
        startActivity(intent);
      //  startActivity (new Intent (getContext (), com.qiniu.pili.droid.shortvideo.demo.activity.MainActivity.class));


    }

   /**
    *  @ 作用： SDK 初始化滤镜管理器委托
    *
    *  @ 时间： 2018/6/20 10:14
    */


    private FilterManager.FilterManagerDelegate mFilterManagerDelegate = new FilterManager.FilterManagerDelegate() {
        @Override
        public void onFilterManagerInited(FilterManager manager) {
            Log.i("123123", "TuSDK initialized!");
        }
    };

    private boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(getActivity ());
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
            ToastUtils.s(getContext (), "Some permissions is not approved !!!");
        }
        return isPermissionOK;
    }


    /**
     *  @ 作用： 获取视频的第一帧
     *
     *  @ 时间： 2018/6/21 12:13
     */


    private Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String> ());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

    public void Onstart(View view){
        if (0<=ic&&ic<5){
            switch (view.getId ()){
                case R.id.image_1_:

                    break;
                case R.id.image_2_:
                    ic--;
                    videoView.setVideoPath (gsonVideous1[ic].getPath ());
                    videoView.pause ();
                    videoView.start ();
                    break;
            }
        }else if (ic==5){

        }

    }
}
