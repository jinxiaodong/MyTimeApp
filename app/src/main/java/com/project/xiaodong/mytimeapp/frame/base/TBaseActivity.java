package com.project.xiaodong.mytimeapp.frame.base;

import android.os.Bundle;

import com.project.xiaodong.mytimeapp.R;

/**
 * Created by xiaodong.jin on 2017/9/21.
 */

public class TBaseActivity extends BaseActivity{
    @Override
    public int getHeaderLayoutId() {
        return R.layout.common_activity_header;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.common_activity_content;
    }

    @Override
    protected void initValue(Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
    }

}
