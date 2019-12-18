package com.project.xiaodong.mytimeapp.business.location.bean;

import com.project.xiaodong.mytimeapp.frame.bean.BaseBean;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.suspension.ISuspensionInterface;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/6.
 */

public class ShowAllCityBean extends BaseBean implements ISuspensionInterface {
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
