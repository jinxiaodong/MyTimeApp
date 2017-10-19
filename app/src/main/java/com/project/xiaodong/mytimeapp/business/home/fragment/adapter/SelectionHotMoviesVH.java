package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.HotPlayMoviesBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreWithHorRecycleView;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.adapter.HeaderAndFooterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaodong.jin on 2017/10/18.
 */
public class SelectionHotMoviesVH extends BaseViewHold<BeanWrapper> {


    public TextView mTvTitle;
    public TextView mTvNum;
    public LoadMoreWithHorRecycleView mLoadMoreRecyclerView;
    public HotPlayMoviesAdapter mAdapter;
    private List<HotPlayMoviesBean> mList;

    public SelectionHotMoviesVH(View view) {
        super(view);

        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
        mLoadMoreRecyclerView = (LoadMoreWithHorRecycleView) view.findViewById(R.id.loadMoreRecyclerView);
        mList = new ArrayList<>();
        mAdapter = new HotPlayMoviesAdapter(mContext, mList);
        mLoadMoreRecyclerView.setNestedScrollingEnabled(false);
        mLoadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mLoadMoreRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onBindViewHolder(int position, List<BeanWrapper> mData) {
        if (mData.get(position).data == null) {
            return;
        }

        mList = (List<HotPlayMoviesBean>) mData.get(position).data;

        mAdapter.getData().clear();
        mAdapter.getData().addAll(mList);
        mAdapter.notifyDataSetChanged();
        mLoadMoreRecyclerView.setOnItemClickListener(new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(mContext, mList.get(position).titleCn, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
