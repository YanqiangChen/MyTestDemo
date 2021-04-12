package com.test.mytestdemo.multiThread;

import android.os.AsyncTask;

public class AsynTaskTest {
    AsyncTask<String,Integer,String> asyncTask;
    public void test(){
        asyncTask = new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute("");
    }
}
