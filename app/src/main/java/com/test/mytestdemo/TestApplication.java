package com.test.mytestdemo;

import android.app.Application;

public class TestApplication extends Application {
    public static TestApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;

    }

    public static TestApplication getContext(){
        return application;

    }
}
