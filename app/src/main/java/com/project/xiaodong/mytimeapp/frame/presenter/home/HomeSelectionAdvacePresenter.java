package com.project.xiaodong.mytimeapp.frame.presenter.home;

import android.content.Context;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.business.home.bean.SelectionAdvanceBean;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.RxSchedulers;
import com.project.xiaodong.mytimeapp.frame.presenter.Presenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.IAdvanceView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */

public class HomeSelectionAdvacePresenter extends Presenter<IAdvanceView> {

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
    public HomeSelectionAdvacePresenter(Context context, IAdvanceView mvpView) {
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
                .getSelectionAdvance(ConstantUrl.HOME_SELECTION_ADVANCE, mParams)
                .compose(RxSchedulers.<SelectionAdvanceBean>compose())
                .subscribe(new Observer<SelectionAdvanceBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SelectionAdvanceBean selectionAdvanceBean) {
                        if (selectionAdvanceBean != null) {
                            if (selectionAdvanceBean.data != null) {
                                if (selectionAdvanceBean.data.data != null) {
                                    if (!isLoadMore) {
                                        mvpView.setData(selectionAdvanceBean.data);
                                    } else {
                                        isLoadMore = false;
                                        mvpView.addData(selectionAdvanceBean.data);
                                    }
                                    mvpView.onComplete(true);
                                } else {
                                    mvpView.onAdvanceEmpty();
                                }
                            } else {
                                mvpView.onAdvanceEmpty();
                            }

                        } else {
                            mvpView.onAdvanceEmpty();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mvpView.onAdvanceFailure("请求出错");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
