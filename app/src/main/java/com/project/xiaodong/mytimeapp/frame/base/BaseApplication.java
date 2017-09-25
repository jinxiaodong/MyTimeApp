package com.project.xiaodong.mytimeapp.frame.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    private static Context context;

    public static Application getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
