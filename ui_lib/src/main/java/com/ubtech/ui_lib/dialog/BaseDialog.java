package com.ubtech.ui_lib.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by lei on 2020/5/22
 * desc: Dialog 基类
 */
public abstract class BaseDialog extends Dialog {

    protected Context mContext;
    public BaseDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (mContext instanceof Activity) {
//            ImmersionBar.with((Activity) mContext, this).init();
//        }
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void show() {
        setDialogWidth();
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mContext instanceof Activity) {
//            ImmersionBar.with((Activity) mContext, this).destroy();
            updateSystemUiVisibility();
        }
    }
    protected void updateSystemUiVisibility() {
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            activity.runOnUiThread(() -> {
                View decorView = activity.getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            });
        }
    }

    protected abstract void setDialogWidth();
}
