package com.project.xiaodong.mytimeapp.frame.presenter.view;

/**
 * Created by xiaodong.jin on 2017/11/27.
 */

public interface ICommonView<T> {
    void onSuccess(T data);
    void onFailure(String message);
}
