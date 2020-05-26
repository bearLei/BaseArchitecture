package com.ubtech.base_lib.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by lei on 2020/5/22
 * desc:
 */
public class AppUtils {
    /**
     * 应用全局上下文
     */
    private static Application mAppContext;


    /**
     * 注册全局上下文对象
     * @param context
     */
    public static void registerContext(Application context){
        mAppContext = context;
    }
    public static Application getContext(){
        return mAppContext;
    }
}
