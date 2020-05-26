package com.ubtech.base_lib.architecture.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ubtech.base_lib.base.BaseActivity;


/**
 *
 * @author Albert.wu
 * @date 2019/8/15.
 */

public abstract class MvpBaseActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    abstract protected P createPresenter();

    protected P getPresenter() {
        return mPresenter;
    }




    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.release();
        }

        super.onDestroy();
    }
}
