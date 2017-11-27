package com.project.xiaodong.mytimeapp.business.location;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.LocationHeaderBean;
import com.project.xiaodong.mytimeapp.business.location.adapter.AllCityAdapter;
import com.project.xiaodong.mytimeapp.business.location.adapter.AllCityListAdapter;
import com.project.xiaodong.mytimeapp.business.location.adapter.GridViewCityAdapter;
import com.project.xiaodong.mytimeapp.business.location.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.business.location.bean.ShowAllCityBean;
import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;
import com.project.xiaodong.mytimeapp.frame.bean.LocationInfo;
import com.project.xiaodong.mytimeapp.frame.block.LocationBlock;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.eventbus.EventCenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.MainCityPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ISuccessOrFailureView;
import com.project.xiaodong.mytimeapp.frame.utils.JsonUtil;
import com.project.xiaodong.mytimeapp.frame.utils.KeyBoardManager;
import com.project.xiaodong.mytimeapp.frame.utils.LoactionUtils;
import com.project.xiaodong.mytimeapp.frame.utils.SharePreferenceUtil;
import com.project.xiaodong.mytimeapp.frame.utils.SoftKeyboardStateHelper;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.helper.IndexBarDataHelperImpl;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.suspension.SuspensionDecoration;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.utils.HeaderRecyclerAndFooterWrapperAdapter;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.utils.ViewHolder;
import com.project.xiaodong.mytimeapp.frame.view.IndexBar.widget.MyIndexBar;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreRecyclerView;
import com.project.xiaodong.mytimeapp.frame.view.recycleview.adapter.HeaderAndFooterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MtimeLocationActivity extends TBaseActivity implements ISuccessOrFailureView<MTimeCityInfo> {


    private static final String LOCATION_MCITYDATA = "LOCATION_MCITYDATA";
    private static final String LOCATION_HOTCITYDATA = "LOCATION_HOTCITYDATA";
    @InjectView(R.id.et_search)
    EditText mEtSearch;
    @InjectView(R.id.tv_cancle)
    TextView mTvCancle;
    @InjectView(R.id.search)
    LinearLayout mSearch;
    @InjectView(R.id.recyclerView)
    LoadMoreRecyclerView mRecyclerView;
    @InjectView(R.id.indexBar)
    MyIndexBar mIndexBar;
    @InjectView(R.id.tvSideBarHint)
    TextView mTvSideBarHint;
    @InjectView(R.id.loadrecyclerView)
    LoadMoreRecyclerView mLoadrecyclerView;


    private LocationHeaderBean mLocationHeaderBean = new LocationHeaderBean();
    private LocationBlock mLocationBlock;
    private static final int LOCATION = 101;

    private List<MTimeCityInfo> hotCityList = new ArrayList<>();
    private List<MTimeCityInfo> allCityList = new ArrayList<>();
    private List<ShowAllCityBean> mCityData = new ArrayList<>();
    private String mBDcity;

    private SoftKeyboardStateHelper mSoftKeyboardStateHelper;
    private LinearLayoutManager mManager;
    private AllCityAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private GridViewCityAdapter mHotCityAdapter;
    private AllCityListAdapter mAllCityListAdapter;
    private SuspensionDecoration mDecoration;
    //sp存储工具
    private SharePreferenceUtil sp;
    MainCityPresenter mMainCityPresenter;


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
        mLocationBlock = new LocationBlock(mContext);
        mMainCityPresenter = new MainCityPresenter(this, this);
        sp = SharePreferenceUtil.getInstance(mContext);
        mSoftKeyboardStateHelper = new SoftKeyboardStateHelper(findViewById(R.id.rl_loaction));
        getCacheData();
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
        mAdapter = new AllCityAdapter(mContext, mCityData);
        mAllCityListAdapter = new AllCityListAdapter(mContext, allCityList);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.current_hot_city_header:
                        GridView gridView = holder.getView(R.id.gv_hot_city);
                        gridView.setAdapter(mHotCityAdapter);
                        holder.setText(R.id.current_city, mLocationHeaderBean.cityName);
                        if (mLocationHeaderBean.status == LocationHeaderBean.LOCATION_FAILURE) {
                            holder.setText(R.id.current_city, "定位失败，点击重试！");
                        }
                        holder.setOnClickListener(R.id.current_city, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mLocationHeaderBean.status != LocationHeaderBean.LOCATION_SUCCESS) {
                                    locationtask();
                                } else if (mLocationHeaderBean.cityCode != null && mLocationHeaderBean.cityName != null) {
                                    LoactionUtils.setUserChooseCity(mLocationHeaderBean.cityBean);
                                    finish();
                                }
                            }
                        });

                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                LoactionUtils.setUserChooseCity(hotCityList.get(position));
                                finish();
                            }
                        });
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(0, R.layout.current_hot_city_header, hotCityList);

        mRecyclerView.setAdapter(mHeaderAdapter);
        mRecyclerView.addItemDecoration(mDecoration = new SuspensionDecoration(this, mCityData)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()))
                .setColorTitleBg("#ffe4e4e4")
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont("#ff999999")
                .setHeaderViewCount(1));

        mLoadrecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLoadrecyclerView.setAdapter(mAllCityListAdapter);
