package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity {

    private EditText mNAme;
    private EditText mAge;
    private Button mBtnInput;
    private Button mBtnOutput;
    private TextView mShow;

    private String name;
    private String age;
    private int index =1;

    //内部存储
    String Path = Environment.getExternalStorageDirectory().toString();
    //文件名字
    String FileName = Path + "/" + "info.txt";

    //创建文件
    File file = new File(FileName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        initView();
    }

    private void initView() {
        mNAme = findViewById(R.id.etName);
        mAge = findViewById(R.id.etAge);
        mBtnInput = findViewById(R.id.btnInput);
        mBtnOutput = findViewById(R.id.btnOutput);
        mShow = findViewById(R.id.tvShow);

        mBtnInput.setOnClickListener(onClick);
        mBtnOutput.setOnClickListener(onClick);

    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnInput:



                    BufferedWriter out = null;
                    try {
//                        FileOutputStream fos = openFileOutput("info.txt", MODE_PRIVATE);
                        name=mNAme.getText().toString();
                        age=mAge.getText().toString();
                        String input = index + "\t\t" + "姓名：" + name + "\t\t" + "年龄：" + age + "\n";
//                        fos.write(input.getBytes(StandardCharsets.UTF_8));
//                        Toast.makeText(FileActivity.this,"正在写入文件",Toast.LENGTH_SHORT).show();
//                        fos.close();

//                        File file = new File(Environment.getExternalStorageDirectory(),"TestFile.txt");
//                        File file = new File("data/data/com.example.demo/files/info.txt");
//                        if (!file.exists()) {
//                            file.getParentFile().mkdirs();
//                            file.createNewFile();
//                        }





                        if (!file.exists()) {
                            // 创建文件
                            try {
                                file.createNewFile();
                                Log.d("path", "create sucessful");
                            } catch (IOException e) {
                                Log.d("path", "create failed");
                                e.printStackTrace();
                            }
                        }
//                        out = new BufferedWriter(new OutputStreamWriter(
//                                new FileOutputStream(file, true)));
//                        out.write(input);

                        FileWriter writer = new FileWriter(file, true);
                        writer.write(input);
                        Toast.makeText(FileActivity.this,"正在写入文件"+FileName,Toast.LENGTH_SHORT).show();
                        index +=1;
                        writer.close();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnOutput:
//                    File file = new File(Path + "/" + "info.txt");
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        //把字节流转换成字符流
                        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                        StringBuilder sBuilder = new StringBuilder();
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            sBuilder.append(line);
                            sBuilder.append("\n");
                        }

                        String output = sBuilder.toString();
                        Toast.makeText(FileActivity.this,"正在读取文件"+file,Toast.LENGTH_SHORT).show();
                        mShow.setText(output);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }

        }
    };
}