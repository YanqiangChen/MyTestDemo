package com.test.mytestdemo;

import android.app.Activity;
import android.os.Bundle;


import com.test.mytestdemo.myview.MyItemDecoration;
import com.test.mytestdemo.myview.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends Activity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test121);
        mRecyclerView=findViewById(R.id.recyler_test);
        List<String> stringList=new ArrayList<>();
        for(int i=0;i<10;i++){
            stringList.add(i+"");
        }
        MyRecyclerAdapter adapter=new MyRecyclerAdapter(this,stringList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new MyItemDecoration(this,RecyclerView.VERTICAL));


    }
}
