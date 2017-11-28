package com.project.xiaodong.mytimeapp.frame.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.project.xiaodong.mytimeapp.frame.constants.DeviceInfo;
import com.project.xiaodong.mytimeapp.frame.utils.LogUtil;
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
        appInit();

        //启动定位
        initLocation();

    }

    private void appInit() {
        //日志初始化
//        Logger.addLogAdapter(new AndroidLogAdapter());
        LogUtil.init(true);
        //屏幕信息\
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LogUtil.i("============appInit, orientation:" + "横屏状态");
            DeviceInfo.WIDTHPIXELS = displayMetrics.heightPixels;
            DeviceInfo.HEIGHTPIXELS = displayMetrics.widthPixels;
        } else {
            LogUtil.i("============appInit, orientation:" + "竖屏状态");
            DeviceInfo.WIDTHPIXELS = displayMetrics.widthPixels;
            DeviceInfo.HEIGHTPIXELS = displayMetrics.heightPixels;
        }
        DeviceInfo.DENSITYDPI = displayMetrics.densityDpi;
        DeviceInfo.DENSITY = displayMetrics.density;
    }

    private void initLocation() {

    }

    public static Context getContext() {
        return context;
    }
}
