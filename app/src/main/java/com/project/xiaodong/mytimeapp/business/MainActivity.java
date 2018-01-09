package com.project.xiaodong.mytimeapp.business;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.TestFragment;
import com.project.xiaodong.mytimeapp.business.home.HomeFragment;
import com.project.xiaodong.mytimeapp.business.home.bean.Loadbean;
import com.project.xiaodong.mytimeapp.business.home.bean.MovieAdvListBean;
import com.project.xiaodong.mytimeapp.business.location.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;
import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;
import com.project.xiaodong.mytimeapp.frame.block.LocationBlock;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.constants.GlobalConstants;
import com.project.xiaodong.mytimeapp.frame.constants.TimeKey;
import com.project.xiaodong.mytimeapp.frame.presenter.main.MainCityPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.main.MainLoadInfoPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ISuccessOrFailureView;
import com.project.xiaodong.mytimeapp.frame.presenter.view.ICommonView;
import com.project.xiaodong.mytimeapp.frame.utils.AlertDialogUtil;
import com.project.xiaodong.mytimeapp.frame.utils.JsonUtil;
import com.project.xiaodong.mytimeapp.frame.utils.LoactionUtils;
import com.project.xiaodong.mytimeapp.frame.utils.PreferencesUtils;
import com.project.xiaodong.mytimeapp.frame.utils.SharePreferenceUtil;
import com.project.xiaodong.mytimeapp.frame.utils.ToastUtil;
import com.project.xiaodong.mytimeapp.frame.view.APSTSViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends TBaseActivity implements ICommonView<Loadbean> {


    @InjectView(R.id.viewpager)
    APSTSViewPager mViewpager;
    //首页
    @InjectView(R.id.iv_tab_home)
    ImageView mIvTabHome;
    @InjectView(R.id.tv_tab_home)
    TextView mTvTabHome;
    @InjectView(R.id.rl_tab_home)
    RelativeLayout mRlTabHome;
    //购票
    @InjectView(R.id.iv_tab_payticket)
    ImageView mIvTabPayticket;
    @InjectView(R.id.tv_tab_payticket)
    TextView mTvTabPayticket;
    @InjectView(R.id.rl_tab_payticket)
    RelativeLayout mRlTabPayticket;

    //商城
    @InjectView(R.id.iv_tab_mall)
    ImageView mIvTabMall;
    @InjectView(R.id.tv_tab_mall)
    TextView mTvTabMall;
    @InjectView(R.id.rl_tab_mall)
    RelativeLayout mRlTabMall;
    //直播
    @InjectView(R.id.iv_tab_live)
    ImageView mIvTabLive;
    @InjectView(R.id.tv_tab_live)
    TextView mTvTabLive;
    @InjectView(R.id.rl_tab_live)
    RelativeLayout mRlTabLive;
    //我的
    @InjectView(R.id.iv_tab_mine)
    ImageView mIvTabMine;
    @InjectView(R.id.tv_tab_mine)
    TextView mTvTabMine;
    @InjectView(R.id.point)
    ImageView mPoint;
    @InjectView(R.id.rl_tab_mine)
    RelativeLayout mRlTabMine;

    @InjectView(R.id.ll_main_page_tab)
    LinearLayout mLlMainPageTab;
    @InjectView(R.id.fl_main_page_tab)
    FrameLayout mFlMainPageTab;
    @InjectView(R.id.content_main)
    RelativeLayout mContentMain;


    List<BaseFragment> mFragments = new ArrayList<>();
    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_CODE_SETTINGS = 2;

    private static final int VIEW_HOME = 0;
    private static final int VIEW_TICKETS = 1;
    private static final int VIEW_MALL = 2;
    private static final int VIEW_LIVE = 3;
    private static final int VIEW_MINE = 4;
    private static final int VIEW_SIZE = 5;

    MainLoadInfoPresenter mMainLoadInfoPresenter;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getHeaderLayoutId() {
        return -1;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        mMainLoadInfoPresenter = new MainLoadInfoPresenter(this, this);
        mFragments.add(new HomeFragment());
        mFragments.add(new TestFragment("购票"));
        mFragments.add(new TestFragment("商城"));
        mFragments.add(new TestFragment("直播"));
        mFragments.add(new TestFragment("我的"));

    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        mViewpager.setNoFocus(true);
        mViewpager.setOffscreenPageLimit(VIEW_SIZE);
        mViewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mFragments));
    }

    @Override
    protected void initListener(Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //0是静止，1是正在滑动，2是停止滑动
                if (state == 2) {
                    //设置滑动ViewPager导航同步变化
                    setTab(mViewpager.getCurrentItem());
                }
            }
        });

    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        requestUserPermissions();
        mViewpager.setCurrentItem(VIEW_HOME);
        setTab(VIEW_HOME);
        mMainLoadInfoPresenter.getLoadInfo();
    }


    @OnClick({R.id.rl_tab_home, R.id.rl_tab_payticket, R.id.rl_tab_mall, R.id.rl_tab_live, R.id.rl_tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_tab_home:
                mViewpager.setCurrentItem(VIEW_HOME);
                break;
            case R.id.rl_tab_payticket:
                mViewpager.setCurrentItem(VIEW_TICKETS);
                break;
            case R.id.rl_tab_mall:
                mViewpager.setCurrentItem(VIEW_MALL);
                break;
            case R.id.rl_tab_live:
                mViewpager.setCurrentItem(VIEW_LIVE);
                break;
            case R.id.rl_tab_mine:
                mViewpager.setCurrentItem(VIEW_MINE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SETTINGS:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length == 0) {
            return;
        }
        switch (requestCode) {
            case REQUEST_PERMISSION:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permission)) {

                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            startLocation();
                        } else {
                            //拒绝:检查是否是永久禁止
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                                //显示提示框
                                showRequstLocationDialog();
                            }
                        }
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                                ToastUtil.makeToast(mContext, "为了不影响您的使用请在设置中开启文件读写权限");
                            }
                        }
                    }
                }
                break;

            default:
                break;
        }

    }

    private void showRequstLocationDialog() {
        boolean isPrompted = PreferencesUtils.getBoolean(this, GlobalConstants.BD_LOACTION_KEY, false);

        if (!isPrompted) {
            AlertDialogUtil locationDialog = new AlertDialogUtil(this)
                    .setTitle("定位服务未开启")
                    .setMessage("请在系统设置中允许使用定位服务")
                    .setNegativeButton("暂不", null)
                    .setPositiveButton("去设置", new AlertDialogUtil.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog) {
                            try {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, REQUEST_CODE_SETTINGS);
                            } catch (Exception e) {

                            }
                        }
                    });
            locationDialog.show();
            PreferencesUtils.putBoolean(this, GlobalConstants.BD_LOACTION_KEY, true);
        }
    }

    private void setTab(int position) {

        mIvTabHome.setSelected(false);
        mTvTabHome.setSelected(false);
        mIvTabPayticket.setSelected(false);
        mTvTabPayticket.setSelected(false);
        mIvTabMall.setSelected(false);
        mTvTabMall.setSelected(false);
        mIvTabLive.setSelected(false);
        mTvTabLive.setSelected(false);
        mIvTabMine.setSelected(false);
        mTvTabMine.setSelected(false);
        switch (position) {
            case VIEW_HOME:
                mIvTabHome.setSelected(true);
                mTvTabHome.setSelected(true);
                break;
            case VIEW_TICKETS:
                mIvTabPayticket.setSelected(true);
                mTvTabPayticket.setSelected(true);
                break;
            case VIEW_MALL:
                mIvTabMall.setSelected(true);
                mTvTabMall.setSelected(true);
                break;
            case VIEW_LIVE:
                mIvTabLive.setSelected(true);
                mTvTabLive.setSelected(true);
                break;
            case VIEW_MINE:
                mIvTabMine.setSelected(true);
                mTvTabMine.setSelected(true);
                break;
        }
    }

    /***
     * 请求权限
     */
    private void requestUserPermissions() {
        ArrayList<String> mPerms = new ArrayList<>();

        //定位权限
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            startLocation();
        } else {
            mPerms.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        //读写SD卡权限
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            mPerms.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!mPerms.isEmpty()) {
            ActivityCompat.requestPermissions(this, mPerms.toArray(new String[mPerms.size()]), REQUEST_PERMISSION);
        }
    }

    private void startLocation() {
        LocationBlock mlLocationBlock = new LocationBlock(this);
        mlLocationBlock.setOnLocationListener(new LocationBlock.OnLocationListener() {
            @Override
            public void onLocationedSuccess(BDLocation bdLocation) {
                //定位成功后：根据经纬度请求接口，获取city属性

                refreshCity(bdLocation.getLatitude() + "", bdLocation.getLongitude() + "");
            }

            @Override
            public void onLocationedFail() {
                LoactionUtils.setLoactionInfo(LoactionUtils.getDefalutLocationInfo());
            }
        });
        mlLocationBlock.statrLocation();
    }

    private void refreshCity(String latitude, String longitude) {

        MainCityPresenter mainCityPresenter = new MainCityPresenter(this, new ISuccessOrFailureView<MTimeCityInfo>() {
            @Override
            public void onSuccess(final MTimeCityInfo data) {
                //当返回成功后，我们去判断是否用户选择的城市和定位的城市一致，
                //一致：我们刷新数据，不一致：我们提示是否要切换城市。
                LoactionUtils.setMTimeCityInfo(data);
                MTimeCityInfo userChooseCity = LoactionUtils.getUserChooseCity();
                if (!data.cityId.equals(userChooseCity.id)) {
                    //如果城市id不一，提示切换城市,切换则刷新数据
                    AlertDialogUtil alertDialogUtil = new AlertDialogUtil(MainActivity.this);
                    alertDialogUtil
                            .setTitle("位置提示")
                            .setMessage(String.format("系统定位到您在%1$s,是否切换至城市%1$s", data.name, data.name))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new AlertDialogUtil.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog) {
                                    LoactionUtils.setUserChooseCity(data);
                                    int currentItem = mViewpager.getCurrentItem();
                                    mFragments.get(currentItem).refreshCity();
                                }
                            }).show();
                }

            }

            @Override
            public void onFailure(String message) {

            }
        });
        mainCityPresenter.getCityInfo(latitude, longitude, ConstantUrl.MTIME_CITY_INFO);

    }

    @Override
    public void onSuccess(Loadbean data) {
        List<MovieAdvListBean> movieAdvList = data.getData().getMovieAdvList();
        if (movieAdvList != null && movieAdvList.size() > 0) {
            SharePreferenceUtil instance = SharePreferenceUtil.getInstance(mContext);
            instance.setValue(TimeKey.MOVIE_ADVLIST, JsonUtil.toJsonString(movieAdvList));
        }

    }

    @Override
    public void onFailure(String message) {

    }

    /**
     * 有网络
     */
    @Override
    protected void onNetworkAvailable(boolean iswify) {
        super.onNetworkAvailable(iswify);
        if (iswify) {
//            RerequestData();
        }
    }

    /**
     * 无网络
     */
    @Override
    protected void onNetworkInvalid() {
        super.onNetworkInvalid();
        //无网络应该先从缓存取数据，若没有怎显示无数据
//        showNoDataNoti(getContentView(),R.layout.default_page_no_content);
        ToastUtil.makeToast(this, "网络已断开~~");
    }
}
