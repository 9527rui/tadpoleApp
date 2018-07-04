package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Dyunamic;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Name;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url.getGet_picture;

/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：22:16 2018/6/18
 *
 * @说明：RecyclerViewAdpter  RecyclerView的适配器
 */

public class RecyclerViewAdpter extends RecyclerView.Adapter<RecyclerViewAdpter.MyViewHodler> {
    private Context context;
    private  List<Dyunamic> list;
    private Name[] userurl;
    final List<Dyunamic> list1;


    public void setData( List<Dyunamic> list,Name[] userurl){
        int a=this.list.size ()-4;
        this.list=list;
        this.userurl=userurl;
        int b=this.list.size ()+1;
        Log.i ("ADD", "setData:a= "+a+"b="+b);

        for (int y=a;y<b;y++){
            Log.i ("", "setData: a");
            notifyItemChanged (y);
        }




    }

    public RecyclerViewAdpter(Context context,List<Dyunamic> list, Name[] userurl){
//        this.list=new ArrayList<> ();
        this.context=context;
        this.list=list;
        list1=list;
        this.userurl=userurl;
//        new Thread (){
//            @Override
//            public void run() {
//                getUserurl getU=new getUserurl ();
//                userurl = getU.getUserurl (list1);
//
//            }
//        }.start ();







    }


    /**
     *  @ 作用： getView 方法
     *
     *  @ 时间： 2018/6/18 22:13
     */


    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate (context, R.layout.dynamic_recycler,null);
        return new MyViewHodler (view);
    }

    /**
     *  @ 作用： 数据绑定
     *
     *  @ 时间： 2018/6/18 22:14
     */


    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {

        Glide.with(context).load(getGet_picture()+"?id="+list.get (position).getUser ()).into(holder.imageTouxiang);
        holder.tvId.setText (userurl[position].getName ());
        holder.edName.setText (list.get (position).getContent ());
        holder.edData.setText (list.get (position).getTime ());
        Glide.with (context).load (list.get (position).getImg1 ()).into (holder.image1);
        Glide.with (context).load (list.get (position).getImg2 ()).into (holder.image2);
//        Glide.with (context).load (list.get (position).getImg3 ()).into (holder.image3);
//        Glide.with (context).load (list.get (position).getImg4 ()).into (holder.image4);




    }


    /**
     *  @ 作用： 返回总条数
     *
     *  @ 时间： 2018/6/18 22:13
     */


    @Override
    public int getItemCount() {
        return list.size ();
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        private ImageView imageTouxiang;
        private TextView tvId;
        private TextView edData;
        private TextView edName;
        private ImageView image1;
        private ImageView image2;
//        private ImageView image3;
//        private ImageView image4;




        public MyViewHodler(View itemView) {
            super (itemView);

            imageTouxiang = (ImageView)itemView. findViewById(R.id.image_touxiang);
            tvId = (TextView)itemView. findViewById(R.id.tv_id);
            edData = (TextView)itemView. findViewById(R.id.ed_data);
            edName = (TextView) itemView.findViewById(R.id.tv_text);
            image1 = (ImageView)itemView. findViewById(R.id.image1);
            image2 = (ImageView) itemView.findViewById(R.id.image2);
//            image3 = (ImageView)itemView. findViewById(R.id.image3);
//            image4 = (ImageView)itemView. findViewById(R.id.image4);
        }
    }

}
