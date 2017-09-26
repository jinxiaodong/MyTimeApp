package com.project.xiaodong.mytimeapp.frame.tabindicator.buildins;

import android.content.Context;

/**
 * 博客: http://hackware.lucode.net
 */
public final class UIUtil {

    public static int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}