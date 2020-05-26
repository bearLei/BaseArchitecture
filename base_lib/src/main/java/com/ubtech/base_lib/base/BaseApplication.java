package com.ubtech.base_lib.base;

import android.app.Application;
import android.os.Build;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ubtech.base_lib.utils.AppUtils;
import com.ubtrobot.log.ALog;
import com.ubtrobot.log.BuildConfig;

/**
 * Created by lei on 2020/5/22
 * desc:基类Application 应用启动基础配置（针对全部App的基础配置）
 */
public class BaseApplication extends Application {


    private static final String DEFAULT_TAG = "JimuPro";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        register();
    }

    private void init(){
        initARouter();
        initALog();
    }
    private void register(){
        AppUtils.registerContext(this);
    }


    private void initARouter(){
        if (BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
    }
    private void initALog(){
        ALog.init(this, DEFAULT_TAG);
    }
}
