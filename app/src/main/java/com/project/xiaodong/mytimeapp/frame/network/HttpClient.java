package com.project.xiaodong.mytimeapp.frame.network;

import android.util.Log;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.network.interceptor.RewriteCacheControlInterceptor;
import com.project.xiaodong.mytimeapp.frame.network.retrofit.RetrofitConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by xiaodong.jin on 2017/10/20.
 */

public class HttpClient {

    private static HttpClient uniqueInstance = null;

    public OkHttpClient getOkClient() {
        return okClient;
    }

    //ok http client
    private OkHttpClient okClient;
    /**
     * 缓存目录，默认为系统给应用分配的缓存目录
     */
    private String cacheDirectory;
    private long cacheSize = 50 * 1024 * 1024;

    /**
     * 请求头
     */
    private Map<String, String> mHeaders;
    private Interceptor mHeaderInterceptor;

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            Log.i("RetrofitLog", "retrofitBack ======================= " + message);
        }
    });

    /**
     * constructor
     */
    private HttpClient() {

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor mRewriteCacheControlInterceptor = new RewriteCacheControlInterceptor();
        appendHeaders();
        cacheDirectory = BaseApplication.getContext().getCacheDir() + "mtime_cache";

        OkHttpClient.Builder build = new OkHttpClient.Builder();

        build.readTimeout(RetrofitConfig.DEFAULT_READTIMEOUT, TimeUnit.SECONDS);
        build.writeTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS);
        build.connectTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS);
        build.addInterceptor(logging);
        build.addInterceptor(mRewriteCacheControlInterceptor);
        build.addInterceptor(mRewriteCacheControlInterceptor);
        build.addInterceptor(mHeaderInterceptor);
        build.cache(new Cache(new File(cacheDirectory), cacheSize));
        okClient = build.build();

//
//        okClient.interceptors().add(new AddCookiesInterceptor());
//        okClient.interceptors().add(new ReceivedCookiesInterceptor());

    }


    /**
     * constructor
     */
    private HttpClient(RetrofitConfig config) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder build = new OkHttpClient.Builder();

        build.readTimeout(RetrofitConfig.DEFAULT_READTIMEOUT, TimeUnit.SECONDS);
        build.writeTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS);
        build.connectTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS);
        build.addInterceptor(logging);
        if (config != null) {
            if (config.supportCookie) {
//                build.setCookieHandler(config.cookieHandler);
                build.cookieJar(config.cookieJarManager);
            }
            if (config.isSetCacheSize) {
                build.cache(new Cache(new File(config.cacheDirectory), config.cacheSize));
            }
        }
        okClient = build.build();
    }

    /**
     * init
     */
    private void initInner(RetrofitConfig config) {
        if (okClient != null) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder build = new OkHttpClient.Builder();
            build.readTimeout(RetrofitConfig.DEFAULT_READTIMEOUT, TimeUnit.SECONDS);
            build.writeTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS);
            build.connectTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS);
            build.addInterceptor(logging);
            if (config != null) {
                if (config.supportCookie) {
//                    okClient.setCookieHandler(config.cookieHandler);
                    build.cookieJar(config.cookieJarManager);
                }
                if (config.isSetCacheSize) {
                    build.cache(new Cache(new File(config.cacheDirectory), config.cacheSize));
                }
            }
            okClient = build.build();
        }
    }

    /**
     * init
     */
    public static void init(RetrofitConfig config) {
        if (uniqueInstance == null) {
            synchronized (HttpClient.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new HttpClient(config);
                }
            }
        } else {
            uniqueInstance.initInner(config);
        }
    }

    /**
     * 初始化当前对象
     *
     * @return
     */
    public static HttpClient instance() {
        if (uniqueInstance != null)
            return uniqueInstance;
        synchronized (HttpClient.class) {
            if (uniqueInstance == null) {
                uniqueInstance = new HttpClient();
            }
        }
        return uniqueInstance;
    }

    private void appendHeaders() {
        if (mHeaders == null) {
            mHeaders = new HashMap<String, String>();
        }
        mHeaders.put("Content-Type", "application/json");
        mHeaders.put("Content-Type", "charset=UTF-8");
        //增加头部信息
        mHeaderInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request.Builder builder = chain.request().newBuilder();
                for (String key : mHeaders.keySet()) {
                    builder.addHeader(key, mHeaders.get(key));
                }


                Request build = builder.build();
                return chain.proceed(build);
            }
        };
    }


}
