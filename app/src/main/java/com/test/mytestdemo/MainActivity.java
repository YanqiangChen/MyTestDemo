package com.test.mytestdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.rmondjone.annotation.BindView;
import com.test.mytestdemo.annotation.BaseTest;
import com.test.mytestdemo.annotation.BindViewTool;
import com.test.mytestdemo.annotation.InjectTimeStatistics;
import com.test.mytestdemo.annotation.MyTest;
import com.test.mytestdemo.annotation.Test;
import com.test.mytestdemo.multiThread.TestThread;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        new TestThread().test();

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