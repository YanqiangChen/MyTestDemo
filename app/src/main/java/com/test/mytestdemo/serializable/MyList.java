package com.test.mytestdemo.serializable;

import java.io.Serializable;
import java.util.Arrays;

public class MyList implements Serializable {
    private String name;
    private transient Object[] arr;
    public MyList(){
    }
    public MyList(String name){
        this.name=name;
        this.arr = new Object[100];
        /*
        给前面30个元素进行初始化
         */
        for (int i = 0; i < 30; i++) {
            this.arr[i] = i;
        }
    }

    @Override
    public String toString() {
        return "MyList{" +
                "name='" + name + '\'' +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        s.defaultWriteObject();
        for (int i = 0; i < 30; i++) {
            s.writeObject(arr[i]);
        }
    }
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        arr = new Object[30];
        for (int i = 0; i < 30; i++) {
            arr[i] = s.readObject();
        }
    }

}
