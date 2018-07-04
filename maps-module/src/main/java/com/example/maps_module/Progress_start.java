package com.example.maps_module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Progress_start extends View {
    private float borderWidth=dipToPx(6);
    private Paint mpaint;
    private float startAngle = 360;
    private float angleLength = 360;
    private float dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
    public Progress_start(Context context) {
        super(context);
        initview();
    }

    public Progress_start(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview();
    }

    public Progress_start(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        drawTextGo(canvas,rectF);
    }

    private void drawTextGo(Canvas canvas, RectF rectF) {
        float centerX=(getWidth())/2;
        mpaint.setColor(getResources().getColor(R.color.blue1));
        mpaint.setStrokeWidth(dipToPx(1));
        mpaint.setTextSize(dipToPx(30));
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        mpaint.setTypeface(font);
        mpaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mpaint.setTextAlign(Paint.Align.CENTER);
        Rect bounds= new Rect();
        mpaint.getTextBounds("GO", 0, "GO".length(), bounds);
        canvas.drawText("GO",centerX,centerX+bounds.height()/2,mpaint);
    }

    private void drawArcGrey(Canvas canvas, RectF rectF) {
        mpaint.setColor(getResources().getColor(R.color.hui));
        mpaint.setStrokeWidth(borderWidth);
        mpaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,startAngle,angleLength,false,mpaint);
    }

}
