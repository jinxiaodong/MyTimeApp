package com.project.xiaodong.mytimeapp.frame.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;


/**
 * Created by xiaodong.jin on 2017/9/21.
 */
public abstract class BaseViewHold<T> extends RecyclerView.ViewHolder {
    protected Context mContext;

    public BaseViewHold(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    public abstract void onBindViewHolder(int position, List<T> mData);
}
