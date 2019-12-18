package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeAdvanceBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaodong.jin on 2017/11/28.
 */
public  class HomeAdvanceVH extends BaseViewHold<HomeAdvanceBean.ListBean> {


    @BindView(R.id.sdv_cover)
    SimpleDraweeView mSdvCover;
    @BindView(R.id.iv_play_big)
    ImageView mIvPlayBig;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.line_splite)
    View mLineSplite;
    @BindView(R.id.movie_desc)
    TextView mMovieDesc;
    @BindView(R.id.ll_type_1)
    LinearLayout mLlType1;
    @BindView(R.id.sdv_cover_0)
    SimpleDraweeView mSdvCover0;
    @BindView(R.id.iv_play)
    ImageView mIvPlay;
    @BindView(R.id.left_cover)
    RelativeLayout mLeftCover;
    @BindView(R.id.title0)
    TextView mTitle0;
    @BindView(R.id.line_splite0)
    View mLineSplite0;
    @BindView(R.id.movie_desc0)
    TextView mMovieDesc0;
    @BindView(R.id.rl_type0)
    RelativeLayout mRlType0;
    @BindView(R.id.icon_from)
    SimpleDraweeView mIconFrom;
    @BindView(R.id.tv_from)
    TextView mTvFrom;
    @BindView(R.id.view_splite)
    View mViewSplite;
    @BindView(R.id.tv_comment)
    TextView mTvComment;
    @BindView(R.id.tv_like)
    TextView mTvLike;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.ll_like_comment)
    LinearLayout mLlLikeComment;
    @BindView(R.id.draw_line)
    View mDrawLine;

    public HomeAdvanceVH(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onBindViewHolder(int position, List<HomeAdvanceBean.ListBean> mData) {
        if (mData.get(position) == null) {
            return;
        }

        HomeAdvanceBean.ListBean listBean = mData.get(position);
            /*
             *type:0:小无、1：小有、2：大有、3、大无
             */
        if (listBean.type == 1 || listBean.type == 0) {
            mLlType1.setVisibility(View.GONE);
            mRlType0.setVisibility(View.VISIBLE);
            mSdvCover0.setImageURI(listBean.image);
            mTitle0.setText(listBean.title);
            mMovieDesc0.setText(listBean.introduction);

        } else {
            mRlType0.setVisibility(View.GONE);
            mLlType1.setVisibility(View.VISIBLE);
            mSdvCover.setImageURI(listBean.image);
            mTitle.setText(listBean.title);
            mMovieDesc.setText(listBean.introduction);

        }
        if (listBean.type == 2 || listBean.type == 1) {
            mIvPlayBig.setVisibility(View.VISIBLE);
            mIvPlay.setVisibility(View.VISIBLE);
        } else {
            mIvPlayBig.setVisibility(View.INVISIBLE);
            mIvPlay.setVisibility(View.INVISIBLE);
        }
        if (TextUtils.isEmpty(listBean.introduction)) {
            mLineSplite0.setVisibility(View.INVISIBLE);
            mLineSplite.setVisibility(View.INVISIBLE);
        } else {
            mLineSplite.setVisibility(View.VISIBLE);
            mLineSplite0.setVisibility(View.VISIBLE);
        }

        mIconFrom.setImageURI(listBean.publicHeadImage);
        mTvFrom.setText(listBean.publicName);
//            mTvComment.setText(listBean.commentCount + "");
//            mTvLike.setText(dataBean.imageCount + "");
        mTvComment.setVisibility(View.INVISIBLE);
        mTvLike.setVisibility(View.INVISIBLE);
    }
}
