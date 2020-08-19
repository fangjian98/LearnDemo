package com.example.learndemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CallbackActivity extends AppCompatActivity implements Employee.Callback {
    private static final String TAG = "CallbackActivity";
    private Employee employee;
    private Button mCall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);

        employee=new Employee();
        employee.setCallback(CallbackActivity.this);

        mCall=findViewById(R.id.btnCall);
        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                employee.doWork();

            }
        });

        /*
         * 为Employee设置回调函数, 在这里定义具体的回调方法
         */
        /*Employee employee=new Employee();
        employee.setCallback(this);
        employee.doWork();*/
    }

    @Override
    public void work(int a) {
        System.out.println("work"+a);
        Log.d(TAG, "onShown: ok !!!");
    }
}