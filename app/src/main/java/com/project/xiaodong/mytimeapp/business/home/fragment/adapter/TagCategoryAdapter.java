package com.project.xiaodong.mytimeapp.business.home.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.business.home.bean.HomeSelectMovieBean;
import com.project.xiaodong.mytimeapp.frame.view.taglayout.FlowLayout;
import com.project.xiaodong.mytimeapp.frame.view.taglayout.TagAdapter;

import java.util.List;

/**
 * Created by xiaodong.jin on 2018/1/9.
 */

public class TagCategoryAdapter extends TagAdapter<HomeSelectMovieBean.CategoryBean> {

    public TagCategoryAdapter(Context context, List<HomeSelectMovieBean.CategoryBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(FlowLayout parent, int position, HomeSelectMovieBean.CategoryBean categoryBean) {
        TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.home_movie_category_tag,
                parent, false);
        tv.setText(categoryBean.name);

        return tv;
    }
}
