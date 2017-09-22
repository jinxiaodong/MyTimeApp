package com.project.xiaodong.mytimeapp.frame.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;
import com.project.xiaodong.mytimeapp.frame.constants.DeviceInfo;

public class DialogUtil {

    private static ImageView sImg;

    public static Dialog createLodingDialog(Context context, String message) {

        View loadingview = LayoutInflater.from(context).inflate(R.layout.loaddialog, null);

        sImg = (ImageView) loadingview.findViewById(R.id.iv_loading);

        TextView tvLoading = (TextView) loadingview.findViewById(R.id.tv_loading_msg);
        if (!TextUtils.isEmpty(message)) {
            tvLoading.setText(message);
        }

        LinearLayout linearLayout = (LinearLayout) loadingview.findViewById(R.id.dialog_view);//加载布局
        Dialog loadingDialog = new Dialog(context, R.style.dialog_transparent_style);//创建自定义样式
        loadingDialog.setCanceledOnTouchOutside(false); //空白区域不可点击

        loadingDialog.setContentView(linearLayout, new LinearLayout.LayoutParams(
                DeviceInfo.WIDTHPIXELS / 3 * 2,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局

        return loadingDialog;


    }

    public static AnimationDrawable getAnimal() {
        if (sImg == null) {
            return null;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) sImg.getBackground();
        return animationDrawable;
    }
}