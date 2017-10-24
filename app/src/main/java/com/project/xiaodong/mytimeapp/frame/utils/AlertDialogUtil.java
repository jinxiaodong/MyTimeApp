package com.project.xiaodong.mytimeapp.frame.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.xiaodong.mytimeapp.R;


/**
 * 警告框
 */

public class AlertDialogUtil {

    private Activity activity;
    public Dialog dialog;
    private View view;
    private View splitLine, titleSplitLine;
    private TextView titleTV, messageTV;
    private Button negativeButton, positiveButton;
    private LinearLayout contentLayout;
    private boolean isHasNegativeButton, isPositiveButton;


    public AlertDialogUtil(Activity activity) {
        this.activity = activity;
        while (activity.getParent() != null) {
            activity = activity.getParent();
        }
        dialog = new Dialog(activity, R.style.alertDialog_style);
        view = View.inflate(activity, R.layout.layout_alertdialog_view, null);
        titleTV = (TextView) view.findViewById(R.id.title);
        messageTV = (TextView) view.findViewById(R.id.message);
        contentLayout = (LinearLayout) view.findViewById(R.id.content);
        //取消按钮
        negativeButton = (Button) view.findViewById(R.id.NO);
        //确定按钮
        positiveButton = (Button) view.findViewById(R.id.YES);
        //分割线
        splitLine = view.findViewById(R.id.view);
        //title分割线
        titleSplitLine = view.findViewById(R.id.title_split_line);
        // 隐藏标题
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
    }

    public AlertDialogUtil show() {
        //判断按钮显示的数量
        if (isHasNegativeButton && isPositiveButton)
            splitLine.setVisibility(View.VISIBLE);
        else{
            splitLine.setVisibility(View.GONE);
            if (isHasNegativeButton)
                negativeButton.setBackgroundResource(R.drawable.selector_alertdialog_btn_bg);
            if (isPositiveButton)
                positiveButton.setBackgroundResource(R.drawable.selector_alertdialog_btn_bg);
        }
        dialog.show();
        return this;
    }

    public AlertDialogUtil addView(View view) {
        if (view != null) {
            contentLayout.removeAllViews();
            contentLayout.addView(view);
        }
        return this;
    }

    public AlertDialogUtil addView(int redId) {
        View view = null;
        if (redId > 0) {
            view = View.inflate(activity, redId, null);
        }
        return addView(view);
    }

    /**
     * 按钮监听
     */

    public interface OnClickListener {
        void onClick(DialogInterface dialog);
    }

    public AlertDialogUtil setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            titleTV.setText(title);
            titleTV.setVisibility(View.VISIBLE);
            titleSplitLine.setVisibility(View.VISIBLE);
        }
        return this;
    }
    public AlertDialogUtil setTitle(int redId) {
        String title = "";
        if (redId > 0){
            title = activity.getString(redId);
        }
        return setTitle(title);
    }

    public AlertDialogUtil setMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            messageTV.setText(message);
            messageTV.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public AlertDialogUtil setMessage(int redId) {
        String message = "";
        if (redId > 0){
            message = activity.getString(redId);
        }
        return setMessage(message);
    }

    public AlertDialogUtil setNegativeButton(String text, final OnClickListener listener) {
        isHasNegativeButton = true;
        if (!TextUtils.isEmpty(text))
            negativeButton.setText(text);
        negativeButton.setVisibility(View.VISIBLE);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null)
                    listener.onClick(dialog);
            }
        });
        return this;
    }

    public AlertDialogUtil setNegativeButton(int redId, final OnClickListener listener) {
        if (redId > 0) {
            return setNegativeButton(activity.getString(redId), listener);
        } else {
            return setNegativeButton("", listener);
        }
    }

    public AlertDialogUtil setPositiveButton(String text, final OnClickListener listener) {
        isPositiveButton = true;
        if (!TextUtils.isEmpty(text))
            positiveButton.setText(text);
        positiveButton.setVisibility(View.VISIBLE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null)
                    listener.onClick(dialog);
            }
        });
        return this;
    }

    public AlertDialogUtil setPositiveButton(int redId, final OnClickListener listener) {
        if (redId > 0) {
            return setPositiveButton(activity.getString(redId), listener);
        } else {
            return setPositiveButton("", listener);
        }
    }


    public boolean isShowing() {
        if (dialog != null)
            return dialog.isShowing();
        else
            return false;
    }

    public void setCanceledOnTouchOutside(boolean cancel){
        dialog.setCanceledOnTouchOutside(cancel);
    }

    public void setCancelable(boolean flag){
        dialog.setCancelable(flag);
    }

    public TextView getMssageTextView(){
        return messageTV;
    }

}
