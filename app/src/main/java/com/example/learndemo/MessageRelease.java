package com.example.learndemo;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MessageRelease {
    /*
     * 服务器端以流的形式把数据返回的
     */
    public static List<Message> releasexml(InputStream in) throws Exception {
        //【0】声明集合对象和类对象
        List<Message> msgList = null;
        Message msg = null;
        /*
         * 一行一行的进行解析 【1】获取XmlPullParser解析的实例
         */
        XmlPullParser parser = Xml.newPullParser();
        // 【2】设置XmlPullParser的参数
        parser.setInput(in, "utf-8");
        // 【3】获取事件类型
        //int type = parser.getEventType();这行代码在其他很多人的代码中都有，但是这里其实将type写死了（即type是一个常量），导致在后面的循环当中无限循环
        //在这个while循环中，将数据封装成Message的对象存到集合msgList当中
        //while (type !=XmlPullParser.END_DOCUMENT) {
        while (parser.getEventType()!=XmlPullParser.END_DOCUMENT) {
            //xml可能因为格式不规范出现parser读一行为null的情况
            if(parser.getName()!=null) {
                //Log.i("tag", "22222"+parser.getName()+parser.getEventType());
                switch (parser.getEventType()) {
                    case XmlPullParser.START_TAG:// 解析开始标签
                        // 【4】具体判断解析到了哪个结点
                        if("root".equals(parser.getName())){
                            //【5】创建一个集合对象
                            Log.i("tag", "<root>");
                            msgList = new ArrayList<Message>();
                        }else if ("msg".equals(parser.getName())) {
                            //【6】创建Message对象
                            Log.i("tag", "<msg>");
                            msg = new Message();
                            //【7】获取id值
                            String id = parser.getAttributeValue(0);
                            msg.setId(id);
                        }else if ("address".equals(parser.getName())) {
                            //【8】获取adress的数据
                            Log.i("tag", "<address>");
                            String address = parser.nextText();
                            msg.setAddress(address);
                        }else if ("body".equals(parser.getName())) {
                            //【8】获取body的数据
                            Log.i("tag", "<body>");
                            String body = parser.nextText();
                            msg.setBody(body);
                        }else if ("date".equals(parser.getName())) {
                            //【8】获取date的数据
                            Log.i("tag", "<date>");
                            String date = parser.nextText();
                            msg.setDate(date);
                        }
                        break;
                    case XmlPullParser.END_TAG:// 解析结束标签
                        //具体判断要解析的结束标签
                        if("msg".equals(parser.getName())){
                            //把javabean对象放入集合当中
                            msgList.add(msg);
                            msg=null;
                            Log.i("tag", "************");
                        }
                        break;
                }
            }
            // 不停地向下解析
            parser.next();
        }
        return msgList;
    }
}

