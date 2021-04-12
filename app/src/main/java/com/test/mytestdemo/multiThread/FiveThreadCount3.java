package com.test.mytestdemo.multiThread;

import java.util.concurrent.CountDownLatch;

public class FiveThreadCount3 {
    private int[] arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    private int total=0;
    public void test() throws InterruptedException{
        int length=arr.length;
        CountDownLatch latch=new CountDownLatch(length%5==0?5:6);
        for(int i=0;i<length;i+=(length/5)){
            MyThread task;
            if(i+length/5<=length){
                task=new MyThread(arr,i,i+length/5,latch);
            }else{
                task=new MyThread(arr,i,length,latch);
            }
            new Thread(task).start();
        }
        latch.await();
    }
    public class MyThread implements Runnable{
        int[] arr;
        int startIndex;
        int endIndex;
        CountDownLatch latch;

        public MyThread(int[] arr, int startIndex, int endIndex, CountDownLatch latch) {
            this.arr = arr;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.latch = latch;
        }

        @Override
        public void run() {
            int sum=0;
            for(int i=startIndex;i<endIndex;i++){
                sum+=arr[i];
            }
            synchronized (MyThread.class){
                total+=sum;
            }
            latch.countDown();


        }
    }

}

