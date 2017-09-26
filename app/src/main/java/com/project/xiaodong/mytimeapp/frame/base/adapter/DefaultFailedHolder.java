package com.project.xiaodong.mytimeapp.frame.base.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.project.xiaodong.mytimeapp.R;

import java.util.List;


/**
 * Created by xiaodong.jin on 2017/9/21.
 */
public class DefaultFailedHolder extends BaseViewHold {

    public DefaultFailedHolder(final Context mContext, View itemView) {
        super(itemView);
        Button bt= (Button) itemView.findViewById(R.id.bt_refresh);
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
