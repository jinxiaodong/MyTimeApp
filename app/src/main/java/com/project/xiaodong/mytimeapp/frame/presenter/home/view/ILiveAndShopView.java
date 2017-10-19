package com.project.xiaodong.mytimeapp.frame.presenter.home.view;

import com.project.xiaodong.mytimeapp.business.home.bean.LiveAndShopBean;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */

public interface ILiveAndShopView {

    void onLiveAndShopSuccess(LiveAndShopBean data);

    void onLiveAndShopFailure(String msg);
}
