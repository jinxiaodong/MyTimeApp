package com.project.xiaodong.mytimeapp.frame.presenter;

/**
 * Created by xiaodong.jin on 2017/9/28.
 */
public interface IBaseListView<T> {

    void setData(T data);

    void addData(T data);

    void onEmpty();

    void onComplete(boolean hasMore);

    void onFailure(String msg);

    void onLoadMoreFailure();
}
