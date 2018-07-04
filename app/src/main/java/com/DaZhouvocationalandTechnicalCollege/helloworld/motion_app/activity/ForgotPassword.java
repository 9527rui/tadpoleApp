package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;

public class ForgotPassword extends BaseActivity implements View.OnClickListener{
    private EditText et_sjh,et_xma,et_qrma,et_yzm;
    private Button bt_hqyzm,bt_zhma;
    @Override
    public int getXml() {
        return R.layout.activity_forgot;
    }

    @Override
    public void initView() {
        initViewsl();

    }

    private void initViewsl() {
        et_sjh=findViewById(R.id.sjh);
        et_xma=findViewById(R.id.xma);
        et_qrma=findViewById(R.id.qrma);
        et_yzm=findViewById(R.id.yzm);
        bt_hqyzm=findViewById(R.id.hqyazma);
        bt_hqyzm.setOnClickListener(this);
        bt_zhma=findViewById(R.id.bt_zhma);
        bt_zhma.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hqyazma:

                break;
            case R.id.bt_zhma:

                break;
        }
    }
}
