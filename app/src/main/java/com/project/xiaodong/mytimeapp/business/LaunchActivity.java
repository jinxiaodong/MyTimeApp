package com.project.xiaodong.mytimeapp.business;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;

import butterknife.InjectView;

/**
 * 启动页
 */
public class LaunchActivity extends TBaseActivity {

    @InjectView(R.id.iv_start)
    ImageView mIvStart;
    @InjectView(R.id.activity_launch)
    RelativeLayout mActivityLaunch;

    @Override
    public int getHeaderLayoutId() {
        return -1;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_launch;
    }



}
