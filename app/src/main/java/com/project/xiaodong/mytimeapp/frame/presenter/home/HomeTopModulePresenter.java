package com.project.xiaodong.mytimeapp.frame.presenter.home;

import android.content.Context;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.business.home.bean.TopModuleBean;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.RxSchedulers;
import com.project.xiaodong.mytimeapp.frame.presenter.Presenter;
import com.project.xiaodong.mytimeapp.frame.presenter.view.IBaseView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2017/9/28.
 */

public class HomeTopModulePresenter extends Presenter<IBaseView<TopModuleBean>> {


    RetrofitClient mRetrofitClient;

    /*******************************************************************************
     * Public/Protected Methods
     * *****************************************************************************
     *
     * @param context
     * @param mvpView
     */
    public HomeTopModulePresenter(Context context, IBaseView mvpView) {
        super(context, mvpView);
        mRetrofitClient = new RetrofitClient();
    }


    public void getData() {

        mRetrofitClient.create(ApiService.class)
                .get(ConstantUrl.HOME_TOP_MODULE, mParams)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxSchedulers.<TopModuleBean>compose())
                .subscribe(new Observer<TopModuleBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TopModuleBean topModuleBean) {

                        if (topModuleBean != null) {
                            if ("1".equals(topModuleBean.code)) {
                                if (topModuleBean.data == null) {
                                    mvpView.onEmpty();
                                } else {
                                    mvpView.initData(topModuleBean.data);
                                }
                            } else {
                                mvpView.onFailure(topModuleBean.showMsg);
                            }
                        } else {
                            mvpView.onFailure("请求失败");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mvpView.onFailure("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

