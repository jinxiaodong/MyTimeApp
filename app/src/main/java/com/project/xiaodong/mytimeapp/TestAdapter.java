package com.project.xiaodong.mytimeapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/9/22.
 */

public class TestAdapter extends BaseRecyclerAdapter {


    public TestAdapter(Context context, List list) {
        super(context, list);
        mData = list;
    }

    @Override
    public BaseViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHold holder = new TestHoledr(inflate(R.layout.item_test, parent));
        return holder;
    }

    private class TestHoledr extends BaseViewHold {

        TextView tvTest;

        public TestHoledr(View itemView) {
            super(itemView);
            tvTest = (TextView) itemView.findViewById(R.id.tv_test);
        }

        @Override
        public void onBindViewHolder(int position, List mData) {
            if (mData == null) {
                return;
            }
            String title = (String) mData.get(position);
            tvTest.setText(title);
        }
    }
}
