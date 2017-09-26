package com.project.xiaodong.mytimeapp;

import android.widget.TextView;

import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;

import butterknife.InjectView;

/**
 * Created by xiaodong.jin on 2017/9/25.
 */

public class TestFragment extends BaseFragment {

    @InjectView(R.id.tv_test)
    TextView mTvTest;

    String title;

    public TestFragment(String title) {
        super();
        this.title = title;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_test;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

    }

    @Override
    protected void initValue() {
        super.initValue();
    }

    @Override
    public void initData() {
        super.initData();
        mTvTest.setText(title);
    }
}
