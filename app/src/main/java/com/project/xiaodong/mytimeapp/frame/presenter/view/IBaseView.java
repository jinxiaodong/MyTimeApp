package com.project.xiaodong.mytimeapp.frame.presenter.view;

/**
 * Created by xiaodong.jin on 2017/9/28.
 */
public interface IBaseView<T> {

    void initData(T data);

    void addData(T data);

    void onEmpty();

    void onFailure(String msg);

}
