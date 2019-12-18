package com.project.xiaodong.mytimeapp.frame.view.stickygridheadersgridview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by xiaodong.jin on 2017/11/9.
 */

public class StickyGridView extends StickyGridHeadersGridView {
    public StickyGridView(Context context) {
        super(context);
    }

    public StickyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
