package com.ubtech.ui_lib.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ubtech.ui_lib.R;

/**
 * Created by lei on 2020/6/30
 * desc: app基础头部View
 */
public class BaseHeadView extends RelativeLayout {
    private Context mContext;
    protected TextView mTitle, mRightTitle;
    protected ImageView mLeftIcon, mRightIcon;

    protected int mLeftImgRes;
    protected int mRightImgRes;
    protected int mTitleRes;
    protected int mRightTitleRes;
    protected boolean mTitleShow;
    protected boolean mRightImgShow;
    protected boolean mRightTitleShow;

    public BaseHeadView(Context context) {
        super(context);
    }

    public BaseHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public BaseHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    private void bindView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.jimu_base_headview, this);
        mTitle = view.findViewById(R.id.headview_title);
        mRightTitle = view.findViewById(R.id.headview_right_title);
        mLeftIcon = view.findViewById(R.id.headview_left_btn);
        mRightIcon = view.findViewById(R.id.headview_right_btn);
    }

    private void initViews(Context context, AttributeSet attrs) {
        this.mContext = context;
        bindView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseHeadView);
        mLeftImgRes = typedArray.getResourceId(R.styleable.BaseHeadView_LeftImg, -1);
        mRightImgRes = typedArray.getResourceId(R.styleable.BaseHeadView_RightImg, -1);
        mTitleRes = typedArray.getResourceId(R.styleable.BaseHeadView_Title, -1);
        mRightTitleRes = typedArray.getResourceId(R.styleable.BaseHeadView_RightTitle, -1);
        mTitleShow = typedArray.getBoolean(R.styleable.BaseHeadView_TitleGone, true);
        mRightImgShow = typedArray.getBoolean(R.styleable.BaseHeadView_RightImgGone, false);
        mRightTitleShow = typedArray.getBoolean(R.styleable.BaseHeadView_ShowRightTitle, false);
        typedArray.recycle();
        setView();
    }

    private void setView() {
        if (-1 != mLeftImgRes) {
            mLeftIcon.setBackgroundResource(mLeftImgRes);
        } else {
            mLeftIcon.setBackgroundResource(R.drawable.ic_return);
        }
        if (-1 != mRightImgRes) {
            mRightIcon.setBackgroundResource(mRightImgRes);
        }
        if (-1 != mTitleRes) {
            mTitle.setText(mTitleRes);
        }
        if (-1 != mRightTitleRes) {
            mRightTitle.setText(mRightTitleRes);
        }
        mTitle.setVisibility(mTitleShow ? VISIBLE : GONE);
        mRightIcon.setVisibility(mRightImgShow ? VISIBLE : GONE);
        mRightTitle.setVisibility(mRightTitleShow ? VISIBLE : GONE);
//        if (mContext instanceof Activity) {
//            LayoutParams layoutParams = (LayoutParams) mLeftIcon.getLayoutParams();
//            if (ImmersionBar.hasNotchScreen((Activity) mContext)){
//                layoutParams.leftMargin = ImmersionBar.getStatusBarHeight((Activity) mContext);
//            }else {
//                layoutParams.leftMargin = (int) mContext.getResources().getDimension(R.dimen.dp_9);
//            }
//            mLeftIcon.setLayoutParams(layoutParams);
//        }
    }

    public void setLeftClick(OnClickListener listener) {
        mLeftIcon.setOnClickListener(listener);
    }

    public void setRightClick(OnClickListener listener) {
        mRightIcon.setOnClickListener(listener);
    }

    public void showLeft(boolean show) {
        mLeftIcon.setVisibility(show ? VISIBLE : GONE);
    }

    public void enableLeft(boolean enable) {
        mLeftIcon.setEnabled(enable);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setTitleBold(boolean bold) {
        if (bold) {
            mTitle.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            mTitle.setTypeface(Typeface.DEFAULT);
        }
    }
}
