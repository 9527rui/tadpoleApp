package com.example.maps_module;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.example.ai_speech.Voice_service;
import com.example.maps_module.utils.LongClickUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class MainActivity extends AppCompatActivity implements LocationSource, AMapLocationListener, View.OnClickListener {
    private AMapLocation aMapLocation;
    private LocationManager locationManager;
    private Location location;
    private int frequency=0;
    private float getdistance=0;
    private float distance=0;
    private float speed;
    private double calorie;
    private boolean isRuning = true;
    private MapView mMapView = null;
    private AMap aMap = null;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private TextView tv_Latitude_and_longitude;
    private List<LatLng> latLngs;
    private Polyline polyline;
    private ImageView mIvFirsthwyd;
    private TextView mTvFirsthwyd;
    private ImageView mIvFourqxyd;
    private TextView mTvFourqxyd;
    private TextView speed2,distance2,calorie2;
    private Progress_start startyd;
    private Progress_stop resetyd;
    private TextView hour;
    private TextView mint;
    private TextView sec;
    private TextView paobumb;
    private RelativeLayout mRlFirstLayout;
    private RelativeLayout mRlFourLayout;
    private ConstraintLayout onelayout;
    private ConstraintLayout towlayout;
    private ConstraintLayout thirdlayout;
    private TimeDownView timeDownView;
    private StepLineView ee;
    private TextView zt;
    private EditText mbgl;
    private float mbgl03=5;
    private String mbgl02;
    private TextView sz_sportmb;
    private int i;
    private long timeusedinsec;
    private boolean isstop = false;
    private Handler mHandler = new Handler() {
        /*
         * edit by yuanjingchao 2014-08-04 19:10
         */
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    // 添加更新ui的代码
                    if (!isstop) {
                        updateView();
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
                case 0:
//                    ee.setCurrentCountpb(mbgl03, distance / 1000);
//                    Log.i("00000000000000", "run: " + mbgl03 + distance);
//                    mHandler.sendEmptyMessageDelayed(0, 100);
                    break;
            }
        }

    };

    private void updateView() {
        timeusedinsec += 1;
        int hours= (int) ((timeusedinsec / 60)/60)%60;
        int minute = (int) (timeusedinsec / 60)%60;
        int second = (int) (timeusedinsec % 60);
        if (hours < 10)
            hour.setText("0" + hours);
        else
            hour.setText("" + hours);
        if (minute < 10)
            mint.setText("0" + minute);
        else
            mint.setText("" + minute);
        if (second < 10)
            sec.setText("0" + second);
        else
            sec.setText("" + second);
        BigDecimal b=new BigDecimal(speed+"");
        speed2.setText(b.setScale(2,BigDecimal.ROUND_HALF_UP)+"");
        BigDecimal c=new BigDecimal(distance/1000+"");
        distance2.setText(c.setScale(2,BigDecimal.ROUND_HALF_UP)+"");
        calorie=60*(distance/1000)*1.036;
        BigDecimal d=new BigDecimal(calorie+"");
        calorie2.setText(d.setScale(0,BigDecimal.ROUND_HALF_UP)+"");
        ee.setCurrentCountpb(mbgl03, distance / 1000);
        Log.i("00000000000000", "run: " + mbgl03 + distance/1000/8);
    }


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.map_activity_main);
        initView ();
        initViewb();
        Log.i ("-------", "onCreate: ");
        //GPS传感器
        locationManager = (LocationManager) this.getSystemService (Context.LOCATION_SERVICE);
       /* if (ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
       }*/

        //初始化经纬度
        initLocation ();
        //
        // aMapLocation=new AMapLocation ();
        //获取地图控件引用
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate (savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap ();
        }
        MyLocationStyle myLocationStyle;
