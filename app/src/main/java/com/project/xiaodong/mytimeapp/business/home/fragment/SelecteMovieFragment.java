package com.project.xiaodong.mytimeapp.business.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeSelectMovieBean;
import com.project.xiaodong.mytimeapp.business.home.fragment.adapter.HomeSelectMovieAdapter;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;
import com.project.xiaodong.mytimeapp.frame.presenter.home.HomeSelectMoviePresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.view.IBaseView;
import com.project.xiaodong.mytimeapp.frame.utils.NetworkUtil;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreRecyclerView;

import butterknife.BindView;

/**
 * Created by xiaodong.jin on 2018/1/8.
 */

public class SelecteMovieFragment extends BaseFragment implements IBaseView<HomeSelectMovieBean> {


    @BindView(R.id.loadMoreRecyclerView)
    LoadMoreRecyclerView mLoadMoreRecyclerView;

    @BindView(R.id.rl_select_movie)
    RelativeLayout mRelativeLayout;

    HomeSelectMoviePresenter mHomeSelectMoviePresenter;
    private HomeSelectMovieAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select_movie;
    }

    @Override
    protected void initValue() {
        super.initValue();
        mHomeSelectMoviePresenter = new HomeSelectMoviePresenter(mContext, this);
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mLoadMoreRecyclerView.setHasLoadMore(true);
//        mLoadMoreRecyclerView.setNoLoadMoreHideView(false);
//        mLoadMoreRecyclerView.setNoLoadMoreHideView(false);
        mLoadMoreRecyclerView.showNoMoreUI();

        mLoadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        super.initData();
        mAdapter = new HomeSelectMovieAdapter(mContext, null);
        mLoadMoreRecyclerView.setAdapter(mAdapter);

        showDialog();
        mHomeSelectMoviePresenter.getData();

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
    public void setData(HomeSelectMovieBean data) {
        dismissDialog();
        BeanWrapper beanWrapper = new BeanWrapper();
        beanWrapper.viewType = 0;
        beanWrapper.data = data.category;

        BeanWrapper beanWrapper1 = new BeanWrapper();
        beanWrapper1.viewType = 1;
        beanWrapper1.data = data.hotTopic;

        BeanWrapper beanWrapper2 = new BeanWrapper();
        beanWrapper2.viewType = 2;
        beanWrapper2.data = data.goodMovie;

        mAdapter.getData().clear();
        mAdapter.getData().add(beanWrapper);
        mAdapter.getData().add(beanWrapper1);
        mAdapter.getData().add(beanWrapper2);

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void addData(HomeSelectMovieBean data) {

    }

    @Override
    public void onEmpty() {
        //显示空布局
        showNoDataNoti(mRelativeLayout, R.layout.default_page_no_content);
    }

    @Override
    public void onFailure(String msg) {
        //显示加载出错布局
        showNoDataNoti(mRelativeLayout, R.layout.default_page_failed);
    }
}
