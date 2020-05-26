package com.ubtech.base_lib.sp;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Albert.wu on 2019/9/17.
 * SharePreference管理类，可管理多个SharePreference文件
 */

public class SharePreferenceManager{


    public static final String SP_FILE_NAME_USER = "file_user";
    public static final String SP_FILE_NAME_SETTING = "setting";
    public static final String SEPARATOR_WIFI_NAME_AND_PWD = "ID_PWD";//Wifi名字和密码之间的分隔符

    public static final String SP_KEY_SAVED_WIFI_SET = "sp_key_saved_wifi_set";
    public static final String SP_KEY_TEMP_XML_ID = "sp_key_temp_xml_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_MODEL_NAME = "model_name";
    public static final String KEY_SYNC_ROBOT_DOWNLOAD = "sync_robot_download";

    private HashMap<String, BaseSharePreference> mSpMap;

    private static final class Holder {
        private static SharePreferenceManager sInstance = new SharePreferenceManager();
    }

    private SharePreferenceManager() {
        mSpMap = new HashMap<>();
    }

    public static SharePreferenceManager getInstance() {
        return Holder.sInstance;
    }

    public <T> BaseSharePreference create(Context context, String name) {
        BaseSharePreference sp = new BaseSharePreference<T>(context, name);
        mSpMap.put(name, sp);
        return sp;
    }

    public BaseSharePreference getSharePreference(String name) {
        return mSpMap.get(name);
    }

    public BaseSharePreference getSharePreference(Context context, String name) {
        BaseSharePreference sp = mSpMap.get(name);
        if (sp == null) {
            sp = new BaseSharePreference(context, name);
            mSpMap.put(name, sp);
        }
        return sp;
    }
}
