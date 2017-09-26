package com.project.xiaodong.mytimeapp.frame.tabindicator;

import android.support.v4.view.ViewPager;

/**
 * 简化和ViewPager绑定
 */

public class ViewPagerHelper {
    public static void bind(final TabIndicatorLayout tabIndicatorLayout, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabIndicatorLayout.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                tabIndicatorLayout.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                tabIndicatorLayout.onPageScrollStateChanged(state);
            }
        });
    }




}
