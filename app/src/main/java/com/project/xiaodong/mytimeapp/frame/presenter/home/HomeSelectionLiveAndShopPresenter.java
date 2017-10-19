package com.project.xiaodong.mytimeapp.frame.presenter.home;

import android.content.Context;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.business.home.bean.LiveAndShopBean;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.RxSchedulers;
import com.project.xiaodong.mytimeapp.frame.presenter.Presenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ILiveAndShopView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */

public class HomeSelectionLiveAndShopPresenter extends Presenter<ILiveAndShopView> {

    RetrofitClient mRetrofitClient;

    /*******************************************************************************
     * Public/Protected Methods
     * *****************************************************************************
     *
     * @param context
     * @param mvpView
     */
    public HomeSelectionLiveAndShopPresenter(Context context, ILiveAndShopView mvpView) {
        super(context, mvpView);
        mRetrofitClient = new RetrofitClient();
    }

    public void getLiveAndShop() {
        mRetrofitClient.create(ApiService.class)
                .getLiveAndShop(ConstantUrl.HOME_SELECTION_LIVE_AND_SHOP)
                .compose(RxSchedulers.<LiveAndShopBean>compose())
                .subscribe(new Observer<LiveAndShopBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LiveAndShopBean liveAndShopBean) {

                        if(liveAndShopBean != null && liveAndShopBean.data !=null) {
                            LiveAndShopBean data = liveAndShopBean.data;
                            mvpView.onLiveAndShopSuccess(data);
                        }else {
                            mvpView.onLiveAndShopFailure("内容为空");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mvpView.onLiveAndShopFailure("请求出错");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
