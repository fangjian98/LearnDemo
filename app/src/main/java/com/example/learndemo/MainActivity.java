package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnParser;
    private Button mBtnHandler;
    private Button mBtnFile;
    private Button mBtnThread;
    private Button mBtnProduce;
    private Button mBtnCallback;
    private Button mBtnNesting;
    private Button mBtnKeyevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnParser = findViewById(R.id.btnParser);
        mBtnHandler=findViewById(R.id.btnHandler);
        mBtnFile = findViewById(R.id.btnFile);
        mBtnThread = findViewById(R.id.btnThread);
        mBtnProduce=findViewById(R.id.btnProduce);
        mBtnCallback = findViewById(R.id.btnCallback);
        mBtnNesting = findViewById(R.id.btnNesting);
        mBtnKeyevent = findViewById(R.id.btnKeyEvent);

        mBtnParser.setOnClickListener(onClick);
        mBtnHandler.setOnClickListener(onClick);
        mBtnFile.setOnClickListener(onClick);
        mBtnThread.setOnClickListener(onClick);
        mBtnProduce.setOnClickListener(onClick);
        mBtnCallback.setOnClickListener(onClick);
        mBtnNesting.setOnClickListener(onClick);
        mBtnKeyevent.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnParser:
                    startActivity(new Intent(MainActivity.this, ParserActivity.class));
                    break;
                case R.id.btnHandler:
                    startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                    break;
                case R.id.btnFile:
                    startActivity(new Intent(MainActivity.this, FileActivity.class));
                    break;
                case R.id.btnThread:
                    startActivity(new Intent(MainActivity.this, ThreadActivity.class));
                    break;
                case R.id.btnProduce:
                    startActivity(new Intent(MainActivity.this, ProduceActivity.class));
                    break;
                case R.id.btnCallback:
                    startActivity(new Intent(MainActivity.this, CallbackActivity.class));
                    break;
                case R.id.btnNesting:
                    startActivity(new Intent(MainActivity.this, NestingActivity.class));
                    break;
                case R.id.btnKeyEvent:
                    startActivity(new Intent(MainActivity.this, KeyeventActivity.class));
                    break;
                default:
                    break;
            }

        }
    };

}