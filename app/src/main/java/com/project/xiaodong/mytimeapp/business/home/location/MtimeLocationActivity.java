package com.project.xiaodong.mytimeapp.business.home.location;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.location.adapter.AllCityAdapter;
import com.project.xiaodong.mytimeapp.business.home.location.adapter.GridViewCityAdapter;
import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;
import com.project.xiaodong.mytimeapp.frame.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.presenter.home.MainCityPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ISuccessOrFailureView;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.helper.IndexBarDataHelperImpl;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.suspension.SuspensionDecoration;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.utils.HeaderRecyclerAndFooterWrapperAdapter;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.utils.ViewHolder;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

public class MtimeLocationActivity extends TBaseActivity implements ISuccessOrFailureView<MTimeCityInfo> {


    MainCityPresenter mMainCityPresenter;
    @InjectView(R.id.recyclerView)
    LoadMoreRecyclerView mRecyclerView;
//    @InjectView(R.id.indexBar)
//    MyIndexBar mIndexBar;
//    @InjectView(R.id.tvSideBarHint)
//    TextView mTvSideBarHint;


    private List<MTimeCityInfo> hotCityList = new ArrayList<>();
    private List<MTimeCityInfo> allCityList = new ArrayList<>();
    private List<ShowAllCityBean> mData = new ArrayList<>();
    private LinearLayoutManager mManager;
    private AllCityAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private GridViewCityAdapter mHotCityAdapter;
    private SuspensionDecoration mDecoration;


    @Override
    public int getHeaderLayoutId() {
        return super.getHeaderLayoutId();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_mtime_location;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        mMainCityPresenter = new MainCityPresenter(this, this);
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        setTitle("选择城市");
        setTitleColor(R.color.white);
        RelativeLayout backButton = getBackButton();
        ImageView imageView = (ImageView) backButton.getChildAt(0);
        imageView.setImageResource(R.drawable.icon_back_gray);
        mHotCityAdapter = new GridViewCityAdapter(mContext, hotCityList, R.layout.hot_city_item);

        mManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new AllCityAdapter(mContext, mData);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.current_hot_city_header:
                        GridView gridView = holder.getView(R.id.gv_hot_city);
                        gridView.setAdapter(mHotCityAdapter);
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(0, R.layout.current_hot_city_header, hotCityList);

        mRecyclerView.setAdapter(mHeaderAdapter);
        mRecyclerView.addItemDecoration(mDecoration = new SuspensionDecoration(this, allCityList)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()))
                .setColorTitleBg("#ff666666")
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont("#ff333333")
                .setHeaderViewCount(1));
//
//        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
//                .setNeedRealIndex(true)//设置需要真实的索引
//                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
//                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount());
    }

    @Override
    protected void initListener(Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        mMainCityPresenter.getCityInfo("", "", ConstantUrl.MTIME_ALL_CITY);
    }

    @Override
    public void onSuccess(MTimeCityInfo data) {
        if (data.p != null && data.p.size() > 0) {
            allCityList.addAll(data.p);
            for (int i = 0; i < allCityList.size(); i++) {
                if (i > 11) {
                    break;
                }
                hotCityList.add(allCityList.get(i));
            }


//            if(allCityList.size()>20) {
//                mIndexBar.setVisibility(View.VISIBLE);
////                mTvSideBarHint.setVisibility(View.VISIBLE);
//            }
//
//            mIndexBar.getDataHelper().sortSourceDatas(allCityList);
//
//            mIndexBar.setmSourceDatas(allCityList)//设置数据
//                    .invalidate();

            IndexBarDataHelperImpl indexBarDataHelper = new IndexBarDataHelperImpl();
            indexBarDataHelper.sortSourceDatas(allCityList);

            Map<String, ShowAllCityBean> mapping = new HashMap<>();

            for (int i = 0; i < allCityList.size(); i++) {
                String suspensionTag = allCityList.get(i).getSuspensionTag();
                ShowAllCityBean showAllCityBean = mapping.get(suspensionTag);
                if (showAllCityBean == null) {
                    showAllCityBean = new ShowAllCityBean();
                    showAllCityBean.tag = suspensionTag;
                    showAllCityBean.mMTimeCityInfos = new ArrayList<>();
                    mapping.put(suspensionTag, showAllCityBean);
                    mData.add(showAllCityBean);
                }
                showAllCityBean.mMTimeCityInfos.add(allCityList.get(i));
            }

            mDecoration.setmDatas(mData);

            mHeaderAdapter.notifyDataSetChanged();
            mAdapter.getData().clear();
            mAdapter.getData().addAll(mData);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onFailure(String message) {

    }

}
