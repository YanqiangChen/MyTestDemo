package com.test.mytestdemo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.test.mytestdemo.R;

import androidx.annotation.Nullable;

class TextDraw extends View {

    Paint paint;

    public TextDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public void initPaint(){
        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.black));
        paint.setStrokeWidth(0);
        paint.setTextSize(36);
        paint.setTypeface(Typeface.DEFAULT_BOLD);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String str="Test";
        //根据paint的文字大小获得Paint.FontMetricsInt
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();

        //根据Paint.FontMetricsInt计算baseline
        canvas.drawText(str, getWidth() / 2 - paint.measureText(str) / 2 ,
                getHeight() / 2  +(fm.bottom - fm.top)/2 - fm.bottom, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 手指按下
                break;
            case MotionEvent.ACTION_MOVE:
                // 手指移动
                break;
            case MotionEvent.ACTION_UP:
                // 手指抬起
                break;
            case MotionEvent.ACTION_CANCEL:
                // 事件被拦截
                break;
            case MotionEvent.ACTION_OUTSIDE:
                // 超出区域
                break;
        }
        return super.onTouchEvent(event);

    }
}
