package com.project.xiaodong.mytimeapp.business.home;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.TestFragment;
import com.project.xiaodong.mytimeapp.business.FragmentAdapter;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.constants.DeviceInfo;
import com.project.xiaodong.mytimeapp.frame.network.RetrofitClient;
import com.project.xiaodong.mytimeapp.frame.network.TimeBaseEntity;
import com.project.xiaodong.mytimeapp.frame.tabindicator.TabIndicatorLayout;
import com.project.xiaodong.mytimeapp.frame.tabindicator.TabLayoutUtil;
import com.project.xiaodong.mytimeapp.frame.view.APSTSViewPager;
import com.project.xiaodong.mytimeapp.frame.view.banner.ConvenientBanner;
import com.project.xiaodong.mytimeapp.frame.view.banner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


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

    private String[] title = new String[]{"精选", "资讯", "选电影", "预告片", "影评"};//
    private Integer[] colors = new Integer[]{Color.parseColor("#FF9100"), Color.parseColor("#F15353"), Color.parseColor("#1E7DD7"), Color.parseColor("#FFBD2F"), Color.parseColor("#8DC635")};

    //
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initValue() {
        super.initValue();

        mFragments.add(new TestFragment("精选"));
        mFragments.add(new TestFragment("资讯"));
        mFragments.add(new TestFragment("选电影"));
        mFragments.add(new TestFragment("预告片"));
        mFragments.add(new TestFragment("影评"));
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mViewpager.setNoFocus(false);
        mViewpager.setOffscreenPageLimit(2);
        //这里要用getChildFragmentManager()
        mViewpager.setAdapter(new FragmentAdapter(getChildFragmentManager(), mFragments));
        mViewpager.setCurrentItem(0);

        initBanner();
    }


    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        super.initData();
        TabLayoutUtil.initTabLayout(mTabIndicator, mViewpager, Arrays.asList(title), Arrays.asList(colors), mContext);

        Log.e("TAG", "好吧不知道有没有成功啊1");
        Map<String, String> map = new HashMap<>();
//        map.put("locationId", "292");

        RetrofitClient.getInstance(mContext).createBaseApi().get("index/topModule.api", map, new Observer<TimeBaseEntity<TomModuleBean>>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("TAG", "好吧不知道有没有成功啊5");
            }

            @Override
            public void onNext(@NonNull TimeBaseEntity<TomModuleBean> tomModuleBeanTimeBaseEntity) {

                if (tomModuleBeanTimeBaseEntity != null) {

                    Log.e("TAG", tomModuleBeanTimeBaseEntity.toString());
                }
                Log.e("TAG", "好吧不知道有没有成功啊6");
            }


            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("TAG", "好吧不知道有没有成功啊7" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "好吧不知道有没有成功啊8");
            }
        });
    }

    private void initBanner() {
        int width = DeviceInfo.WIDTHPIXELS;
        int height = (int) ((float) width / 1.44);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mCbBanner.getLayoutParams();
        if (lp == null) {
            lp = new LinearLayout.LayoutParams(width, height);
            mCbBanner.setLayoutParams(lp);
        } else {
            lp.height = height;
        }

        mCbBanner
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.bg_loaddialog, R.drawable.bg_status})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                })
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                })
                .setOffscreenPageLimit(10);
    }

}
