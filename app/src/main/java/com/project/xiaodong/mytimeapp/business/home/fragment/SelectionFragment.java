package com.project.xiaodong.mytimeapp.business.home.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.HomeFragment;
import com.project.xiaodong.mytimeapp.business.home.bean.HotPlayMoviesBean;
import com.project.xiaodong.mytimeapp.business.home.fragment.adapter.SelectionAdapter;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeSelectionPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.view.IBaseView;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreWithHorRecycleView;
import com.project.xiaodong.mytimeapp.frame.view.refresh.PullRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by xiaodong.jin on 2017/10/17.
 */

public class SelectionFragment extends BaseFragment implements IBaseView<HotPlayMoviesBean> {


    @InjectView(R.id.loadMoreRecyclerView)
    LoadMoreWithHorRecycleView mLoadMoreRecyclerView;
    @InjectView(R.id.pull_refresh)
    PullRefreshLayout mPullRefresh;

    HomeSelectionPresenter mHomeSelectionPresenter;

    List<BeanWrapper> mData;
    private SelectionAdapter mAdapter;
    private HashMap mHashMapMenu;
    private boolean isRefresh;

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
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mPullRefresh.init();
        mLoadMoreRecyclerView.setHasLoadMore(false);
        mLoadMoreRecyclerView.setNoLoadMoreHideView(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mLoadMoreRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new SelectionAdapter(mContext, mData);
        mLoadMoreRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void initListener() {
        super.initListener();

        mPullRefresh.setRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullRefresh.refreshComplete();
            }

            @Override
            public boolean isCandoRefresh() {
                HomeFragment parentFragment = (HomeFragment) getParentFragment();
                return parentFragment.isRefrsh();
            }


        });

    }

    @Override
    public void initData() {
        super.initData();
        mAdapter.getData().clear();
        mData.clear();
        //占位
        mData.add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, null));
        mData.add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, null));
        mData.add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, null));
        mData.add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, null));
        mData.add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, null));
        mData.add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, null));
        mData.add(getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, null));
        mAdapter.getData().addAll(mData);
//        data.add(getDataByType(SelectionAdapter.TYPE_LIVE_SHOP, null));
        mHomeSelectionPresenter.getData("");
    }


    @Override
    public void setData(HotPlayMoviesBean data) {
        List<HotPlayMoviesBean> movies = data.movies;

        mAdapter.getData().set(0, getDataByType(SelectionAdapter.TYPE_HOT_MOVIES, movies));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addData(HotPlayMoviesBean data) {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onFailure(String msg) {

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
