package com.test.mytestdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rmondjone.annotation.BindView;
import com.test.mytestdemo.annotation.BaseTest;
import com.test.mytestdemo.annotation.BindViewTool;
import com.test.mytestdemo.annotation.InjectTimeStatistics;
import com.test.mytestdemo.annotation.MyTest;
import com.test.mytestdemo.annotation.Test;
import com.test.mytestdemo.arithmetic.Calculate;
import com.test.mytestdemo.multiThread.TestThread;
import com.test.mytestdemo.multiThread.ThreadLocalTest;
import com.test.mytestdemo.multiThread.ThreadPool2;
import com.test.mytestdemo.serializable.SerializableTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    @BaseTest(R.id.btn_test)
    @BindView(R.id.btn_test)
    public Button btn;
    private GestureDetector detector;
    private Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager windowManager;



    @InjectTimeStatistics("test_123")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAnnotation();
//        getView();
        BindViewTool.bind(this);
        btn.setText("SBB");

        int[] maxArray = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int test=new Calculate().maxSubArray2(maxArray);
//        new ThreadPool2().test();
        int[] arr = new int[]{3,2,1,0,4};

        new Calculate().generateMatrix(3);
        new ThreadLocalTest().test();
//        [[1,2],[3,5],[6,7],[8,10],[12,16]]
//[4,8]
        int[][] arrMerge=new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        new Calculate().addToIntervals(arrMerge,new int[]{4,8});
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SerializableTest().deserializeStudent();
                new com.test.mytestdemo.reflection.Test().test9();
                Intent intent=new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                new com.test.mytestdemo.myview.Test().testValue(findViewById(R.id.iv_girl),(ImageView) findViewById(R.id.iv_girl));
//                new com.test.mytestdemo.myview.Test().testValue2(findViewById(R.id.iv_girl));
//                Intent intent=new Intent(MainActivity.this, RecyclerViewActivity.class);
//                startActivity(intent);

            }
        });
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
        checkPermission();
        iniGestureListener();
        goToTest();
        mFloatingButton=new Button(this);
        mFloatingButton.setText("button");
        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,0,0,
                PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        windowManager.addView(mFloatingButton,mLayoutParams);




    }

    public void goToTest(){
        Intent intent=new Intent(this,TestActivity.class);
        startActivity(intent);
    }


    private void iniGestureListener() {
        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(MainActivity.this, "double  click up!",Toast.LENGTH_SHORT);
                return super.onDoubleTap(e);
            }
        };
        detector = new GestureDetector(MainActivity.this, listener);
    }

    public void checkPermission(){
        int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            String[] str=new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(MainActivity.this, str, 1);
        }

    }

    //注解
    public void getView(){
        //获得成员变量
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                //判断注解
                if (field.getAnnotations() != null) {
                    //确定注解类型
                    if (field.isAnnotationPresent(BaseTest.class)) {
                        //允许修改反射属性
                        field.setAccessible(true);
                        BaseTest getViewTo = field.getAnnotation(BaseTest.class);
                        //findViewById将注解的id，找到View注入成员变量中
                        field.set(this, findViewById(getViewTo.value()));
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    public void getAnnotation(){
        Method[] methods = Test.class.getMethods();
        Field[] fields = Test.class.getDeclaredFields();
        for (Field field : fields) {
            MyTest annotation = field.getAnnotation(MyTest.class);
            if(annotation!=null){
                System.out.println("property="+annotation.name());
            }
        }
        for (Method method : methods) {
            MyTest annotation = method.getAnnotation(MyTest.class);
            if(annotation!=null){
                System.out.println("sayHello="+annotation.name());
            }
        }




    }

    public void testZgyote(){
        try {
            String abi = "arm64-v8a";
            //process        反射调用android.os.Process类，获取openZygoteSocketIfNeeded方法
            Class<?> ProcessClazz = Class.forName("android.os.Process");
            Method method = ProcessClazz.getDeclaredMethod("openZygoteSocketIfNeeded", String.class);
            method.setAccessible(true);

            //ZygoteState
            Class<?> ZygoteStateClazz = Class.forName("android.os.Process$ZygoteState");
            Field abilistfeild=ZygoteStateClazz.getDeclaredField("abiList");
            abilistfeild.setAccessible(true);

            //连接zygote，返回一个ZygoteState的对象
            Object ZygoteStateobj=method.invoke(null,abi);

            //获取ZygoteState的abiList值，他的值就是cpu的架构
            List<String> abilist= (List<String>) abilistfeild.get(ZygoteStateobj);
            for(int i = 0 ;i < abilist.size();i++){
                Log.i("Zygote","hehe "+ " "+ abilist.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getStackTrace();
            Log.i("Zygote","error="+e.toString());
        }
    }
}