package com.example.jcs.fish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Matrix2 extends View {
    Bitmap bitmap;
    Paint mPaint;
    Context context;

    public Matrix2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);   //创建bitmap
        canvas.drawBitmap(bitmap, matrix, mPaint);

        matrix.setScale(2, 2, 0, 0);
//        matrix.setSkew(0.3F, 0.3F);
        matrix.setRotate(30,50,50);

        canvas.drawBitmap(bitmap, matrix, mPaint);

    }
}
