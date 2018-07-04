package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.GsonVideou;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Name;
import com.bumptech.glide.Glide;

import org.xutils.common.Callback;

import java.util.HashMap;

import static com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url.getGet_picture;

public class RecyclerViewAdper1 extends RecyclerView.Adapter<RecyclerViewAdper1.MyViewHodler> {
    private Context context;
    private GsonVideou[] gsonVideous;
    private Name[] userurl;
    private TextView textVideo;
    private ImageView imageVideo2;
    private ImageView imageVideo1;
    private Bitmap[] bitmaps;
    private ImageView videoView;
    //private Thread thread;
    private Handler handler=new Handler (){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage (msg);


        }
    };





    public RecyclerViewAdper1(Context context, final GsonVideou[] gsonVideous, Name[] userurl){
        this.context=context;
        this.gsonVideous=gsonVideous;
        this.userurl=userurl;
        this.bitmaps=bitmaps;
//        thread= new Thread (){
//            @Override
//            public void run() {
//                for (int a =0;a<gsonVideous.length;a++){
//                    bitmaps[a]=createVideoThumbnail (gsonVideous[a].getPath (),100,200);
//                }
//            }
//        };
    }
    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= View.inflate (context, R.layout.videolayout,null);
        return new MyViewHodler (view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, final int position) {

        Glide.with(context).load(gsonVideous[position].getPath ()).into(videoView);













    }

    @Override
    public int getItemCount() {
        return userurl.length;
    }

    class MyViewHodler extends RecyclerView.ViewHolder{
        public MyViewHodler(View itemView) {
            super (itemView);

            //textVideo = (TextView) itemView.findViewById(R.id.text_video);
           // imageVideo2 = (ImageView)itemView. findViewById(R.id.image_video2);
           // imageVideo1 = (ImageView)itemView. findViewById(R.id.image_video1);
            videoView=(ImageView) itemView.findViewById (R.id.v_video1);
        }
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


    /**
     * 给出url，获取视频的第一帧
     *
     * @param url
     * @return
     */
    public static Bitmap getVideoThumbnail(String url) {
        Bitmap bitmap = null;
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(url, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }


}
