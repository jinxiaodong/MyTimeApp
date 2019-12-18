package com.project.xiaodong.mytimeapp.frame.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;

/**
 * Created by xiaodong.jin on 2017/9/22.
 * <p>
 * 禁止该库的上拉加载
 */

public class PullRefreshLayout extends RefreshLayout {


    private OnRefreshListener mListener;

    public PullRefreshLayout(Context context) {
        super(context);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean isChildScrollToBottom() {
        return false;
    }

    @Override
    public boolean isChildScrollToTop() {

        if (mListener != null && !mListener.isCandoRefresh()) {
            return false;
        }
        return super.isChildScrollToTop();
    }

    @Override
    public void setHeaderView(View child) {
        if (child == null) {
            super.setHeaderView(createHeaderView());
        } else {
            super.setHeaderView(child);
        }
    }

    @Override
    public void setOnPullRefreshListener(OnPullRefreshListener listener) {

        listener = new OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                textView.setText("正在载入...");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                if (mListener != null) {

                    mListener.onRefresh();
                }
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                textView.setText(enable ? "释放刷新" : "下拉刷新");
                imageView.setVisibility(View.VISIBLE);
                imageView.setRotation(enable ? 180 : 0);
            }


        };
        super.setOnPullRefreshListener(listener);

    }


    /*
     *初始化一下定制
     */
    public void init() {
        setTargetScrollWithLayout(true);
        setHeaderView(null);
        setOnPullRefreshListener(null);
    }

    /**
     * 结束刷新
     */
    public void refreshComplete() {
        setRefreshing(false);
        progressBar.setVisibility(GONE);
    }

    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;

    private View createHeaderView() {
        View headerView = LayoutInflater.from(this.getContext())
                .inflate(R.layout.layout_header_view, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.default_refresh_down);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }


    /**
     * 下拉刷新回调
     */
    public interface OnRefreshListener {
        void onRefresh();

        boolean isCandoRefresh();
    }

    /**
     * 设置
     *
     * @param listener
     */
    public void setRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }
}
