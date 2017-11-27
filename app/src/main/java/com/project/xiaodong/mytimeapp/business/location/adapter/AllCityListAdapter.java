package com.project.xiaodong.mytimeapp.business.location.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.location.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/27.
 */

public class AllCityListAdapter extends BaseRecyclerAdapter<MTimeCityInfo> {

    public AllCityListAdapter(Context context, List<MTimeCityInfo> list) {
        super(context, list);
    }

    @Override
    public BaseViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemCityListVH(inflate(R.layout.item_city_vh, parent));
    }

    private class ItemCityListVH extends BaseViewHold<MTimeCityInfo> {
        TextView text1;

        public ItemCityListVH(View view) {
            super(view);
            text1 = (TextView) view.findViewById(R.id.text1);
        }

        @Override
        public void onBindViewHolder(int position, List<MTimeCityInfo> mData) {
            if (mData == null && mData.size() == 0) {
                return;
            }

            text1.setText(mData.get(position).n);
        }
    }
}
