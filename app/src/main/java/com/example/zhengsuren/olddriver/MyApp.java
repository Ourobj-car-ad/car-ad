package com.example.zhengsuren.olddriver;

import android.app.Application;
import com.example.zhengsuren.olddriver.MainActivity.MyHandler;
import android.os.Handler;

/**
 * Created by zhengsuren on 16/5/24.
 */
public class MyApp extends Application
{
    //使用自定义的Application，需要同时在Manifest表中注册Application的name
    private MyHandler handler = null;

    public MyHandler getHandler() {
        return handler;
    }

    public void setHandler(MyHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
