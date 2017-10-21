/**
 * @file GlobalConstants
 * @copyright (c) 2016 Macalline All Rights Reserved.
 * @author SongZheng
 * @date 2016/3/4
 */
package com.project.xiaodong.mytimeapp.frame.constants;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.utils.file.FileUtil;

/**
 * @author SongZheng
 * @description TODO
 * @date 2016/3/4
 */
public class GlobalConstants extends BaseConstants{
    /*******************************************************************************
     *	Public/Protected Variables
     *******************************************************************************/
    /**服务器环境*/
    public enum ServerType{
        RELEASE,//正式
        STG,//STG
        TEST,//测试
        UAT1,//测试
        DEV,//开发
    }
    public static ServerType gServer = ServerType.RELEASE;
//    static{
//        if(BuildConfig.SERVER.equals("stg")){
//            gServer = ServerType.STG;
//        }else if(BuildConfig.SERVER.equals("test")){
//            gServer = ServerType.TEST;
//        }else if(BuildConfig.SERVER.equals("uat1")){
//            gServer = ServerType.UAT1;
//        }else if(BuildConfig.SERVER.equals("dev")){
//            gServer = ServerType.DEV;
//        }
//    }
    /* 应用包名*/
    public final static String PACKAGE_NAME = BaseApplication.getInstance().getPackageName();
    /**应用版本code*/
    public static int APP_VERSION_CODE = 1;
    /** 应用版本号 */
    public static String APP_VERSION_NAME = "1.0";

    public static String HX_APPID = "23847DAC";
    public static String USER_CENTER_APPSECRET = "0558e9a1311e326fb47e378cf9c9523517941af3";
    public static String APP_CODE="FC"; //主app MAIN  当家FC
    public static String USER_TYPE = "8";//IM用户类型 1 主app  8 当家app
    static {
        if (gServer == ServerType.STG) {
            HX_APPID = "EE37D157";
            USER_CENTER_APPSECRET = "bb181ca77874ac4183f92fff1661c75e9958223c";
        }
    }
    /* 缓存根目录 */
    public final static String CACHE_ROOT = FileUtil.getDataPath() + "cache/";
    /* 数据缓存目录 */
    public final static String CACHE_DATA = CACHE_ROOT + "data/";
    /* 图片缓存目录 */
    public final static String CACHE_IMG = CACHE_ROOT + "images/";
    /* 音频缓存目录 */
    public final static String CACHE_AUDIO = CACHE_ROOT + "audio/";
    /*聊天目录 */
    public final static String CACHE_CHAT = CACHE_ROOT + "chat/";
    /** 错误日志目录 */
    public final static String CACHE_ERROR = CACHE_ROOT + "error/";
    /* webview缓存路径 * */
    public static final String WEBVIEW_CACHE_PATH = "/webview";

    /**城市缓存时间 5天*/
    public static final long CITY_INFO_CACHETIME = 1000 * 60 * 60 * 24 * 5;
    /*城市数据存储路径*/
    public static final String CITY_FILE_PATH = GlobalConstants.CACHE_DATA + "cityinfo";

    /**成功相应code码*/
    public static final String REQUEST_SUCCESS = "200";
    /**短信倒计时时间（单位：毫秒）*/
    public static final long TIMING_VALUE = 60000;

    //多选图片（最可选数量）
    public static final int MAX_IMAG_NUMBER = 9;

    //添加商品到清单来源 0 收藏功能 1 导购员报价单（Im或者清单扫一扫扫描报价单二维码） 2 清单扫一扫(扫商品)功能
    public static final int COLLOET = 0;
    public static final int QUOTED_PRICE = 1;
    public static final int SCAN = 2;

    //导购手册类型，0 店铺 1品牌
    public static final int SHOPPER = 0;
    public static final int BRAND = 1;


    //IM预约userType  用户类型，2：导购员，3：设计师，4：房产经纪人
    public static final String GUIDER = "2";
    public static final String DESIGNER = "3";
    public static final String BROKER = "4";

    /***-------------------当前链接的网络类型-----------------------------*/
    public static int NO_NETWORK = -1;//无网络服务
    public static final int NETWORK_WIFI = 0;//wifi链接
    public static final int NETWORK_2G = 1;//2G网络
    public static final int NETWORK_3G = 2;//3G网络
    public static final int NETWORK_4G = 3;//4G网络
    public static final int NETWORK_UNKNOWN = 4;//未知网络
    public static int CURRENT_NETWORK_TYPE = NETWORK_UNKNOWN;//默认未知网络

    /***app如果有更新 7天提示一次*/
    public static final long UPDATE_CHANNEL_TIME = 1000 * 60 * 60 * 24 * 7;

    /*******************************************************************************
     *	Private Variables
     *******************************************************************************/

    /*******************************************************************************
     *	Overrides From Base
     *******************************************************************************/

    /*******************************************************************************
     *	Public/Protected Methods
     *******************************************************************************/

    /*******************************************************************************
     *	Private Methods
     *******************************************************************************/

    /*******************************************************************************
     *	Internal Class,Interface
     *******************************************************************************/
}
