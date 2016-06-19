package com.lvzhihao.test.mytest;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by vzhihao on 2016/6/18.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
