package com.project.xiaodong.mytimeapp.business.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by xiaodong.jin on 2017/9/26.
 */

public class HomeFragment extends BaseFragment implements IBaseView<TopModuleBean> {


    private final int START_TURNING = 5000; //轮播图时间
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.rl_city)
    RelativeLayout rlCity;
    @BindView(R.id.btn_search)
    ImageButton btnSearch;
    @BindView(R.id.position_1)
    ImageButton position1;
    @BindView(R.id.rel_right)
    RelativeLayout relRight;
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rel_center)
    RelativeLayout relCenter;
    @BindView(R.id.driver)
    View driver;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.cb_banner)
    ConvenientBanner cbBanner;
    @BindView(R.id.tab_indicator)
    TabIndicatorLayout tabIndicator;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    APSTSViewPager viewpager;
    Unbinder unbinder;

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
        viewpager.setNoFocus(false);
        viewpager.setOffscreenPageLimit(2);
        //这里要用getChildFragmentManager()
        viewpager.setAdapter(new FragmentAdapter(getChildFragmentManager(), mFragments));
        viewpager.setCurrentItem(0);

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
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cbBanner.getLayoutParams();
        if (lp == null) {
            lp = new LinearLayout.LayoutParams(width, height);
            cbBanner.setLayoutParams(lp);
        } else {
            lp.height = height;
        }

        cbBanner
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
        TabLayoutUtil.initTabLayout(tabIndicator, viewpager, title, colors, images, mContext);

    }

    private void setBanner(List<TopModuleBean.GalleryListBean> galleryList) {
        for (TopModuleBean.GalleryListBean galleryListBean : galleryList) {
            if (galleryListBean != null && galleryListBean.img != null) {
                networkImages.add(galleryListBean.img);
            }
        }

        //只有一张图片时，不轮播，指示器不显示
        if (networkImages.size() == 1) {
            cbBanner.setCanLoop(false);
            cbBanner.setPointViewVisible(false);
        } else {
            cbBanner.setCanLoop(true);
            cbBanner.setPointViewVisible(true);
        }
        if (networkImages.size() > 0) {
            cbBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, networkImages);
        }
        startTurn();
    }

    private void startTurn() {
        if (cbBanner != null && !cbBanner.isTurning() && networkImages != null && networkImages.size() > 1) {
            cbBanner.startTurning(START_TURNING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
