package com.ubtech.base_lib.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.annotation.NonNull;

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

}
