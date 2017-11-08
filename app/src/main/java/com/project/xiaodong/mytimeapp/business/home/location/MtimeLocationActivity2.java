//package com.project.xiaodong.mytimeapp.business.home.location;
//
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.project.xiaodong.mytimeapp.R;
//import com.project.xiaodong.mytimeapp.business.home.location.adapter.GridViewCityAdapter;
//import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;
//import com.project.xiaodong.mytimeapp.frame.bean.MTimeCityInfo;
//import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
//import com.project.xiaodong.mytimeapp.frame.presenter.home.MainCityPresenter;
//import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ISuccessOrFailureView;
//import com.project.xiaodong.mytimeapp.frame.view.IndexBar.helper.IndexBarDataHelperImpl;
//import com.project.xiaodong.mytimeapp.frame.view.NoScrollGridview;
//import com.project.xiaodong.mytimeapp.frame.view.recycleview.LoadMoreRecyclerView;
//import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
//import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleArrayAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.InjectView;
//
//public class MtimeLocationActivity2 extends TBaseActivity implements ISuccessOrFailureView<MTimeCityInfo> {
//
//
//    MainCityPresenter mMainCityPresenter;
//    @InjectView(R.id.search)
//    LinearLayout mSearch;
//    @InjectView(R.id.current_city)
//    TextView mCurrentCity;
//    @InjectView(R.id.gv_hot_city)
//    NoScrollGridview mGvHotCity;
//    @InjectView(R.id.sgv)
//    StickyGridHeadersGridView mSgv;
//    @InjectView(R.id.loadMoreRecyclerView)
//    LoadMoreRecyclerView mLoadMoreRecyclerView;
//
//
//    private List<MTimeCityInfo> hotCityList = new ArrayList<>();
//    private List<MTimeCityInfo> allCityList = new ArrayList<>();
//    private GridViewCityAdapter mHotCityAdapter;
//    private List<String> mShowData;
//
//
//    @Override
//    public int getHeaderLayoutId() {
//        return super.getHeaderLayoutId();
//    }
//
//    @Override
//    public int getContentLayoutId() {
//        return R.layout.activity_mtime_location2;
//    }
//
//    @Override
//    protected void initValue(Bundle savedInstanceState) {
//        super.initValue(savedInstanceState);
//        mMainCityPresenter = new MainCityPresenter(this, this);
//    }
//
//    @Override
//    protected void initWidget(Bundle savedInstanceState) {
//        super.initWidget(savedInstanceState);
//        setTitle("选择城市");
//        setTitleColor(R.color.white);
//        RelativeLayout backButton = getBackButton();
//        ImageView imageView = (ImageView) backButton.getChildAt(0);
//        imageView.setImageResource(R.drawable.icon_back_gray);
//        mHotCityAdapter = new GridViewCityAdapter(mContext, hotCityList, R.layout.hot_city_item);
//        mGvHotCity.setAdapter(mHotCityAdapter);
//
//        mSgv.setNumColumns(4);
////        mSgv.setAreHeadersSticky(true);
////        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
////                .setNeedRealIndex(true)//设置需要真实的索引
////                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
////                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount());
//    }
//
//    @Override
//    protected void initListener(Bundle savedInstanceState) {
//        super.initListener(savedInstanceState);
//    }
//
//    @Override
//    protected void initData(Bundle savedInstanceState) {
//        super.initData(savedInstanceState);
//
//        mMainCityPresenter.getCityInfo("", "", ConstantUrl.MTIME_ALL_CITY);
//    }
//
//    @Override
//    public void onSuccess(MTimeCityInfo data) {
//        if (data.p != null && data.p.size() > 0) {
//            allCityList.addAll(data.p);
//            for (int i = 0; i < allCityList.size(); i++) {
//                if (i > 11) {
//                    break;
//                }
//                hotCityList.add(allCityList.get(i));
//            }
//            IndexBarDataHelperImpl indexBarDataHelper = new IndexBarDataHelperImpl();
//            indexBarDataHelper.sortSourceDatas(allCityList);
//            mHotCityAdapter.notifyDataSetChanged();
//            mShowData = new ArrayList<>();
//            for (int i = 0; i < allCityList.size(); i++) {
//                mShowData.add(allCityList.get(i).getSuspensionTag());
//            }
////            mSgv.setAdapter(new StickyGridHeadersSimpleArrayAdapter<String>(mContext, getResources().getStringArray(R.array.countries), R.layout.city_header, R.layout.hot_city_item));
//        }
//    }
//
//
//    @Override
//    public void onFailure(String message) {
//
//    }
//
//
//}
