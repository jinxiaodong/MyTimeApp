package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeSelectMovieBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;
import com.project.xiaodong.mytimeapp.frame.view.NoScrollGridview;
import com.project.xiaodong.mytimeapp.frame.view.taglayout.TagFlowLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaodong.jin on 2018/1/8.
 */

public class HomeSelectMovieAdapter extends BaseRecyclerAdapter<BeanWrapper> {


    public HomeSelectMovieAdapter(Context context, List<BeanWrapper> list) {
        super(context, list);
    }


    @Override
    public int getItemViewType(int position) {
        return mData.get(position).viewType;
    }

    @Override
    public BaseViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHold holder = null;
        switch (viewType) {
            case 0:
                holder = new HotCategoryViewHolder(inflate(R.layout.item_select_movie_vh, parent));
                break;
            case 1:
                holder = new HotTopicViewHolder(inflate(R.layout.item_hot_topic_vh, parent));
                break;

            case 2:
                holder = new HotMovieViewHolder(inflate(R.layout.item_hot_movie_vh, parent));
                break;
        }
        return holder;
    }


    /*
    *   分类
     *  */
    class HotCategoryViewHolder extends BaseViewHold<BeanWrapper> {

        private TagFlowLayout tagLayout;
        private TagCategoryAdapter mCategoryAdapter;

        public HotCategoryViewHolder(View view) {
            super(view);
            tagLayout = (TagFlowLayout) view.findViewById(R.id.tag_layout);
            mCategoryAdapter = new TagCategoryAdapter(mContext, null);
            tagLayout.setAdapter(mCategoryAdapter);
        }

        @Override
        public void onBindViewHolder(int position, List<BeanWrapper> mData) {
            if (mData == null || mData.size() == 0) {
                return;
            }
            List<HomeSelectMovieBean.CategoryBean> data = (List<HomeSelectMovieBean.CategoryBean>) mData.get(position).data;
            if (data != null && data.size() > 0) {
                mCategoryAdapter.setDataList(data);
            }
        }
    }

    /*
    热门主题
    * */
    class HotTopicViewHolder extends BaseViewHold<BeanWrapper> {
        @InjectView(R.id.tv_more)
        TextView mTvMore;
        @InjectView(R.id.hot_topic_grid)
        NoScrollGridview mHotTopicGrid;

        HotTopicGridAdapter mHotTopicGridAdapter;

        public HotTopicViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mTvMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public void onBindViewHolder(int position, List<BeanWrapper> mData) {
            if (mData == null || mData.size() == 0) {
                return;
            }
            final List<HomeSelectMovieBean.HotTopicBean> data = (List<HomeSelectMovieBean.HotTopicBean>) mData.get(position).data;
            if (data == null || data.size() == 0) {
                return;
            }
            mHotTopicGridAdapter = new HotTopicGridAdapter(mContext, data);
            mHotTopicGrid.setAdapter(mHotTopicGridAdapter);
            mHotTopicGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    HomeSelectMovieBean.HotTopicBean hotTopicBean = data.get(position);

                }
            });
        }
    }

    class HotMovieViewHolder extends BaseViewHold<BeanWrapper> {
        @InjectView(R.id.tv_more)
        TextView mTvMore;
        @InjectView(R.id.sdv_cover)
        SimpleDraweeView mSdvCover;
        @InjectView(R.id.tv_title)
        TextView tvtitle;

        public HotMovieViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mTvMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public void onBindViewHolder(int position, List<BeanWrapper> mData) {
            if (mData == null || mData.size() == 0) {
                return;
            }

            HomeSelectMovieBean.GoodMovieBean data = (HomeSelectMovieBean.GoodMovieBean) mData.get(position).data;
            if (data == null) {
                return;
            }
            mSdvCover.setImageURI(data.image);
            tvtitle.setText(data.title);

            mSdvCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
