package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.RecordlsActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.NewsContent;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseFragment;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.service.pay_service;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.Okhttp;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.msg;
import com.example.custom_view.MyScrollView;
import com.example.custom_view.StatisticsArcView;
import com.example.custom_view.StepArcView;
import com.example.maps_module.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends BaseFragment implements MyScrollView.OnScrollListener, View.OnClickListener {
    private ListView list_tj;
    private String data;
    private List<Newsdata> newsdatalist1 = new ArrayList<Newsdata>();
    private Newsdata newsdata;
    private List<Bitmap> bitmapList1 = new ArrayList<>();
    private static final String TAG = "在ACTIVITY中的步数";
    private pay_service.mybinder binder;
    private Intent intent;
    private StepArcView cc;
    private StatisticsArcView dd;
    private LinearLayout lnyc,lldraw,cklsjl;
    private TextView tdqb,tdql,tdqr,slc;
    private int BUYAOJIAZAI=0;
    private MyScrollView sv2;
    private int step_number;
    private int a;
    private int a1;
    private String[] data01;
    private  float datazh;
    private SharedPreferences sp;
    private Button begin;
    private  Thread thread=null;
    private int number=10000;
    private int num;
    private String num1;
    private boolean isruning=true;
    private  final pay_service.mybinder mybinder=new pay_service.mybinder();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    a= (int) msg.obj;
                    cc.setCurrentCount(number,a);
                    dd.setCurrentCountls(data01);
                    slc.setText(datazh*0.7*0.0001+"");
                    tdqb.setText(a+"");
                    BigDecimal b=new BigDecimal(a*0.7/1000+"");
                    tdql.setText(b.setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    tdqr.setText(a/35+"");
                    break;
                case 2:
                    a1= (int) msg.obj;
                    cc.setCurrentCount(number,a1);
                    dd.setCurrentCountls(data01);
                    slc.setText(datazh*0.7*0.0001+"");
                    tdqb.setText(a1+"");
                    tdql.setText(a1*0.7/1000+"");
                    tdqr.setText(a1/35);
                    break;
                    case 3:
                        initImeger();
                        break;
                case 4:
                    initListview();
                    break;

            }
        }
    };
    private void initListview() {
        BaseAdapter adapter = new myAdapter1(getContext(),newsdatalist1,bitmapList1);
        list_tj.setAdapter(adapter);
        list_tj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("url",newsdatalist1.get(position).getT1348649079062().get(position).getUrl());
                intent.setClass(getContext(),NewsContent.class);
                startActivity(intent);
            }
        });
    }

    private void initImeger() {
        new Thread() {
            @Override
            public void run() {
                Okhttp okhttp = new Okhttp();
                for (int a = 0; a < 5; a++) {
                    Bitmap bitmap = okhttp.getBitmap(newsdatalist1.get(a).getT1348649079062().get(a).getImgsrc());
                    bitmapList1.add(bitmap);
                }
                handler.sendEmptyMessageDelayed(4, 0);
            }
        }.start();
    }
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder=(pay_service.mybinder)service;
            Log.i(TAG, "onServiceConnected: 链接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceConnected: 链接断开");
        }
    };

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeview = inflater.inflate(R.layout.home_fragment,container,false);
        lnyc=homeview.findViewById(R.id.lnyc);
        lldraw=homeview.findViewById(R.id.lldraw);
        begin=homeview.findViewById (R.id.begin);
        begin.setOnClickListener (this);
        tdqb=homeview.findViewById(R.id.tdqb);
        tdql=homeview.findViewById(R.id.tdql);
        tdqr=homeview.findViewById(R.id.tdqr);
        slc=homeview.findViewById(R.id.slc);
        sv2=homeview.findViewById(R.id.scrollView2);
        sv2.setOnScrollListener(this);
        dd=homeview.findViewById(R.id.dd);
        intent=new Intent(getContext(),pay_service.class);
        sp = getActivity().getSharedPreferences("mbbs", 0);
        list_tj = homeview.findViewById(R.id.list_kedou);
        cklsjl=homeview.findViewById(R.id.cklsjl);
        cklsjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RecordlsActivity.class));
            }
        });
        initview();

        if (BUYAOJIAZAI==0){
            initnetwork();
        }



       // startService(intent);
       // bindService(intent,serviceConnection , Service.BIND_AUTO_CREATE);
        // int s = binder.getStep_number();
        // Log.i(TAG, "initView: "+s);


        cc =homeview.findViewById(R.id.cc);
