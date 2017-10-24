package com.project.xiaodong.mytimeapp;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.project.xiaodong.mytimeapp.business.home.HomeFragment;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreRecyclerView;
import com.project.xiaodong.mytimeapp.frame.view.refresh.PullToRefreshWithHorFrameLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by xiaodong.jin on 2017/9/25.
 */

public class TestFragment extends BaseFragment {


    String title;

    LoadMoreRecyclerView loadMoreRecyclerView;
    PullToRefreshWithHorFrameLayout pullRefresh;
    private List<String> list;

    public TestFragment(String title) {
        super();
        this.title = title;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        pullRefresh = (PullToRefreshWithHorFrameLayout) findViewById(R.id.pull_refresh);

        loadMoreRecyclerView = (LoadMoreRecyclerView) findViewById(R.id.loadMoreRecyclerView);
        loadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        loadMoreRecyclerView.setAdapter(new TestAdapter(mContext, list));
    }


    @Override
    protected void initListener() {
        super.initListener();
        pullRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return ((HomeFragment) getParentFragment()).isRefrsh();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pullRefresh.refreshComplete();
            }
        });
    }

    @Override
    public void refreshCity() {

    }

    @Override
    protected void initValue() {
        super.initValue();
        list = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            list.add(new String("item" + i));
        }
    }

    @Override
    public void initData() {
        super.initData();
    }
}
