package com.test.mytestdemo;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import tablayout.huangbo.com.tablayout.HbElasticView;
import tablayout.huangbo.com.tablayout.MyIndicator;


public class TestTabActivity extends Activity {
    MyIndicator myIndicator;
    ScrollView srolview;
    HbElasticView mHbElasticView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mHbElasticView = (HbElasticView) findViewById(R.id.hb);
        myIndicator = (MyIndicator) findViewById(R.id.my);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.add("tab5", 10);
                myIndicator.setChanger(4);
                Toast.makeText(TestTabActivity.this, "添加tab5", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_speed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.setAnimationTime(100);
                Toast.makeText(TestTabActivity.this, "修改移动速度改为100", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_jinzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.setProhibitPositio(2);
                Toast.makeText(TestTabActivity.this, "禁止tab3修改状态", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_length).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.setFull();
                Toast.makeText(TestTabActivity.this, "修改下标线的长度铺满", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.resetNomalColor(0xff000000,0xffffffff,0xff000000);
            }
        });

    }

    private class Service extends IntentService {

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        public Service(String name) {
            super(name);
        }

        @Override
        public void onCreate() {
            super.onCreate();
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {

        }
    }
}

