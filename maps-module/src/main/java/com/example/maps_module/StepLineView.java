package com.example.maps_module;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.math.BigDecimal;

public class StepLineView extends View {

    private float borderWidth=dipToPx(8);
    private Paint mpaint;
    private float dataTextSize = dipToPx(12);
    private String stepNumber = "0";
    private String stepNumber2 = "0";
    private String lastNumber2 = "0";
    private float startAngle = 0;
    private float angleLength = 1;
    private int animationLength = 3000;
    private float currentAngleLength = 0;
    private String c01;
    private float dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
    public StepLineView(Context context) {
        super(context);
        init();
    }

    public StepLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StepLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setStrokeWidth(borderWidth);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setStrokeCap(Paint.Cap.SQUARE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewHight=this.getHeight();
        int viewWidth = this.getWidth();
        drawtargetline(canvas,viewHight,viewWidth);
        drawcurrentline(canvas,viewHight,viewWidth);
        drawtextnumber(canvas,viewHight,viewWidth);
        drawPercentage(canvas,viewHight,viewWidth);


    }

    private void drawPercentage(Canvas canvas, int viewHight, int viewWidth) {
        mpaint.setStrokeWidth(dipToPx(2));
        mpaint.setColor(getResources().getColor(R.color.lv));
        mpaint.setTextSize(dipToPx(10));
        canvas.drawText(c01+"%",currentAngleLength,viewHight*11/32,mpaint);
    }

    private void drawtargetline(Canvas canvas, int viewHight, int viewWidth) {
        mpaint.setStrokeWidth(dipToPx(7));
        mpaint.setColor(getResources().getColor(R.color.hui));
        canvas.drawLine(0,viewHight/4,viewWidth,viewHight/4,mpaint);
    }
    private void drawcurrentline(Canvas canvas, int viewHight, int viewWidth) {
        mpaint.setStrokeWidth(dipToPx(7));
        mpaint.setColor(getResources().getColor(R.color.blue1));
        canvas.drawLine(startAngle,viewHight/4,currentAngleLength,viewHight/4,mpaint);
    }
    private void drawtextnumber(Canvas canvas, int viewHight, int viewWidth) {
        mpaint.setColor(getResources().getColor(R.color.hei));
        mpaint.setTextSize(dipToPx(12));
        double a=currentAngleLength/9;
//        Log.i("777777777777", "drawtextnumber: "+a);
        if (a>100){
            a=100;
        }
//        Log.i("8888888888888888", "drawtextnumber: "+a);
        BigDecimal b=new BigDecimal(a+"");
         c01= String.valueOf(b.setScale(0, BigDecimal.ROUND_HALF_UP));
//        Log.i("444444444444", "drawtextnumber: "+c01);
        String stepString = "目标已完成"+b.setScale(0,BigDecimal.ROUND_HALF_UP)+"%";
        canvas.drawText(stepString,viewWidth*7/20,viewHight*3/4,mpaint);

    }
    public void setCurrentCountpb(float totalStepNum, float currentCounts) {
        int viewWidth = this.getWidth();
        stepNumber2=currentCounts+"";
        lastNumber2=totalStepNum+"";
        if (currentCounts > totalStepNum) {
            currentCounts = totalStepNum;
        }

        float scalePrevious = Float.parseFloat(stepNumber)/totalStepNum;
        float previousAngleLength = scalePrevious * viewWidth * angleLength;
        float scale = (float) currentCounts / totalStepNum;
        float currentAngleLength = scale * angleLength*viewWidth;
//        Log.i("333333333333", "setCurrentCountpb: "+viewWidth);
//        Log.i("222222222222", "setCurrentCountpb: "+currentAngleLength);
        setAnimation(previousAngleLength, currentAngleLength, animationLength);
        stepNumber=String.valueOf(currentCounts);
    }
    public void setAnimation(float last, float current, int length) {
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(last, current);
        progressAnimator.setDuration(length);
        progressAnimator.setTarget(currentAngleLength);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngleLength = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        progressAnimator.start();
    }
}
