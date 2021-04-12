package com.test.mytestdemo.multiThread;

import android.util.Log;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool2 {
    public static class MyThread implements Runnable{
        public String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);

            }catch (InterruptedException e){

            }
            Log.e("ThreadPool2","线程："+Thread.currentThread().getName()+" 执行"+name);
        }
    }
    ThreadPoolExecutor executor=new ThreadPoolExecutor(1,2,0, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(2),new ThreadPoolExecutor.DiscardPolicy());

    public void test(){
        for(int i=0;i<6;i++){
            executor.execute(new MyThread("线程"+i));
        }
    }


}
