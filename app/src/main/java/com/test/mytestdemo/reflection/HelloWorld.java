package com.test.mytestdemo.reflection;

import android.util.Log;

public class HelloWorld implements Hello{
    @Override
    public void moring(String name) {
        Log.e("name",name);
    }
}
