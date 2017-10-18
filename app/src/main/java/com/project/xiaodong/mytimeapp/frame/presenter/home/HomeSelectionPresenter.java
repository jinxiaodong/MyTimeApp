package com.project.xiaodong.mytimeapp.frame.presenter.home;

import android.content.Context;

import com.project.xiaodong.mytimeapp.business.home.apiserveice.ApiService;
import com.project.xiaodong.mytimeapp.business.home.bean.HotPlayMoviesBean;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.RxSchedulers;
import com.project.xiaodong.mytimeapp.frame.presenter.Presenter;
import com.project.xiaodong.mytimeapp.frame.presenter.view.IBaseView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2017/10/17.
 */

public class HomeSelectionPresenter extends Presenter<IBaseView<HotPlayMoviesBean>> {


    RetrofitClient mRetrofitClient;

    /*******************************************************************************
     * Public/Protected Methods
     * *****************************************************************************
     *
     * @param context
     * @param mvpView
     */
    public HomeSelectionPresenter(Context context, IBaseView<HotPlayMoviesBean> mvpView) {
        super(context, mvpView);
        mRetrofitClient = new RetrofitClient();
    }


    public void getData(String locationId) {

        put("locationId","292");
        mRetrofitClient.mBaseUrl(ConstantUrl.BASE_URL_TYPE2)
                .create(ApiService.class)
                .getHotplay(ConstantUrl.HOT_PLAY_MOVIES, mParams)
                .compose(RxSchedulers.<HotPlayMoviesBean>compose())
                .subscribe(new Observer<HotPlayMoviesBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HotPlayMoviesBean hotPlayMoviesBean) {
                        if (hotPlayMoviesBean != null) {
                            List<HotPlayMoviesBean> movies = hotPlayMoviesBean.movies;
                            if (movies != null && movies.size() > 0) {
                                mvpView.setData(hotPlayMoviesBean);
                            } else {
                                mvpView.onEmpty();
                            }
                        } else {
                            mvpView.onEmpty();
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
