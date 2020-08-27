package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;

public class SettingActivity extends AppCompatActivity {

    private Button mChange;
    private TextView mSetting,mBluetooth,mDisplay,mNotifications,mSound,mApps,mStorage;
    private Boolean flag=false;

    InputStream inputStream;

    //内部存储
    String Path = Environment.getExternalStorageDirectory().toString();
    //文件名字
    String FileName = Path + "/" + "string_Chinese.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mChange=findViewById(R.id.btnChange);
        mSetting=findViewById(R.id.settings);
        mBluetooth=findViewById(R.id.bluetooth);
        mDisplay=findViewById(R.id.display);
        mNotifications=findViewById(R.id.notifications);
        mSound=findViewById(R.id.sound);
        mApps=findViewById(R.id.apps);
        mStorage=findViewById(R.id.storage);

        try {
            inputStream= getAssets().open("string_Chinese.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            show(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=!flag;
                try {
                    show(flag);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void show(Boolean flag) throws IOException {
        if(!flag){
            inputStream= getAssets().open("string_Chinese.xml");
        }else {
            inputStream= getAssets().open("string_English.xml");
        }


//        mSetting.setText(initConfig(inputStream,"settings"));
        mBluetooth.setText(initConfig(inputStream,"bluetooth"));
//        mDisplay.setText(initConfig(inputStream,"display"));
        mNotifications.setText(initConfig(inputStream,"notifications"));
        mSound.setText(initConfig(inputStream,"sound"));
//        mApps.setText(initConfig(inputStream,"apps"));
//        mStorage.setText(initConfig(inputStream,"storage"));


    }


    /**
     * 获取config 文件中的配置项内容
     * @return
     */
    public String initConfig(InputStream inputStream,String tag) {
//        File configFile = new File(path);
        String tagValue = "";
//        if (!configFile.exists()) {
//            return tagValue;
//        }
        try {
//            InputStream inputStream = new FileInputStream(path);
//            InputStream inputStream= getAssets().open("string_Chinese.xml");
            System.out.println(inputStream);

            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(inputStream, "UTF-8");
            int type = xmlPullParser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:

                        String name = xmlPullParser.getName();
                        System.out.println("Start Tag " + name);

                        if("string".equals(name)){
                            if(tag.equals(xmlPullParser.getAttributeValue(0))){
                                tagValue = xmlPullParser.nextText();
                                return tagValue;
                            }
                        }
                        break;

                        /*for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                            System.out.print("< " + name + "> 的属性名:"
                                    + xmlPullParser.getAttributeName(i));
                            System.out.println(" 属性值为: "+xmlPullParser.getAttributeValue(i));


                            if(tag.equals(xmlPullParser.getAttributeValue(0))){
                                tagValue = xmlPullParser.nextText();
                                return tagValue;

                            }
                        }
                        break;*/

                        /*String startTagName = xmlPullParser.getName();
                        if(tag.equals(startTagName)) {
                            tagValue = xmlPullParser.nextText();
                            Log.i("fangjian",tagValue);
                            return tagValue;
                        }
                        break;*/
                }
                type = xmlPullParser.next();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return tagValue;
    }
}