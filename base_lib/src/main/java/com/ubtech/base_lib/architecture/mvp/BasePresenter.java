package com.ubtech.base_lib.architecture.mvp;

import java.lang.ref.WeakReference;

/**
 *
 * @author Albert.wu
 * @date 2019/8/15.
 */

public abstract class BasePresenter <V>{

    private WeakReference<V> mViewRef;

    public BasePresenter(V v) {
        attachView(v);
    }

    private void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    private void detachView() {
        if(mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView() {
        if(mViewRef != null) {
            return mViewRef.get();
        }
        return null;
    }

    public void release() {
        detachView();
    }

}
