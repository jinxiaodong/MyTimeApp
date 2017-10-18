package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/10/17.
 */

public class SelectionAdapter extends BaseRecyclerAdapter<BeanWrapper> {


    //正在售票
    public static final int TYPE_HOT_MOVIES = 0;
    //直播、商场
    public static final int TYPE_LIVE_SHOP = 1;
    //精彩预告
    public static final int TYPE_ADVANCE = 2;


    public SelectionAdapter(Context context, List<BeanWrapper> list) {
        super(context, list);
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).viewType;
    }

    @Override
    public BaseViewHold onCreateViewHolder(ViewGroup parent, int viewType) {

        BaseViewHold holder = null;
        switch (viewType) {
            case TYPE_HOT_MOVIES:
                holder = new SelectionHotMoviesVH(inflate(R.layout.holder_selection_hotmovies, parent));
                break;

            case TYPE_LIVE_SHOP:

                break;
            case TYPE_ADVANCE:

                break;
        }
        return holder;
    }
}
