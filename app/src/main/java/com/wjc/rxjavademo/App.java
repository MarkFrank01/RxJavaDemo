package com.wjc.rxjavademo;

import android.app.Application;

/**
 * Created by WJC on 2017/10/2.
 */

public class App extends Application{

    private static App INSTANCE;

    public static App getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE=this;
    }
}
