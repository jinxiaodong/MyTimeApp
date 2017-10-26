package com.project.xiaodong.mytimeapp.business.home.location;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.base.activity.TBaseActivity;
import com.project.xiaodong.mytimeapp.frame.bean.MTimeCityInfo;
import com.project.xiaodong.mytimeapp.frame.constants.ConstantUrl;
import com.project.xiaodong.mytimeapp.frame.presenter.home.MainCityPresenter;
import com.project.xiaodong.mytimeapp.frame.presenter.home.view.ISuccessOrFailureView;
import com.project.xiaodong.mytimeapp.frame.utils.LogUtil;
import com.project.xiaodong.mytimeapp.frame.view.NoScrollGridview;
import com.project.xiaodong.mytimeapp.frame.view.stickygridheaders.StickyGridHeadersGridView;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class MtimeLocationActivity extends TBaseActivity implements ISuccessOrFailureView<MTimeCityInfo> {


    MainCityPresenter mMainCityPresenter;
    @InjectView(R.id.current_city)
    TextView mCurrentCity;
    @InjectView(R.id.gv_hot_city)
    NoScrollGridview mGvHotCity;
    @InjectView(R.id.sgv_city)
    StickyGridHeadersGridView mSgvCity;
    @InjectView(R.id.activity_mtime_location)
    LinearLayout mActivityMtimeLocation;

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
        //头不吸顶
        mSgvCity.setStickyHeaderIsTranscluent(false);
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
            List<MTimeCityInfo> citys = data.p;
            LogUtil.e("" + citys.toString());
        }
    }

    @Override
    public void onFailure(String message) {

    }

}
