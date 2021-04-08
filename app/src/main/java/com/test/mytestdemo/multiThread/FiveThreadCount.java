package com.test.mytestdemo.multiThread;

import android.util.Log;

public class FiveThreadCount {
    private int count=0;
    private int[] arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
    private int j=0;
    private class myThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(j<arr.length){
                synchronized (this){
                    if(j>arr.length){
                        return;
                    }
                    count+=arr[j];
                    j++;
                    Log.e("FiveThreadCount",Thread.currentThread().getName());
                }
            }
        }
    }

}

