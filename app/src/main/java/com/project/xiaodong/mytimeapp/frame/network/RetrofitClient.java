package com.project.xiaodong.mytimeapp.frame.network;

import android.content.Context;

import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.utils.LogUtil;

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

    private void initRequest() {


        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(HttpClient.instance().getOkClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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
