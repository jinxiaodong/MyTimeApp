package com.project.xiaodong.mytimeapp.frame.constants;

/**
 * Created by xiaodong.jin on 2017/9/27.
 * url地址
 */

public class ConstantUrl {
    public static final String BASE_URL_TYPE1 = "https://comm-api-m.mtime.cn/";
    public static final String BASE_URL_TYPE2 = "https://api-m.mtime.cn/";
    public static final String BASE_URL_TYPE3 = "https://ticket-api-m.mtime.cn/";

    /**
     * 根据经纬度获取城市
     */
    public static final String MTIME_CITY_INFO = "GetCityByLongitudelatitude.api";
    /**
     * 获取所有城市
     */
    public static final String MTIME_ALL_CITY = "Showtime/HotCitiesByCinema.api";

    /*
     *首页TopModule
     */
    public static final String HOME_TOP_MODULE = "index/topModule.api";

    /*
     *首页精选：正在售票
     */

    public static final String HOT_PLAY_MOVIES = "PageSubArea/HotPlayMovies.api";
    /**
     * 首页精选：商成
     */
    public static final String HOME_SELECTION_LIVE_AND_SHOP = "index/middleModule.api";


    /**
     * 首页精选：精彩预告
     */
    public static final String HOME_SELECTION_ADVANCE = "choice/feed.api";


    /**
     * 预加载的信息
     */
    public static final String HOME_LOAD_INFO = "startup/load.api";
}
