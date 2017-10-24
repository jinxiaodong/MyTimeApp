package com.project.xiaodong.mytimeapp.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private Animation mAnimation;

    @Override
    public int getHeaderLayoutId() {
        return -1;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);

    }

    @Override
    protected void initListener(Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIvStart.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        startActivity(new Intent(mContext, MainActivity.class));
                        finish();
                    }
                }, 500);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mIvStart.setAnimation(mAnimation);

    }
}
