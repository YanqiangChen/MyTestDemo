package com.test.mytestdemo.multiThread;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
    public static final ThreadLocal<String> THREAD_LOCAL=new ThreadLocal<>();

    public void test(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        try {
            THREAD_LOCAL.set("wupx");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String value=THREAD_LOCAL.get();
                    THREAD_LOCAL.set("huxy");
                    value=THREAD_LOCAL.get();
                    Log.e("ThreadLocal","Thread："+value);

                }
            }).start();
            Thread.sleep(1000);
            Log.e("ThreadLocal","MainThread："+THREAD_LOCAL.get());

        }catch (Exception e){

        }



    }

}
