package com.project.xiaodong.mytimeapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.project.xiaodong.mytimeapp.frame.base.TBaseActivity;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.DefaultLoadMoreView;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreRecyclerView;
import com.project.xiaodong.mytimeapp.frame.view.refresh.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TBaseActivity {


    private PullRefreshLayout pullRefresh;
    private LoadMoreRecyclerView mLoadMoreRecyclerView;

    private TestAdapter mAdapter;

    @Override
    public int getContentLayoutId() {
        return super.getContentLayoutId();
    }

    @Override
    public int getHeaderLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        pullRefresh = (PullRefreshLayout) findViewById(R.id.pullRefresh);
        mLoadMoreRecyclerView = (LoadMoreRecyclerView) findViewById(R.id.loadMoreRecycle);

        mLoadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLoadMoreRecyclerView.setNoLoadMoreHideView(false);
        mLoadMoreRecyclerView.setNoLoadMoreHideViewFrist(true);
        mLoadMoreRecyclerView.setLoadMoreView(new DefaultLoadMoreView(mContext));
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(new String("item" + i));
        }
        mAdapter = new TestAdapter(mContext, list);
        mLoadMoreRecyclerView.setAdapter(mAdapter);
        pullRefresh.init();
        pullRefresh.setRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefresh.refreshComplete();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        showDialog();
//        getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dismissDialog();
//            }
//        }, 10000);
    }
}
