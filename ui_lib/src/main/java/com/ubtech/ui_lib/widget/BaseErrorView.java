package com.ubtech.ui_lib.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ubtech.base_lib.imp.IActionCallBack1;
import com.ubtech.ui_lib.R;


/**
 * Created by lei on 2020/8/14
 * desc:基础错误页面
 */
public class BaseErrorView extends RelativeLayout {
    private View mRootView;
    private TextView refreshView;
    private ImageView bachIcon;
    private TextView mTip;
    private IActionCallBack1 iRefreshCallBack1;
    private IActionCallBack1 iBackCallBack1;

    public BaseErrorView(Context context) {
        super(context);
        initView(context);
    }

    public BaseErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BaseErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.base_error_view, this);
        refreshView = mRootView.findViewById(R.id.btn_refresh);
        bachIcon = mRootView.findViewById(R.id.back);
        mTip = findViewById(R.id.tip);
        refreshView.setOnClickListener(v -> {
            if (null != iRefreshCallBack1) {
                iRefreshCallBack1.onAction1();
            }
        });
        bachIcon.setOnClickListener(v -> {
            if (null != iBackCallBack1) {
                iBackCallBack1.onAction1();
            } else if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        });
    }

    public void setTip(String s) {
        mTip.setText(s);
    }

    public void hideTopBackIcon() {
        bachIcon.setVisibility(GONE);
    }

    public void setRefreshTip(String str){
        refreshView.setText(str);
    }

    public void registerRefreshClickListener(IActionCallBack1 iActionCallBack1) {
        this.iRefreshCallBack1 = iActionCallBack1;
    }

    public void registerBackClickListener(IActionCallBack1 iActionCallBack1) {
        this.iBackCallBack1 = iActionCallBack1;
    }
}
