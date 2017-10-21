package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.SelectionAdvanceBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */
public class SelectionAdvanceVH extends BaseViewHold<BeanWrapper> {


    @InjectView(R.id.sdv_cover)
    SimpleDraweeView mSdvCover;
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.line_splite)
    View mLineSplite;
    @InjectView(R.id.movie_desc)
    TextView mMovieDesc;
    @InjectView(R.id.ll_type_1)
    LinearLayout mLlType1;
    @InjectView(R.id.sdv_cover_0)
    SimpleDraweeView mSdvCover0;
    @InjectView(R.id.title0)
    TextView mTitle0;
    @InjectView(R.id.line_splite0)
    View mLineSplite0;
    @InjectView(R.id.movie_desc0)
    TextView mMovieDesc0;
    @InjectView(R.id.rl_type0)
    RelativeLayout mRlType0;
    @InjectView(R.id.icon_from)
    SimpleDraweeView mIconFrom;
    @InjectView(R.id.tv_from)
    TextView mTvFrom;
    @InjectView(R.id.view_splite)
    View mViewSplite;
    @InjectView(R.id.tv_comment)
    TextView mTvComment;
    @InjectView(R.id.tv_like)
    TextView mTvLike;
    @InjectView(R.id.iv_more)
    ImageView mIvMore;
    @InjectView(R.id.ll_like_comment)
    LinearLayout mLlLikeComment;
    @InjectView(R.id.draw_line)
    View mDrawLine;

    public SelectionAdvanceVH(View view) {
        super(view);
        ButterKnife.inject(this, view);
    }

    @Override
    public void onBindViewHolder(int position, List<BeanWrapper> mData) {
        if (mData.get(position).data == null) {
            return;
        }

        SelectionAdvanceBean.AdvanceBean.DataBean dataBean = (SelectionAdvanceBean.AdvanceBean.DataBean) mData.get(position).data;

        if (dataBean.dataType == 0) {
            mLlType1.setVisibility(View.GONE);
            mRlType0.setVisibility(View.VISIBLE);
            mSdvCover0.setImageURI(dataBean.img1);
            mTitle0.setText(dataBean.title);
            mMovieDesc0.setText(dataBean.content);

        } else {
            mRlType0.setVisibility(View.GONE);
            mLlType1.setVisibility(View.VISIBLE);
            mSdvCover.setImageURI(dataBean.img1);
            mTitle.setText(dataBean.title);
            mMovieDesc.setText(dataBean.content);
        }

        if (TextUtils.isEmpty(dataBean.content)) {
            mLineSplite0.setVisibility(View.INVISIBLE);
            mLineSplite.setVisibility(View.INVISIBLE);
        } else {
            mLineSplite.setVisibility(View.VISIBLE);
            mLineSplite0.setVisibility(View.VISIBLE);
        }

        mIconFrom.setImageURI(dataBean.publicHeadImage);
        mTvFrom.setText(dataBean.publicName);
        mTvComment.setText(dataBean.commentCount+"");
        mTvLike.setText(dataBean.imageCount+"");
    }
}
