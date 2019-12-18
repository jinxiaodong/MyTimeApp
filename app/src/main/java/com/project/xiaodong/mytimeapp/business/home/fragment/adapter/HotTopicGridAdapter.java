package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeSelectMovieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by xiaodong.jin on 2018/1/9.
 */

public class HotTopicGridAdapter extends BaseAdapter {


    private Context mContext;
    private List<HomeSelectMovieBean.HotTopicBean> mData;

    public HotTopicGridAdapter(Context context, List<HomeSelectMovieBean.HotTopicBean> list) {
        mContext = context;
        mData = list;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public HomeSelectMovieBean.HotTopicBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_hot_topic, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        HomeSelectMovieBean.HotTopicBean hotTopicBean = mData.get(position);
        holder.mSdv.setImageURI(hotTopicBean.bgImage);
        holder.mTvTopicName.setText(hotTopicBean.name);
        holder.mTvNumber.setText(hotTopicBean.movieCount+"");
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.sdv)
        SimpleDraweeView mSdv;
        @BindView(R.id.tv_topic_name)
        TextView mTvTopicName;
        @BindView(R.id.tv_number)
        TextView mTvNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
