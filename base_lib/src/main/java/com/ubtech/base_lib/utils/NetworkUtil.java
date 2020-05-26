package com.ubtech.base_lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.ubtrobot.log.ALog;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Title NetWorkUtil
 * @Description 是检测网络的一个工具包
 */
public class NetworkUtil {

    public static final String WIFI_PREFIX = "JIMUPRO";

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mConnectivityManager == null) {
                return false;
            }
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            return mNetworkInfo != null && mNetworkInfo.isConnected();
        }
        return false;
    }

    /**
     * -1：TYPE_NONE  0：TYPE_MOBILE  1：TYPE_WIFI
     *
     * @param context
     * @return
     */
    public static NetworkType getAPNType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return NetworkType.NONE_NET;
        }
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetworkType.NONE_NET;
        }
        int nType = networkInfo.getType();
        if (nType == -1) {
            return NetworkType.NONE_NET;
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            return NetworkType.MOBILE;
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetworkType.WIFI;
        } else {
            return NetworkType.OTHER;
        }
    }

    public enum NetworkType {
        WIFI, MOBILE, OTHER, NONE_NET
    }

    /**
     * 连接wifi
     * @param targetSsid wifi的SSID
     * @param targetPsd 密码
     * @param enc 加密类型
     */
    public static void connectWifi(Context context, String targetSsid, String targetPsd, String enc) {
        // 1、注意热点和密码均包含引号，此处需要需要转义引号
        String ssid = "\"" + targetSsid + "\"";
        String psd = "\"" + targetPsd + "\"";

        //2、配置wifi信息
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = ssid;
        switch (enc) {
            case "WEP":
                // 加密类型为WEP
                conf.wepKeys[0] = psd;
                conf.wepTxKeyIndex = 0;
                conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                break;
            case "WPA":
                // 加密类型为WPA
                conf.preSharedKey = psd;
                break;
            case "OPEN":
                //开放网络
                conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        }
        //3、链接wifi
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.addNetwork(conf);
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration i : list) {
            if (i.SSID != null && i.SSID.equals(ssid)) {
                wifiManager.disconnect();
                boolean isSuccess = wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();
                ALog.d("isSuccess:" + isSuccess);
                break;
            }
        }
    }

    public static void disconnectWifi(Context context){
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                wifiManager.disableNetwork(wifiInfo.getNetworkId());
            }
//            wifiManager.disconnect();
        }
    }

    /**
     * 获取机器人Wifi列表
     * @param context
     * @return
     */
    public static List<ScanResult> getFilterScanResults(Context context){
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> results = wifiManager.getScanResults();
        return filterScanResults(results);
    }

    /**
     * 获取当前连接WIFI的SSID
     */
    public static String getSSID(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                String ssid = wifiInfo.getSSID();
                if (ssid.length() > 2 && ssid.charAt(0) == '"' && ssid.charAt(ssid.length() - 1) == '"') {
                    return ssid.substring(1, ssid.length() - 1);
                }
            }
        }
        return "";
    }

    /**
     * 判断是否连接机器人Wifi
     * @param context
     * @return
     */
    public static boolean isRobotWifiConnected(Context context, String ssid){
        if (TextUtils.isEmpty(ssid)) {
            ssid = getSSID(context);
        }
        return ssid.startsWith(WIFI_PREFIX)
                || ssid.startsWith(WIFI_PREFIX.toLowerCase());
    }


    /**
     * 过滤以JIMU开头的Wifi，即机器人Wifi
     * @param source
     * @return
     */
    public static List<ScanResult> filterScanResults(List<ScanResult> source){
        return filterScanResults(source, WIFI_PREFIX);
    }

    /**
     * 过滤Wifi
     * @param source
     * @param filterStr
     * @return
     */
    public static List<ScanResult> filterScanResults(List<ScanResult> source, String filterStr) {
        if (source == null || source.size() == 0 || TextUtils.isEmpty(filterStr)) {
            return source;
        }
        List<ScanResult> target = new ArrayList<>();
        for (ScanResult scanResult : source) {
            if (!TextUtils.isEmpty(scanResult.SSID)
                    && (scanResult.SSID.startsWith(filterStr)
                    || scanResult.SSID.startsWith(filterStr.toLowerCase()))) {
                target.add(scanResult);
            }
        }
        return target;
    }
}
