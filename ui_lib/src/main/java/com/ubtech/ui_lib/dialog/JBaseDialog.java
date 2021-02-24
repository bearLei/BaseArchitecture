package com.ubtech.ui_lib.dialog;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ubtech.base_lib.imp.IActionCallBack2;
import com.ubtech.base_lib.utils.DensityUtils;
import com.ubtech.ui_lib.R;

/**
 * Created by lei on 2020/5/22
 * desc: 通用的弹窗
 */
public class JBaseDialog extends BaseDialog {

    private View mRoot;
    private TextView mTitle, mContent, mLeftBtn, mRightBtn;

    private int gravity = Gravity.CENTER;
    private int contentGravity = Gravity.CENTER_HORIZONTAL;
    private int topBtnColor;
    private String topBtnDesc;
    private int bottomBtnColor;
    private String bottomBtnDesc;
    private String content;
    private String title;
    private boolean autoDismiss;
    private boolean showTopBtn = true;
    private boolean showBottomBtn = true;
    private IActionCallBack2 iActionCallBack2;
    private DialogBuilder builder;

    public JBaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public JBaseDialog(@NonNull Context context, int themeResId, DialogBuilder builder) {
        super(context,themeResId);
        this.builder = builder;
        init();
    }

    public JBaseDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.dialog_theme);
        init();
    }

    protected JBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        gravity = builder.gravity;
        contentGravity = builder.contentGravity;
        topBtnColor = builder.leftBtnColor;
        topBtnDesc = builder.leftBtnDesc;
        bottomBtnColor = builder.rightBtnColor;
        bottomBtnDesc = builder.rightBtnDesc;
        content = builder.content;
        title = builder.title;
        this.autoDismiss = builder.autoDismiss;
        showTopBtn = builder.showLeftBtn;
        showBottomBtn = builder.showRightBtn;
        iActionCallBack2 = builder.iActionCallBack2;
        mRoot = LayoutInflater.from(mContext).inflate(R.layout.jimu_dialog_base, null);
        mTitle = mRoot.findViewById(R.id._dialog_title);
        mContent = mRoot.findViewById(R.id._dialog_content);
        mLeftBtn = mRoot.findViewById(R.id._dialog_left_btn);
        mRightBtn = mRoot.findViewById(R.id._dialog_right_btn);

        mLeftBtn.setOnClickListener(v -> {
            if (autoDismiss) {
                dismiss();
            }
            if (null != iActionCallBack2) {
                updateSystemUiVisibility();
                iActionCallBack2.onAction1();
            }
        });
        mRightBtn.setOnClickListener(v -> {
            if (autoDismiss) {
                dismiss();
            }
            if (null != iActionCallBack2) {
                updateSystemUiVisibility();
                iActionCallBack2.onAction2();
            }
        });
        dynamicUi();
        this.setContentView(mRoot);
    }

    private void dynamicUi() {
        Window window = getWindow();
        if (null != window) {
            window.setBackgroundDrawableResource(R.drawable.shape_dialog_normal_bg);
            window.setGravity(gravity);
        }
        if (0 != topBtnColor) {
            mLeftBtn.setBackground(mContext.getResources().getDrawable(topBtnColor));
        }
        if (0 != bottomBtnColor) {
            mRightBtn.setBackground(mContext.getResources().getDrawable(bottomBtnColor));
        }
        if (null != topBtnDesc) {
            mLeftBtn.setText(topBtnDesc);
        }
        if (null != bottomBtnDesc) {
            mRightBtn.setText(bottomBtnDesc);
        }

        if (null != content && !TextUtils.isEmpty(content)) {
            mContent.setVisibility(View.VISIBLE);
            mContent.setText(content);
        } else {
            mContent.setVisibility(View.GONE);
            mContent.setText("");
        }
        if (null != title && !TextUtils.isEmpty(title)) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(title);
        } else {
            mTitle.setVisibility(View.GONE);
            mTitle.setText("");
        }
        mLeftBtn.setVisibility(showTopBtn ? View.VISIBLE : View.GONE);
        mRightBtn.setVisibility(showBottomBtn ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void setDialogWidth() {
        if (mContext instanceof Activity) {
            setOwnerActivity((Activity) mContext);
            if (null != getWindow()) {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.width = (int) DensityUtils.getFullScreenWidth(mContext);
                attributes.height = (int) DensityUtils.getFullScreenHeight(mContext);
                getWindow().setAttributes(attributes);
            }
        }
    }


}
