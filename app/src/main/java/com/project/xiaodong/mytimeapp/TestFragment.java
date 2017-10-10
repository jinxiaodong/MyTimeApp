package com.project.xiaodong.mytimeapp;

import android.support.v7.widget.LinearLayoutManager;

import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaodong.jin on 2017/9/25.
 */

public class TestFragment extends BaseFragment {


    String title;

    LoadMoreRecyclerView loadMoreRecyclerView;
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
        loadMoreRecyclerView = (LoadMoreRecyclerView) findViewById(R.id.loadMoreRecyclerView);
        loadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        loadMoreRecyclerView.setAdapter(new TestAdapter(mContext, list));
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
