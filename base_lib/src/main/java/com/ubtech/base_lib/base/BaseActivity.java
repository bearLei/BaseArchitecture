package com.ubtech.base_lib.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ubtrobot.log.ALog;

/**
 * Created by lei on 2020/5/22
 * desc:
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ALog.tag(TAG).d("onCreate");
        setContentView(bindLayout());
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ALog.tag(TAG).d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ALog.tag(TAG).d("onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ALog.tag(TAG).d("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ALog.tag(TAG).d("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ALog.tag(TAG).d("onDestroy");
    }

    /**
     * @return 页面布局
     */
    protected abstract int bindLayout();

    protected void initData() {
    }

    private void initView() {
    }
}
