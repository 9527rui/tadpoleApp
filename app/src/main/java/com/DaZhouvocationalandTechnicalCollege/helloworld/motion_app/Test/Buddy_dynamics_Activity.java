package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Get_Dynamic;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.base.BaseActivity;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Dyunamic;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Name;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.DividerListItemDecoration;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.RecyclerViewAdpter;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils.getUserurl;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：yangshijie
 * @QQ:
 * @时间：14:18 2018/6/18
 * @说明：Buddy_dynamics_Activity 校园动态展示类
 */

public class Buddy_dynamics_Activity extends BaseActivity {
    private int crtt = 1;
    private List<Dyunamic> list;
    private RecyclerView recyclerView;
    private Name[] userurl;
    private int c=5;
    private RecyclerViewAdpter adpter;


    @Override
    public int getXml() {
        return R.layout.activity_buddy_dynamics_;
    }

    @Override
    public void initView() {
        list=new ArrayList<> ();
        recyclerView=(RecyclerView)findViewById (R.id.recyclerView);
        RefreshLayout refreshLayout = (RefreshLayout) findViewById (R.id.refreshLayout);
        refreshLayout.setOnRefreshListener (new OnRefreshListener () {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list=new ArrayList<> ();
                crtt=1;
                initData ();
                refreshlayout.finishRefresh (2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener (new OnLoadMoreListener () {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                upData ();
                refreshlayout.finishLoadMore (2000/*,false*/);//传入false表示加载失败
            }


        });


    }

    /**
     * @ 作用： 下拉刷新， 初始化加载
     * @ 时间： 2018/6/18 16:58
     */


    @Override
    public void initData() {
        new Thread () {
            @Override
            public void run() {

                Get_Dynamic getDynamic = new Get_Dynamic ();
                final Dyunamic[] dyunamics = getDynamic.Get_Dynamic (0);
                if (dyunamics==null){
                    runOnUiThread (new Runnable () {
                        @Override
                        public void run() {
                            Toast.makeText (Buddy_dynamics_Activity.this, "没有任何动态！", Toast.LENGTH_SHORT).show ();
                        }
                    });
                    return;
                }
                for (int a=0;a<dyunamics.length;a++){
                    list.add (a,dyunamics[a]);
                }
                getUserurl getU=new getUserurl ();
                userurl = getU.getUserurl (list);
                Log.i ("长度", "run: "+userurl.length);
                runOnUiThread (new Runnable () {
                    @Override
                    public void run() {
                        adpter=new RecyclerViewAdpter (getApplicationContext (),list,userurl);
                        recyclerView.setAdapter (adpter);
                        recyclerView.setLayoutManager (new LinearLayoutManager (Buddy_dynamics_Activity.this));
                        recyclerView.addItemDecoration (new DividerListItemDecoration (getApplicationContext (),DividerListItemDecoration.VERTICAL_LIST));
                        Toast.makeText (Buddy_dynamics_Activity.this, "更新成功！", Toast.LENGTH_SHORT).show ();
                    }
                });
                for (int a = 0; a < dyunamics.length; a++) {
                    Log.i ("数据", "" + dyunamics[a].toString ());
                }


            }
        }.start ();
    }

    /**
     * @ 作用： 上拉加载
     * @ 时间： 2018/6/18 16:57
     */


    public void upData() {
        final int c1=c+5;
        c=c1;

        final int x = crtt++;
        new Thread () {
            @Override
            public void run() {
                Get_Dynamic getDynamic = new Get_Dynamic ();
                final Dyunamic[] dyunamics = getDynamic.Get_Dynamic (x);
                if (dyunamics == null) {
                    runOnUiThread (new Runnable () {
                        @Override
                        public void run() {
                            Toast.makeText (Buddy_dynamics_Activity.this, "没有数据了！", Toast.LENGTH_SHORT).show ();
                            }
                    });
                    return;
                }
                for (int a=0;a<dyunamics.length;a++){
                    list.add (dyunamics[a]);
                }
                getUserurl getU=new getUserurl ();
                userurl = getU.getUserurl (list,c1);
                runOnUiThread (new Runnable () {
                    @Override
                    public void run() {
//                        recyclerView.setAdapter (new RecyclerViewAdpter (getApplicationContext (),list,userurl));
//                        recyclerView.setLayoutManager (new LinearLayoutManager (Buddy_dynamics_Activity.this));
//                        recyclerView.addItemDecoration (new DividerListItemDecoration (getApplicationContext (),DividerListItemDecoration.VERTICAL_LIST));
                        adpter.setData (list,userurl);
                        //recyclerView.setAdapter (adpter);

                        Toast.makeText (Buddy_dynamics_Activity.this, "加载成功！", Toast.LENGTH_SHORT).show ();
                    }
                });








                for (int a = 0; a < dyunamics.length; a++) {
                    Log.i ("数据", "" + dyunamics[a].toString ());

                }

            }
        }.start ();


    }
}
