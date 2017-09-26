package com.project.xiaodong.mytimeapp.business.home;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.TestFragment;
import com.project.xiaodong.mytimeapp.business.FragmentAdapter;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.tabindicator.TabIndicatorLayout;
import com.project.xiaodong.mytimeapp.frame.tabindicator.TabLayoutUtil;
import com.project.xiaodong.mytimeapp.frame.view.APSTSViewPager;
import com.project.xiaodong.mytimeapp.frame.view.banner.ConvenientBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;



/**
 * Created by xiaodong.jin on 2017/9/26.
 */

public class HomeFragment extends BaseFragment {
    @InjectView(R.id.tv_city_name)
    TextView mTvCityName;
    @InjectView(R.id.rl_city)
    RelativeLayout mRlCity;
    @InjectView(R.id.btn_search)
    ImageButton mBtnSearch;
    @InjectView(R.id.position_1)
    ImageButton mPosition1;
    @InjectView(R.id.rel_right)
    RelativeLayout mRelRight;
    @InjectView(R.id.img_header)
    ImageView mImgHeader;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.rel_center)
    RelativeLayout mRelCenter;
    @InjectView(R.id.driver)
    View mDriver;
    @InjectView(R.id.rl_header)
    RelativeLayout mRlHeader;
    @InjectView(R.id.cb_banner)
    ConvenientBanner mCbBanner;
    @InjectView(R.id.tab_indicator)
    TabIndicatorLayout mTabIndicator;
    @InjectView(R.id.appbar)
    AppBarLayout mAppbar;
    @InjectView(R.id.viewpager)
    APSTSViewPager mViewpager;
    private List<BaseFragment> mFragments = new ArrayList<>();

    private String[] title = new String[]{"精选", "资讯"};//, "选电影", "预告片", "影评"
    private Integer[] colors = new Integer[]{Color.parseColor("#FF9100"), Color.parseColor("#F15353")};
//, Color.parseColor("#1E7DD7"), Color.parseColor("#FFBD2F"), Color.parseColor("#8DC635")
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initValue() {
        super.initValue();

        mFragments.add(new TestFragment("精选"));
        mFragments.add(new TestFragment("资讯"));
//        mFragments.add(new TestFragment("选电影"));
//        mFragments.add(new TestFragment("预告片"));
//        mFragments.add(new TestFragment("影评"));
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mViewpager.setNoFocus(false);
        mViewpager.setOffscreenPageLimit(2);
        //这里要用getChildFragmentManager()
        mViewpager.setAdapter(new FragmentAdapter(getChildFragmentManager(), mFragments));
        mViewpager.setCurrentItem(0);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        super.initData();
        TabLayoutUtil.initTabLayout(mTabIndicator, mViewpager, Arrays.asList(title), Arrays.asList(colors), mContext);


    }

}
