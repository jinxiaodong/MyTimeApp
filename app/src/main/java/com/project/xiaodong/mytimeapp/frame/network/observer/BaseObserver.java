package com.project.xiaodong.mytimeapp.frame.network.observer;

import com.project.xiaodong.mytimeapp.frame.bean.TimeBaseEntity;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaodong.jin on 2017/9/28.
 */

public abstract class BaseObserver<T extends TimeBaseEntity> implements Observer<TimeBaseEntity<T>> {


    Class clazz;

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }


    @Override
    public void onNext(@NonNull TimeBaseEntity<T> tTimeBaseEntity) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
