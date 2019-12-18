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
import com.project.xiaodong.mytimeapp.business.home.HomeFragment;
import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.view.APSTSViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends TBaseActivity {


    List<BaseFragment> mFragments = new ArrayList<>();

    private static final int VIEW_HOME = 0;
    private static final int VIEW_TICKETS = 1;
    private static final int VIEW_MALL = 2;
    private static final int VIEW_LIVE = 3;
    private static final int VIEW_MINE = 4;
    private static final int VIEW_SIZE = 5;
    @BindView(R.id.iv_tab_home)
    ImageView ivTabHome;
    @BindView(R.id.tv_tab_home)
    TextView tvTabHome;
    @BindView(R.id.rl_tab_home)
    RelativeLayout rlTabHome;
    @BindView(R.id.iv_tab_payticket)
    ImageView ivTabPayticket;
    @BindView(R.id.tv_tab_payticket)
    TextView tvTabPayticket;
    @BindView(R.id.rl_tab_payticket)
    RelativeLayout rlTabPayticket;
    @BindView(R.id.iv_tab_mall)
    ImageView ivTabMall;
    @BindView(R.id.tv_tab_mall)
    TextView tvTabMall;
    @BindView(R.id.rl_tab_mall)
    RelativeLayout rlTabMall;
    @BindView(R.id.iv_tab_live)
    ImageView ivTabLive;
    @BindView(R.id.tv_tab_live)
    TextView tvTabLive;
    @BindView(R.id.rl_tab_live)
    RelativeLayout rlTabLive;
    @BindView(R.id.iv_tab_mine)
    ImageView ivTabMine;
    @BindView(R.id.tv_tab_mine)
    TextView tvTabMine;
    @BindView(R.id.point)
    ImageView point;
    @BindView(R.id.rl_tab_mine)
    RelativeLayout rlTabMine;
    @BindView(R.id.ll_main_page_tab)
    LinearLayout llMainPageTab;
    @BindView(R.id.fl_main_page_tab)
    FrameLayout flMainPageTab;
    @BindView(R.id.viewpager)
    APSTSViewPager viewpager;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;

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
        mFragments.add(new HomeFragment());
        mFragments.add(new TestFragment("购票"));
        mFragments.add(new TestFragment("商城"));
        mFragments.add(new TestFragment("直播"));
        mFragments.add(new TestFragment("我的"));

    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        viewpager.setNoFocus(true);
        viewpager.setOffscreenPageLimit(VIEW_SIZE);
        viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mFragments));
    }

    @Override
    protected void initListener(Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                    setTab(viewpager.getCurrentItem());
                }
            }
        });

    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewpager.setCurrentItem(VIEW_HOME);
        setTab(VIEW_HOME);
    }

    private void setTab(int position) {

        ivTabHome.setSelected(false);
        tvTabHome.setSelected(false);
        ivTabPayticket.setSelected(false);
        tvTabPayticket.setSelected(false);
        ivTabMall.setSelected(false);
        tvTabMall.setSelected(false);
        ivTabLive.setSelected(false);
        tvTabLive.setSelected(false);
        ivTabMine.setSelected(false);
        tvTabMine.setSelected(false);
        switch (position) {
            case VIEW_HOME:
                ivTabHome.setSelected(true);
                tvTabHome.setSelected(true);
                break;
            case VIEW_TICKETS:
                ivTabPayticket.setSelected(true);
                tvTabPayticket.setSelected(true);
                break;
            case VIEW_MALL:
                ivTabMall.setSelected(true);
                tvTabMall.setSelected(true);
                break;
            case VIEW_LIVE:
                ivTabLive.setSelected(true);
                tvTabLive.setSelected(true);
                break;
            case VIEW_MINE:
                ivTabMine.setSelected(true);
                tvTabMine.setSelected(true);
                break;
        }
    }

    @OnClick({R.id.rl_tab_home, R.id.rl_tab_payticket, R.id.rl_tab_mall, R.id.rl_tab_live, R.id.rl_tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_tab_home:
                viewpager.setCurrentItem(VIEW_HOME);
                break;
            case R.id.rl_tab_payticket:
                viewpager.setCurrentItem(VIEW_TICKETS);
                break;
            case R.id.rl_tab_mall:
                viewpager.setCurrentItem(VIEW_MALL);
                break;
            case R.id.rl_tab_live:
                viewpager.setCurrentItem(VIEW_LIVE);
                break;
            case R.id.rl_tab_mine:
                viewpager.setCurrentItem(VIEW_MINE);
                break;
        }
    }

}
