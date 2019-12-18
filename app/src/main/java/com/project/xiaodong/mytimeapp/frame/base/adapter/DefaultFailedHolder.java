package com.project.xiaodong.mytimeapp.frame.base.adapter;

import android.content.Context;
import android.view.View;

import com.project.xiaodong.mytimeapp.R;

import java.util.List;


/**
 * Created by xiaodong.jin on 2017/9/21.
 */
public class DefaultFailedHolder extends BaseViewHold {

    public DefaultFailedHolder(final Context mContext, View itemView) {
        super(itemView);
        View bt = itemView.findViewById(R.id.ll_failure);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((HxBaseActivity)mContext).reRequestData();
            }
        });

    }

    @Override
    public void onBindViewHolder(int position, List mData) {

    }
}
