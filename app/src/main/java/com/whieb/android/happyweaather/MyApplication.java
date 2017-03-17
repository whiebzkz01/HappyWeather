package com.whieb.android.happyweaather;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhukongzhen on 2017/3/17.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
