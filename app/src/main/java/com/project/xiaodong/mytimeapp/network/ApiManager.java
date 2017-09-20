package com.project.xiaodong.mytimeapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaodong.jin on 2017/9/20.
 */

public class ApiManager {
    private BaseApiService mApiService;
    private static ApiManager mApiManager;

    public synchronized static ApiManager getInstance() {

        if (mApiManager == null) {
            mApiManager = new ApiManager();
        }
        return mApiManager;
    }

    public BaseApiService getApiService(String baseurl) {
        if (mApiService == null) {
            OkHttpClient okClient = new OkHttpClient.Builder().build();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okClient.interceptors().add(logging);
            /* 可以通过 setLevel 改变日志级别
                共包含四个级别：NONE、BASIC、HEADER、BODY

                NONE 不记录

                BASIC 请求/响应行
                --> POST /greeting HTTP/1.1 (3-byte body)
                <-- HTTP/1.1 200 OK (22ms, 6-byte body)

                HEADER 请求/响应行 + 头

                --> Host: example.com
                Content-Type: plain/text
                Content-Length: 3

                <-- HTTP/1.1 200 OK (22ms)
                Content-Type: plain/text
                Content-Length: 6

                BODY 请求/响应行 + 头 + 体
                */

            // 可以通过实现 Logger 接口更改日志保存位置
            //            HttpLoggingIntercetptor logging = new HttpLoggingInterceptor(new Logger() {
            //                @Override
            //                public void log(String message) {
            //                    Timber.tag("okhttp").d(message);
            //                }
            //            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .client(okClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mApiService = retrofit.create(BaseApiService.class);
        }

        return mApiService;
    }
}
