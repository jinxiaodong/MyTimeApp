package com.project.xiaodong.mytimeapp.business;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.TestFragment;
import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.view.APSTSViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

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

    private static final int VIEW_HOME = 0;
    private static final int VIEW_TICKETS = 1;
    private static final int VIEW_MALL = 2;
    private static final int VIEW_LIVE = 3;
    private static final int VIEW_MINE = 4;
    private static final int VIEW_SIZE = 5;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getHeaderLayoutId() {
        return -1;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        mFragments.add(new TestFragment("首页"));
        mFragments.add(new TestFragment("购票"));
        mFragments.add(new TestFragment("商城"));
        mFragments.add(new TestFragment("直播"));
        mFragments.add(new TestFragment("我的"));

    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        mViewpager.setNoFocus(false);
        mViewpager.setOffscreenPageLimit(VIEW_SIZE);
        mViewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mFragments));
    }

    @Override
    protected void initListener(Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //0是静止，1是正在滑动，2是停止滑动
                if (state == 2) {
                    //设置滑动ViewPager导航同步变化
                    setTab(mViewpager.getCurrentItem());
                }
            }
        });

    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mViewpager.setCurrentItem(VIEW_HOME);
        setTab(VIEW_HOME);
    }

    private void setTab(int position) {

        mIvTabHome.setSelected(false);
        mTvTabHome.setSelected(false);
        mIvTabPayticket.setSelected(false);
        mTvTabPayticket.setSelected(false);
        mIvTabMall.setSelected(false);
        mTvTabMall.setSelected(false);
        mIvTabLive.setSelected(false);
        mTvTabLive.setSelected(false);
        mIvTabMine.setSelected(false);
        mTvTabMine.setSelected(false);
        switch (position) {
            case VIEW_HOME:
                mIvTabHome.setSelected(true);
                mTvTabHome.setSelected(true);
                break;
            case VIEW_TICKETS:
                mIvTabPayticket.setSelected(true);
                mTvTabPayticket.setSelected(true);
                break;
            case VIEW_MALL:
                mIvTabMall.setSelected(true);
                mTvTabMall.setSelected(true);
                break;
            case VIEW_LIVE:
                mIvTabLive.setSelected(true);
                mTvTabLive.setSelected(true);
                break;
            case VIEW_MINE:
                mIvTabMine.setSelected(true);
                mTvTabMine.setSelected(true);
                break;
        }
    }

    @OnClick({R.id.rl_tab_home, R.id.rl_tab_payticket, R.id.rl_tab_mall, R.id.rl_tab_live, R.id.rl_tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_tab_home:
                mViewpager.setCurrentItem(VIEW_HOME);
                break;
            case R.id.rl_tab_payticket:
                mViewpager.setCurrentItem(VIEW_TICKETS);
                break;
            case R.id.rl_tab_mall:
                mViewpager.setCurrentItem(VIEW_MALL);
                break;
            case R.id.rl_tab_live:
                mViewpager.setCurrentItem(VIEW_LIVE);
                break;
            case R.id.rl_tab_mine:
                mViewpager.setCurrentItem(VIEW_MINE);
                break;
        }
    }
}
