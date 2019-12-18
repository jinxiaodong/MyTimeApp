package com.project.xiaodong.mytimeapp.business.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.HomeFragment;
import com.project.xiaodong.mytimeapp.business.home.bean.HotPlayMoviesBean;
import com.project.xiaodong.mytimeapp.business.home.bean.LiveAndShopBean;
import com.project.xiaodong.mytimeapp.business.home.bean.MovieAdvListBean;
import com.project.xiaodong.mytimeapp.business.home.bean.SelectionAdvanceBean;
import com.project.xiaodong.mytimeapp.business.home.fragment.adapter.SelectionAdapter;
import com.project.xiaodong.mytimeapp.business.location.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;
import com.project.xiaodong.mytimeapp.frame.constants.TimeKey;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeSelectionAdvacePresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeSelectionLiveAndShopPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeSelectionPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.IAdvanceView;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ILiveAndShopView;
import com.project.xiaodong.mytimeapp.frame.presenter.view.IBaseView;
import com.project.xiaodong.mytimeapp.frame.utils.JsonUtil;
import com.project.xiaodong.mytimeapp.frame.utils.LoactionUtils;
import com.project.xiaodong.mytimeapp.frame.utils.LogUtil;
import com.project.xiaodong.mytimeapp.frame.utils.NetworkUtil;
import com.project.xiaodong.mytimeapp.frame.utils.SharePreferenceUtil;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreWithHorRecycleView;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.OnLoadMoreListener;
import com.project.xiaodong.mytimeapp.frame.view.refresh.PullToRefreshWithHorFrameLayout;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static com.project.xiaodong.mytimeapp.R.id.loadMoreRecyclerView;

/**
 * Created by xiaodong.jin on 2017/10/17.
 */

public class SelectionFragment extends BaseFragment implements IBaseView<HotPlayMoviesBean>, ILiveAndShopView, IAdvanceView {


    @BindView(loadMoreRecyclerView)
    LoadMoreWithHorRecycleView mLoadMoreRecyclerView;
    @BindView(R.id.pull_refresh)
    PullToRefreshWithHorFrameLayout mPullRefresh;
    @BindView(R.id.rl_selection)
    RelativeLayout mRelativeLayout;
    /*
     *正在热映
     */
    HomeSelectionPresenter mHomeSelectionPresenter;
    /**
     * 直播商场
     */
    HomeSelectionLiveAndShopPresenter mHomeSelectionLiveAndShopPresenter;
    /**
     * 精彩预告
     */
    HomeSelectionAdvacePresenter mHomeSelectionAdvacePresenter;
    private SelectionAdapter mAdapter;
    private HashMap mHashMapMenu;
    private boolean isRefresh;
    private HomeFragment mParentFragment;

