package com.project.xiaodong.mytimeapp.frame.bean;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/9/27.
 */

public class TimeBaseEntity<T> extends BaseBean {
    public String code;
    public String msg;
    public String showMsg;
    public T data;


    public List<T> movies;
    public int count;
    public int totalCinemaCount;
    public int totalComingMovie;
    public int totalHotMovie;

}
