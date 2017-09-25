package com.project.xiaodong.mytimeapp;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.view.APSTSViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends TBaseActivity {


    @InjectView(R.id.viewpager)
    APSTSViewPager mViewpager;
    //首页
    @InjectView(R.id.iv_tab_home)
    ImageView mIvTabHome;
    @InjectView(R.id.tv_tab_home)
    TextView mTvTabHome;
    @InjectView(R.id.rl_tab_home)
    RelativeLayout mRlTabHome;
    //购票
    @InjectView(R.id.iv_tab_payticket)
    ImageView mIvTabPayticket;
    @InjectView(R.id.tv_tab_payticket)
    TextView mTvTabPayticket;
    @InjectView(R.id.rl_tab_payticket)
    RelativeLayout mRlTabPayticket;

    //商城
    @InjectView(R.id.iv_tab_mall)
    ImageView mIvTabMall;
    @InjectView(R.id.tv_tab_mall)
    TextView mTvTabMall;
    @InjectView(R.id.rl_tab_mall)
    RelativeLayout mRlTabMall;
    //直播
    @InjectView(R.id.iv_tab_live)
    ImageView mIvTabLive;
    @InjectView(R.id.tv_tab_live)
    TextView mTvTabLive;
    @InjectView(R.id.rl_tab_live)
    RelativeLayout mRlTabLive;
    //我的
    @InjectView(R.id.iv_tab_mine)
    ImageView mIvTabMine;
    @InjectView(R.id.tv_tab_mine)
    TextView mTvTabMine;
    @InjectView(R.id.point)
    ImageView mPoint;
    @InjectView(R.id.rl_tab_mine)
    RelativeLayout mRlTabMine;

    @InjectView(R.id.ll_main_page_tab)
    LinearLayout mLlMainPageTab;
    @InjectView(R.id.fl_main_page_tab)
    FrameLayout mFlMainPageTab;
    @InjectView(R.id.content_main)
    RelativeLayout mContentMain;


    List<BaseFragment> mFragments = new ArrayList<>();
    @Override
    public int getContentLayoutId() {
        return super.getContentLayoutId();
    }

    @Override
    public int getHeaderLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mFragments.add(new TestFragment("首页"));
        mFragments.add(new TestFragment("首页"));
        mFragments.add(new TestFragment("首页"));
        mFragments.add(new TestFragment("首页"));
        mFragments.add(new TestFragment("首页"));
    }

}
