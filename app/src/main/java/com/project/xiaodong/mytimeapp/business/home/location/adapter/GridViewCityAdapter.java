package com.project.xiaodong.mytimeapp.business.home.location.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.frame.bean.MTimeCityInfo;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/7.
 */

public class GridViewCityAdapter extends BaseAdapter {
    Context mContext;
    List<MTimeCityInfo> mData;
    int mItemResId;
    private LayoutInflater mInflater;

    public GridViewCityAdapter(Context context, List<MTimeCityInfo> hotCityList, int item) {
        mContext = context;
        mData = hotCityList;
        mItemResId = item;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mItemResId, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        T hot_city_item = getItem(position);
        MTimeCityInfo cityInfo = (MTimeCityInfo) getItem(position);
        String string = cityInfo.n;
        if (string instanceof CharSequence) {
            holder.textView.setText(string);
        } else {
            holder.textView.setText(string);
        }

        return convertView;
    }

    protected class ViewHolder {
        public TextView textView;
    }
}
