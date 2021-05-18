package cn.sadhu.stickyheaderdemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by sadhu on 2016/9/21.
 * Email static.sadhu@gmail.com
 */
public class MyApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            Fresco.initialize(this);
    }
}

