package com.project.xiaodong.mytimeapp.frame.base.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.application.ActivityStack;
import com.project.xiaodong.mytimeapp.frame.eventbus.EventCenter;
import com.project.xiaodong.mytimeapp.frame.task.core.ITask;
import com.project.xiaodong.mytimeapp.frame.task.core.TaskManager;
import com.project.xiaodong.mytimeapp.frame.utils.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

import static com.project.xiaodong.mytimeapp.frame.utils.NetworkUtil.NETWORK_CHANGE_ACTION;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public abstract class BaseActivity extends BasePermissionsActivity {
    protected Activity mActivity;
    private LinearLayout mRootView;
    private ViewGroup mHeaderView;
    private ViewGroup mContentView;
    private LayoutInflater mLayoutInflater;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.pop(this);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.push(this);
        if (enabledEventBus()) {
            EventBus.getDefault().register(this);
        }
        mActivity = this;

        mLayoutInflater = LayoutInflater.from(this);
        setContentView(R.layout.time_base_layout);
        mRootView = (LinearLayout) findViewById(R.id.rootLayout);

        if (this.getHeaderLayoutId() > -1) {
            this.mHeaderView = (ViewGroup) this.mLayoutInflater.inflate(this.getHeaderLayoutId(), (ViewGroup) null);
            this.mRootView.addView(this.mHeaderView, -1, -2);
        }

        if (this.getContentLayoutId() > -1) {
            this.mContentView = (ViewGroup) this.mLayoutInflater.inflate(this.getContentLayoutId(), (ViewGroup) null);
            this.mRootView.addView(this.mContentView, -1, -1);
        }
        if (!NetworkUtil.isNetworkAvailable(this)) {
            onNetworkInvalid();
        }
        ButterKnife.bind(this);
        initValue(savedInstanceState);
        initWidget(savedInstanceState);
        initListener(savedInstanceState);
        initData(savedInstanceState);
    }

    protected final View inflate(int resLayoutId) {
        return getInflater().inflate(resLayoutId, null);
    }

    protected final LayoutInflater getInflater() {
        return mLayoutInflater;
    }


    protected void initData(Bundle savedInstanceState) {
    }

    protected void initListener(Bundle savedInstanceState) {
    }

    protected void initWidget(Bundle savedInstanceState) {
    }

    protected void initValue(Bundle savedInstanceState) {

    }

    /**
     * 无网络
     */
    protected static void onNetworkInvalid() {

    }

    /**
     * 有网络
     */
    protected static void onNetworkAvailable() {
    }

    public abstract int getHeaderLayoutId();

    public abstract int getContentLayoutId();

    public ViewGroup getHeaderView() {
        return this.mHeaderView;
    }

    public void showHeaderView() {
        if (this.mHeaderView != null) {
            this.mHeaderView.setVisibility(View.VISIBLE);
        }

    }

    public void hideHeaderView() {
        if (this.mHeaderView != null) {
            this.mHeaderView.setVisibility(View.GONE);
        }

    }

    public ViewGroup getContentView() {
        return this.mContentView;
    }

    public void close() {
        this.finish();
    }


    private final static BroadcastReceiver mNetworkMonitorReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (NETWORK_CHANGE_ACTION.equals(intent.getAction())) {
                if (!NetworkUtil.isNetworkAvailable(context)) { // network invalid
                    onNetworkInvalid();
                } else if (NetworkUtil.isConnectedWifi(context)) { // valid wifi
                    onNetworkAvailable();
                } else if (NetworkUtil.isConnectedMobile(context)) {// valid mobile
                    onNetworkAvailable();
                }
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mNetworkMonitorReceiver, NetworkUtil.getNetworkChangeFilter());

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mNetworkMonitorReceiver);

    }

    /**
     * POSTING, 该事件在哪个线程发布出来的，就会在这个线程中运行，也就是说发布事件和接收事件线程在同一个线程。
     * 使用这个方法时，不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。
     * <p>
     * MAIN, 不论事件是在哪个线程中发布出来的，都会在UI线程中执行，接收事件就会在UI线程中运行，所以在方法中是不能执行耗时操作的。
     * <p>
     * BACKGROUND, 如果事件是在UI线程中发布出来的，会在子线程中运行，如果事件本来就是子线程中发布出来的，那么函数直接在该子线程中执行。
     * <p>
     * ASYNC 无论事件在哪个线程发布，都会创建新的子线程在执行
     */
    @Subscribe(threadMode = ThreadMode.MAIN)//, priority = 100
    public final void onEventCenter(EventCenter event) {
        if (null != event) {
            onEventCallback(event);
        }
    }

    /**
     * 根据code区分当前事件类型
     */
    protected void onEventCallback(EventCenter event) {
        // handle event
    }

    /**
     * 注册event bus
     *
     * @return true 自动注册eventbus
     */
    protected boolean enabledEventBus() {
        return false;
    }

    /**
     * 执行异步任务
     * <p>
     * 执行结果将在onEventCallback(EventCenter event)方法里返回，task的id 相当于event的code
     *
     * @param task 计算缓存大小   {@link com.project.xiaodong.mytimeapp.frame.task.CalculateCacheTask }
     *             清除缓存       {@link com.project.xiaodong.mytimeapp.frame.task.ClearCacheTask}
     *             压缩图片       {@link com.project.xiaodong.mytimeapp.frame.task.CompressImageTask}
     */
    protected final void execTask(ITask task) {
        if (null != task) {
            TaskManager.getInstance().excute(task);
        }
    }


}
