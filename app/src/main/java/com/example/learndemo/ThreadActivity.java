package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.widget.TextView;

public class ThreadActivity extends AppCompatActivity {


    private TextView tvThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        tvThread=findViewById(R.id.tvThread);

//        mThread.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                tvThread.setText("正在同步时间");
                tvThread.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        }).start();

        new TimeThread().start(); //启动新的线程

    }

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    //在主线程里面处理消息并更新UI界面
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long sysTime = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("hh:mm:ss", sysTime);
                    tvThread.setText(sysTimeStr); //更新时间　　　　　　
                    break;
                default:
                    break;
            }
        }
    };


//    Thread mThread=new Thread(){
//        @Override
//        public void run() {
//            tvThread.setText("change");
//            tvThread.setTextColor(getResources().getColor(R.color.colorPrimary));
//
//        }
//    };

//    public static class MyThread extends Thread{
//
//        @Override
//        public void run() {
//             super.run();
//        }
//
//        public static void main(String[] args){
//            MyThread thread=new MyThread();
//            thread.start();
//        }
//    }


}