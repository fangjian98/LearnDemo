package com.example.learndemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ParserActivity extends AppCompatActivity {

    private EditText etXml;
    private Button mSave,mParser,mDraw;
    private TextView tvParser;

    //内部存储
    String Path = Environment.getExternalStorageDirectory().toString();
    //文件名字
    String FileName = Path + "/" + "Message.xml";
    //创建文件
    File file = new File(FileName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);

        etXml=findViewById(R.id.etXml);
        mSave=findViewById(R.id.btnSave);
        mParser=findViewById(R.id.btnParser);
        mDraw=findViewById(R.id.btnDraw);
        tvParser=findViewById(R.id.tvParser);

        mSave.setOnClickListener(onClick);
        mParser.setOnClickListener(onClick);
        mDraw.setOnClickListener(onClick);

        try {
            read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void read() throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(file);
        //把字节流转换成字符流
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        StringBuilder sBuilder = new StringBuilder();
        String line = "";
        while (true) {
            try {
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sBuilder.append(line);
            sBuilder.append("\n");
            String output = sBuilder.toString();
            etXml.setText(output);
        }
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnSave:
                    String input=etXml.getText().toString();
                    FileWriter writer = null;
                    try {
                        writer = new FileWriter(file, false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        writer.write(input);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnParser:
                    try {
                        //通过上下文，获取资产的管理者
//            InputStream in = getAssets().open("Message.xml");
                        FileInputStream in = new FileInputStream(file);
                        //【2】调用定义的xml业务方法
                        List<Message> msList = MessageRelease.releasexml(in);
                        StringBuffer sb = new StringBuffer();
                        for(Message msg:msList){
                            sb.append(msg.toString());
                        }
                        tvParser.setText(sb.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnDraw:
                    startActivity(new Intent(ParserActivity.this, ThreadActivity.class));
                    break;
                default:
                    break;
            }
        }
    };
}