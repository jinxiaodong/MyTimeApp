package com.project.xiaodong.mytimeapp.frame.bean.indexbar;


import com.project.xiaodong.mytimeapp.frame.bean.BaseBean;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.suspension.ISuspensionInterface;

public abstract class BaseIndexBean extends BaseBean implements ISuspensionInterface {
    private String baseIndexTag;//所属的分类（城市的汉语拼音首字母）

    public String getBaseIndexTag() {
        return baseIndexTag;
    }

    public BaseIndexBean setBaseIndexTag(String baseIndexTag) {
        this.baseIndexTag = baseIndexTag;
        return this;
    }

    @Override
    public String getSuspensionTag() {
        return baseIndexTag;
    }

    @Override
    public boolean isShowSuspension() {
        return true;
    }
}
