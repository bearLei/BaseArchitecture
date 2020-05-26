package com.ubtech.jimuplus.base;

import android.app.Activity;

import com.ubtech.base_lib.base.BaseApplication;
import com.ubtech.base_lib.utils.DensityUtils;
import com.ubtech.base_lib.utils.ScreenUtil;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;

/**
 * Created by lei on 2020/5/25
 * desc:
 */
public class JimuPlusApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }


    private void init(){
        initAutoSizeConfig();
    }

    private void initAutoSizeConfig(){
        AutoSizeConfig.getInstance().setOnAdaptListener(new onAdaptListener() {
            @Override
            public void onAdaptBefore(Object target, Activity activity) {
                AutoSizeConfig.getInstance().setScreenWidth(DensityUtils.screenWidth(getApplicationContext()));
                AutoSizeConfig.getInstance().setScreenHeight(DensityUtils.screenHeight(getApplicationContext()));
            }

            @Override
            public void onAdaptAfter(Object target, Activity activity) {

            }
        });
    }

}
