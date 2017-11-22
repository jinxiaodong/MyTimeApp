package com.project.xiaodong.mytimeapp.frame.presenter.home;

import android.content.Context;
import android.text.TextUtils;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.business.location.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.RxSchedulers;
import com.project.xiaodong.mytimeapp.frame.presenter.Presenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ISuccessOrFailureView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2017/10/25.
 */
public class MainCityPresenter extends Presenter<ISuccessOrFailureView<MTimeCityInfo>> {

    RetrofitClient mRetrofitClient;

    /*******************************************************************************
     * Public/Protected Methods
     * *****************************************************************************
     *
     * @param context
     * @param mvpView
     */
    public MainCityPresenter(Context context, ISuccessOrFailureView<MTimeCityInfo> mvpView) {
        super(context, mvpView);
        mRetrofitClient = new RetrofitClient();
    }

    public void getCityInfo(String latitude, String longitude, String url) {

        if (!TextUtils.isEmpty(latitude)) {
            put("latitude", latitude);
        }
        if (!TextUtils.isEmpty(longitude)) {
            put("longitude", longitude);
        }


        mRetrofitClient.mBaseUrl(ConstantUrl.BASE_URL_TYPE2)
                .create(ApiService.class)
                .getTimeCityInfo(url, mParams)
                .compose(RxSchedulers.<MTimeCityInfo>compose())
                .subscribe(new Observer<MTimeCityInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MTimeCityInfo mTimeCityInfo) {
                        if (mTimeCityInfo != null) {
                            mvpView.onSuccess(mTimeCityInfo);
                        } else {
                            mvpView.onFailure("数据为空");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mvpView.onFailure("请求出错");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    public void getAllCity() {

        mRetrofitClient.mBaseUrl(ConstantUrl.BASE_URL_TYPE2)
                .create(ApiService.class)
                .getTimeCityInfo(ConstantUrl.MTIME_ALL_CITY, mParams)
                .compose(RxSchedulers.<MTimeCityInfo>compose())
                .subscribe(new Observer<MTimeCityInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MTimeCityInfo mTimeCityInfo) {
                        if (mTimeCityInfo != null) {
                            mvpView.onSuccess(mTimeCityInfo);
                        } else {
                            mvpView.onFailure("数据为空");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mvpView.onFailure("请求出错");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
