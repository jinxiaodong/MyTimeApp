package com.project.xiaodong.mytimeapp.business.location.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.location.bean.ShowAllCityBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;
import com.project.xiaodong.mytimeapp.business.location.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.view.NoScrollGridview;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/7.
 */

public class AllCityAdapter extends BaseRecyclerAdapter<ShowAllCityBean> {


    private OnCityItemClickListener mOnItemClickListener;

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
            final List<MTimeCityInfo> mTimeCityInfos = cityInfo.mMTimeCityInfos;

            if (mTimeCityInfos == null || mTimeCityInfos.size() == 0) {
                return;
            }
            ngv.setAdapter(new GridViewCityAdapter(mContext, mTimeCityInfos, R.layout.hot_city_item));
            ngv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MTimeCityInfo cityInfo1 = mTimeCityInfos.get(position);
                    mOnItemClickListener.onItemClick(cityInfo1);
                }
            });
        }
    }


    public void setOnItemClickListener(OnCityItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public interface OnCityItemClickListener {
        void onItemClick(MTimeCityInfo cityinfo);
    }

}
