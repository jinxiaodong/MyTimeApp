package com.project.xiaodong.mytimeapp.business.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.view.banner.holder.Holder;

/**
 */
public class NetworkImageHolderView implements Holder<String> {

    private SimpleDraweeView imageView;

    @Override
    public View createView(Context context) {
        Log.e("tag", context.toString());
        imageView = new SimpleDraweeView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(R.drawable.allwhite_background);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        imageView.setImageURI(data);
    }
}
