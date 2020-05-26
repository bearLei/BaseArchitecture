package com.ubtech.base_lib.architecture.mvp;

/**
 *
 * @author Albert.wu
 * @date 2019/8/15.
 */

public interface IRxView extends IBaseView {
    void showLoading();
    void hideLoading();
    void showError();
}