//        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
//                .setNeedRealIndex(true)//设置需要真实的索引
//                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
//                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount());
    }

    @Override
    protected void initListener(Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        mAdapter.setOnItemClickListener(new AllCityAdapter.OnCityItemClickListener() {
            @Override
            public void onItemClick(MTimeCityInfo cityinfo) {
                LoactionUtils.setUserChooseCity(cityinfo);
                finish();
            }
        });


        mSoftKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                mLoadrecyclerView.setVisibility(View.VISIBLE);
                mTvCancle.setVisibility(View.VISIBLE);
                mEtSearch.setCursorVisible(true);
            }

            @Override
            public void onSoftKeyboardClosed() {
//                mLoadrecyclerView.setVisibility(View.INVISIBLE);
//                mTvCancle.setVisibility(View.GONE);
//                mEtSearch.setCursorVisible(false);
            }
        });
        mTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadrecyclerView.setVisibility(View.INVISIBLE);
                mTvCancle.setVisibility(View.GONE);
                if (mSoftKeyboardStateHelper.isSoftKeyboardOpened()) {
                    KeyBoardManager.closeKeybord(mEtSearch, mContext);
                }
                mEtSearch.setText("");
                mEtSearch.setCursorVisible(false);
            }
        });
        mLoadrecyclerView.setOnItemClickListener(new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                LoactionUtils.setUserChooseCity(allCityList.get(position));
                finish();
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //在list中做匹配搜索：暂时不做
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getCity();
        locationtask();
    }

    @Override
    protected void onEventCallback(EventCenter event) {
        super.onEventCallback(event);

    }

    private void getCity() {
        mMainCityPresenter.getCityInfo("", "", ConstantUrl.MTIME_ALL_CITY);
    }

    @AfterPermissionGranted(LOCATION)
    private void locationtask() {
        mLocationHeaderBean.status = LocationHeaderBean.LOCATION_BEGIN;
        mLocationHeaderBean.cityCode = null;
        mLocationHeaderBean.cityName = "定位中...";
        mHeaderAdapter.notifyDataSetChanged();
        String[] perms = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            mLocationBlock.statrLocation();
            mLocationBlock.setOnLocationListener(new LocationBlock.OnLocationListener() {
                @Override
                public void onLocationedSuccess(BDLocation bdLocation) {
                    mLocationHeaderBean.status = LocationHeaderBean.LOCATION_SUCCESS;
                    LocationInfo locationInfo = LoactionUtils.getLocationInfo();
                    mBDcity = locationInfo.city;
                    if (mBDcity != null && mBDcity.contains("市"))
                        mBDcity = mBDcity.substring(0, mBDcity.length() - 1);
                    mLocationHeaderBean.cityName = mBDcity;
                    getLocationCode();
                    mHeaderAdapter.notifyDataSetChanged();

                }

                @Override
                public void onLocationedFail() {
                    mLocationHeaderBean.cityCode = null;
                    mLocationHeaderBean.status = LocationHeaderBean.LOCATION_FAILURE;
                }
            });
        }
    }

    private void getLocationCode() {
        if (mLocationHeaderBean.status != LocationHeaderBean.LOCATION_SUCCESS) {

            return;
        }
        if (allCityList.size() != 0) {
            for (MTimeCityInfo cityInfo : allCityList) {
                if (cityInfo.n != null && mBDcity != null && cityInfo.n.equals(mBDcity)) {
                    mLocationHeaderBean.cityCode = cityInfo.id;
                    mLocationHeaderBean.cityBean = cityInfo;
                    mLocationHeaderBean.status = LocationHeaderBean.LOCATION_SUCCESS;
                    return;
                }
            }
            //如果没有匹配到怎么办？
            mLocationHeaderBean.cityCode = null;
            mLocationHeaderBean.status = LocationHeaderBean.LOCATION_NO_MATCHER;
        }
    }

    @Override
    public void onSuccess(MTimeCityInfo data) {
        hideNoDataNoti();
        if (data.p != null && data.p.size() > 0) {
            allCityList.addAll(data.p);
            hotCityList.clear();
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

            //数据分组
            divideData();

            mDecoration.setmDatas(mCityData);
            if (mLocationHeaderBean.cityCode == null) {
                getLocationCode();
            }

            mAdapter.getData().clear();
            mAdapter.getData().addAll(mCityData);
            mAdapter.notifyDataSetChanged();
            mHeaderAdapter.notifyDataSetChanged();
            mAllCityListAdapter.getData().clear();
            mAllCityListAdapter.getData().addAll(allCityList);
            mAllCityListAdapter.notifyDataSetChanged();

            //缓存数据
            cacheData();
        }
    }

    /**
     * 缓存数据
     */
    private void cacheData() {
        sp.setValue(LOCATION_MCITYDATA, JsonUtil.toJsonString(mCityData));
        sp.setValue(LOCATION_HOTCITYDATA, JsonUtil.toJsonString(hotCityList));
    }

    private void getCacheData() {
        String mcityString = sp.getValue(LOCATION_MCITYDATA, "");
        String hotCityString = sp.getValue(LOCATION_HOTCITYDATA, "");
        if (!TextUtils.isEmpty(mcityString)) {
            List<ShowAllCityBean> showAllCityBeen = JsonUtil.parseList(mcityString, ShowAllCityBean.class);
            if (showAllCityBeen != null) {
                mCityData.clear();
                mCityData.addAll(showAllCityBeen);
            }
        }
        if (!TextUtils.isEmpty(hotCityString)) {
            List<MTimeCityInfo> mTimeCityInfos = JsonUtil.parseList(hotCityString, MTimeCityInfo.class);
            if (mTimeCityInfos != null) {
                hotCityList.clear();
                hotCityList.addAll(mTimeCityInfos);
            }
        }
    }

    //数据分组
    private void divideData() {
        Map<String, ShowAllCityBean> mapping = new HashMap<>();
        mCityData.clear();
        for (int i = 0; i < allCityList.size(); i++) {
            String suspensionTag = allCityList.get(i).getSuspensionTag();
            ShowAllCityBean showAllCityBean = mapping.get(suspensionTag);
            if (showAllCityBean == null) {
                showAllCityBean = new ShowAllCityBean();
                showAllCityBean.tag = suspensionTag;
                showAllCityBean.mMTimeCityInfos = new ArrayList<>();
                mapping.put(suspensionTag, showAllCityBean);
                mCityData.add(showAllCityBean);
            }
            showAllCityBean.mMTimeCityInfos.add(allCityList.get(i));
        }
    }


    @Override
    public void onFailure(String message) {
        dismissDialog();
        showNoDataNoti(getContentView(), R.layout.default_page_failed);
        View bt_refresh = getContentView().findViewById(R.id.bt_refresh);
        bt_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCity();
                locationtask();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
