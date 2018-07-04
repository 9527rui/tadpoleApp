package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment.CommunityFragment;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment.HomeFragment;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment.MineFragment;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment.PlanFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity {
    long firstTime=0;
    private TextView textView;
    private LinearLayout mlinearLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<android.support.v4.app.Fragment> mFragments = new ArrayList<>();
    private MyAdapter adapter;
    private String[] titles = {"首页", "计划", "社区","我的"};


    @Override
    public int getXml() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        viewPager = findViewById(R.id.viewpager);
        tabLayout =  findViewById(R.id.tablayout);
        mlinearLayout=findViewById(R.id.mlinearLayout);

        HomeFragment tab01=new HomeFragment();
        PlanFragment tab02=new PlanFragment();
        CommunityFragment tab03=new CommunityFragment();
        MineFragment tab04=new MineFragment();
        mFragments.add(tab01);
        mFragments.add(tab02);
        mFragments.add(tab03);
        mFragments.add(tab04);
        adapter = new MyAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
        initEvent();
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        selectvisibility();
                        break;
                    case 1:
                        selectvisibility();
                        break;
                    case 2:
                        selectvisibility();
                        break;
                    case 3:
                        selectvisibility();
                        mlinearLayout.setVisibility(View.GONE);
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                textView = tab.getCustomView().findViewById(R.id.tv_title);
                textView.setTextColor(getResources().getColor(R.color.bai_2));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                textView = tab.getCustomView().findViewById(R.id.tv_title);
                textView.setTextColor(getResources().getColor(R.color.bai_8));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void selectvisibility() {
        mlinearLayout.setVisibility(View.VISIBLE);
    }

    class MyAdapter extends FragmentPagerAdapter {

        private Context context;


        public MyAdapter(FragmentManager fm,Context context) {
            super(fm);
            this.context = context;

        }
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragments.get(position);

        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.tab_custom, null);
            textView =  v.findViewById(R.id.tv_title);
            textView.setText(titles[position]);
            textView.setTextSize(18);
            if (position == 0) {
                textView.setTextColor(v.getResources().getColor(R.color.bai_2));
            }
            return v;
        }
    }


    @Override
    public void initData() {


    }

    @Override
    public void onBackPressed() {
        long SecondTime=System.currentTimeMillis();
        if (SecondTime-firstTime>2000){
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
            firstTime=SecondTime;
        }else {
            System.exit(0);
        }
    }

}
