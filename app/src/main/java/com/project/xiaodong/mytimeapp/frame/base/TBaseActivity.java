package com.project.xiaodong.mytimeapp.frame.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.view.dialog.DialogUtil;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class TBaseActivity extends BaseActivity {


    /*TAG*/
    public final String TAG = getClass().getSimpleName();

    /**
     * activity
     */
    protected Context mContext;
    /**
     * 处理框
     */
    private Dialog mWaittingDialog = null;

    /**
     * 处理框动画
     */
    private AnimationDrawable mAnimationDrawable = null;
    /**
     * 用于在异步请求中更新ui
     */
    private Handler mHandler;

    @Override
    public int getHeaderLayoutId() {
        return R.layout.common_activity_header;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.common_activity_content;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        mContext = this;
    }

    /**
     * @param
     * @return
     * @description getHandler
     * @author SongZheng
     * @date 2016/3/7
     */
    public Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(mContext.getMainLooper());
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

}
