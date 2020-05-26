package com.ubtech.base_lib.http.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.jakewharton.disklrucache.DiskLruCache;
import com.ubtech.base_lib.utils.SystemUtils;
import com.ubtrobot.log.ALog;

import java.io.File;
import java.io.IOException;

public class ProDiskLruCache {
    private Context context;
    private final String DIR = "proCache";
    private DiskLruCache mDiskLruCache = null;
    private Gson gson;

    public ProDiskLruCache(Context context) {
        this.context = context;
        gson = new Gson();
        initDiskLruCache();
    }

    public void initDiskLruCache() {
        File diskCacheDir = getDiskCacheDir(context, DIR);
        Log.e("Test", diskCacheDir.getAbsolutePath());
        if (diskCacheDir.exists()) {
            boolean mkdirs = diskCacheDir.mkdirs();
        }
        int appVersion = getAppVersion(context);
        try {
            mDiskLruCache = DiskLruCache.open(diskCacheDir, appVersion, 1, 1024 * 1024 * 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDiskCache(EngineKey engineKey, String data) {
        if (mDiskLruCache == null || TextUtils.isEmpty(data)) return;
        DiskLruCache.Editor edit;
        try {
            edit = mDiskLruCache.edit(engineKey.hashCode() + "");
            edit.set(0, data);
            edit.commit();
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
            ALog.E("sd卡缓存保存失败");
        }
    }


    public CacheResource getDiskCache(String key, long cacheLife) {
        if (mDiskLruCache == null) return null;
        try {
            String cacheJsonData = "";
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {
                cacheJsonData = snapshot.getString(0);
                if (TextUtils.isEmpty(cacheJsonData)) return null;
                CacheResource cacheResource = gson.fromJson(cacheJsonData, CacheResource.class);
                if (cacheResource == null) return null;
                long createTime = cacheResource.getCreateTime();
                long currentTime = SystemUtils.getSystemTime();
                if ((currentTime - createTime) < cacheLife) {
                    cacheResource.setSurvial(true);
                    return cacheResource;
                } else {
                    //缓存过期则删除
//                    removeCache(key);
                    cacheResource.setSurvial(false);
                    return cacheResource;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File getDiskCacheDir(Context context, String dir) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                cachePath = externalCacheDir.getPath();
            } else {
                cachePath = context.getCacheDir().getPath();
            }
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + dir);
    }


    public void removeCache(String key) {
        if (mDiskLruCache == null) return;
        try {
            mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取App版本号
     *
     * @param context
     * @return
     */
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;

    }
}
