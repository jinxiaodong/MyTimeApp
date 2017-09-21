package com.project.xiaodong.mytimeapp.frame.base;

import com.project.xiaodong.mytimeapp.frame.utils.JsonUtil;

import java.io.Serializable;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class BaseBean implements Serializable {
    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}
