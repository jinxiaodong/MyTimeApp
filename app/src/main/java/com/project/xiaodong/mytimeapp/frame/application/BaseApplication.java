package com.project.xiaodong.mytimeapp.frame.application;

import android.app.Application;
import android.content.Context;

import com.baidu.location.BDLocation;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.project.xiaodong.mytimeapp.frame.bean.LocationInfo;
import com.project.xiaodong.mytimeapp.frame.block.LocationBlock;
import com.project.xiaodong.mytimeapp.frame.utils.LoactionUtils;
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
        //日志初始化
        Logger.addLogAdapter(new AndroidLogAdapter());
        //fresco初始化
        Fresco.initialize(this, ImageConfigUtil.getOkHttpImagePipelineConfig(this));

        //启动定位
        initLocation();

    }

    private void initLocation() {
        LocationBlock mlLocationBlock = new LocationBlock(this);
        mlLocationBlock.setOnLocationListener(new LocationBlock.OnLocationListener() {
            @Override
            public void onLocationedSuccess(BDLocation bdLocation) {
                //获取定位结果
                String province = bdLocation.getProvince();//获取省信息
                String city = bdLocation.getCity();        //获取城市信息
                String district = bdLocation.getDistrict(); //获取区县信息
                String cityCode = bdLocation.getCityCode();//获取城市码
                Double longitude = bdLocation.getLongitude();//获取纬度信息
                Double latitude = bdLocation.getLatitude(); //获取经度信息

                LocationInfo locationInfo = new LocationInfo();
                locationInfo.province = province;
                locationInfo.city = city;
                locationInfo.district = district;
                locationInfo.cityCode = cityCode;
                locationInfo.longitude = longitude;
                locationInfo.latitude = latitude;
                LogUtil.e(locationInfo.toString());
                LoactionUtils.setLoactionInfo(locationInfo);
            }

            @Override
            public void onLocationedFail() {
                LoactionUtils.setLoactionInfo(LoactionUtils.getDefalutLocationInfo());
            }
        });
        mlLocationBlock.statrLocation();
    }

    public static Context getContext() {
        return context;
    }
}
