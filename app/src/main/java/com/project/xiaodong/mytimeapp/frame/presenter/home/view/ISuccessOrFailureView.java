package com.project.xiaodong.mytimeapp.frame.presenter.home.view;

/**
 * Created by xiaodong.jin on 2017/10/25.
 */

public interface ISuccessOrFailureView<T> {

    void onSuccess(T data);

    void onFailure(String message);

}
