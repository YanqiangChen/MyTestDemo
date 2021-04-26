package com.test.mytestdemo.reflection;

import android.util.Log;

import com.test.mytestdemo.serializable.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public void test(){
        Class cls = String.class;   //直接通过Class的静态变量获取

        String s = "Hello";   //实例变量的getClass获取
        Class cls2 = s.getClass();

        try {
            Class cls3 = Class.forName("java.lang.String");  //知道类的完整类名
        }catch (ClassNotFoundException e){

        }

    }

    public void test2(){
        try {
            Class studentClass=StudentTest.class;
            Field score=studentClass.getField("score");   //获取score public
            String scoreName=score.getName();
            Class scoreType=score.getType();
            int scoreModifiers=score.getModifiers();




            Field name=studentClass.getField("name");
            Field gradle=studentClass.getDeclaredField("gradle");  //获取private 字段gradle

            StudentTest studentTest=new StudentTest();
            studentTest.name="小莉同学";
            studentTest.setGradle("100");
            String strName=(String)name.get(studentTest);   //获取字段值
            gradle.setAccessible(true);  //private字段不能够访问，设置以后可以访问 可能会失败
//            String strGradle=(String)gradle.get(studentClass);
//            Log.e("strGradle",strGradle);

            name.set(studentTest,"莉莉");
            String nameChanged=studentTest.getName();
            Log.e("nameChanged",nameChanged);

        }catch (Exception e){
            Log.e("","");

        }

    }
    public void test3(){
        try {
            Class studentClass=StudentTest2.class;
            Method methodScore = studentClass.getMethod("getScore",String.class);
            Method methodName = studentClass.getMethod("getName",String.class);
            Method methodGradle = studentClass.getDeclaredMethod("getGrade",int.class);
            String scoreName = methodScore.getName();
            Class returnType = methodScore.getReturnType();
            Class[] paramTypes = methodScore.getParameterTypes();
            int scoreModifiers = methodScore.getModifiers();
            Log.e("","");

            String s = "Hello world";
            Class strClass=s.getClass();
            Method subStr=strClass.getMethod("substring",int.class);
            String str=(String) subStr.invoke(s,6);
            Log.e("str",str);



        }catch (Exception e){
            Log.e("","");

        }




    }

    public void test4(){
        try {
            Method m = Integer.class.getMethod("parseInt", String.class);   //调用静态方法
            Integer n = (Integer) m.invoke(null, "12345");

        }catch (Exception e){

        }


    }
    public void test5(){
        try {
            StudentTest studentTest=StudentTest.class.newInstance();

        }catch (Exception e){

        }

    }
    public void test6(){
        try {
            Constructor<Integer> constructor=Integer.class.getConstructor(int.class);
            int num=constructor.newInstance(123);

            Constructor<Integer> constructor2=Integer.class.getConstructor(String.class);
            int num2=constructor2.newInstance("123");

            Log.e("","");


        }catch (Exception e){

        }

    }
    public void test8(){
        Class i = Integer.class;
        Class n = i.getSuperclass();
        Class o = n.getSuperclass();
        Log.e("","");

        Class s = Integer.class;
        Class[] is = s.getInterfaces();
        for (Class inter : is) {
            Log.e("inter",inter.getName());
        }


    }
    public void test9(){
        Hello hello=new HelloWorld();
        hello.moring("test");

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("morning")) {
                    Log.e("",""+args[0]);
                }
                return null;
            }
        };
        Hello hello2 = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[] { Hello.class }, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        hello2.moring("Bob");



    }


}
