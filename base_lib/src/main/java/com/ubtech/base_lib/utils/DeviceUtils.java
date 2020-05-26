package com.ubtech.base_lib.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import java.util.UUID;

import static android.text.TextUtils.isEmpty;

/**
 * @Description :
 * @Created Time : yinrongwu on 2019/9/4.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public class DeviceUtils {

    public static String getDeviceId(Context context) {
        String deviceId = "";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                    || tm == null) {
                //如果没有权限||TelephonyManager 为null
                deviceId = getAndroidId(context);
                if (!TextUtils.isEmpty(deviceId)) {
                    return Md5Utils.encode(deviceId);
                }

                deviceId = getUUID();
                if (!TextUtils.isEmpty(deviceId)) {
                    return Md5Utils.encode(deviceId);
                }
                return "";
            }
            @SuppressLint("HardwareIds")
            String imei = tm.getDeviceId();
            if (!isEmpty(imei)) {

                return imei;
            }
            //序列号（sn）
            @SuppressLint("HardwareIds")
            String sn = tm.getSimSerialNumber();
            if (!isEmpty(sn)) {
                return sn;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return deviceId.toString();
    }


    private static String getUUID() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }


    private static String getAndroidId(Context context) {
        @SuppressLint("HardwareIds")
        String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }


}
