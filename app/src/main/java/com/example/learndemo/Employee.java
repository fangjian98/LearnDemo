package com.example.learndemo;

public class Employee {

    // 定义回调接口的成员变量
    private Callback mCallback;

    // 声明回调接口
    public interface Callback {
        void work(int a);
    }

    // 设置回调接口对象成员变量/
    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    // 调用回调接口对象中的方法/
    public void doWork() {
        // to do sth
        int a = 3 * 7;
        mCallback.work(a);
    }

}
