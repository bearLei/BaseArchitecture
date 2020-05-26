package com.ubtech.base_lib.sp;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;


public class BaseSharePreference<T> {


    private final SharedPreferences mSpf;

    public BaseSharePreference(Context context, String name) {
        mSpf = context.getApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }


    public void put(String key, String value) {
        mSpf.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        mSpf.edit().putInt(key, value).apply();
    }

    public void put(String key, long value) {
        mSpf.edit().putLong(key, value).apply();
    }

    public void put(String key, Float value) {
        mSpf.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        mSpf.edit().putBoolean(key, value).apply();
    }

    public void put(String key, Set<String> value) {
        mSpf.edit().putStringSet(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSpf.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mSpf.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mSpf.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mSpf.getLong(key, defValue);
    }

    public String getString(String key, String defValue) {
        return mSpf.getString(key, defValue);
    }

    public Set<String> getStringSet(String key, Set<String> defValue) {
        return mSpf.getStringSet(key, defValue);
    }
}
