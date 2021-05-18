package com.test.mytestdemo.myview;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

import androidx.annotation.Nullable;

import static org.xmlpull.v1.XmlPullParserFactory.PROPERTY_NAME;

class ImgMoveView extends View {
    public ImgMoveView(Context context) {
        super(context);
    }

    public ImgMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPosition(PointF position){
        int x = Math.round(position.x);
        int y = Math.round(position.y);
        setTranslationX(x);
        setTranslationY(y);
    }
    public PointF getPosition(){
        return new PointF(getX(),getY());
    }
    /**
     * A Property wrapper around the position functionality handled by the
     * ImgMoveView#setPosition(PointF) and ImgMoveView#getPosition() methods.
     */
    public static final Property<ImgMoveView, PointF> POSITION = new Property<ImgMoveView, PointF>(PointF.class,PROPERTY_NAME) {
        @Override
        public PointF get(ImgMoveView object) {
            return object.getPosition();

        }
        @Override
        public void set(ImgMoveView object, PointF value) {
            object.setPosition(value);
        }
    };

}
