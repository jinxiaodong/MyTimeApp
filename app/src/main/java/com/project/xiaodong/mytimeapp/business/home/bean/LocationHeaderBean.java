package com.project.xiaodong.mytimeapp.business.home.bean;

import com.project.xiaodong.mytimeapp.business.location.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.bean.indexbar.BaseIndexPinyinBean;

/**
 * Created by xiaodong.jin on 2017/11/17.
 */
public class LocationHeaderBean extends BaseIndexPinyinBean {

    public static final int LOCATION_BEGIN = 0;
    public static final int LOCATION_SUCCESS = 1;
    public static final int LOCATION_FAILURE = 2;
    public static final int LOCATION_NO_PERMISSION = 3;
    public static final int LOCATION_NO_MATCHER = 4;

    public int status = LOCATION_BEGIN;//0:定位中  1：定位成功 2：定位失败 3:没有权限 4：为开通城市
    public String cityName;
    public String cityCode;
    public MTimeCityInfo cityBean;

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public String getPinYin() {
        return null;
    }
}
