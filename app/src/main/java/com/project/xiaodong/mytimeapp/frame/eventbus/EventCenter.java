package com.project.xiaodong.mytimeapp.frame.eventbus;

import com.project.xiaodong.mytimeapp.frame.bean.BaseBean;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class EventCenter<T> extends BaseBean {

    public int code;
    public T data;

    public EventCenter() {
    }

    public EventCenter(int code, T data) {
        this.data = data;
        this.code = code;
    }
}
