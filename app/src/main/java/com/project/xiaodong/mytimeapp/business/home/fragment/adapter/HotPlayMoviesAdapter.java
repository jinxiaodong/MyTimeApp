package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.HotPlayMoviesBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseRecyclerAdapter;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaodong.jin on 2017/10/18.
 */

public class HotPlayMoviesAdapter extends BaseRecyclerAdapter<HotPlayMoviesBean> {


    public HotPlayMoviesAdapter(Context context, List<HotPlayMoviesBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotPlayMoviesVH(inflate(R.layout.holder_hotplay_movies, parent));
    }

    class HotPlayMoviesVH extends BaseViewHold<HotPlayMoviesBean> {

        @InjectView(R.id.sdv_cover)
        SimpleDraweeView mSdvCover;
        @InjectView(R.id.tv_max_3d)
        TextView mTvMax3d;
        @InjectView(R.id.tv_mark)
        TextView mTvMark;
        @InjectView(R.id.tv_movie_name)
        TextView mTvMovieName;


        public HotPlayMoviesVH(View view) {
            super(view);
            ButterKnife.inject(this,view);

        }

        @Override
        public void onBindViewHolder(int position, List<HotPlayMoviesBean> mData) {
            if (mData.get(position) == null) {
                return;
            }
            HotPlayMoviesBean hotPlayMoviesBean = mData.get(position);

            mSdvCover.setImageURI(hotPlayMoviesBean.img);
            mTvMovieName.setText(hotPlayMoviesBean.titleCn);
            mTvMark.setText(hotPlayMoviesBean.ratingFinal + "");

            StringBuffer isMax3D = new StringBuffer();
            if (hotPlayMoviesBean.isDMAX) {
                isMax3D.append("IMAX");
            }
            if (hotPlayMoviesBean.is3D) {
                isMax3D.append(" 3D");
            }
            mTvMax3d.setText(isMax3D);
        }
    }
}
