package com.test.mytestdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
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
import com.test.mytestdemo.recyclerView2.RecyclerViewActivity;
import com.test.mytestdemo.serializable.SerializableTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    @BaseTest(R.id.btn_test)
    @BindView(R.id.btn_test)
    public Button btn;

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
//                new SerializableTest().deserializeStudent();
//                new com.test.mytestdemo.reflection.Test().test9();
//                Intent intent=new Intent(MainActivity.this, RecyclerViewActivity.class);
//                startActivity(intent);
                Snackbar.make(getWindow().getDecorView(), "This is a message", Snackbar.LENGTH_LONG).show();




            }
        });
        checkPermission();
//        goToTest();
        goToTab();


    }

    public void goToTab(){
        Intent intent=new Intent(MainActivity.this,TestTabActivity.class);
        startActivity(intent);
    }
    public void goToTest(){
        Intent intent=new Intent(this,TestActivity.class);
        startActivity(intent);
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
}