package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;

import java.util.List;

import static android.content.ContentValues.TAG;

class myAdapter1 extends BaseAdapter {
    Context content;
    List<Newsdata> newsdatalist1;
    List<Bitmap> bitmapList1;
    public myAdapter1(Context content, List<Newsdata> newsdatalist1, List<Bitmap> bitmapList1) {
        this.content=content;
        this.bitmapList1=bitmapList1;
        this.newsdatalist1=newsdatalist1;
    }

    public int getCount() {
        Log.i(TAG, "getCount: "+newsdatalist1+newsdatalist1.size());

        if (newsdatalist1==null||newsdatalist1.size()<=0){
            return 0;
        }
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {LayoutInflater inflater = LayoutInflater.from(content);
        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.list_item, null);
        ImageView image=group.findViewById(R.id.img_01);
        image.setImageBitmap(bitmapList1.get(position));
        TextView textView1=group.findViewById(R.id.txt_bt);
        textView1.setText(newsdatalist1.get(position).getT1348649079062().get(position).getTitle());
        TextView textView2=group.findViewById(R.id.txt_nr);
        textView2.setText(newsdatalist1.get(position).getT1348649079062().get(position).getDigest());
        TextView textView3=group.findViewById(R.id.txt_time);
        textView3.setText(newsdatalist1.get(position).getT1348649079062().get(position).getPtime());
        return group;
    }
}
