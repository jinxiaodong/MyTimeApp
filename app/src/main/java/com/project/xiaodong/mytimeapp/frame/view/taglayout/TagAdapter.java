package com.project.xiaodong.mytimeapp.frame.view.taglayout;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class TagAdapter<T>
{
    protected Context context;
    private List<T> mTagDatas;
    private OnDataChangedListener mOnDataChangedListener;
    private HashSet<Integer> mCheckedPosList = new HashSet<Integer>();

    public TagAdapter(Context context,List<T> datas)
    {
        this.context = context;
        mTagDatas = datas;
    }

    public TagAdapter(Context context,T[] datas)
    {
        this.context = context;
        mTagDatas = new ArrayList<T>(Arrays.asList(datas));
    }

    interface OnDataChangedListener
    {
        void onChanged();
    }

    void setOnDataChangedListener(OnDataChangedListener listener)
    {
        mOnDataChangedListener = listener;
    }

    public void setSelectedList(int... poses)
    {
        Set<Integer> set = new HashSet<>();
        for (int pos : poses)
        {
            set.add(pos);
        }
        setSelectedList(set);
    }

    public void setSelectedList(Set<Integer> set)
    {
        mCheckedPosList.clear();
        if (set != null)
            mCheckedPosList.addAll(set);
        notifyDataChanged();
    }

    HashSet<Integer> getPreCheckedList()
    {
        return mCheckedPosList;
    }


    public int getCount()
    {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    public void notifyDataChanged()
    {
        mOnDataChangedListener.onChanged();
    }

    public T getItem(int position)
    {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);

    public boolean setSelected(int position, T t)
    {
        return false;
    }

    public List<T> getDataList(){
        return mTagDatas;
    }

    public void setDataList(List<T> datalist){
        this.mTagDatas = datalist;
        notifyDataChanged();
    }
}