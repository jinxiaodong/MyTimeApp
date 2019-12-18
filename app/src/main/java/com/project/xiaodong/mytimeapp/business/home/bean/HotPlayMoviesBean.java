package com.project.xiaodong.mytimeapp.business.home.bean;

import com.project.xiaodong.mytimeapp.frame.bean.BaseBean;
import com.project.xiaodong.mytimeapp.frame.bean.TimeBaseEntity;

/**
 * Created by xiaodong.jin on 2017/10/17.
 */

public class HotPlayMoviesBean extends TimeBaseEntity<HotPlayMoviesBean> {


    public String actorName1;
    public String actorName2;
    public String btnText;
    public String commonSpecial;
    public String directorName;
    public String img;
    public boolean is3D;
    public boolean isDMAX;
    public boolean isFilter;
    public boolean isHot;
    public boolean isIMAX;
    public boolean isIMAX3D;
    public boolean isNew;
    public int length;
    public int movieId;
    public NearestShowtimeBean nearestShowtime;
    public boolean preferentialFlag;
    public int rDay;
    public int rMonth;
    public int rYear;
    public double ratingFinal;
    public String titleCn;
    public String titleEn;
    public String type;
    public int wantedCount;


    public static class NearestShowtimeBean extends BaseBean {
        /**
         * isTicket : true
         * nearestCinemaCount : 186
         * nearestShowDay : 1508227200
         * nearestShowtimeCount : 1744
         */

        public boolean isTicket;
        public int nearestCinemaCount;
        public int nearestShowDay;
        public int nearestShowtimeCount;


    }
}
