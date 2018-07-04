package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.user;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.FaqActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.AboutActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.DetailsActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.NumPicker;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseFragment;
import com.example.custom_view.CircleImageView;

import java.math.BigDecimal;


public class MineFragment extends BaseFragment implements View.OnClickListener {
    private TextView mbbs,mbtz,fromxc,Photograph,Cancel;
    private LinearLayout yundongmb,tzmb,llxq,about,faq,setting;
    public String number,Bodyweight;
    private EditText mb_tz;
    private CircleImageView setimg;
    public SharedPreferences sp;
    private NumPicker np;
    private TextView tv1,tv3;
    private TextView tv2;
    private TextView tv_name;
    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mineview = inflater.inflate(R.layout.mine_fragment,container,false);
        tv_name=mineview.findViewById (R.id.my_tvname);
        tv1=mineview.findViewById (R.id.my_tv1);
        tv2=mineview.findViewById (R.id.my_tv2);
        tv3=mineview.findViewById (R.id.my_tv3);
        mbbs=mineview.findViewById(R.id.mbtv);
        mbtz=mineview.findViewById(R.id.tzmbtv);
        yundongmb=mineview.findViewById(R.id.yundmb);
        yundongmb.setOnClickListener(this);
        tzmb=mineview.findViewById(R.id.tzmb);
        tzmb.setOnClickListener(this);
        setimg=mineview.findViewById(R.id.setimg);
        setimg.setOnClickListener(this);
        setimg.setImageResource(R.drawable.touxiang);
        llxq=mineview.findViewById(R.id.ll_xq);
        llxq.setOnClickListener(this);
        about=mineview.findViewById(R.id.about_pg);
        about.setOnClickListener(this);
        faq=mineview.findViewById(R.id.ll_faq);
        faq.setOnClickListener(this);
        setting=mineview.findViewById(R.id.ll_setting);
        setting.setOnClickListener(this);
        np = new NumPicker(getActivity());
        sp = getActivity().getSharedPreferences("mbbs", 0);
        number=sp.getString("number","10000");
        Bodyweight=sp.getString("Bodyweight","50");
        mbbs.setText(number);
        Bodyweight=sp.getString("Bodyweight","");
        mbtz.setText(Bodyweight);
        np.setOnCancelListener(new NumPicker.OnCancelClickListener() {
            @Override
            public void onClick() {
                np.dismiss();
            }
        });
        np.setOnComfirmListener(new NumPicker.onComfirmClickListener() {
            @Override
            public void onClick(int num) {
                num=num*1000;
                number=""+num;
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("number",number);
                edit.commit();
                MineFragment.this.mbbs.setText(number);
                np.dismiss();
            }
        });
        initData ();
        return mineview;
    }

    @Override
    protected void initData() {
        double b=0;
        int c=0;
        int x=0;
        if (user.getUser_Motion_record()!=null){
            for (int a=0;a<user.getUser_Motion_record().length;a++){
                if (user.getUser_Motion_record()[a]==null){
                    x=0;
                }else {
                    x=Integer.parseInt (user.getUser_Motion_record()[a]);
                }

               b =b+x;
            }

            c=(int)(b/7);
            Log.i ("rizhi", "initData: "+b);
            b= (b*0.7*0.001);
        }


        tv1.setText (c+"");
        Log.i("000000000000000000", "initData: "+c);
        BigDecimal e=new BigDecimal(b);
        tv2.setText (e.setScale(1,BigDecimal.ROUND_HALF_UP)+"");
        if (c>10000){
            tv3.setText(R.string.jkm);
        }else if (c<=10000&&c>=5000){
            tv3.setText(R.string.jky);
        }else if (c<=5000&&c>=3000){
            tv3.setText(R.string.jkb);
        }else if(c<3000){
            tv3.setText(R.string.jkc);
        }
        if (user.getName ()!=null){
            tv_name.setText (user.getName ());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yundmb:
                        np.show();
               break;
            case R.id.tzmb:
                LayoutInflater inflater = getLayoutInflater ();
                View gl01 = inflater.inflate (R.layout.dialogtz,null);
                mb_tz = gl01.findViewById (R.id.mb_tz);
                AlertDialog.Builder dl01 = new AlertDialog.Builder (getContext());
                dl01.setTitle ("体重目标设置：");
                dl01.setView (gl01);
                dl01.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mb_tz.getText ().toString ().equals ("")) {
                            Toast.makeText (getContext(), "设置失败：输入数值不能为空！", Toast.LENGTH_SHORT).show ();
                        } else {
                            Bodyweight=mb_tz.getText().toString();
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString("Bodyweight",Bodyweight);
                            edit.commit();
                            mbtz.setText(Bodyweight);
                        }
                    }
                });
                dl01.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dl01.show ();

                break;
            case R.id.ll_xq:
                        startActivity(new Intent(getActivity(),DetailsActivity.class));
                break;
            case R.id.setimg:
                LayoutInflater inflater1 = getLayoutInflater ();
                View dialog01 = inflater1.inflate (R.layout.dialog_img,null);
                fromxc=dialog01.findViewById(R.id.fromxc_img);
                Photograph=dialog01.findViewById(R.id.Photograph_img);
                Cancel=dialog01.findViewById(R.id.Cancel_01);
                final Dialog dialog = new Dialog(getContext(), R.style.transparentFrameWindowStyle);
                dialog.setContentView(dialog01,new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                Window window = dialog.getWindow();
                window.setWindowAnimations(R.style.anim_style);
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.x = 0;
                layoutParams.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                dialog.onWindowAttributesChanged(layoutParams);
                dialog.setCanceledOnTouchOutside(true);
                dialog.findViewById(R.id.fromxc_img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"选择相片",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.Photograph_img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"拍照上传",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });
                dialog.findViewById(R.id.Cancel_01).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.about_pg:
                        startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.ll_faq:
                        startActivity(new Intent(getActivity(), FaqActivity.class));
                break;
            case R.id.ll_setting:

                break;

        }

        }



}
