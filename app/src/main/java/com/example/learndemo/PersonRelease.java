package com.example.learndemo;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonRelease {

    // 服务器端以流的形式把数据返回的
    public static List<Person> parserXml(InputStream in) throws Exception {

        // 声明集合对象和类对象
        List<Person> personList = null;
        Person person = null;

        // 获取XmlPullParser解析的实例
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(in, "utf-8");

        // 获取事件类型
        while (parser.getEventType()!=XmlPullParser.END_DOCUMENT) {

            if(parser.getName()!=null) {
                switch (parser.getEventType()) {
                    case XmlPullParser.START_TAG:// 解析开始标签

                        if("persons".equals(parser.getName())){ // 具体判断解析到了哪个结点
                            personList = new ArrayList<Person>(); //创建一个集合对象
                        }else if ("person".equals(parser.getName())) {
                            person = new Person(); // 创建Message对象
                            String id = parser.getAttributeValue(0); // 获取id值
                            person.setId(id);
                        }else if ("name".equals(parser.getName())) {
                            String name = parser.nextText(); // 获取name的数据
                            person.setName(name);
                        }else if ("age".equals(parser.getName())) {
                            String age = parser.nextText(); // 获取age的数据
                            person.setAge(age);
                        }else if ("sex".equals(parser.getName())) {
                            String sex = parser.nextText(); // 获取sex的数据
                            person.setSex(sex);
                        }
                        break;

                    case XmlPullParser.END_TAG:// 解析结束标签

                        if("person".equals(parser.getName())){ //具体判断要解析的结束标签
                            //把javabean对象放入集合当中
                            personList.add(person);
                            person=null;
                        }
                        break;
                }
            }
            parser.next(); // 不停地向下解析
        }
        return personList;
    }
}
