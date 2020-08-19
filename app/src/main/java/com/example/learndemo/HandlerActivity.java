package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = "HandlerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        //在主线程中开启handler
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "主线程：" + Thread.currentThread().getName());
                    }
                });
            }
        }).start();

        //创建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "子线程：" + Thread.currentThread().getName());
                    }
                });
                Looper.loop();
            }
        });
    }
}