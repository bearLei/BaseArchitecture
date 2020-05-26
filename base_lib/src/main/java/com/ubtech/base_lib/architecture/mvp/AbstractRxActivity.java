package com.ubtech.base_lib.architecture.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 *
 * @author Albert.wu
 * @date 2019/8/15.
 */

public abstract class AbstractRxActivity<P extends AbstractRxPresenter> extends MvpBaseActivity<P> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



}
