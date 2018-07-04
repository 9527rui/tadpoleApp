package com.example.maps_module;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Progress_stop extends View{
    private float borderWidth=dipToPx(6);
    private Paint mpaint;
    private float startAngle = 360;
    private float angleLength = 360;
    private float currentAngleLength2 = 0;
    private String stepNumber4 = "0";
    private String stepNumber3 = "0";
    private String lastNumber3 = "0";
    private int animationLength = 3000;
    private float dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
    public Progress_stop(Context context) {
        super(context);
        initview();
    }

    public Progress_stop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview();
    }

    public Progress_stop(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview();
    }

    private void initview() {
        mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setStrokeWidth(borderWidth);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeCap(Paint.Cap.ROUND);
        mpaint.setStrokeJoin(Paint.Join.ROUND);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX=(getWidth())/2;
        RectF rectF=new RectF(0+borderWidth,borderWidth,2*centerX-borderWidth,2*centerX-borderWidth);
        drawArcGrey(canvas, rectF);
        drawArcWhite(canvas, rectF);
        drawTextGo(canvas,rectF);
    }



    private void drawTextGo(Canvas canvas, RectF rectF) {
        float centerX=(getWidth())/2;
        mpaint.setColor(getResources().getColor(R.color.blue1));
        mpaint.setStrokeWidth(dipToPx(1));
        mpaint.setTextSize(dipToPx(16));
        mpaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        mpaint.setTypeface(font);
        mpaint.setTextAlign(Paint.Align.CENTER);
        Rect bounds= new Rect();
        mpaint.getTextBounds("Pause", 0, "Pause".length(), bounds);
        canvas.drawText("Pause",centerX,centerX+bounds.height()/2,mpaint);
    }

    private void drawArcGrey(Canvas canvas, RectF rectF) {
        mpaint.setColor(getResources().getColor(R.color.hui));
        mpaint.setStrokeWidth(borderWidth);
        mpaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,startAngle,angleLength,false,mpaint);
    }
    private void drawArcWhite(Canvas canvas, RectF rectF) {
        mpaint.setColor(getResources().getColor(R.color.blue1));
        mpaint.setStrokeWidth(borderWidth);
        mpaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,startAngle,currentAngleLength2,false,mpaint);
    }
    public void setProgress(int totaltime , int currenttime) {
        if (currenttime > totaltime) {
            currenttime = totaltime;
        }
        float scalePrevious = (float) Integer.valueOf(stepNumber4) / totaltime;
        float previousAngleLength = scalePrevious * angleLength;
        float scale = (float) currenttime / totaltime;
        float currentAngleLength = scale * angleLength;
        setAnimation(previousAngleLength, currentAngleLength, animationLength);
        stepNumber4=String.valueOf(currenttime);

    }

    private void setAnimation(float last, float current, int length) {
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(last, current);
        progressAnimator.setDuration(length);
        progressAnimator.setTarget(currentAngleLength2);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngleLength2 = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        progressAnimator.start();
    }


}