//        final AssetManager assets = getAssets ();
//        InputStream open=null;
//        try {
//              open = assets.open ("log.png");
//        } catch (IOException e) {
//            e.printStackTrace ();
//        }
//        final Bitmap bitmap = BitmapFactory.decodeStream (open);
//
//
//        BitmapDescriptor myLocationIcon=new BitmapDescriptor ();
        myLocationStyle = new MyLocationStyle ();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval (2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle (myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled (true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        // 设置定位监听



        aMap.setLocationSource (this);
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled (true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType (AMap.LOCATION_TYPE_LOCATE);
        mUiSettings = aMap.getUiSettings ();//实例化UiSettings类对象
        mUiSettings.setScaleControlsEnabled (true);//控制比例尺控件是否显示
        for (int a = 0; a < 7; a++) {
            //缩放地图
            aMap.moveCamera (CameraUpdateFactory.zoomIn ());
        }


    }

    private void initViewb() {
        mIvFirsthwyd=findViewById(R.id.iv_first_hwyd);
        mTvFirsthwyd=findViewById(R.id.tv_first_hwyd);
        mIvFourqxyd=findViewById(R.id.iv_four_qxyd);
        mTvFourqxyd=findViewById(R.id.tv_four_qxyd);
        mRlFirstLayout=findViewById(R.id.rl_first_layout);
        mRlFourLayout=findViewById(R.id.rl_four_layout);
        startyd=findViewById(R.id.start_yd);
        resetyd=findViewById(R.id.reset_yd);
        resetyd.setOnClickListener(this);
        startyd.setOnClickListener(this);
        hour = (TextView) findViewById(R.id.hour);
        mint = (TextView) findViewById(R.id.mint);
        sec = (TextView) findViewById(R.id.sec);
        paobumb=findViewById(R.id.pbmb);
        paobumb.setOnClickListener(this);
        mRlFirstLayout.setOnClickListener(this);
        mRlFourLayout.setOnClickListener(this);
        mRlFirstLayout.setSelected(true);
        onelayout=findViewById(R.id.one_layout);
        towlayout=findViewById(R.id.tow_layout);
        thirdlayout=findViewById(R.id.third_layout);
        zt=findViewById(R.id.zt_yd);
        ee=findViewById(R.id.ee);
        speed2=findViewById(R.id.speed);
        distance2=findViewById(R.id.distance);
        calorie2=findViewById(R.id.calorie);
        sz_sportmb=findViewById(R.id.sz_sportmb);
    }

    private void initView() {
        //polyline= new Polyline ();
        latLngs = new ArrayList<LatLng> ();
        mMapView = findViewById (R.id.map);

    }

    @SuppressLint("MissingPermission")
    private void initLocation() {
        location = locationManager.getLastKnownLocation (LocationManager.GPS_PROVIDER);
        //GPS可能为空
        if (location == null) {
            location = locationManager.getLastKnownLocation (LocationManager.NETWORK_PROVIDER);
        }
        Log.i ("location", "" + location);
        aMapLocation = new AMapLocation (location);//通过aMapLocation拿到经纬度
//        new Thread () {
//
//            @Override
//            public void run() {
//                super.run ();
//
//                while (isRuning) {
//                    try {
//                        Thread.sleep (1000);
//                    /*    final double latitude = aMapLocation.getLatitude ();//获取纬度
//                        final double longitude = aMapLocation.getLongitude ();//获取经度
//
//                        Log.i ("当前经纬度", "onCreate:latitude== " + latitude + "longitude==" + longitude);
//                        Request request = new Request.Builder ()
//                                .url ("http://39.105.37.77:8080/test/warp?x=" + latitude + "&y=" + longitude)
//                                .build ();
//                        client.newCall (request).execute ();
//
//
//                        runOnUiThread (new Runnable () {
//                            @Override
//                            public void run() {
//                                tv_Latitude_and_longitude.setText ("当前经度：" + latitude + "\n" + "当前纬度：" + longitude);
//                            }
//                        });*/
//                        if (!isRuning) {
//                            break;
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace ();
//                    } catch (Exception e) {
//                        e.printStackTrace ();
//                    }
//                }
//            }
//        }.start ();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        isRuning = false;
        Log.i ("------", "onDestroy: ");
        //mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        //mMapView.onDestroy();
        //销毁定位监听

        mMapView.onDestroy ();
        if (null != mlocationClient) {
            mlocationClient.onDestroy ();
        }
        mHandler.removeMessages (1);

    }

    @Override
    protected void onResume() {
        super.onResume ();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause ();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause ();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState (outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState (outState);

        //upamap(outState);

    }


    /**
      * @时间 ：2018/6/15  9:52
      *
      * @说明 ：解决地图卡顿
      */

//    private void upamap(Bundle outState) {
//        final Bundle bundle=outState;
//
//        new Thread (){
//            @Override
//            public void run() {
//                while (true){
//                    try {
//                        Log.i ("", "开始地图重新绘画");
//                        Thread.sleep (10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace ();
//                        mMapView.onSaveInstanceState (bundle);
//                        Log.i ("", "地图重新绘画");
//                    }
//                }
//
//            }
//        }.start ();
//    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient (this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption ();
            //设置定位回调监听
            mlocationClient.setLocationListener (this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode (AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption (mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation ();//启动定位
        }

    }

    @Override
    public void deactivate() {

        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation ();
            mlocationClient.onDestroy ();
        }
        mlocationClient = null;

    }


    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager ().getPackageInfo (
                    context.getPackageName (), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray ();
            MessageDigest md = MessageDigest.getInstance ("SHA1");
            byte[] publicKey = md.digest (cert);
            StringBuffer hexString = new StringBuffer ();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString (0xFF & publicKey[i])
                        .toUpperCase (Locale.US);
                if (appendString.length () == 1)
                    hexString.append ("0");
                hexString.append (appendString);
                hexString.append (":");
            }
            String result = hexString.toString ();
            return result.substring (0, result.length () - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace ();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ();
        }
        return null;
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode () == 0) {

                //定位成功回掉
                final double longitude = aMapLocation.getLongitude ();
                final double latitude = aMapLocation.getLatitude ();
                speed = aMapLocation.getSpeed ();
                Log.i ("==========", "经度="+latitude+" 纬度="+longitude+" 速度="+speed);
                latLngs.add (new LatLng (latitude,longitude));
                polyline =aMap.addPolyline(new PolylineOptions ().
                        addAll(latLngs).width(20).color(Color.argb(255, 255, 10, 50)));


                //float distance = AMapUtils.calculateLineDistance(latLng1,latLng2);


                if (frequency>0){
                    getdistance = getdistance (latLngs.get (frequency), latLngs.get (frequency + 1));
                    distance=distance+getdistance;

                }else {
                    getdistance=0;
                }
//                tv_Latitude_and_longitude.setText ("当前经度：" + latitude + "\n" + "当前纬度：" + longitude+"\n"+"速度为："+speed+"\n"+" 运动距离="+distance);

                mListener.onLocationChanged (aMapLocation);// 显示系统小蓝点
                frequency++;
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode () + ": " + aMapLocation.getErrorInfo ();
                Log.e ("AmapErr", errText);
            }
        }

    }

    public float  getdistance(LatLng start,LatLng end){

        return AMapUtils.calculateLineDistance(start,end);
    }


    @Override
    public void onClick(View v) {
        int i1 = v.getId ();
        if (i1 == R.id.rl_first_layout) {
            seleted ();
            mRlFirstLayout.setSelected (true);
            zt.setText ("跑步运动进行中");

        } else if (i1 == R.id.rl_four_layout) {
            seleted ();
            mRlFourLayout.setSelected (true);
            zt.setText ("骑行运动进行中");

        } else if (i1 == R.id.start_yd) {
            Intent intent=new Intent(this,Voice_service.class);
            intent.putExtra("run",1);
            startService(intent);
            onelayout.setVisibility (View.GONE);
            towlayout.setVisibility (View.VISIBLE);
            timeDownView = findViewById (R.id.rpv);
            timeDownView.downSecond (3);
            timeDownView.setOnTimeDownListener (new TimeDownView.DownTimeWatcher () {
                @Override
                public void onTime(int num) {

                }

                @Override
                public void onLastTime(int num) {

                }

                @Override
                public void onLastTimeFinish(int num) {
                    towlayout.setVisibility (View.GONE);
                    thirdlayout.setVisibility (View.VISIBLE);
                    mHandler.removeMessages (1);
                    mHandler.sendEmptyMessage (1);
                    isstop = false;
//                        new Thread() {
//                                @Override
//                                public void run() {
//                                        try {
//                                            Thread.sleep(100);
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    mHandler.removeMessages(0);
//                                                    mHandler.sendEmptyMessage(0);
//                                                }
//                                            });
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                }
//
//                            }.start();
                }

            });

        } else if (i1 == R.id.reset_yd) {
            LongClickUtils.setLongClick (new Handler (), resetyd, 2000, new View.OnLongClickListener () {
                @Override
                public boolean onLongClick(View v) {
                    hour.setText ("00");
                    mint.setText ("00");
                    sec.setText ("00");
                    timeusedinsec = 0;
                    isstop = true;
                    new Thread () {
                        @Override
                        public void run() {
                            for (i = 0; i < 3; i++) {
                                try {
                                    Thread.sleep (1000);
                                    runOnUiThread (new Runnable () {
                                        @Override
                                        public void run() {

                                            resetyd.setProgress (3, i);

                                        }
                                    });
                                } catch (InterruptedException e) {
                                    e.printStackTrace ();
                                }


                            }
                        }
                    }.start ();
                    return true;
                }
            });


        } else if (i1 == R.id.pbmb) {
            LayoutInflater inflater = getLayoutInflater ();
            View gl = inflater.inflate (R.layout.dialog01, null);
            mbgl = gl.findViewById (R.id.mb_sport);
            AlertDialog.Builder dl = new AlertDialog.Builder (this);
            dl.setTitle ("运动目标设置：");
            dl.setView (gl);
            dl.setPositiveButton ("确定", new DialogInterface.OnClickListener () {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (mbgl.getText ().toString ().equals ("")) {
                        Toast.makeText (MainActivity.this, "设置失败：输入数值不能为空！", Toast.LENGTH_SHORT).show ();
                    } else {
                        mbgl02 = mbgl.getText ().toString ();
                        mbgl03 = Float.parseFloat (mbgl02);
                        Toast.makeText (MainActivity.this, "设置成功：" + mbgl02 + "公里", Toast.LENGTH_SHORT).show ();
                        sz_sportmb.setText (mbgl02);
                    }
                }
            });
            dl.setNegativeButton ("取消", new DialogInterface.OnClickListener () {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dl.show ();


        }
    }
    private void seleted() {
        mRlFirstLayout.setSelected(false);
        mRlFourLayout.setSelected(false);
    }
    public void run(View view){
        int i1 = view.getId ();
        if (i1 == R.id.iv_first_hwyd) {

        } else if (i1 == R.id.iv_four_qxyd) {

        }
    }

}
