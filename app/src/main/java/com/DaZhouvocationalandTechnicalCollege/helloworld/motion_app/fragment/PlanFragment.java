package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.MplActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity.RunningplActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseFragment;


public class PlanFragment extends BaseFragment implements View.OnClickListener{
    private LinearLayout my_plan,running_plan,qx_plan,ds_plan,jz_plan;
    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View planview = inflater.inflate(R.layout.plan_fragment,container,false);
        initview(planview);
        return planview;
    }

    private void initview(View view) {
        my_plan=view.findViewById(R.id.pl_mine);
        running_plan=view.findViewById(R.id.pl_running);
        qx_plan=view.findViewById(R.id.pl_qx);
        ds_plan=view.findViewById(R.id.pl_ds);
        jz_plan=view.findViewById(R.id.pl_jz);
        my_plan.setOnClickListener(this);
        running_plan.setOnClickListener(this);
        qx_plan.setOnClickListener(this);
        ds_plan.setOnClickListener(this);
        jz_plan.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pl_mine:
                startActivity(new Intent(getContext(), MplActivity.class));
                break;
            case R.id.pl_running:
                startActivity(new Intent(getContext(), RunningplActivity.class));
                break;
            case R.id.pl_qx:
                break;
            case R.id.pl_ds:
                break;
            case R.id.pl_jz:
                break;
        }
    }
}
