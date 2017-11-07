package com.project.xiaodong.mytimeapp.business.home.location.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;
import com.project.xiaodong.mytimeapp.frame.bean.MTimeCityInfo;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/7.
 */

public class AllCityAdapter extends BaseRecyclerAdapter<MTimeCityInfo> {


    private GridViewCityAdapter mGridViewCityAdapter;

    public AllCityAdapter(Context context, List<MTimeCityInfo> list) {
        super(context, list);
    }

    @Override
    public BaseViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllCityVH(inflate(R.layout.all_city_view_holder, parent));
    }

    private class AllCityVH extends BaseViewHold<MTimeCityInfo> {

        private TextView tvCity;

        public AllCityVH(View view) {
            super(view);
            tvCity = (TextView) view.findViewById(R.id.tv_city);

        }

        @Override
        public void onBindViewHolder(int position, List<MTimeCityInfo> mData) {
            MTimeCityInfo cityInfo = mData.get(position);
            if (cityInfo == null) {
                return;
            }
            tvCity.setText(cityInfo.n);
        }
    }
}
