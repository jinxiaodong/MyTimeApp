package com.project.xiaodong.mytimeapp.business.home.location.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.location.ShowAllCityBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;
import com.project.xiaodong.mytimeapp.frame.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.view.NoScrollGridview;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/7.
 */

public class AllCityAdapter extends BaseRecyclerAdapter<ShowAllCityBean> {



    public AllCityAdapter(Context context, List<ShowAllCityBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllCityVH(inflate(R.layout.all_city_view_holder, parent));
    }

    private class AllCityVH extends BaseViewHold<ShowAllCityBean> {

        private NoScrollGridview ngv;

        public AllCityVH(View view) {
            super(view);
            ngv = (NoScrollGridview) view.findViewById(R.id.ngv);

        }

        @Override
        public void onBindViewHolder(int position, List<ShowAllCityBean> mData) {
            ShowAllCityBean cityInfo = mData.get(position);
            if (cityInfo == null) {
                return;
            }
            List<MTimeCityInfo> mTimeCityInfos = cityInfo.mMTimeCityInfos;

            if (mTimeCityInfos == null || mTimeCityInfos.size() == 0) {
                return;
            }
            ngv.setAdapter(new GridViewCityAdapter(mContext, mTimeCityInfos, R.layout.hot_city_item));
        }
    }
}
