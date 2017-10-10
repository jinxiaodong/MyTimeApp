package com.project.xiaodong.mytimeapp.business.home;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
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
import com.project.xiaodong.mytimeapp.business.home.adapter.NetworkImageHolderView;
import com.project.xiaodong.mytimeapp.business.home.bean.TopModuleBean;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.constants.DeviceInfo;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeTopModulePresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.view.IBaseView;
import com.project.xiaodong.mytimeapp.frame.tabindicator.TabIndicatorLayout;
import com.project.xiaodong.mytimeapp.frame.tabindicator.TabLayoutUtil;
import com.project.xiaodong.mytimeapp.frame.view.APSTSViewPager;
import com.project.xiaodong.mytimeapp.frame.view.banner.ConvenientBanner;
import com.project.xiaodong.mytimeapp.frame.view.banner.holder.CBViewHolderCreator;
import com.project.xiaodong.mytimeapp.frame.view.banner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


/**
 * Created by xiaodong.jin on 2017/9/26.
 */

public class HomeFragment extends BaseFragment implements IBaseView<TopModuleBean> {
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

    private final int START_TURNING = 5000; //轮播图时间

    private List<BaseFragment> mFragments = new ArrayList<>();
    private List<String> networkImages = new ArrayList<String>();

    //    private String[] title = new String[]{"精选", "资讯", "选电影", "预告片", "影评"};//
    private List<String> title = new ArrayList<>();//
    //    private Integer[] colors = new Integer[]{Color.parseColor("#FF9100"), Color.parseColor("#F15353"), Color.parseColor("#1E7DD7"), Color.parseColor("#FFBD2F"), Color.parseColor("#8DC635")};
    private List<Integer> colors = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    HomeTopModulePresenter mHomeTopModulePresenter;

    //
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initValue() {
        super.initValue();
        mHomeTopModulePresenter = new HomeTopModulePresenter(mContext, this);
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
        mHomeTopModulePresenter.getData();
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
                .setOffscreenPageLimit(10);
    }

    @Override
    public void initData(TopModuleBean data) {
        Log.e("tag", "成功");
        //分类tab集合数据
        List<TopModuleBean.CategoryListBean> categoryList = data.categoryList;

        //banner
        List<TopModuleBean.GalleryListBean> galleryList = data.galleryList;


        if (galleryList != null && galleryList.size() > 0) {
            setBanner(galleryList);
        }

        if (categoryList != null && categoryList.size() > 0) {
            setTable(categoryList);
        }

    }


    @Override
    public void addData(TopModuleBean data) {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onFailure(String msg) {

    }


    private void setTable(List<TopModuleBean.CategoryListBean> categoryList) {
        for (TopModuleBean.CategoryListBean categoryListBean : categoryList) {
            if (categoryListBean != null) {
                title.add(categoryListBean.name);
                colors.add(Color.parseColor(categoryListBean.selectColor));
                images.add(categoryListBean.img);
            }
        }
        TabLayoutUtil.initTabLayout(mTabIndicator, mViewpager, title, colors, images, mContext);

    }

    private void setBanner(List<TopModuleBean.GalleryListBean> galleryList) {
        for (TopModuleBean.GalleryListBean galleryListBean : galleryList) {
            if (galleryListBean != null && galleryListBean.img != null) {
                networkImages.add(galleryListBean.img);
            }
        }

        //只有一张图片时，不轮播，指示器不显示
        if (networkImages.size() == 1) {
            mCbBanner.setCanLoop(false);
            mCbBanner.setPointViewVisible(false);
        } else {
            mCbBanner.setCanLoop(true);
            mCbBanner.setPointViewVisible(true);
        }
        if (networkImages.size() > 0) {
            mCbBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, networkImages);
        }
        startTurn();
    }

    private void startTurn() {
        if (mCbBanner != null && !mCbBanner.isTurning() && networkImages != null && networkImages.size() > 1) {
            mCbBanner.startTurning(START_TURNING);
        }
    }
}
