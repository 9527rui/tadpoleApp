package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：20:50 2018/6/11
 *
 * @说明：BaseActivity Fragment基类
 */

public abstract class BaseActivity extends FragmentActivity {
    // private int r;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setXml();
        initView();
        initData();
    }

    private void setXml() {
        setContentView(getXml());
    }

    public abstract int getXml();

    public abstract void initView();

    public abstract void initData();
}
