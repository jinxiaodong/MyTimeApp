/**
 * @file PullToRefreshFrameLayout
 * @copyright (c) 2016 Macalline All Rights Reserved.
 * @author SongZheng
 * @date 2016/9/4
 */
package com.project.xiaodong.mytimeapp.frame.view.refresh;

import android.content.Context;
import android.util.AttributeSet;

import com.project.xiaodong.mytimeapp.business.home.bean.MovieAdvListBean;
import com.project.xiaodong.mytimeapp.frame.view.pulltorefresh.TimeRefreshView;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @author SongZheng
 * @description 扩展第三方下拉刷新库
 * @date 2016/9/4
 */
public class PullToRefreshFrameLayout extends PtrFrameLayout {

    /*******************************************************************************
     *	Public/Protected Variables
     *******************************************************************************/

    /*******************************************************************************
     * Private Variables
     *******************************************************************************/
    private TimeRefreshView mTHerdview;
    /*******************************************************************************
     *	Overrides From Base
     *******************************************************************************/

    /*******************************************************************************
     * Public/Protected Methods
     *******************************************************************************/
    public PullToRefreshFrameLayout(Context context) {
        super(context);
        init();
    }

    public PullToRefreshFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /*******************************************************************************
     * Private Methods
     *******************************************************************************/
    private void init() {
        // the following are default settings
        setResistance(1.6f);
        setRatioOfHeaderHeightToRefresh(1.1f);
        setDurationToClose(200);
        setDurationToCloseHeader(500);
        // default is false
        setPullToRefresh(false);
        // default is true
        setKeepHeaderWhenRefresh(true);
        setLoadingMinTime(1000);

        //etHeaderView
        mTHerdview = new TimeRefreshView(getContext());
        mTHerdview.setLayoutParams(new PullToRefreshFrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mTHerdview.setUp(this);
        setHeaderView(mTHerdview);
        addPtrUIHandler(mTHerdview);

    }

    public void setMovieAdvList(List<MovieAdvListBean> movieAdvList) {
        mTHerdview.setMovieAdvList(movieAdvList);
    }

    /*******************************************************************************
     *	Internal Class,Interface
     *******************************************************************************/
}
