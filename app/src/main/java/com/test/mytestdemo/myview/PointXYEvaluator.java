package com.test.mytestdemo.myview;

import android.animation.TypeEvaluator;

public class PointXYEvaluator implements TypeEvaluator<PointXY> {
    @Override
    public PointXY evaluate(float fraction, PointXY startValue, PointXY endValue) {
        int pointX= (int) (startValue.getPointX()+(endValue.getPointX()-startValue.getPointX())*fraction);
        int pointy= (int) (startValue.getPointY()+(endValue.getPointY()-startValue.getPointY())*fraction);
        return new PointXY(pointX,pointy);

    }
}
