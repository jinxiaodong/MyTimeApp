package com.project.xiaodong.mytimeapp.frame.view.refresh;

import android.view.View;


/**
 * Created by xiaodong.jin on 2017/9/22.
 */

public class RefreshUtils {

//    public static void initRefresh(PullRefreshLayout refreshLayout) {
//        refreshLayout.setHeaderViewBackgroundColor(0xff888888);
//        refreshLayout.setHeaderView(createHeaderView());// add headerView
//        refreshLayout.setFooterView(createFooterView());
//        refreshLayout.setTargetScrollWithLayout(true);
//        refreshLayout
//                .setOnPullRefreshListener(new RefreshLayout.OnPullRefreshListener() {
//
//                    @Override
//                    public void onRefresh() {
//                        textView.setText("正在刷新");
//                        imageView.setVisibility(View.GONE);
//                        progressBar.setVisibility(View.VISIBLE);
//                        new Handler().postDelayed(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                swipeRefreshLayout.setRefreshing(false);
//                                progressBar.setVisibility(View.GONE);
//                            }
//                        }, 2000);
//                    }
//
//                    @Override
//                    public void onPullDistance(int distance) {
//                        // pull distance
//                    }
//
//                    @Override
//                    public void onPullEnable(boolean enable) {
//                        textView.setText(enable ? "松开刷新" : "下拉刷新");
//                        imageView.setVisibility(View.VISIBLE);
//                        imageView.setRotation(enable ? 180 : 0);
//                    }
//                });
//    }

    private static View createFooterView() {
        return null;
    }

    private static View createHeaderView() {
        return null;
    }

}
