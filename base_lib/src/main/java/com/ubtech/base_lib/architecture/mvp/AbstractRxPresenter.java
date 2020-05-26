package com.ubtech.base_lib.architecture.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *
 * @author Albert.wu
 * @date 2019/8/15.
 */

public abstract class AbstractRxPresenter<V extends IRxView> extends BasePresenter<V> {

    private CompositeDisposable mCompositeDisposable;

    public AbstractRxPresenter (V v) {
        super(v);
        createCompositeDisposable();
    }

    private void createCompositeDisposable() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void addDisposable(Disposable disposable) {
        if(mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    private void clearDisposable() {
        if(mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    @Override
    public void release() {
        clearDisposable();
        super.release();
    }

}
