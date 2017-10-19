package com.project.xiaodong.mytimeapp.frame.presenter.home.view;

import com.project.xiaodong.mytimeapp.business.home.bean.SelectionAdvanceBean;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */

public interface IAdvanceView {
    void setData(SelectionAdvanceBean data);

    void addData(SelectionAdvanceBean data);

    void onEmpty();

    void onFailure(String msg);
}
