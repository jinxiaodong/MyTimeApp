package com.project.xiaodong.mytimeapp.frame.tabindicator;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.tabindicator.buildins.UIUtil;
import com.project.xiaodong.mytimeapp.frame.tabindicator.buildins.commonnavigator.CommonNavigator;
import com.project.xiaodong.mytimeapp.frame.tabindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.project.xiaodong.mytimeapp.frame.tabindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.project.xiaodong.mytimeapp.frame.tabindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.project.xiaodong.mytimeapp.frame.tabindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.project.xiaodong.mytimeapp.frame.tabindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/9/26.
 */

public class TabLayoutUtil {

    public static void initTabLayout(TabIndicatorLayout tabIndicatorLayout, final ViewPager mViewPager,
                                     final List<String> titles, final List<Integer> colors, final Context mContext) {


        tabIndicatorLayout.setBackgroundResource(R.color.main_white);
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.65f);//滚动中心点
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles == null ? 0 : titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);

                View inflate = LayoutInflater.from(context).inflate(R.layout.tab_home_indicator, null);
                ImageView tabImg = (ImageView) inflate.findViewById(R.id.tab_img);
                final TextView tabName = (TextView) inflate.findViewById(R.id.tab_name);
                tabImg.setImageResource(R.drawable.tab_user01);
                tabName.setText(titles.get(index));
                commonPagerTitleView.setContentView(inflate);

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        tabName.setTextColor(colors.get(index));//选中文本颜色
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        tabName.setTextColor(Color.BLACK); //未选中颜色
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
//                        titleImg.setScaleX(1.3f + (0.8f - 1.3f) * leavePercent);
//                        titleImg.setScaleY(1.3f + (0.8f - 1.3f) * leavePercent);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
//                        titleImg.setScaleX(0.8f + (1.3f - 0.8f) * enterPercent);
//                        titleImg.setScaleY(0.8f + (1.3f - 0.8f) * enterPercent);
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return commonPagerTitleView;

//                单文本
//                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
//                simplePagerTitleView.setText(titles.get(index));
//                simplePagerTitleView.setNormalColor(Color.parseColor("#9e9e9e"));
//                simplePagerTitleView.setSelectedColor(colors.get(index));
//                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mViewPager.setCurrentItem(index);
//                    }
//                });
//                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(UIUtil.dip2px(context, 2)); //指示线的宽度
//                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(colors);
                return indicator;
            }


        });

        tabIndicatorLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabIndicatorLayout, mViewPager);

    }
}
