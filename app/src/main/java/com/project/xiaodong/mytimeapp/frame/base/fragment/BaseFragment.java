package com.project.xiaodong.mytimeapp.frame.base.fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.xiaodong.mytimeapp.frame.application.BaseApplication;
import com.project.xiaodong.mytimeapp.frame.view.dialog.DialogUtil;

import butterknife.ButterKnife;

/**
 * Created by xiaodong.jin on 2017/9/25.
 */

public abstract class BaseFragment extends Fragment {

    /*TAG*/
    public final String TAG = getClass().getSimpleName();
    /**
     * 当前选中的fragment索引
     */
    protected int mSelectIndex;
    /**
     * fragment 索引
     */
    protected int mIndex;
    /**
     * 没有设置 fragment 索引则不需要check
     */
    protected boolean mHaveIndex;
    /**
     * 是否初始化Fragment
     */
    protected boolean isFirst;

    private boolean isOnCreated;
    private boolean isVisible;
    private boolean isLoaded;
    private Handler mHandler;
    private Dialog mWaittingDialog;

    public LayoutInflater mLayoutInflater;
    private View mRootView;
    public Context mContext;
    private AnimationDrawable mAnimationDrawable;

    protected abstract int getLayoutId();

    protected void initWidget() {
    }

    protected void initValue() {
    }

    /**
     * fragmnet 懒加载
     */
    private void lazyinit() {
        if (!isOnCreated || !isVisible) {
            return;
        }
        if (isLoaded) {
            return;
        }
        //因为fragment每次显示到屏幕上时，都会调用initData方法，isLoaded字段为了只调用一次initData方法
        isLoaded = true;
        initData();
    }

    public void initData() {

    }

    protected void initListener() {

    }

    /**
     * 默认关闭懒加载
     * 重写该方法，return true 开启懒加载
     */
    protected boolean openLazyinit() {
        return false;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        if (mRootView == null) {
            mRootView = mLayoutInflater.inflate(getLayoutId(), container, false);
        }
        if (mContext == null) {
            mContext = getActivity();
        }
        ButterKnife.inject(this, mRootView);

        isOnCreated = true;
        initValue();
        initWidget();
        initListener();
        if (openLazyinit()) {
            lazyinit();
        } else {
            initData();
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWaittingDialog != null) {
            dismissDialog();
        }
        if (mRootView != null) {
            ButterKnife.reset(mRootView);
        }
    }

    /**
     * ViewPager + Fragment
     * 切换页面时会回调
     **/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisible && isVisible != getUserVisibleHint()) {
            onFragmentInVisible();
        }
        isVisible = getUserVisibleHint();
        if (isVisible) {
            onFragmentVisible();
            lazyinit();
        }
    }

    /**
     * add多个fragment到同一个layout id的时，
     * 调用hide和show时回调
     **/
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isVisible && hidden) {
            onFragmentInVisible();
        }
        isVisible = !hidden;
        if (isVisible) {
            onFragmentVisible();
            lazyinit();
        }
    }

    /**
     * fragment可见时回调
     */
    protected void onFragmentVisible() {
    }

    /**
     * fragment不可见时回调
     */
    protected void onFragmentInVisible() {
    }

    /**
     * 无网络:由子类自己去处理
     */
    protected static void onNetworkInvalid() {

    }

    /**
     * 有网络
     */
    protected static void onNetworkAvailable() {
    }

    /**
     * 无网络刷新后，重新获得数据，供子类实现
     */
    public void reRequestData() {

    }

    /**
     * @param
     * @return
     * @description getHandler
     */
    public Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(BaseApplication.getContext().getMainLooper());
        }
        return mHandler;
    }

    /**
     * 显示默认等待框
     */
    public void showDialog() {
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mWaittingDialog == null) {
                    mWaittingDialog = DialogUtil.createLodingDialog(mContext, "");
                }
                if (mAnimationDrawable == null) {
                    mAnimationDrawable = DialogUtil.getAnimal();
                }
                mAnimationDrawable.start();
                mWaittingDialog.show();
            }
        });
    }

    public void showDialog(final String msg) {
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mWaittingDialog == null) {
                    mWaittingDialog = DialogUtil.createLodingDialog(mContext, msg);
                }
                if (mAnimationDrawable == null) {
                    mAnimationDrawable = DialogUtil.getAnimal();
                }
                mAnimationDrawable.start();
                mWaittingDialog.show();
            }
        });
    }

    /**
     * 取消对话框
     */
    public void dismissDialog() {

        getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
                    mAnimationDrawable.stop();
                }
                if (mWaittingDialog != null && mWaittingDialog.isShowing()) {
                    mWaittingDialog.dismiss();
                }
            }
        });
    }
    /**
     * 根据id查找view
     *
     * @param res
     * @return
     */
    public View findViewById(int res) {
        try {
            if (mRootView != null) {
                View view = mRootView.findViewById(res);
                return view;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
