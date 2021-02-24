package com.ubtech.jimuplus.http.client;

import com.ubtech.base_lib.utils.AppUtils;
import com.ubtech.base_lib.utils.DeviceUtils;

import java.io.IOException;
import java.util.Random;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求拦截器
 */

public class RequestInterceptor implements Interceptor {


    public RequestInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder();
        //添加公共头部信息
//        requestBuilder.addHeader(HttpApiConstant.X_UBT_APPID_KEY, HttpApiConstant.APP_ID);
//        requestBuilder.addHeader(HttpApiConstant.APP_TYPE_KEY, HttpApiConstant.APP_TYPE_VALUE);
//        requestBuilder.addHeader(HttpApiConstant.PRODUCT_KEY, HttpApiConstant.UBT_PRODUCT_ID);
//        requestBuilder.addHeader(HttpApiConstant.VERSION_KEY, getAppVersion());//获取版本号
//        requestBuilder.addHeader(HttpApiConstant.X_UBT_SIGN_KEY, sign());
//        requestBuilder.addHeader(HttpApiConstant.DEVICE_ID_KEY, DeviceUtils.getDeviceId(AppUtils.getContext()));
//        requestBuilder.addHeader(HttpApiConstant.LANGUAGE_KEY, getLanguage());
//        requestBuilder.addHeader(HttpApiConstant.SYSTEM_AREA, "zh-hans");
//        requestBuilder.header("Content-Type", "application/json");
//        requestBuilder.header("Accept", "application/json");
        requestBuilder.method(originalRequest.method(), originalRequest.body());
        Request build = requestBuilder.build();
        return chain.proceed(build);
    }

//    private String sign() {
//        long now = System.currentTimeMillis() / 1000;
//        String randStr = randomAlphanumeric(10);
//        String versionNum = "v2";
//        /** 规则：
//         *        1. 计算当前时间戳（单位：秒）拼接 AppKey 和 1-10 位大小写字母和数字随机字符
//         *           和终端设备号 X-UBT-DeviceId 生成字符串的 MD5 值，得到签名段.
//         *        2. 将签名段与先前的时间戳使用空格连接，同时使用空格连接 randStr 字符串，得到最终 X-UBT-Sign 内容.
//         */
//        String sign = NetWorkMd5Utils.encode(now + HttpApiConstant.APP_KEY + randStr +
//                DeviceUtils.getDeviceId(AppUtils.getContext()))
//                + HttpApiConstant.SIGN_PART_SEPARATOR + now
//                + HttpApiConstant.SIGN_PART_SEPARATOR + randStr
//                + HttpApiConstant.SIGN_PART_SEPARATOR + versionNum;
//        return sign;
//    }


}
