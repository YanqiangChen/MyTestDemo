package com.test.mytestdemo.decoration;

public class Pancakes extends ABpancakes{
    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 8;
    }
}
