package com.test.mytestdemo.multiThread;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    private static volatile ThreadPool instance;
    private ThreadPool(){
    }
    public ThreadPool getInstance(){
        if(instance==null){
            synchronized (this){
                if(instance==null){
                    instance=new ThreadPool();
                }
            }
        }
        return instance;
    }
    public static ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(2,6,5,
            TimeUnit.SECONDS,new ArrayBlockingQueue<>(5),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());



    ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(5);

    ScheduledFuture<?> schedule=scheduledExecutorService.schedule(new Runnable() {
        @Override
        public void run() {

        }
    },3,TimeUnit.SECONDS);

    ScheduledFuture<?> schedule2=scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {

        }
    },3,1,TimeUnit.SECONDS);

    ScheduledFuture<?> schedule3=scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
        @Override
        public void run() {

        }
    },3,1,TimeUnit.SECONDS);








    public static class Task implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            }catch (Exception e){

            }
            Log.e("ThreadPool",Thread.currentThread().getName()+" 执行任务");

        }
    }
    public static void test(){
        for(int i=0;i<10;i++){
            poolExecutor.execute(new Task());
            Future<?> submit=poolExecutor.submit(new Task());
        }

    }

    public void test2(){
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.e("","");

            }
        },100,1000);
        try {
            Thread.sleep(1000);
        }catch (Exception e){

        }
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();

            }
        },100,1000);
    }






}
