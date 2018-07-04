package com.example.custom_view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;


public class StatisticsArcView extends View {
    private float borderWidth=dipToPx(12);
    private Paint mpaint;
    private float dataTextSize = dipToPx(12);
    private String time1,time2,time3,time4,time5,time6,time7;
    private double historydata1,historydata2,historydata3,historydata4,historydata5,historydata6,historydata7;
    private float data1= (float) 0.875,data2= (float) 0.875,data3= (float) 0.875,data4= (float) 0.875,data5= (float) 0.875,data6= (float) 0.875,data7= (float) 0.875;

    private float dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
    public StatisticsArcView(Context context) {
        super(context);
    }



    public StatisticsArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatisticsArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setStrokeWidth(borderWidth);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setStrokeCap(Paint.Cap.SQUARE);
        float viewHight=this.getHeight();
        float viewWidth = this.getWidth();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        time1=month+"."+(day-7);
        time2=month+"."+(day-6);
        time3=month+"."+(day-5);
        time4=month+"."+(day-4);
        time5=month+"."+(day-3);
        time6=month+"."+(day-2);
        time7=month+"."+(day-1);
        Log.i("111111111111111111", "setCurrentCountls: "+time1+time2+time3+time4+time5+time6+time7);
        drawdayline(canvas,viewHight,viewWidth);
        drawdata(canvas,viewHight,viewWidth);

    }
    public int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Rect bounds_Number = new Rect();
        paint.getTextBounds("5000", 0, "5000".length(), bounds_Number);
        return bounds_Number.height();
    }



    private void drawdayline(Canvas canvas, float viewHight, float viewWidth) {
        mpaint.setColor(getResources().getColor(R.color.blue));
        canvas.drawLine(viewWidth/14,viewHight*7/8,viewWidth/14,viewHight*data1,mpaint);
        canvas.drawLine(viewWidth*3/14,viewHight*7/8,viewWidth*3/14,viewHight*data2,mpaint);
        canvas.drawLine(viewWidth*5/14,viewHight*7/8,viewWidth*5/14,viewHight*data3,mpaint);
        canvas.drawLine(viewWidth*7/14,viewHight*7/8,viewWidth*7/14,viewHight*data4,mpaint);
        canvas.drawLine(viewWidth*9/14,viewHight*7/8,viewWidth*9/14,viewHight*data5,mpaint);
        canvas.drawLine(viewWidth*11/14,viewHight*7/8,viewWidth*11/14,viewHight*data6,mpaint);
        canvas.drawLine(viewWidth*13/14,viewHight*7/8,viewWidth*13/14,viewHight*data7,mpaint);
    }
    private void drawdata(Canvas canvas, float viewHight, float viewWidth) {
        mpaint.setTextSize(dataTextSize);
        mpaint.setColor(getResources().getColor(R.color.hei));
        canvas.drawText(time1,viewWidth/28,viewHight,mpaint);
        canvas.drawText(time2,viewWidth*5/28,viewHight,mpaint);
        canvas.drawText(time3,viewWidth*9/28,viewHight,mpaint);
        canvas.drawText(time4,viewWidth*13/28,viewHight,mpaint);
        canvas.drawText(time5,viewWidth*17/28,viewHight,mpaint);
        canvas.drawText(time6,viewWidth*21/28,viewHight,mpaint);
        canvas.drawText(time7,viewWidth*25/28,viewHight,mpaint);
    }
    public void setCurrentCountls(String[] historydata) {
        if (historydata[0]==null){
            return;
        }

        historydata7= Integer.valueOf(historydata[0])*0.0000875;
        data7= (float) (0.875-historydata7);
        if (data7<0){
            data7=0;
        }
        historydata6= Integer.valueOf(historydata[1])*0.0000875;
        data6= (float) (0.875-historydata6);
        if (data6<0){
            data6=0;
        }
        historydata5= Integer.valueOf(historydata[2])*0.0000875;
        data5= (float) (0.875-historydata5);
        if (data5<0){
            data5=0;
        }
        historydata4= Integer.valueOf(historydata[3])*0.0000875;
        data4= (float) (0.875-historydata4);
        if (data4<0){
            data4=0;
        }
        historydata3= Integer.valueOf(historydata[4])*0.0000875;
        data3= (float) (0.875-historydata3);
        if (data3<0){
            data3=0;
        }
        historydata2= Integer.valueOf(historydata[5])*0.0000875;
        data2= (float) (0.875-historydata2);
        if (data2<0){
            data2=0;
        }
        historydata1= Integer.valueOf(historydata[6])*0.0000875;
        data1= (float) (0.875-historydata1);
        if (data1<0){
            data1=0;
        }
        Log.i("2222222222222222", "setCurrentCountls: "+historydata[1]);



    }

}