    private MTimeCityInfo mMTimeCityInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_selection;
    }

    @Override
    protected void onNetworkInvalid() {
        super.onNetworkInvalid();
        showNoDataNoti(mRelativeLayout, R.layout.default_page_failed);
    }

    @Override
    public void reRequestData() {
        super.reRequestData();
        hideNoDataNoti();
        if (NetworkUtil.isNetworkAvailable(mContext)) {
            initData();
        } else {
            onNetworkInvalid();
        }
    }

    @Override
    protected void initValue() {
        super.initValue();
        mMTimeCityInfo = LoactionUtils.getUserChooseCity();
        mHashMapMenu = new HashMap();
//        mData = new ArrayList<>();
        mHomeSelectionPresenter = new HomeSelectionPresenter(mContext, this);
        mHomeSelectionLiveAndShopPresenter = new HomeSelectionLiveAndShopPresenter(mContext, this);
        mHomeSelectionAdvacePresenter = new HomeSelectionAdvacePresenter(mContext, this);
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mParentFragment = (HomeFragment) getParentFragment();
        mPullRefresh.disableWhenHorizontalMove(true);

        mLoadMoreRecyclerView.setHasLoadMore(false);
        mLoadMoreRecyclerView.setNoLoadMoreHideView(true);
        mLoadMoreRecyclerView.setNoLoadMoreHideViewFrist(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mLoadMoreRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new SelectionAdapter(mContext, null);
        mLoadMoreRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();

        mPullRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                return mParentFragment.isRefrsh();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mMTimeCityInfo = LoactionUtils.getUserChooseCity();
                mHomeSelectionPresenter.getData(mMTimeCityInfo.id);
            }
        });


        mLoadMoreRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                LogUtil.e("sssssssssss");
                mHomeSelectionAdvacePresenter.getMoreAdvance();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();

        String value = SharePreferenceUtil.getInstance(mContext).getValue(TimeKey.MOVIE_ADVLIST, "");
        List<MovieAdvListBean> movieAdvListBeen = JsonUtil.parseList(value, MovieAdvListBean.class);
        if (movieAdvListBeen != null && movieAdvListBeen.size() > 0) {
            mPullRefresh.setMovieAdvList(movieAdvListBeen);
        }
        refreshCity();

    }

    @Override
    public void refreshCity() {
        showDialog();
        mMTimeCityInfo = LoactionUtils.getUserChooseCity();
        mHomeSelectionPresenter.getData(mMTimeCityInfo.id);
    }


    @Override
    public void setData(HotPlayMoviesBean data) {
        dismissDialog();
        mPullRefresh.refreshComplete();

        mAdapter.getData().clear();
//        mAdapter.getData().addAll(mData);
        List<HotPlayMoviesBean> movies = data.movies;
        mAdapter.getData().add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, movies));
        mHomeSelectionLiveAndShopPresenter.getLiveAndShop();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addData(HotPlayMoviesBean data) {

    }

    @Override
    public void onEmpty() {
        dismissDialog();
        mPullRefresh.refreshComplete();
        mAdapter.getData().clear();
        mHomeSelectionLiveAndShopPresenter.getLiveAndShop();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        dismissDialog();
        mPullRefresh.refreshComplete();
        mAdapter.getData().clear();
        showNoDataNoti(mRelativeLayout, R.layout.default_page_failed);
    }

    /**
     * 精彩预告
     *
     * @param data
     */
    @Override
    public void setData(SelectionAdvanceBean.AdvanceBean data) {
        List<SelectionAdvanceBean.AdvanceBean.DataBean> data1 = data.data;
        if (data1 != null) {
            for (SelectionAdvanceBean.AdvanceBean.DataBean dataBean : data1) {
                if (dataBean != null) {
                    BeanWrapper beanWrapper = new BeanWrapper();
                    beanWrapper.viewType = dataBean.type;
                    beanWrapper.data = dataBean;
                    mAdapter.getData().add(beanWrapper);
                }
            }
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void addData(SelectionAdvanceBean.AdvanceBean data) {

        List<SelectionAdvanceBean.AdvanceBean.DataBean> data1 = data.data;
        if (data1 != null) {
            for (SelectionAdvanceBean.AdvanceBean.DataBean dataBean : data1) {
                if (dataBean != null) {
                    BeanWrapper beanWrapper = new BeanWrapper();
                    beanWrapper.viewType = dataBean.type;
                    beanWrapper.data = dataBean;
                    mAdapter.getData().add(beanWrapper);
                }
            }
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onAdvanceEmpty() {
//        mLoadMoreRecyclerView.setHasLoadMore(false);
        mLoadMoreRecyclerView.showNoMoreUI();
    }

    @Override
    public void onAdvanceFailure(String msg) {
//        mLoadMoreRecyclerView.setHasLoadMore(false);
        mLoadMoreRecyclerView.showFailUI();
    }

    @Override
    public void onComplete(boolean hasMore) {
        mLoadMoreRecyclerView.setHasLoadMore(hasMore);
    }


    @Override
    public void onLiveAndShopSuccess(LiveAndShopBean data) {
        mPullRefresh.refreshComplete();
        mAdapter.getData().add(getDataByType(SelectionAdapter.TYPE_LIVE_SHOP, data));
        mAdapter.notifyDataSetChanged();
        mHomeSelectionAdvacePresenter.getAdvanceData();
    }

    @Override
    public void onLiveAndShopFailure(String msg) {

    }

    private BeanWrapper getDataByType(int type, Object data) {
        BeanWrapper bean = null;
        if (mHashMapMenu.containsKey(type)) {
            bean = (BeanWrapper) mHashMapMenu.get(type);
            bean.data = data;
        } else {
            bean = new BeanWrapper();
            bean.viewType = type;
            bean.data = data;
            mHashMapMenu.put(type, bean);
        }
        return bean;
    }
}
