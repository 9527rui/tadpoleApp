package com.example.custom_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.math.BigDecimal;


public class StepArcView extends View{
    private float borderWidth = dipToPx(14);
    private float numberTextSize = 0;
    private String stepNumber = "0";
    private String lastNumber = "0";
    private String stepNumber1 = "0";
    private float startAngle = 180;
    private float angleLength = 180;
    private float currentAngleLength = 0;
    private String mileage="";
    private String numbermileage;
    private String heat;
    private String numberheat;
    private int animationLength = 3000;
    private int colorStart = Color.parseColor("#49BCCE");
    private int colorEnd = Color.parseColor("#FF6766");
    private int colorEmpty = Color.parseColor("#EAEAEA");
    private Context mContext;



    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    public StepArcView(Context context) {
        super(context);
    }

    public StepArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StepArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs, defStyleAttr);
    }

    private void initAttr(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.StepArcView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.StepArcView_startColor) {
                colorStart = a.getColor(attr, colorStart);

            } else if (attr == R.styleable.StepArcView_endColor) {
                colorEnd = a.getColor(attr, colorEnd);

            } else if (attr == R.styleable.StepArcView_emptyColor) {
                colorEmpty = a.getColor(attr, colorEmpty);

            } else {
            }
        }
        a.recycle();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewHight=this.getHeight();
        int viewWidth = this.getWidth();
        float centerX=(getWidth())/2;
        RectF rectF=new RectF(0+borderWidth,borderWidth,2*centerX-borderWidth,2*centerX-borderWidth);
        drawArcYellow(canvas, rectF);
        drawArcRed(canvas, rectF);
        drawTextNumber(canvas, centerX);
        drawTextStepString(canvas, centerX);
        drawTexttoday(canvas,centerX);
        drawTextHeat(canvas,viewHight,viewWidth);
        drawTextMileage(canvas,viewHight,viewWidth);
    }

    private void drawTextMileage(Canvas canvas, int viewHight, int viewWidth) {
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setStrokeWidth(borderWidth);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setStrokeCap(Paint.Cap.SQUARE);
        mpaint.setTextSize(dipToPx(20));
        mpaint.setColor(getResources().getColor(R.color.orange_color));
        String stringheat="里程:"+numbermileage+"公里";
        canvas.drawText(stringheat,viewWidth*1/25,viewHight*9/10,mpaint);
    }

    private void drawTextHeat(Canvas canvas, int viewHight, int viewWidth) {
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setStrokeWidth(borderWidth);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setStrokeCap(Paint.Cap.SQUARE);
        mpaint.setTextSize(dipToPx(20));
        mpaint.setColor(getResources().getColor(R.color.orange_color));
        String stringheat="燃烧:"+numberheat+"千卡";
        canvas.drawText(stringheat,viewWidth*24/44,viewHight*9/10,mpaint);
    }

    private void drawArcYellow(Canvas canvas, RectF rectF) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.hui));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(borderWidth);
        canvas.drawArc(rectF,startAngle,angleLength,false,paint);
    }

    private void drawArcRed(Canvas canvas, RectF rectF) {
        Paint paintCurrent = new Paint();
        paintCurrent.setColor(colorEmpty);
        paintCurrent.setStrokeJoin(Paint.Join.ROUND);
        paintCurrent.setStrokeCap(Paint.Cap.ROUND);
        paintCurrent.setStyle(Paint.Style.STROKE);
        paintCurrent.setAntiAlias(true);
        paintCurrent.setStrokeWidth(borderWidth);
        int[] colors = new int[]{colorStart, colorEnd};
        LinearGradient linearGradient = new LinearGradient(0, 0, 800, 0, colors,
                null, LinearGradient.TileMode.CLAMP);
        paintCurrent.setShader(linearGradient);
        canvas.drawArc(rectF,startAngle,currentAngleLength,false,paintCurrent);

    }
    private void drawTexttoday(Canvas canvas, float centerX) {
        Paint vTextPaint = new Paint();
        vTextPaint.setTextSize(dipToPx(17));
        vTextPaint.setTextAlign(Paint.Align.CENTER);
        vTextPaint.setAntiAlias(true);
        vTextPaint.setColor(getResources().getColor(R.color.grey));
        String stepString1 = "今日步数";
        Rect bounds = new Rect();
        vTextPaint.getTextBounds(stepString1, 0, stepString1.length(), bounds);
        canvas.drawText(stepString1, centerX, getHeight() *4/ 10 + bounds.height() - getFontHeight(numberTextSize), vTextPaint);
    }
    private void drawTextNumber(Canvas canvas, float centerX) {
        Paint vTextPaint = new Paint();
        vTextPaint.setTextAlign(Paint.Align.CENTER);
        vTextPaint.setAntiAlias(true);
        vTextPaint.setTextSize(numberTextSize);
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        vTextPaint.setTypeface(font);
        vTextPaint.setColor(getResources().getColor(R.color.start_yund));
        Rect bounds_Number = new Rect();
        vTextPaint.getTextBounds(stepNumber1, 0, stepNumber1.length(), bounds_Number);
        canvas.drawText(stepNumber1, centerX, getHeight() / 2 + bounds_Number.height() / 2, vTextPaint);

    }


    private void drawTextStepString(Canvas canvas, float centerX) {
        Paint vTextPaint = new Paint();
        vTextPaint.setTextSize(dipToPx(14));
        vTextPaint.setTextAlign(Paint.Align.CENTER);
        vTextPaint.setAntiAlias(true);
        vTextPaint.setColor(getResources().getColor(R.color.grey));
        String stepString = "目标步数"+lastNumber;
        Rect bounds = new Rect();
        vTextPaint.getTextBounds(stepString, 0, stepString.length(), bounds);
        canvas.drawText(stepString, centerX, getHeight() / 2 + bounds.height() + getFontHeight(numberTextSize), vTextPaint);

    }
    public int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Rect bounds_Number = new Rect();
        paint.getTextBounds(stepNumber, 0, stepNumber.length(), bounds_Number);
        return bounds_Number.height();
    }
    public void setCurrentCount(int totalStepNum, int currentCounts) {
        stepNumber1=currentCounts+"";
        lastNumber=totalStepNum+"";
        if (currentCounts > totalStepNum) {
            currentCounts = totalStepNum;
        }
        mileage=currentCounts*0.7/1000+"";
        BigDecimal b=new BigDecimal(mileage);
        numbermileage=b.setScale(2,BigDecimal.ROUND_HALF_UP)+"";
        heat=currentCounts/35+"";
        BigDecimal c=new BigDecimal(heat);
        numberheat=c.setScale(0,BigDecimal.ROUND_HALF_UP)+"";

      float scalePrevious = (float) Integer.valueOf(stepNumber) / totalStepNum;
      float previousAngleLength = scalePrevious * angleLength;
        float scale = (float) currentCounts / totalStepNum;
        float currentAngleLength = scale * angleLength;
        setAnimation(previousAngleLength, currentAngleLength, animationLength);
        stepNumber=String.valueOf(currentCounts);
        setTextSize(currentCounts);
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
    public void setTextSize(int num) {
        String s = String.valueOf(num);
        int length = s.length();
        if (length <= 4) {
            numberTextSize = dipToPx(40);
        } else if (length > 4 && length <= 6) {
            numberTextSize = dipToPx(30);
        } else if (length > 6 && length <= 8) {
            numberTextSize = dipToPx(25);
        } else if (length > 8) {
            numberTextSize = dipToPx(20);
        }
    }

}
