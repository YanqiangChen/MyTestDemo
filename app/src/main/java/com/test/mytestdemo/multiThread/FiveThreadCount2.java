package com.test.mytestdemo.multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FiveThreadCount2 {
    private int[] arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    private int total=0;
    public void test() throws InterruptedException, ExecutionException {
        ExecutorService service= Executors.newFixedThreadPool(5);
        int length=arr.length;
        for(int i=0;i<length;i+=(length/5)) {
            FutureTask<Integer> task;
            if(i+(length/5)<length){
                task=new FutureTask<Integer>(new MyCallable(arr,i,i+(length/5)));
            }else{
                task=new FutureTask<Integer>(new MyCallable(arr,i,length));
            }
            service.execute(task);
            total+=task.get();
        }
        service.isShutdown();

    }

    public class MyCallable implements Callable<Integer>{
        int[] arr;
        int startIndex;
        int endIndex;
        public MyCallable(int[] arr,int startIndex,int endIndex){
            this.startIndex=startIndex;
            this.endIndex=endIndex;
            this.arr=arr;

        }
        @Override
        public Integer call() throws Exception {
            int sum=0;
            for(int i=startIndex;i<endIndex;i++){
                sum+=arr[i];
            }
            return sum;
        }
    }

}
