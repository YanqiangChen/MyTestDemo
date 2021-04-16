package com.test.mytestdemo.serializable;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.test.mytestdemo.TestApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {
    File sdcardDir = Environment.getExternalStorageDirectory();
    File cacheDir = TestApplication.getContext().getExternalCacheDir();
    String cache=cacheDir.getAbsolutePath() + File.separator + "recorder";
    public void test(){
        try {
            Student s1=new Student("张三",23);
            Student s2=new Student("李四",24);  //File.separator
            File cacheDir2 = new File(cache);
            if (!cacheDir2.exists()) {
                cacheDir2.mkdirs();
            }
            ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(cacheDir2+File.separator + "test.txt"));
            os.writeObject(s1);
            os.writeObject(s2);
            os.close();
        }catch (IOException e){
            Log.e("","");

        }

    }
    public void deserializeStudent(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cache+File.separator+"test.txt"));
            Student student = (Student) ois.readObject();
            ois.close();


        }catch (Exception exception){
            Log.e("","");
        }

    }
    public void test2(){
        try {
            System.out.println("序列化...");
            MyList myList = new MyList("ArrayList");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cache+File.separator+"test.txt"));
            oos.writeObject(myList);
            oos.flush();
            oos.close();
        }catch (Exception e){

        }

    }
    public void deserializeStudent2(){
        try{
            System.out.println("反序列化...");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cache+File.separator+"test.txt"));
            MyList myList = (MyList) ois.readObject();
            ois.close();
            System.out.println(myList);
        }catch (Exception e){

        }

    }

}
