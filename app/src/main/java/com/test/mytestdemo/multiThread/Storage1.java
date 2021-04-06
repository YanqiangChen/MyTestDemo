package com.test.mytestdemo.multiThread;

import android.util.Log;

import java.util.LinkedList;

public class Storage1 implements AbstractStorage{
    //仓库最大容量
     private final int MAX_SIZE = 100;
     //仓库存储的载体
     private LinkedList list = new LinkedList();

    //生产产品
    @Override
    public void produce(int num) {
        synchronized (list){
            while (list.size()+num > MAX_SIZE){
                Log.e("Thread","【要生产的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行生产任务!");
                try {
                    list.wait();
                }catch (Exception e){

                }
            }
            for(int i=0;i<num;i++){
                list.add(new Object());
            }
            Log.e("Thread","【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());
            list.notifyAll();

        }

    }

    @Override
    public void consume(int num) {
        synchronized (list){
            while (num>list.size()){
                Log.e("Thread","【要消费的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行生产任务!");
                try {
                    list.wait();
                }catch (Exception e){

                }
            }
            for(int i=0;i<num;i++){
                list.remove();
            }
            Log.e("","【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }

    }
}
