package com.example.jcs.fish;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {
    private Paint mPaint;

    public TestView(Context context) {
        super(context);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);

    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);

    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(LAYER_TYPE_SOFTWARE,null);

//        mPaint.setColor(Color.GREEN);
//        canvas.save();
//        canvas.rotate(40);
//        canvas.drawRect(100,0,200,100,mPaint);
//        canvas.restore();
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(100,0,200,100,mPaint);

//        canvas.save();
//        canvas.clipRect(100,0,200,100);
//        canvas.drawColor(Color.GREEN);
//        canvas.restore();
//        canvas.drawColor(Color.YELLOW);


    }
}
