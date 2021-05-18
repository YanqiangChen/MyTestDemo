package com.example.jcs.fish;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class TestDrawable extends Drawable {

    private Paint mPaint;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    private Bitmap dstBitmap;
    private Bitmap srcBitmap;
    private int width = 400;
    private int height = 400;
    private Activity mActivity;

    public TestDrawable(Activity activity) {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(50);
        bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);

        dstBitmap = makeDst(width, height);
        srcBitmap = makeSrc(width, height);
        mActivity=activity;



    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void draw(Canvas canvas) {
//        drawPoint(canvas);
//        drawPath(canvas);
//        drawRect(canvas);
//        drawOval(canvas);
//        drawArc(canvas);

        //将文字卸载了bitmap上并没有将图片画在画布上，所以此时什么都不会有
//        bitmapCanvas.drawText("Wjx", 0,  100, mPaint);
        //将bitmap显示出来
//        canvas.drawBitmap(bitmap,0, 0, mPaint);

//        dstBitmap = makeDst(width, height);
//        srcBitmap = makeSrc(width, height);
//
//        canvas.drawColor(Color.GREEN);
//        int layerId = canvas.saveLayer(0, 0, width * 2, height * 2, mPaint, Canvas.ALL_SAVE_FLAG);
//        //在调用saveLayer()函数时，会生成一块全新的画布（Bitmap)，这块画布的大小就是我们指定的所要保存的大小。新生成的画布是全透明的，
//        // 在调用saveLayer()函数后所有的绘图操作都是在这块画布上进行的，调用drawXXX会生成一个透明的图层来专门绘制这个图形，
//        // 而每次生成的图层都会叠加到最近的画布上，调用XFermode算法会将计算好的画布整体覆盖在原始的画布上。
//
//        //去掉saveLayer()之后就不会 新建一个画布，所有的绘图操作都会在最底层的画布上进行操作，所以调用Xfermode算法的时候计算出来的图像是 针对的底层不透明的绿色背景
//        canvas.drawBitmap(dstBitmap, 0, 0 ,mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(srcBitmap, width/2, height/2, mPaint);
//        mPaint.setXfermode(null);
//        canvas.restoreToCount(layerId);

//        Bitmap bitmap2 = BitmapFactory.decodeResource(mActivity.getResources(), R.mipmap.ic_launcher, null);
//        canvas.drawBitmap(bitmap2, 0, 0, mPaint);
//        int layerId = canvas.saveLayer(0, 0, 400, 400, mPaint, Canvas.ALL_SAVE_FLAG);
//        //将新建的图层水平斜切45度，在进行绘画一个矩形,但是只是对新建的画布产生影响，并不会对原有的画布有影响
//        canvas.skew(1.732f, 0);
//        canvas.drawRect(0, 0, 150, 160, mPaint);
//        canvas.restoreToCount(layerId);

         //保存画布的位置信息
        canvas.save();
        //将画布旋转40度
        canvas.rotate(40);
        //画一个矩形
        canvas.drawRect(100, 0, 200, 100, mPaint);
        //恢复画布
        canvas.restore();
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(100, 0, 200, 100, mPaint);
    }


    /**
     * 创建一张矩形图片
     * @param width
     * @param height
     * @return
     */
    static Bitmap makeSrc(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFF66AAFF);
        c.drawRect(0, 0, width, height,paint);
        return bitmap;
    }

    /**
     * 创建一张圆形图片
     * @param width
     * @param height
     * @return
     */
    static Bitmap makeDst(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0 ,width, height), p);
        return bitmap;
    }


    public void drawArc(Canvas canvas){
        RectF rectF = new RectF(50,50,250,200);
        // 绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF,mPaint);
        // 绘制圆弧
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF,0,90,false,mPaint);

        RectF rectF2 = new RectF(50,250,250,400);
        // 绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF2,mPaint);
        // 绘制圆弧
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF2,0,90,true,mPaint);
    }
    public void drawOval(Canvas canvas){
        canvas.drawCircle(100,100,50,mPaint);
        canvas.drawOval(new RectF(180,50,280,120),mPaint);

    }
    public void drawRect(Canvas canvas){
        canvas.drawRect(new RectF(50,50,150,100),mPaint);
        canvas.drawRoundRect(new RectF(50,120,150,200),13,15,mPaint);

    }
    public void drawPath(Canvas canvas){
        // 在坐标(50,50)(50,250)之间绘制一条直线
        canvas.drawLine(50,50,50,250,mPaint);
        // 绘制一组线 每四数字(两个点的坐标)确定一条线
//        canvas.drawLines(new float[]{
//                100,50,200,50,
//                100,100,200,100,
//                100,150,200,150,
//                100,200,200,200,
//                100,250,200,250
//        },mPaint);

        //绘制数组中指点坐标的线 每四数字(两个点的坐标)确定一条线
        canvas.drawLines(new float[]{
                250,50,350,50,
                250,100,350,100,
                250,150,350,150,
                250,200,350,200,
                250,250,350,250
        },12,8,mPaint);




    }

    public void drawPoint(Canvas canvas){
        canvas.drawPoint(50,50,mPaint);
        //绘制一组点，坐标位置由float数组指定
        canvas.drawPoints(new float[]{
                50,100,
                100,100,
                150,100,
                200,100,
                250,100,
        },mPaint);
        //绘制一组点中指定的几个坐标
        canvas.drawPoints(new float[]{
                50,150,
                100,150,
                150,150,
                200,150,
                250,150,
        },2,4,mPaint);

    }
}
