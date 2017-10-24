package com.project.xiaodong.mytimeapp.business.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.HomeFragment;
import com.project.xiaodong.mytimeapp.business.home.bean.HotPlayMoviesBean;
import com.project.xiaodong.mytimeapp.business.home.bean.LiveAndShopBean;
import com.project.xiaodong.mytimeapp.business.home.bean.SelectionAdvanceBean;
import com.project.xiaodong.mytimeapp.business.home.fragment.adapter.SelectionAdapter;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeSelectionAdvacePresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeSelectionLiveAndShopPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeSelectionPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.IAdvanceView;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ILiveAndShopView;
import com.project.xiaodong.mytimeapp.frame.presenter.view.IBaseView;
import com.project.xiaodong.mytimeapp.frame.utils.LogUtil;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreWithHorRecycleView;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.OnLoadMoreListener;
import com.project.xiaodong.mytimeapp.frame.view.refresh.PullToRefreshWithHorFrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static com.project.xiaodong.mytimeapp.R.id.loadMoreRecyclerView;

/**
 * Created by xiaodong.jin on 2017/10/17.
 */

public class SelectionFragment extends BaseFragment implements IBaseView<HotPlayMoviesBean>, ILiveAndShopView, IAdvanceView {


    @InjectView(loadMoreRecyclerView)
    LoadMoreWithHorRecycleView mLoadMoreRecyclerView;
    @InjectView(R.id.pull_refresh)
    PullToRefreshWithHorFrameLayout mPullRefresh;

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
    List<BeanWrapper> mData;
    private SelectionAdapter mAdapter;
    private HashMap mHashMapMenu;
    private boolean isRefresh;
    private HomeFragment mParentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_selection;
    }


    @Override
    protected void initValue() {
        super.initValue();
        mHashMapMenu = new HashMap();
        mData = new ArrayList<>();
        mHomeSelectionPresenter = new HomeSelectionPresenter(mContext, this);
        mHomeSelectionLiveAndShopPresenter = new HomeSelectionLiveAndShopPresenter(mContext, this);
        mHomeSelectionAdvacePresenter = new HomeSelectionAdvacePresenter(mContext, this);
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mParentFragment = (HomeFragment) getParentFragment();
        mPullRefresh.disableWhenHorizontalMove(true);

        mLoadMoreRecyclerView.setHasLoadMore(true);
        mLoadMoreRecyclerView.setNoLoadMoreHideView(true);
        mLoadMoreRecyclerView.setNoLoadMoreHideViewFrist(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mLoadMoreRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new SelectionAdapter(mContext, mData);
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
                mHomeSelectionPresenter.getData("");
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
    public void refreshCity() {

    }

    @Override
    public void initData() {
        super.initData();
        mAdapter.getData().clear();
        mData.clear();
        //占位
        mData.add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, null));
        mData.add(getDataByType(SelectionAdapter.TYPE_LIVE_SHOP, null));
        mAdapter.getData().addAll(mData);
        mHomeSelectionPresenter.getData("");

    }


    @Override
    public void setData(HotPlayMoviesBean data) {
        mPullRefresh.refreshComplete();

        mAdapter.getData().clear();
        mAdapter.getData().addAll(mData);
        List<HotPlayMoviesBean> movies = data.movies;
        mAdapter.getData().set(0, getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, movies));
        mHomeSelectionLiveAndShopPresenter.getLiveAndShop();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addData(HotPlayMoviesBean data) {

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
    public void onEmpty() {
    }

    @Override
    public void onFailure(String msg) {
    }

    @Override
    public void onLiveAndShopSuccess(LiveAndShopBean data) {
        mPullRefresh.refreshComplete();
        mAdapter.getData().set(1, getDataByType(SelectionAdapter.TYPE_LIVE_SHOP, data));
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
