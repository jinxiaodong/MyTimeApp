package com.project.xiaodong.mytimeapp.frame.network;

import android.content.Context;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.interceptor.RewriteCacheControlInterceptor;
import com.project.xiaodong.mytimeapp.frame.network.retrofit.RetrofitConfig;
import com.project.xiaodong.mytimeapp.frame.utils.LogUtil;

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
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaodong.jin on 2017/9/27.
 */

public class RetrofitClient {

    private static Context mContext;
    private static Retrofit mRetrofit;

    /*
     * 请求host——url
     */
    private String mBaseUrl = ConstantUrl.BASE_URL_TYPE1;
    /**
     *
     */
    private String mUrl;

    /**
     * 请求头
     */
    private Map<String, String> mHeaders;
    /**
     * 请求参数
     */
    private Map<String, String> mParams = new HashMap<>();
    /**
     * 缓存目录，默认为系统给应用分配的缓存目录
     */
    private String cacheDirectory;

    private long cacheSize = 50 * 1024 * 1024;
    /**
     * 返回数据的实体类
     */
    private Class clazz;
    private ApiService mApiService;
    private Interceptor mHeaderInterceptor;

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

    private void addCookie() {

    }

    private void initRequest() {
        //开启log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        appendHeaders();

        Interceptor mRewriteCacheControlInterceptor = new RewriteCacheControlInterceptor();
        cacheDirectory = BaseApplication.getContext().getCacheDir() + "mtime_cache";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//
//                .cookieJar(new NovateCookieManger(context))
                .readTimeout(RetrofitConfig.DEFAULT_READTIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(logging)
                .addInterceptor(mHeaderInterceptor)
                .cache(new Cache(new File(cacheDirectory), cacheSize))
                .build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LogUtil.e("retrofit创建");

//        mApiService = mRetrofit.create(ApiService.class);
    }


    public RetrofitClient() {
    }


    public RetrofitClient mBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        return this;
    }
//
//    public RetrofitClient url(String url) {
//        mBaseUrl = ConstantUrl.BASE_URL_TYPE1;
//        mUrl = url;
//        return this;
//    }
//
//    public RetrofitClient headers(Map<String, String> headers) {
//        this.mHeaders = headers;
//        return this;
//    }
//
//    public RetrofitClient params(Map params) {
//        mParams = params;
//        return this;
//    }

    public <T> T create(final Class<T> service) {
        initRequest();
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofit.create(service);
    }

//    /**
//     * get
//     *
//     * @param observer
//     */
//    public void get(Observer observer) {
//        initRequest();
//        Log.e("TAG", "好吧不知道有没有成功啊4");
//        mApiService
//                .get(mUrl, mParams)
//                .compose(RxSchedulers.compose())
//                .subscribe(observer);
//    }


//    public void post(Observer observer) {
//        mApiService.post(mUrl, mParams)
//                .compose(RxSchedulers.compose())
//                .subscribe(observer);
//    }
}
