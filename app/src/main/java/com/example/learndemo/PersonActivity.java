package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class PersonActivity extends AppCompatActivity {

    private Button mSwitch;
    private TextView mPerson,mAge,mSex;
    private boolean Flag=false;
    private String mText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        mSwitch=findViewById(R.id.btnSwitch);
        mPerson=findViewById(R.id.tvPerson);
        mAge=findViewById(R.id.tvAge);
//        mSex=findViewById(R.id.tvSex);

        show(Flag);

        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flag= !Flag;
                show(Flag);
            }
        });

    }

    private void show(boolean flag) {

        try {
            InputStream in = getAssets().open("person.xml"); //通过上下文，获取资产的管理者
            List<Person> personList = PersonRelease.parserXml(in); //调用定义的xml业务方法

            StringBuffer sb = new StringBuffer();
            if(!flag){
                for(Person person:personList){
                    if(person.getId().equals("0"))
                        sb.append(person.toChineseString());
                }
                mText="切换为英文";
            }else{
                for(Person person:personList){
                    if(person.getId().equals("1"))
                        sb.append(person.toEnglishString());
                }
                mText="切换为中文";
            }
            mSwitch.setText(mText);
            mPerson.setText(sb.toString());
            mAge.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}