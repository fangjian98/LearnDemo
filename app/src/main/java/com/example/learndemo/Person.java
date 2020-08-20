package com.example.learndemo;

public class Person {

    private String id;
    private String name;
    private String age;
    private String sex;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String toChineseString() {
        return "人物 [姓名=" + name + ", 年龄=" + age + ", 性别="
                + sex + "]";
    }

    public String toEnglishString() {
        return "Persons [name=" + name + ", age=" + age + ", sex="
                + sex + "]";
    }




}