//        num= user.getNum();
//        if (num!=0){
//            SharedPreferences.Editor edit = sp.edit();
//            edit.putInt("number",num);
//            edit.commit();
//        }else {
//            num=sp.getInt("number",10000);
//        }
        num1=sp.getString("number","10000");
        number= Integer.parseInt(num1);

        // Log.i(TAG, "initView: "+mybinder.getStep_number());

      //  Log.i("线程为空！", "initView: "+thread);


       // initThread();

        return homeview;
    }

    private void initview() {
        new Thread(){
            @Override
            public void run() {
                Okhttp okhttp = new Okhttp();
                data = okhttp.getSting("http://c.3g.163.com/nc/article/list/T1348649079062/0-20.html");
                newsdata = new Newsdata();
                removeBOM(data);
                try {
                    JSONObject object = new JSONObject(data);
                    JSONArray t1348649079062 = object.optJSONArray("T1348649079062");
                    List<Newsdata.T1348649079062Bean>T1348649079062Bean=new ArrayList<>();
                    newsdata.setT1348649079062(T1348649079062Bean);
                    for (int i=0;i<t1348649079062.length();i++){
                        JSONObject object1 = t1348649079062.optJSONObject(i);
                        if (object1 != null) {
                            String title = object1.optString("title");
                            Log.i(TAG, "onResponse: " + title);
                            String imgsrc = object1.optString("imgsrc");
                            String digest = object1.optString("digest");
                            String ptime = object1.optString("ptime");
                            String url = object1.optString("url");
                            Newsdata.T1348649079062Bean bean = new Newsdata.T1348649079062Bean();
                            bean.setTitle(title);
                            bean.setImgsrc(imgsrc);
                            bean.setDigest(digest);
                            bean.setPtime(ptime);
                            bean.setUrl(url);
                            T1348649079062Bean.add(bean);
                            newsdatalist1.add(newsdata);
                            System.out.println("解析成功");
                        }
                    }

                    handler.sendEmptyMessageDelayed(3, 0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }

    /**
 *  @ 作用： 过去7天步数
 *
 *  @ 时间： 2018/6/23 13:24
 */


    private void initnetwork() {
        data01=new String[7];
        final String[] user_motion_record = user.getUser_Motion_record ();
        if (user_motion_record==null){
            return;
        }else {
            for (int a =0;a<user_motion_record.length;a++){
                if (user_motion_record[a]!=null){
                    data01[a]=user_motion_record[a];
                }else {
                    data01[a]="1";
                }
              //  String s=datazh+"";

                datazh= Float.parseFloat(datazh+data01[a]);
                Log.i("00000000000000", "initnetwork: "+datazh+data);
            }
        }

//        BigDecimal f=new BigDecimal(datazh*0.7*0.0001);

    }


    private void initThread() {
        if (thread==null){

            thread=new Thread(){
                @Override
                public void run() {
                    while (isruning){
                        try {
                            Thread.sleep(1000);
                            step_number = mybinder.getStep_number();
                            handler.sendMessage(msg.getmsg(1,step_number));
                            Log.i(TAG, "initView: "+step_number);
                        } catch (InterruptedException e) {

                        }
                    }

                }
            };
        }


        thread.start();
    }


    @Override
    protected void initData() {
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    //  cc.setCurrentCount(100,mybinder.getStep_number());

                    handler.sendMessage(msg.getmsg(2,mybinder.getStep_number()));
                    Log.i(TAG, "initData: "+mybinder.getStep_number());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    @Override
    public void onDestroy() {
        isruning=false;
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
        isruning=false;
        BUYAOJIAZAI=1;
    }

    @Override
    public void onStart() {
        //initThread();
        super.onStart();
    }
    @Override
    public void onScroll(int scrollY) {
        float alpha = 0;
        lnyc.setVisibility(View.VISIBLE);
        lnyc.setAlpha(0);
        if (scrollY >= 800) {
            alpha=1;
//            lnyc.setVisibility(View.VISIBLE);
//            Log.i("1111111111111111", "onScroll: " + scrollY);
        } else {
            alpha=scrollY/(800*1.0f);
//            lnyc.setVisibility(View.GONE);
//            Log.i("22222222222222222", "onScroll: " + scrollY);
        }
        lnyc.setAlpha(alpha);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: ");
        isruning=true;
        initThread();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        startActivity (new Intent (getActivity (), MainActivity.class));

    }
    public static final String removeBOM(String json) {

        if (TextUtils.isEmpty(json)) {

            return json;

        }
        if (json.startsWith("\ufeff")) {

            return json.substring(1);

        } else {

            return json;

        }
    }



}
