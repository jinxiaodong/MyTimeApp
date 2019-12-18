package com.project.xiaodong.mytimeapp.business.location.bean;

import com.project.xiaodong.mytimeapp.frame.bean.indexbar.BaseIndexPinyinBean;

/**
 * Created by xiaodong.jin on 2017/10/23.
 */

public class MTimeCityInfo extends BaseIndexPinyinBean<MTimeCityInfo> {


    /**
     * "count": 248,
     * "id": 292,
     * "n": "上海",
     * "pinyinFull": "Shanghai",
     * "pinyinShort":
     * 请求接口返回的城市返回后用：当使用getU
     */
    public String count = "248";
    public String id = "292";
    public String n = "上海";
    public String pinyinFull = "Shanghai";


    /**
     * 根据经纬度请求返回的结构
     * :目前只用来判断是否要切换城市
     */
    public String name = "上海";     //城市名
    public String pinyinShort = "sh";  //拼音
    public String cityId = "292";       //城市id
    public double longitude;
    public double latitude;

    @Override
    public String getTarget() {
        return pinyinFull;
    }

    @Override
    public String getPinYin() {
        return pinyinFull;
    }

    @Override
    public boolean isNeedToPinyin() {
        return true;
    }
}
