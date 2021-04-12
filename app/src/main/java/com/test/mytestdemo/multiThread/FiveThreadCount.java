package com.test.mytestdemo.multiThread;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FiveThreadCount {
    private int count=0;
    private int[] arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
    private int j=0;
    private class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(j<arr.length){
                synchronized (this){
                    if(j>=arr.length){
                        return;
                    }
                    count+=arr[j];
                    j++;
                    Log.e("FiveThreadCount",Thread.currentThread().getName());
                }
            }
        }
    }
    public void test(){
        for(int i=0;i<5;i++){
            new MyThread().start();
        }
    }
    public void test2(){
        Thread thread=new MyThread();
        for(int i=0;i<5;i++){
            new Thread(thread).start();
        }
    }
    public void test3(){
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            service.execute(new MyThread());
        }
    }
    public void test4(){
        ExecutorService service= Executors.newCachedThreadPool();
        Thread thread=new MyThread();
        for(int i=0;i<5;i++){
            service.execute(thread);
        }

    }

}

