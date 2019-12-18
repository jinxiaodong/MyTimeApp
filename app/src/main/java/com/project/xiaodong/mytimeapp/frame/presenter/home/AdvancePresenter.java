package com.project.xiaodong.mytimeapp.frame.presenter.home;

import android.content.Context;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeAdvanceBean;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.RxSchedulers;
import com.project.xiaodong.mytimeapp.frame.presenter.IBaseListView;
import com.project.xiaodong.mytimeapp.frame.presenter.Presenter;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2017/11/28.
 */

public class AdvancePresenter extends Presenter<IBaseListView<HomeAdvanceBean>> {


    RetrofitClient mRetrofitClient;
    private int pageNum = 1;
    private boolean isLoadMore;

    /*******************************************************************************
     * Public/Protected Methods
     * *****************************************************************************
     *
     * @param context
     * @param mvpView
     */
    public AdvancePresenter(Context context, IBaseListView<HomeAdvanceBean> mvpView) {
        super(context, mvpView);
        mRetrofitClient = new RetrofitClient();
    }


    public void getAdvanceData() {

        isLoadMore = false;
        pageNum = 1;
        request(pageNum);
    }

    public void getMoreAdvance() {
        pageNum++;
        isLoadMore = true;
        request(pageNum);
    }

    public void request(int pageNum) {

        put("pageIndex", pageNum);
        mRetrofitClient.mBaseUrl(ConstantUrl.BASE_URL_TYPE3)
                .create(ApiService.class)
                .getAdvanceData(ConstantUrl.HOME_ADVANCE, mParams)
                .compose(RxSchedulers.<HomeAdvanceBean>compose())
                .subscribe(new Observer<HomeAdvanceBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeAdvanceBean homeAdvanceBean) {
                        if (homeAdvanceBean.data != null) {
                            if (!isLoadMore) {
                                mvpView.setData(homeAdvanceBean.data);
                            } else {
                                isLoadMore = false;
                                mvpView.addData(homeAdvanceBean.data);
                            }
                            mvpView.onComplete(true);

                        } else {
                            if (isLoadMore) {
                                mvpView.onComplete(false);
                            } else {
                                mvpView.onEmpty();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (isLoadMore) {
                            mvpView.onLoadMoreFailure();
                            return;
                        }
                        mvpView.onFailure("请求出错");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
