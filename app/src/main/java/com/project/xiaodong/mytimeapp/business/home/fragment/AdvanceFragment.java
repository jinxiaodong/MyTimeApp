package com.project.xiaodong.mytimeapp.business.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.HomeFragment;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeAdvanceBean;
import com.project.xiaodong.mytimeapp.business.home.bean.MovieAdvListBean;
import com.project.xiaodong.mytimeapp.business.home.fragment.adapter.HomeAdvanceAdapter;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.constants.TimeKey;
import com.project.xiaodong.mytimeapp.frame.presenter.IBaseListView;
import com.project.xiaodong.mytimeapp.frame.presenter.home.AdvancePresenter;
import com.project.xiaodong.mytimeapp.frame.utils.JsonUtil;
import com.project.xiaodong.mytimeapp.frame.utils.NetworkUtil;
import com.project.xiaodong.mytimeapp.frame.utils.SharePreferenceUtil;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreRecyclerView;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.OnLoadMoreListener;
import com.project.xiaodong.mytimeapp.frame.view.refresh.PullToRefreshFrameLayout;

import java.util.List;

import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by xiaodong.jin on 2017/11/28.
 */

public class AdvanceFragment extends BaseFragment implements IBaseListView<HomeAdvanceBean> {


    @InjectView(R.id.loadMoreRecyclerView)
    LoadMoreRecyclerView mLoadMoreRecyclerView;
    @InjectView(R.id.pullToRefresh)
    PullToRefreshFrameLayout mPullToRefresh;
    @InjectView(R.id.rl_advance)
    RelativeLayout mRelativeLayout;

    private AdvancePresenter mAdvancePresenter;

    private HomeAdvanceAdapter mAdapter;
    private HomeFragment mParentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_advance;
    }


    @Override
    protected void initValue() {
        super.initValue();
        mAdvancePresenter = new AdvancePresenter(mContext, this);
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mParentFragment = (HomeFragment) getParentFragment();
        mLoadMoreRecyclerView.setHasLoadMore(true);
        mLoadMoreRecyclerView.setNoLoadMoreHideView(true);
        mLoadMoreRecyclerView.setNoLoadMoreHideViewFrist(true);
        mLoadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mAdapter = new HomeAdvanceAdapter(mContext, null);
        mLoadMoreRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mPullToRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return mParentFragment.isRefrsh();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mAdvancePresenter.getAdvanceData();
            }
        });
        mLoadMoreRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mAdvancePresenter.getMoreAdvance();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        String value = SharePreferenceUtil.getInstance(mContext).getValue(TimeKey.MOVIE_ADVLIST, "");
        List<MovieAdvListBean> movieAdvListBeen = JsonUtil.parseList(value, MovieAdvListBean.class);
        if (movieAdvListBeen != null && movieAdvListBeen.size() > 0) {
            mPullToRefresh.setMovieAdvList(movieAdvListBeen);
        }
        mAdvancePresenter.getAdvanceData();
    }

    @Override
    public void refreshCity() {
        mAdvancePresenter.getAdvanceData();

    }

    @Override
    public void setData(HomeAdvanceBean data) {
        mPullToRefresh.refreshComplete();
        List<HomeAdvanceBean.ListBean> list = data.list;
        if (list != null && list.size() > 0) {
            mAdapter.getData().clear();
            mAdapter.getData().addAll(list);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addData(HomeAdvanceBean data) {
        List<HomeAdvanceBean.ListBean> list = data.list;
        if (list != null && list.size() > 0) {
            mAdapter.getData().addAll(list);
            mAdapter.notifyDataSetChanged();
        } else {
            mLoadMoreRecyclerView.showFailUI();
        }
    }

    @Override
    public void onEmpty() {
        //数据空
    }

    @Override
    public void onComplete(boolean hasMore) {
        mLoadMoreRecyclerView.setHasLoadMore(hasMore);
        if (!hasMore) {
            mLoadMoreRecyclerView.setHasLoadMore(true);
            mLoadMoreRecyclerView.showNoMoreUI();
        }
    }

    @Override
    public void onFailure(String msg) {
        showNoDataNoti(mRelativeLayout, R.layout.default_page_failed);

    }

    @Override
    public void onLoadMoreFailure() {
        mLoadMoreRecyclerView.showFailUI();
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
}
