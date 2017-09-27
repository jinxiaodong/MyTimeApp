package com.project.xiaodong.mytimeapp.frame.network;

import com.project.xiaodong.mytimeapp.frame.bean.BaseBean;

/**
 * Created by xiaodong.jin on 2017/9/27.
 */

public class TimeMovieListEntity<T> extends BaseBean {

    T movies;
    int count;
    int totalCinemaCount;
    int totalComingMovie;
    int totalHotMovi;
}
