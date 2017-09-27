package com.project.xiaodong.mytimeapp.frame.network;

import com.project.xiaodong.mytimeapp.frame.bean.BaseBean;

/**
 * Created by xiaodong.jin on 2017/9/27.
 */

public class TimeBaseEntEntity<T> extends BaseBean {
    String code;
    String msg;
    String showMsg;
    T data;


}
