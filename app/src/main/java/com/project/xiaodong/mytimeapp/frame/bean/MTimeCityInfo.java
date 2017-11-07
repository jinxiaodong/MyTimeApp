package com.project.xiaodong.mytimeapp.frame.bean;

import com.project.xiaodong.mytimeapp.frame.bean.indexbar.BaseIndexPinyinBean;

/**
 * Created by xiaodong.jin on 2017/10/23.
 */

public class MTimeCityInfo extends BaseIndexPinyinBean<MTimeCityInfo> {


    public String name;     //城市名
    public String pinyinShort;  //拼音
    public String cityId;       //城市id
    public double longitude;
    public double latitude;

    /**
     * "count": 248,
     * "id": 292,
     * "n": "上海",
     * "pinyinFull": "Shanghai",
     * "pinyinShort":
     */
    public String count;
    public String id;
    public String n;
    public String pinyinFull;


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
