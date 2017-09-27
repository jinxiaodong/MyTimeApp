package com.project.xiaodong.mytimeapp.frame.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.project.xiaodong.mytimeapp.business.home.TomModuleBean;
import com.project.xiaodong.mytimeapp.frame.base.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.utils.LogUtil;
import com.project.xiaodong.mytimeapp.frame.utils.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import okhttp3.Cache;
import okhttp3.CacheControl;
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

    private static Context mcontext;
    private static Retrofit mRetrofit;
    public static String baseUrl = ConstantUrl.Base_URL;
    private static OkHttpClient mOkHttpClient;
    private Cache mCache;
    private ApiService mApiService;
    private Class mClass;


    public static RetrofitClient getInstance(Context context) {
        if (context != null) {
            mcontext = context;
        }
        return new RetrofitClient(context);
    }

    public static RetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mcontext = context;
        }
        Log.e("TAG", "好吧不知道有没有成功啊2");
        return new RetrofitClient(context, url);
    }

    public static RetrofitClient getInstance(Context context, String url, Map<String, String> headers) {
        if (context != null) {
            mcontext = context;
        }
        return new RetrofitClient(context, url, headers);
    }

    private RetrofitClient(Context context) {

        this(context, baseUrl, null);
    }

    private RetrofitClient(Context context, String url) {

        this(context, url, null);
    }

    public RetrofitClient(Context context, String url, Map<String, String> headers) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }


        //开启log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "mtime_cache");
        try {
            if (mCache == null) {
                //100Mb
                mCache = new Cache(cacheFile, 1024 * 1024 * 100);
            }
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Content-Type", "charset=UTF-8")
                        .build();

                return chain.proceed(build);
            }
        };


        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(RetrofitConfig.DEFAULT_READTIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(RetrofitConfig.DEFAULT_WRITETIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logging)
                .cache(mCache)
                .build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LogUtil.e("retrofit创建");
    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            if (!NetworkUtil.isNetworkAvailable(BaseApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtil.isNetworkAvailable(BaseApplication.getContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + RetrofitConfig.CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl);

    /**
     * ApiBaseUrl
     *
     * @param newApiBaseUrl
     */
    public static void changeApiBaseUrl(String newApiBaseUrl) {
        baseUrl = newApiBaseUrl;
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }

    /**
     * addcookieJar
     */
    public static void addCookie() {
//        mOkHttpClient.newBuilder().cookieJar(new NovateCookieManger(mContext)).build();
//        retrofit = builder.client(okHttpClient).build();
    }

    /**
     * ApiBaseUrl
     *
     * @param newApiHeaders
     */
    public static void changeApiHeader(Map<String, String> newApiHeaders) {

//        mOkHttpClient.newBuilder().addInterceptor(new BaseInterceptor(newApiHeaders)).build();
        builder.client(mOkHttpClient).build();
    }

    /**
     * create BaseApi  defalte ApiManager
     *
     * @return ApiManager
     */
    public RetrofitClient createBaseApi() {
        Log.e("TAG", "好吧不知道有没有成功啊3");
        mApiService = create(ApiService.class);
        return this;
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofit.create(service);
    }

    public RetrofitClient clazz(Class clazz) {
        mClass = clazz;
        return this;
    }

    public void get(String url,Map map,Observer<TimeBaseEntity<TomModuleBean>> observer) {
        Log.e("TAG", "好吧不知道有没有成功啊4");
        mApiService
                .get(url,map)
                .compose(RxSchedulers.compose())
                .subscribe(observer);

    }


}
