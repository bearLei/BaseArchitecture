package com.ubtech.ui_lib.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ubtech.base_lib.utils.DensityUtils;
import com.ubtech.ui_lib.R;

/**
 * Created by lei on 2020/9/24
 * desc:
 */
public class SimpleDialog extends BaseDialog {

    private View mRoot;
    private RelativeLayout mContainer;
    private TextView mTitle, mContent;

    public SimpleDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public SimpleDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.dialog_theme);
        init();
    }

    protected SimpleDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        mRoot = LayoutInflater.from(mContext).inflate(R.layout.jimu_dialog_simple, null);
        mContainer = mRoot.findViewById(R.id.container);
        mTitle = mRoot.findViewById(R.id._dialog_title);
        mContent = mRoot.findViewById(R.id._dialog_content);
        Window window = getWindow();
        if (null != window) {
            window.setBackgroundDrawableResource(R.drawable.shape_dialog_normal_bg);
            window.setGravity(Gravity.CENTER);
        }
        mContainer.setOnClickListener(v -> {
            this.dismiss();
        });
        this.setContentView(mRoot);
    }

    public void setTitle(String str) {
        mTitle.setText(str);
    }

    public void setContent(String str) {
        mContent.setText(str);
    }


    @Override
    protected void setDialogWidth() {
        if (mContext instanceof Activity) {
            setOwnerActivity((Activity) mContext);
            if (null != getWindow()) {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.width = DensityUtils.getFullScreenWidth(mContext);
                attributes.height = DensityUtils.getFullScreenHeight(mContext);
                getWindow().setAttributes(attributes);
            }
        }
    }
}
