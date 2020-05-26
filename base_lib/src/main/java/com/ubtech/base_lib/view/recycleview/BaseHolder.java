package com.ubtech.base_lib.view.recycleview;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by lei on 2020/5/22
 * desc   :
 */
public abstract class BaseHolder<T> {

    protected Context mContext;
    protected HolderParent mHolderParent;
    protected View mRootView;
    protected T mData;
    protected RecyclerView.ViewHolder mSysTemHolder;

    public BaseHolder(Context context) {
        mContext = context;
        mRootView = initView(mContext);
        if(mRootView == null){
            mRootView = new View(mContext);
        }
        JViewHolder.setHolderTag(mRootView, this);
    }

    public BaseHolder(Context context, HolderParent parent) {
        mContext = context;
        mHolderParent = parent;
        mRootView = initView(mContext);
        if(mRootView == null){
            mRootView = new View(mContext);
        }
        JViewHolder.setHolderTag(mRootView, this);
    }

    public View getRootView() {
        return mRootView;
    }

    public Context getContext() {
        return mContext;
    }

    public HolderParent getHolderParent() {
        return mHolderParent;
    }

    public void setHolderParent(HolderParent parent) {
        mHolderParent = parent;
    }

    public void setData(T data) {
        mData = data;
        updateUI(mContext, data);
    }

    public T getData() {
        return mData;
    }

    @NonNull
    protected abstract View initView(Context context);

    protected abstract void  updateUI(Context context, T data);

    public RecyclerView.ViewHolder getSysTemHolder() {
        return mSysTemHolder;
    }

    public void setSysTemHolder(RecyclerView.ViewHolder sysTemHolder) {
        mSysTemHolder = sysTemHolder;
    }

}
