package com.project.xiaodong.mytimeapp.frame.network;

import com.project.xiaodong.mytimeapp.frame.bean.BaseBean;

/**
 * Created by xiaodong.jin on 2017/9/27.
 */

public class TimeBaseEntity<T> extends BaseBean {
    public String code;
    public String msg;
    public String showMsg;
    public T data;
    T movies;
    int count;
    int totalCinemaCount;
    int totalComingMovie;
    int totalHotMovi;

}
