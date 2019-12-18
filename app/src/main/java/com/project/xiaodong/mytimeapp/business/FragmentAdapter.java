package com.project.xiaodong.mytimeapp.business;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.project.xiaodong.mytimeapp.frame.base.fragment.BaseFragment;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/9/26.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    List<BaseFragment> mFragmentList = null;


    public FragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentList != null && position < mFragmentList.size()) {
            return mFragmentList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
