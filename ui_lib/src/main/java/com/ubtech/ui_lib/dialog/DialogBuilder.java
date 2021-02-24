package com.ubtech.ui_lib.dialog;

import android.content.Context;

import com.ubtech.base_lib.imp.IActionCallBack2;
import com.ubtech.ui_lib.R;

/**
 * Created by lei on 2020/7/6
 * desc:
 */
public class DialogBuilder {
    int gravity;
    int contentGravity;
    int leftBtnColor;
    String leftBtnDesc;
    int rightBtnColor;
    String rightBtnDesc;
    String content;
    String title;
    boolean autoDismiss;
    boolean showLeftBtn = true;
    boolean showRightBtn = true;
    IActionCallBack2 iActionCallBack2;

    public DialogBuilder gravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public DialogBuilder contentGravity(int gravity) {
        this.contentGravity = gravity;
        return this;
    }

    public DialogBuilder leftBtnColor(int resId) {
        this.leftBtnColor = resId;
        return this;
    }

    public DialogBuilder leftBtnDesc(String desc) {
        this.leftBtnDesc = desc;
        return this;
    }

    public DialogBuilder rightBtnColor(int resId) {
        this.rightBtnColor = resId;
        return this;
    }

    public DialogBuilder rightBtnDesc(String desc) {
        this.rightBtnDesc = desc;
        return this;
    }

    public DialogBuilder content(String content) {
        this.content = content;
        return this;
    }

    public DialogBuilder title(String title) {
        this.title = title;
        return this;
    }

    public DialogBuilder showLeftBtn(boolean show) {
        this.showLeftBtn = show;
        return this;
    }

    public DialogBuilder showRightBtn(boolean show) {
        this.showRightBtn = show;
        return this;
    }
    public DialogBuilder aotoDismiss(boolean autoDismiss){
        this.autoDismiss = autoDismiss;
        return this;
    }
    public DialogBuilder actionCallBack02(IActionCallBack2 iActionCallBack2) {
        this.iActionCallBack2 = iActionCallBack2;
        return this;
    }

    public JBaseDialog build(Context context) {
        return new JBaseDialog(context, R.style.dialog_theme,this);
    }

}
