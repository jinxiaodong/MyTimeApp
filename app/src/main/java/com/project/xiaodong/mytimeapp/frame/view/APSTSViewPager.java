package com.project.xiaodong.mytimeapp.frame.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xiaodong.jin on 2017/9/25.
 */
public class APSTSViewPager extends ViewPager {
    private boolean mNoFocus = false; //if true, keep View don't move
    public APSTSViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public APSTSViewPager(Context context){
        this(context,null);
    }

/*    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mNoFocus) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mNoFocus) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (mNoFocus)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    public void setNoFocus(boolean b){mNoFocus = b;}
}