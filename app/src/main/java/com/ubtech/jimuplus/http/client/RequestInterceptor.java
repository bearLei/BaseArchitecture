package com.ubtech.jimuplus.http.client;

import android.content.Context;
import android.text.TextUtils;

import com.ubtech.base_lib.http.cache.AnnotationCollection;
import com.ubtech.jimuplus.http.HttpCommonParams;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description : 网络请求拦截器
 * @Created Time : yinrongwu on 2019/8/15.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public class RequestInterceptor implements Interceptor {

    private ConcurrentHashMap<String, AnnotationCollection> serviceMethodMap;
    private Context mContext;

    public RequestInterceptor(Context context, ConcurrentHashMap<String, AnnotationCollection> serviceMethodMap) {
        this.mContext=context;
        this.serviceMethodMap = serviceMethodMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder();
        HttpUrl url = originalRequest.url();
        AnnotationCollection annotations = null;
        if (serviceMethodMap != null && serviceMethodMap.size() > 0) {
            String urlStr = url.toString();
            String key = urlStr;
            String query = url.encodedQuery();
            if (!TextUtils.isEmpty(query)) {
                key = urlStr.replace(query, "").replace("?", "");
            }
            annotations = serviceMethodMap.get(key);
        }

        if (annotations == null || !annotations.isCleanAllHeaders()) {
            //添加公共头部信息
            requestBuilder.addHeader(HttpCommonParams.X_UBT_APPID_KEY, HttpCommonParams.X_UBT_APPID_VALUE);
            requestBuilder.addHeader(HttpCommonParams.APP_TYPE_KEY, HttpCommonParams.APP_TYPE_VALUE);
            requestBuilder.addHeader(HttpCommonParams.PRODUCT_KEY, HttpCommonParams.PRODUCT_VALUE);
            requestBuilder.addHeader(HttpCommonParams.APP_SOURCE_KEY, HttpCommonParams.APP_SOURCE_VALUE);
            requestBuilder.addHeader(HttpCommonParams.VERSION_KEY, HttpCommonParams.VERSION_VALUE);
            requestBuilder.addHeader(HttpCommonParams.X_UBT_SIGN_KEY, HttpCommonParams.sign(mContext));
            requestBuilder.addHeader(HttpCommonParams.DEVICE_ID_KEY, HttpCommonParams.getDeviceId(mContext));
            requestBuilder.addHeader(HttpCommonParams.LANGUAGE_KEY, HttpCommonParams.getLanguage());
//            requestBuilder.addHeader(HttpCommonParams.AUTHORIZATION_KEY, UserInfo.getUserToken());
        }

        if (annotations != null) {
            String cleanHeaderName = annotations.getCleanHeaderName();
            if (!TextUtils.isEmpty(cleanHeaderName)) {
                if (cleanHeaderName.contains(HttpCommonParams.X_UBT_APPID_KEY)) {
                    requestBuilder.removeHeader(HttpCommonParams.X_UBT_APPID_KEY);
                }
                if (cleanHeaderName.contains(HttpCommonParams.APP_TYPE_KEY)) {
                    requestBuilder.removeHeader(HttpCommonParams.APP_TYPE_KEY);
                }
                if (cleanHeaderName.contains(HttpCommonParams.PRODUCT_KEY)) {
                    requestBuilder.removeHeader(HttpCommonParams.PRODUCT_KEY);
                }
                if (cleanHeaderName.contains(HttpCommonParams.APP_SOURCE_KEY)) {
                    requestBuilder.removeHeader(HttpCommonParams.APP_SOURCE_KEY);
                }
                if (cleanHeaderName.contains(HttpCommonParams.VERSION_KEY)) {
                    requestBuilder.removeHeader(HttpCommonParams.VERSION_KEY);
                }
                if (cleanHeaderName.contains(HttpCommonParams.DEVICE_ID_KEY)) {
                    requestBuilder.removeHeader(HttpCommonParams.DEVICE_ID_KEY);
                }
                if (cleanHeaderName.contains(HttpCommonParams.X_UBT_APPID_KEY)) {
                    requestBuilder.removeHeader(HttpCommonParams.X_UBT_APPID_KEY);
                }
                if (cleanHeaderName.contains(HttpCommonParams.LANGUAGE_KEY)) {
                    requestBuilder.removeHeader(HttpCommonParams.LANGUAGE_KEY);
                }
            }
        }

        requestBuilder.header("Content-Type", "application/json");
        requestBuilder.header("Accept", "application/json");
        requestBuilder.method(originalRequest.method(), originalRequest.body());
        Request build = requestBuilder.build();
        return chain.proceed(build);
    }
}
