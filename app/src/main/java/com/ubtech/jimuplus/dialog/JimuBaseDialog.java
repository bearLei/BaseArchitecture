package com.ubtech.jimuplus.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import com.ubtech.base_lib.view.dialog.BaseDialog;
import com.ubtech.jimuplus.R;

/**
 * Created by lei on 2020/5/22
 * desc: 通用的弹窗
 */
public class JimuBaseDialog extends BaseDialog {

    private View mRoot;
    private TextView mTitle, mContent, mTopBtn, mBottomBtn;

    private int gravity = Gravity.CENTER;
    private int contentGravity = Gravity.CENTER_HORIZONTAL;
    private int topBtnColor;
    private String topBtnDesc;
    private int bottomBtnColor;
    private String bottomBtnDesc;
    private String content;
    private String title;
    private boolean showTopBtn = true;
    private boolean showBottomBtn = true;
    private IActionCallBack2 iActionCallBack2;

    public JimuBaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public JimuBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected JimuBaseDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        mRoot = LayoutInflater.from(mContext).inflate(R.layout.jimu_dialog_base, null);
        mTitle = mRoot.findViewById(R.id._dialog_title);
        mContent = mRoot.findViewById(R.id._dialog_content);
        mTopBtn = mRoot.findViewById(R.id._dialog_top_btn);
        mBottomBtn = mRoot.findViewById(R.id._dialog_bottom_btn);

        mTopBtn.setOnClickListener(v -> {
            dismiss();
            if (null != iActionCallBack2) {
                iActionCallBack2.onAction1();
            }
        });
        mBottomBtn.setOnClickListener(v -> {
            dismiss();
            if (null != iActionCallBack2) {
                iActionCallBack2.onAction2();
            }
        });
        dynamicUi();
        this.setContentView(mRoot);
    }

    private void dynamicUi(){
        Window window = getWindow();
        if (null != window){
            window.setBackgroundDrawableResource(R.drawable.shape_dialog_normal_bg);
            window.setGravity(gravity);
        }
        if (0 != topBtnColor){
            mTopBtn.setBackground(mContext.getDrawable(topBtnColor));
        }
        if (0 != bottomBtnColor){
            mBottomBtn.setBackground(mContext.getDrawable(bottomBtnColor));
        }
        if (null != topBtnDesc){
            mTopBtn.setText(topBtnDesc);
        }
        if (null != bottomBtnDesc){
            mBottomBtn.setText(bottomBtnDesc);
        }

        if (null != content && !TextUtils.isEmpty(content)){
            mContent.setVisibility(View.VISIBLE);
            mContent.setText(content);
        }else {
            mContent.setVisibility(View.GONE);
            mContent.setText("");
        }
        if (null != title && !TextUtils.isEmpty(title)){
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(title);
        }else {
            mTitle.setVisibility(View.GONE);
            mTitle.setText("");
        }
        mTopBtn.setVisibility(showTopBtn?View.VISIBLE:View.GONE);
        mBottomBtn.setVisibility(showBottomBtn?View.VISIBLE:View.GONE);
    }

    @Override
    protected void setDialogWidth() {
        if (mContext instanceof Activity) {
            setOwnerActivity((Activity) mContext);
            if (null != getWindow()) {
                // TODO: 2020/5/22 待UI确认尺寸
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.width = DensityUtils.screenWidth(mContext) * 95 / 100;
                attributes.y = (int) DensityUtils.dp2Px(mContext, 10);
                getWindow().setAttributes(attributes);
            }
        }
    }


    class DialogBuilder {
        int gravity;
        int contentGravity;
        int topBtnColor;
        String topBtnDesc;
        int bottomBtnColor;
        String bottomBtnDesc;
        String content;
        String title;
        boolean showTopBtn;
        boolean showBottomBtn;
        IActionCallBack2 iActionCallBack2;

        DialogBuilder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        DialogBuilder contentGravity(int gravity) {
            this.contentGravity = gravity;
            return this;
        }

        DialogBuilder topBtnColor(int resId) {
            this.topBtnColor = resId;
            return this;
        }

        DialogBuilder topBtnDesc(String desc) {
            this.topBtnDesc = desc;
            return this;
        }

        DialogBuilder bottomBtnColor(int resId) {
            this.bottomBtnColor = resId;
            return this;
        }

        DialogBuilder bottomBtnDesc(String desc) {
            this.bottomBtnDesc = desc;
            return this;
        }

        DialogBuilder content(String content) {
            this.content = content;
            return this;
        }

        DialogBuilder title(String title) {
            this.title = title;
            return this;
        }

        DialogBuilder showTopBtn(boolean show) {
            this.showTopBtn = show;
            return this;
        }

        DialogBuilder showBottomBtn(boolean show) {
            this.showBottomBtn = show;
            return this;
        }

        DialogBuilder actionCallBack02(IActionCallBack2 iActionCallBack2) {
            this.iActionCallBack2 = iActionCallBack2;
            return this;
        }


    }

}
