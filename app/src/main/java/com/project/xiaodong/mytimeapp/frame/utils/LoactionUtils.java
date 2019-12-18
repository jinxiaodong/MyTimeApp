package com.project.xiaodong.mytimeapp.frame.utils;

import android.text.TextUtils;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.bean.LocationInfo;
import com.project.xiaodong.mytimeapp.business.location.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.eventbus.LocationEvents;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xiaodong.jin on 2017/10/23.
 * <p>
 * 存储百度定位信息:方便请求
 */

public class LoactionUtils {

    public static final String LOCATION = "location";
    public static final String MTIME_LOCATION = "mt_location";
    public static final String USER_CHOOSE_LOCATION = "user_location";

    /**
     * 百度定位信息
     *
     * @param loactionInfo
     */
    public static void setLoactionInfo(LocationInfo loactionInfo) {
        SharePreferenceUtil sharePreferenceUtil = SharePreferenceUtil.getInstance(BaseApplication.getContext());
        sharePreferenceUtil.setValue(LOCATION, JsonUtil.toJsonString(loactionInfo));
    }

    /**
     * 百度定位信息
     */
    public static LocationInfo getLocationInfo() {
        SharePreferenceUtil sharePreferenceUtil = SharePreferenceUtil.getInstance(BaseApplication.getContext());
        String value = sharePreferenceUtil.getValue(LOCATION, "");
        if (TextUtils.isEmpty(value)) {
            return getDefalutLocationInfo();
        }
        LocationInfo parse = JsonUtil.parse(value, LocationInfo.class);
        if (parse == null) {
            return getDefalutLocationInfo();
        }
        return parse;
    }

    public static LocationInfo getDefalutLocationInfo() {
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.city = "上海市";
        locationInfo.longitude = 121.39208;
        locationInfo.latitude = 31.243143;

        return locationInfo;
    }

    /**
     * Mtime请求返回城市信息
     *
     * @param data
     */
    public static void setMTimeCityInfo(MTimeCityInfo data) {
        SharePreferenceUtil sharePreferenceUtil = SharePreferenceUtil.getInstance(BaseApplication.getContext());
        sharePreferenceUtil.setValue(MTIME_LOCATION, JsonUtil.toJsonString(data));
    }

    public static MTimeCityInfo getMTimeCityInfo() {

        MTimeCityInfo cityInfo = new MTimeCityInfo();
        cityInfo.cityId = cityInfo.id = "292";
        cityInfo.name =cityInfo.n =  "上海";
        cityInfo.pinyinShort = "sh";

        SharePreferenceUtil sharePreferenceUtil = SharePreferenceUtil.getInstance(BaseApplication.getContext());
        String value = sharePreferenceUtil.getValue(MTIME_LOCATION, "");
        if (!TextUtils.isEmpty(value)) {
            MTimeCityInfo parse = JsonUtil.parse(value, MTimeCityInfo.class);
            if (parse != null) {
                cityInfo = parse;
            }
        }
        return cityInfo;
    }

    /**
     * 用户选择城市存储
     *
     * @param cityInfo
     */
    public static void setUserChooseCity(MTimeCityInfo cityInfo) {
        if (cityInfo == null) {
            return;
        }
        try {
            SharePreferenceUtil sharePreferenceUtil = SharePreferenceUtil.getInstance(BaseApplication.getContext());
            sharePreferenceUtil.setValue(USER_CHOOSE_LOCATION, JsonUtil.toJsonString(cityInfo));
            EventBus.getDefault().post(new LocationEvents(LocationEvents.code, LocationEvents.target));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static MTimeCityInfo getUserChooseCity() {
        MTimeCityInfo cityInfo = new MTimeCityInfo();
        cityInfo.cityId = cityInfo.id = "292";
        cityInfo.name =cityInfo.n =  "上海";
        cityInfo.pinyinShort = "sh";

        SharePreferenceUtil sharePreferenceUtil = SharePreferenceUtil.getInstance(BaseApplication.getContext());
        String value = sharePreferenceUtil.getValue(USER_CHOOSE_LOCATION, "");
        if (!TextUtils.isEmpty(value)) {
            MTimeCityInfo parse = JsonUtil.parse(value, MTimeCityInfo.class);
            if (parse != null) {
                cityInfo = parse;
            }
        }
        return cityInfo;
    }

}
