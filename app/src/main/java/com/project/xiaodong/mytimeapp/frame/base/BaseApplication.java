package com.project.xiaodong.mytimeapp.frame.base;

import android.app.Application;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class BaseApplication extends Application {
    private static BaseApplication mInstance;

    public static Application getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
