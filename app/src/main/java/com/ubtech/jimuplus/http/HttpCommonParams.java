package com.ubtech.jimuplus.http;

import android.content.Context;

import com.ubtech.base_lib.utils.DeviceUtils;
import com.ubtech.base_lib.utils.Md5Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description : 网络请求公共参数配置
 * @Created Time : yinrongwu on 2019/8/15.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public class HttpCommonParams {
    public static final String CHARACTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String SIGN_PART_SEPARATOR = " "; // 就是一个空格，由文档提供
    private static final String APP_KEY = "a0a1dfa9a7d74d999f28b6b8ceaa0de3";

    public static String BASE_URL = "https://test79.ubtrobot.com";
    public static String SERVICE_DIR = "com.ubtech.http.service";

    public static String X_UBT_APPID_KEY = "X-UBT-AppId";
    public static String X_UBT_APPID_VALUE = "400010011";
    public static String APP_TYPE_KEY = "app_type";
    public static String APP_TYPE_VALUE = "2";
    public static String PRODUCT_KEY = "product";
    public static String PRODUCT_VALUE = "20001";
    public static String VERSION_KEY = "version";
    public static String VERSION_VALUE = "3.4.0";
    public static String APP_SOURCE_KEY = "app_source";
    public static String APP_SOURCE_VALUE = "Jimu";
    public static String X_UBT_SIGN_KEY = "X-UBT-Sign";
    public static String DEVICE_ID_KEY = "X-UBT-DeviceId";
    public static final String LANGUAGE_KEY = "language";
    public static final String AUTHORIZATION_KEY = "authorization";

    public static final int TIMEOUT = 10;//超时时间 S

    public static Map<String, String> getHeaderCommonParams() {
        HashMap<String, String> headerCommonParams = new HashMap<>();
        headerCommonParams.put(X_UBT_APPID_KEY, X_UBT_APPID_VALUE);
        headerCommonParams.put(APP_TYPE_KEY, APP_TYPE_VALUE);
        headerCommonParams.put(PRODUCT_KEY, PRODUCT_VALUE);
        headerCommonParams.put(VERSION_KEY, VERSION_VALUE);
        headerCommonParams.put(APP_SOURCE_KEY, APP_SOURCE_VALUE);
        return headerCommonParams;
    }


    /**
     * V3.2.0 版本开始，后台鉴权，需要更新sign
     *
     * @return 接口需要的sign
     * @Date 2019.02.25
     */
    public static String sign(Context context) {
        long now = System.currentTimeMillis() / 1000;
//        long now = getServerTimestamp() / 1000;
        String randStr = randomAlphanumeric(10);
        String versionNum = "v2";
        /** 规则：
         *        1. 计算当前时间戳（单位：秒）拼接 AppKey 和 1-10 位大小写字母和数字随机字符
         *           和终端设备号 X-UBT-DeviceId 生成字符串的 MD5 值，得到签名段.
         *        2. 将签名段与先前的时间戳使用空格连接，同时使用空格连接 randStr 字符串，得到最终 X-UBT-Sign 内容.
         */
        String sign = Md5Utils.encode(now + APP_KEY + randStr +
                DeviceUtils.getDeviceId(context))
                + SIGN_PART_SEPARATOR + now
                + SIGN_PART_SEPARATOR + randStr
                + SIGN_PART_SEPARATOR + versionNum;
        return sign;
    }

    private static String randomAlphanumeric(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(CHARACTER.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取设备ID
     *
     * @return
     */
    public static String getDeviceId(Context context) {
        return DeviceUtils.getDeviceId(context);
    }

    public static String getLanguage() {
        return "zh-hans";
    }


}
