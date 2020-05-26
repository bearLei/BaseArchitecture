package com.ubtech.base_lib.http.cache;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ubtech.base_lib.http.HttpApi;
import com.ubtech.base_lib.http.interceptor.CacheInterceptor;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Description :
 * @Created Time : yinrongwu on 2019/9/5.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public abstract class CacheSubject<T> implements Observer<T> {

    private Gson mGson;

    public CacheSubject() {
        mGson = new Gson();
    }

    @Override
    public void onSubscribe(final Disposable d) {
        CacheInterceptor cacheInterceptor = HttpApi.getHttpApi().getCacheInterceptor();
        cacheInterceptor.setCacheCallback(new CacheCallback() {
            @Override
            public void readCacheListener(String jsonData) {
                if (!TextUtils.isEmpty(jsonData)) {
                    if (mGson != null) {
                        Class<T> tClass = getTClass();
                        T t = mGson.fromJson(jsonData, tClass);
                        if (t != null) {
                            prepare(d, t);
                        }
                    }
                }
            }
        });
    }

    public abstract void prepare(Disposable d, T cache);

    @Override
    public void onComplete() {

    }

    public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
