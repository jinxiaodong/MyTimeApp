package com.project.xiaodong.mytimeapp.frame.presenter.main;

import android.content.Context;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.business.home.bean.Loadbean;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.RxSchedulers;
import com.project.xiaodong.mytimeapp.frame.presenter.Presenter;
import com.project.xiaodong.mytimeapp.frame.presenter.view.ICommonView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2017/11/27.
 */

public class MainLoadInfoPresenter extends Presenter<ICommonView<Loadbean>> {

    RetrofitClient mRetrofitClient;

    /*******************************************************************************
     * Public/Protected Methods
     * *****************************************************************************
     *
     * @param context
     * @param mvpView
     */
    public MainLoadInfoPresenter(Context context, ICommonView<Loadbean> mvpView) {
        super(context, mvpView);
        mRetrofitClient = new RetrofitClient();
    }

    public  void  getLoadInfo(){

        put("isUpgrade","true");
        mRetrofitClient.create(ApiService.class)
                .getLoadInfo(ConstantUrl.HOME_LOAD_INFO,mParams)
                .compose(RxSchedulers.<Loadbean>compose())
                .subscribe(new Observer<Loadbean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Loadbean loadbean) {
                            if(loadbean != null) {
                                Loadbean.DataBean data = loadbean.getData();
                                if(data != null) {
                                    mvpView.onSuccess(loadbean);
                                }else {

                                    mvpView.onFailure("数据为空");
                                }
                            }else {
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
