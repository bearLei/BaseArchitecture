package com.ubtech.base_lib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Created by Frank on 2019/9/12.
 */

public class ScreenUtil {

    private static float sDensity;
    private static float sScaledDensity;

    public static void setCustomDensity(Activity activity, final Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sDensity == 0){
            sDensity = appDisplayMetrics.density;
            sScaledDensity = appDisplayMetrics.scaledDensity;
            //监听系统字体切换，更改sScaledDensity
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0){
                        sScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }
                @Override
                public void onLowMemory() { }
            });
        }
        //横屏以640dp为基准，具体基准值需参考设计图
        final float targetDensity = appDisplayMetrics.widthPixels / 640;
        final float targetScaledDensity = targetDensity * (sScaledDensity / sDensity);
        final int targetDensityDpi = (int)(160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }



}
