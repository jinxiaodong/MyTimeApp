package com.project.xiaodong.mytimeapp.frame.presenter.home;

import android.content.Context;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeSelectMovieBean;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.RxSchedulers;
import com.project.xiaodong.mytimeapp.frame.presenter.Presenter;
import com.project.xiaodong.mytimeapp.frame.presenter.view.IBaseView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2018/1/8.
 */

public class HomeSelectMoviePresenter extends Presenter<IBaseView<HomeSelectMovieBean>> {

    RetrofitClient mRetrofitClient;

    /*******************************************************************************
     * Public/Protected Methods
     * *****************************************************************************
     *
     * @param context
     * @param mvpView
     */
    public HomeSelectMoviePresenter(Context context, IBaseView<HomeSelectMovieBean> mvpView) {
        super(context, mvpView);
        mRetrofitClient = new RetrofitClient();
    }


    public void getData() {
        mRetrofitClient.mBaseUrl(ConstantUrl.BASE_URL_TYPE3)
                .create(ApiService.class)
                .getSelectMovie(ConstantUrl.HOME_SETECT_MOVIE)
                .compose(RxSchedulers.<HomeSelectMovieBean>compose())
                .subscribe(new Observer<HomeSelectMovieBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        
                    }

                    @Override
                    public void onNext(@NonNull HomeSelectMovieBean homeSelectMovieBean) {
                        HomeSelectMovieBean data = homeSelectMovieBean.data;
                        if(data == null) {
                            mvpView.onEmpty();
                        }else {
                            mvpView.setData(data);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mvpView.onFailure(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
