package com.test.mytestdemo.myview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.view.View;
import android.widget.ImageView;

public class Test {

    public void testValue(View view, ImageView imageView){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setTarget(view);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float timeAnim= (float) animation.getAnimatedValue();
                if(timeAnim<0.4f){
                    imageView.setTranslationY(-1000*timeAnim);
                }else if(timeAnim>=0.4f && timeAnim<=0.8f){
                    imageView.setScaleX((float) Math.abs((timeAnim-0.6)/0.2));
                }else {
                    imageView.setTranslationY(-1000*(1-timeAnim));
                }
            }
        });
    }

    public void testValue2(View view){
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointXYEvaluator(),
                new PointXY(view.getLeft(),view.getTop()),new PointXY(view.getLeft()+200,view.getTop()+200));
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointXY pointValue= (PointXY) animation.getAnimatedValue();
                view.setTranslationX(pointValue.getPointX());
                view.setTranslationY(pointValue.getPointY());
            }
        });

        valueAnimator.start();
    }

    public void testValue3(ImgMoveView view){
        Path translationPath=new Path();
        translationPath.moveTo(0,0);
        translationPath.lineTo(200, 300);

        ObjectAnimator pathMoveAnim= ObjectAnimator.ofObject(view, ImgMoveView.POSITION,
                null, translationPath);
        pathMoveAnim.start();

    }



}
