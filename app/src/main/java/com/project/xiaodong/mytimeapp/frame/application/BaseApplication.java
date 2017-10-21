package com.project.xiaodong.mytimeapp.frame.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.project.xiaodong.mytimeapp.frame.view.frescoconfig.ImageConfigUtil;

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
        //fresco初始化
        Fresco.initialize(this, ImageConfigUtil.getOkHttpImagePipelineConfig(this));

        //日志初始化
        Logger.addLogAdapter(new AndroidLogAdapter());

    }

    public static Context getContext() {
        return context;
    }
}
