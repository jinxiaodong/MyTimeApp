package com.project.xiaodong.mytimeapp.business.home.location;

import com.project.xiaodong.mytimeapp.frame.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.suspension.ISuspensionInterface;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/6.
 */

public class ShowAllCityBean implements ISuspensionInterface {
    public String tag;
    public List<MTimeCityInfo> mMTimeCityInfos;

    @Override
    public boolean isShowSuspension() {
        return true;
    }

    @Override
    public String getSuspensionTag() {
        return tag;
    }
}
