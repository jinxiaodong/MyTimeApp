package com.project.xiaodong.mytimeapp.frame.network.retrofit;

import android.content.Context;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.network.cookie.CookieJarManager;
import com.project.xiaodong.mytimeapp.frame.network.cookie.PersistentCookieStore;

import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by xiaodong.jin on 2017/9/27.
 */

public class RetrofitConfig {

    /**
     * 默认读取超时时间--30s
     */
    public static final int DEFAULT_READTIMEOUT = 60;
    /**
     * 默认写超时时间--15s
     */
    public static final int DEFAULT_WRITETIMEOUT = 60;
    /**
     * 默认连接超时时间--15s
     */
    public static final int DEFAULT_CONNECTTIMEOUT = 60;
    /**
     * cookie管理策略
     */
    public CookieManager cookieHandler = null;
    /**
     * 是否支持cookie
     */
    public boolean supportCookie;
    /**
     * 是否设置缓存大小
     */
    public boolean isSetCacheSize;

    public CookieJarManager cookieJarManager;
    /**
     * 缓存目录，默认为系统给应用分配的缓存目录
     */
    public String cacheDirectory;
    /**
     * 缓存默认大小
     */
    public long cacheSize = 50 * 1024 * 1024;

    /*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/

    /**
     * 设缓存有效期为两天
     */
    public static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    public static final String CACHE_CONTROL_AGE = "max-age=0";

    public RetrofitConfig(Context context) {
        cookieHandler =  new CookieManager(new PersistentCookieStore(context),
                CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        cookieJarManager = new CookieJarManager(context);
        cacheDirectory = BaseApplication.getContext().getCacheDir() + "mtime_cache";
    }
}
