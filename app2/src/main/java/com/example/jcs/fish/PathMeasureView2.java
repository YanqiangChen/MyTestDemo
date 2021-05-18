package com.example.jcs.fish;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class PathMeasureView2 extends View {
    private Paint mPaint;
    private Path path;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;

    public PathMeasureView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        path = new Path();
        path.addCircle(0, 0, 100, Path.Direction.CW);
        mPathMeasure = new PathMeasure(path, false);
        mDst = new Path();
        mLength = mPathMeasure.getLength();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 2);
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

        mDst.reset();
        float start, stop;
        if (mAnimatorValue <= 1) {
            start = 0;
            stop = mLength * mAnimatorValue;
        } else {  //1-2
            start = (mAnimatorValue - 1) * mLength;
            stop = mLength;
        }
        mPathMeasure.getSegment(start, stop, mDst, true);
        canvas.translate(200, 400);
        canvas.drawPath(mDst, mPaint);
    }

}
