package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeAdvanceBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/28.
 */

public class HomeAdvanceAdapter extends BaseRecyclerAdapter<HomeAdvanceBean.ListBean> {


    public HomeAdvanceAdapter(Context context, List<HomeAdvanceBean.ListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeAdvanceVH(inflate(R.layout.holder_home_advance, parent));
    }

}
