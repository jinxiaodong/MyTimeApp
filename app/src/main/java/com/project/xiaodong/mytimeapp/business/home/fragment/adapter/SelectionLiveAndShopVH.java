package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.LiveAndShopBean;
import com.project.xiaodong.mytimeapp.frame.base.adapter.BaseViewHold;
import com.project.xiaodong.mytimeapp.frame.bean.BeanWrapper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */
public class SelectionLiveAndShopVH extends BaseViewHold<BeanWrapper> {


    @InjectView(R.id.tv_live)
    TextView mTvLive;
    @InjectView(R.id.tv_all)
    TextView mTvAll;
    @InjectView(R.id.sdv_live)
    SimpleDraweeView mSdvLive;
    @InjectView(R.id.iv_paly_back)
    ImageView mIvPalyBack;
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.tv_shop)
    TextView mTvShop;
    @InjectView(R.id.tv_more)
    TextView mTvMore;
    @InjectView(R.id.sdv_position1)
    SimpleDraweeView mSdvPosition1;
    @InjectView(R.id.sdv_position2)
    SimpleDraweeView mSdvPosition2;
    @InjectView(R.id.sdv_position3)
    SimpleDraweeView mSdvPosition3;

    public SelectionLiveAndShopVH(View view) {
        super(view);
        ButterKnife.inject(this, view);
    }

    @Override
    public void onBindViewHolder(int position, List<BeanWrapper> mData) {
        LiveAndShopBean liveAndShopBean = (LiveAndShopBean) mData.get(position).data;
        if (liveAndShopBean == null) {
            return;
        }
        List<LiveAndShopBean.LiveListBean> liveList = liveAndShopBean.liveList;

        if (liveList != null && liveList.size() > 0) {
            LiveAndShopBean.LiveListBean liveListBean = liveList.get(0);
            if (liveAndShopBean != null) {
                mSdvLive.setImageURI(liveListBean.img);
                mTitle.setText(liveListBean.title);
            }
        }


        LiveAndShopBean.MallDivBean mallDiv = liveAndShopBean.mallDiv;

        if (mallDiv != null) {
            List<LiveAndShopBean.MallDivBean.DivListBean> divList = mallDiv.divList;
            if (divList != null && divList.size() > 0) {
                mSdvPosition1.setImageURI(divList.get(0).imgSrc);
                mSdvPosition2.setImageURI(divList.get(1).imgSrc);
                mSdvPosition3.setImageURI(divList.get(2).imgSrc);
            }

        }

    }

    @OnClick({R.id.tv_all, R.id.sdv_live, R.id.iv_paly_back, R.id.tv_more, R.id.sdv_position1, R.id.sdv_position2, R.id.sdv_position3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                break;
            case R.id.sdv_live:
                break;
            case R.id.iv_paly_back:
                break;
            case R.id.tv_more:
                break;
            case R.id.sdv_position1:
                break;
            case R.id.sdv_position2:
                break;
            case R.id.sdv_position3:
                break;
        }
    }
}
