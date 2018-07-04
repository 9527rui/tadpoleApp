package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：20:50 2018/6/11
 *
 * @说明：BaseFragment   Activity基类
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        
        
        
        return initView(inflater,container,savedInstanceState);
        
        
        
    }

    protected abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    protected abstract void initData();


}
