package com.test.mytestdemo.multiThread;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {

    private static ReentrantLock mLock=new ReentrantLock();
    private static Condition condition=mLock.newCondition();
    private static boolean isFlag=true;
    private static final ThreadLocal<Integer> sThreadLocal = new ThreadLocal<Integer>();


    public void test(){
        OneThread oneThread=new OneThread();
        TwoThread twoThread=new TwoThread();
        oneThread.start();
        twoThread.start();
        sThreadLocal.get();
    }
    public static class OneThread extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    mLock.lock();
                    if(!isFlag){
                        condition.await();
                    }
//                    System.out.println("OneThread 被执行");
                    Log.e("Thread","OneThread 被执行");
                    isFlag=false;
                    condition.signal();
                }catch (Exception e){
                }finally {
                    mLock.unlock();
                }
            }
        }
    }
    public static class TwoThread extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    mLock.lock();
                    if(isFlag){
                        condition.await();
                    }
//                System.out.println("TwoThread 被执行");
                    Log.e("Thread","TwoThread 被执行");
                    isFlag=true;
                    condition.signal();
                }catch (Exception e){

                }finally {
                    mLock.unlock();
                }
            }

        }
    }

}
