package com.ubtech.base_lib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

/**
 * Created by yxtao on 2017/8/25.
 */

public class DensityUtils {
    /*public static void init(Context context) {
        context = context.getApplicationContext();
    }*/

    public static float dp2Px(Context context, int dp) {
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        return dp * metrics.density + 0.5f;
    }

    public static int screenWidth(Context context) {
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int screenHeight(Context context) {
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static int[] screenSize(Context context) {
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        int[] size = {metrics.widthPixels, metrics.heightPixels};
        return size;
    }

    public static int getActionBarSize(@NonNull Context context) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.actionBarSize, value, true);
        int actionBarSize = TypedValue.complexToDimensionPixelSize(
                value.data, context.getResources().getDisplayMetrics());
        return actionBarSize;
    }
    public static int getFullScreenWidth(Context context) {
        int widthPixels = 0;
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            widthPixels = dm.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return widthPixels;
    }


    public static int getFullScreenHeight(Context context) {
        int widthPixels = 0;
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            widthPixels = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return widthPixels;
    }
}
