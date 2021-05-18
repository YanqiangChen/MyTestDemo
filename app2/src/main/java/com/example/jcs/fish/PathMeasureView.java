package com.example.jcs.fish;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class PathMeasureView extends View {
    private Paint mPaint;
    private Path path;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private float mLength;
    private float[] pos;
    private float[] tan;
    private Bitmap bitmap;

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        path = new Path();
        path.moveTo(0, 0);
        path.quadTo(100, 200, 200, 0);
        path.quadTo(300, -200, 400, 0);
        mPathMeasure = new PathMeasure(path, false);    //初始化PathMeasure
        mLength = mPathMeasure.getLength();     //获取Path长度
        pos = new float[2];  //new数组
        tan = new float[2];  //new数组
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);   //创建bitmap

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(200, 400);
        canvas.drawPath(path, mPaint);
        mPathMeasure.getPosTan(mLength * mAnimatorValue, pos, tan);   //获取某一长度 pos获取点的坐标 tan获取点的正切值
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        canvas.rotate(degrees, pos[0], pos[1]);
        canvas.drawBitmap(bitmap, pos[0] - bitmap.getWidth()/2, pos[1] - bitmap.getHeight(), mPaint);
        canvas.restore();
    }


}
