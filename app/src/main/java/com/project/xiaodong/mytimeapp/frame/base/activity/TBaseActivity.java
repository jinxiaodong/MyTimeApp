package com.project.xiaodong.mytimeapp.frame.base.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.view.dialog.DialogUtil;

import java.util.Timer;
import java.util.TimerTask;

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


    /**
     * 标题右边按钮布局
     */
    private RelativeLayout mRelRight;
    /**
     * 标题
     */
    private TextView mTvTitle;
    /**
     * 返回按钮布局
     */
    private RelativeLayout mRelBack;
    /**
     * 页面header布局
     */
    private RelativeLayout mRelHeader;
    /**
     * 遮罩图层布局
     */
    private LinearLayout mLinWrapper;

    /**
     * 用于在计时操作
     */
    private Timer mTimer;
    /**
     * 无数据view
     */
    private View mNoDataView;

    /*是否正在切换Fragment*/
    private boolean isSwitchFragmenting = false;

    @Override
    public int getHeaderLayoutId() {
        return R.layout.common_activity_header;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.common_activity_content;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            cancelTimer();
            if (mWaittingDialog != null) {
                mWaittingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            close();
        }
        return false;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        mContext = this;
        try {
            if (getHeaderView() != null) {
                mRelBack = (RelativeLayout) findViewById(R.id.rel_back);
                mRelRight = (RelativeLayout) findViewById(R.id.rel_right);
                mRelHeader = (RelativeLayout) findViewById(R.id.rel_header);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
    }

    @Override
    protected void initListener(Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        try {
            if (getHeaderView() != null && mRelBack != null) {
                mRelBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        close();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    /**
     *
     * @param resLayoutId           resID
     * @param fragment              fragment
     * @param isAddBackStack        是否加入返回栈
     *                              替换Fragment (默认有动画效果)
     */
    protected void replaceFragment(int resLayoutId, Fragment fragment, boolean isAddBackStack) {
        if (isSwitchFragmenting) {
            return;
        }

        isSwitchFragmenting = true;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(resLayoutId, fragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
        isSwitchFragmenting = false;
    }

    /**
     * @param resLayId       resid
     * @param fragment       fragment
     * @param isAddBackStack 是否加入返回栈
     * @param isAnimation    切换动画
     * @return
     * @description 替换Fragment
     */
    protected void replaceFragment(int resLayId, Fragment fragment,
                                   boolean isAddBackStack, boolean isAnimation) {
        if (isSwitchFragmenting) {
            return;
        }
        isSwitchFragmenting = true;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right, R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        fragmentTransaction.replace(resLayId, fragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
        isSwitchFragmenting = false;
    }



    /**
     * @param resLayId
     * @param showFragment
     * @param isAnimation
     * @param isAddBackStack
     * @param hideFragments  要隐藏的Fragment数组
     * @return
     * @description 添加Fragment
     */
    protected void addFragment(int resLayId, Fragment showFragment,
                               boolean isAnimation, boolean isAddBackStack,
                               Fragment... hideFragments) {
        if (isSwitchFragmenting) {
            return;
        }
        isSwitchFragmenting = true;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        if (isAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,
                    R.anim.slide_out_left, R.anim.slide_in_right,
                    R.anim.slide_out_left);
        }
        if (hideFragments != null) {
            for (Fragment hideFragment : hideFragments) {
                if (hideFragment != null)
                    fragmentTransaction.hide(hideFragment);
            }
        }
        fragmentTransaction.add(resLayId, showFragment);
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
        isSwitchFragmenting = false;
    }


    /**
     * @param showFragment   显示的fragment
     * @param hideFragments  要隐藏的Fragment数组
     * @param isAddBackStack 是否加入返回栈
     * @description 显示隐藏Fragment
     */
    protected void showHideFragment(Fragment showFragment, boolean isAnimation,
                                    boolean isAddBackStack, Fragment... hideFragments) {
        if (isSwitchFragmenting) {
            return;
        }
        isSwitchFragmenting = true;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        if (isAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,
                    R.anim.slide_out_left, R.anim.slide_in_right,
                    R.anim.slide_out_left);
        }
        if (hideFragments != null) {
            for (Fragment hideFragment : hideFragments) {
                if (hideFragment != null && hideFragment.isAdded())
                    fragmentTransaction.hide(hideFragment);
            }
        }
        if (showFragment != null) {
            fragmentTransaction.show(showFragment);
        }
        if (isAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
        isSwitchFragmenting = false;
    }

    /**
     * @param timerTask 定时器任务
     * @param seconds   定时器时长
     * @param loop      是否循环
     * @return Timer
     * @description 在子类中实现定时刷新功能
     */
    public Timer getTimer(TimerTask timerTask, int seconds, boolean loop) {
        try {
            cancelTimer();
            mTimer = new Timer();
            if (loop) {
                mTimer.schedule(timerTask, seconds * 1000, seconds * 1000);
            } else {
                mTimer.schedule(timerTask, seconds * 1000);
            }
            return mTimer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param
     * @return
     * @description 停止计时
     * @author SongZheng
     * @date 2016/3/7
     */
    public void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    /**
     * @param
     * @return
     * @description getHandler
     */
    public Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(mContext.getMainLooper());
        }
        return mHandler;
    }

    /**
     * @param
     * @return
     * @description 设置标题
     * @author SongZheng
     * @date 2016/3/7
     */
    public void setTitle(String title) {
        if (getHeaderView() == null) {
            return;
        }
        try {
            mTvTitle = (TextView) findViewById(R.id.tv_title);

            mTvTitle.setText(title);
            mRelHeader.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param
     * @return
     * @description 设置标题
     */
    public void setTitle(int strResId) {
        if (getHeaderView() == null) {
            return;
        }
        try {
            mTvTitle = (TextView) findViewById(R.id.tv_title);

            mTvTitle.setText(getResString(strResId));
            mTvTitle.setVisibility(View.VISIBLE);
            mRelHeader.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param
     * @return
     * @description getResString
     */
    public String getResString(int res) {
        return getResources().getString(res) + "";
    }

    /**
     * @param
     * @return
     * @description 隐藏标题
     */
    public void hideTitle() {
        if (getHeaderView() == null) {
            return;
        }
        try {
            mTvTitle.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param
     * @return
     * @description 设置标题栏显示状态
     */
    public void shwoOrHideHeader(int viewState) {
        if (getHeaderView() == null) {
            return;
        }
        try {
            mRelHeader.setVisibility(viewState);
        } catch (Exception e) {
        }
    }

    /**
     * @param
     * @return
     * @description 设置返回按钮显示状态
     */
    public void showOrHideBackButton(int viewState) {
        if (getHeaderView() == null) {
            return;
        }
        try {
            if (mRelBack == null) {
                mRelBack = (RelativeLayout) findViewById(R.id.rel_back);
            }
            mRelBack.setVisibility(viewState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param
     * @return
     * @description getBackButton
     */
    public RelativeLayout getBackButton() {
        if (getHeaderView() == null) {
            return null;
        }
        if (mRelBack == null) {
            mRelBack = (RelativeLayout) findViewById(R.id.rel_back);
        }
        return mRelBack;
    }

    /**
     * @param
     * @return
     * @description 获得标题栏右部布局
     */
    public RelativeLayout getHeaderRightLayout() {
        return mRelRight;
    }

    /**
     * 获取头布局
     */
    public View getRelHeader() {
        mRelHeader = (RelativeLayout) findViewById(R.id.rel_header);
        return mRelHeader;
    }

    /**
     * 设置标题栏颜色
     */
    public void setHeaderColor(int colorId) {
        mRelHeader = (RelativeLayout) findViewById(R.id.rel_header);

        if (getHeaderView() == null) {
            return;
        }
        if (mRelHeader == null) {
            return;
        }
        mRelHeader.setBackgroundColor(getResources().getColor(colorId));
    }

    /**
     * 设置标题颜色
     */
    public void setTitleColor(int colorId) {
        if (getHeaderView() == null) {
            return;
        }
        try {
            mRelHeader = (RelativeLayout) findViewById(R.id.rel_header);
            mTvTitle = (TextView) findViewById(R.id.tv_title);

            mTvTitle.setTextColor(getResources().getColor(colorId));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * 显示无数据提示
     */
    public void showNoDataNoti(ViewGroup viewGroup, int layoutResId) {
        try {
            if (mNoDataView == null) {
                mNoDataView = mLayoutInflater.inflate(layoutResId, null);
                mNoDataView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //donothing
                    }
                });
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                viewGroup.addView(mNoDataView, lp);
            } else {
                mNoDataView.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 隐藏无数据提示
     */
    public void hideNoDataNoti() {
        if (mNoDataView != null) {
            mNoDataView.setVisibility(View.GONE);
        }
    }
}
